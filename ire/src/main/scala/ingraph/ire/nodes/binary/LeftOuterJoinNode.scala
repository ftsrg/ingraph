package ingraph.ire.nodes.binary

import ingraph.ire.datatypes.Slot._
import ingraph.ire.datatypes._
import ingraph.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}

import scala.collection.immutable.VectorBuilder

class LeftOuterJoinNode(override val next: (ReteMessage) => Unit,
                        override val primaryTupleWidth: Int,
                        override val secondaryTupleWidth: Int,
                        override val primaryMask: Mask,
                        override val secondaryMask: Mask)
  extends JoinNodeBase with SingleForwarder {

  override def onSizeRequest(): Long = 0

  override def onPrimary(changeSet: ChangeSet): Unit = {

    val positive = new VectorBuilder[Tuple]()
//    val joinedPositive = joinTuples(changeSet.positive, secondaryIndexer, primaryMask, Primary)
    for (
      tuple <- changeSet.positive;
      primaryKey = extract(tuple, primaryMask)
      ) {
      secondaryIndexer.get(primaryKey) match {
        case None => positive += (tuple ++ nullFiller)
        case Some(buffer) => positive ++= buffer.map(combine(tuple, _, Primary))
      }

      primaryIndexer.addBinding(primaryKey, tuple)
    }

    forward(ChangeSet(
      positive = positive.result(),
      negative = changeSet.negative.flatMap(calculatePrimaryNegative)
    ))
  }

  private def calculatePrimaryPositive(inputTuple: Tuple): Iterable[Tuple] = {
    val joinedPositiveTuples: Iterable[Tuple] = joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
    val primaryKey = extract(inputTuple, primaryMask)

    primaryIndexer.addBinding(primaryKey, inputTuple)

    if(joinedPositiveTuples.isEmpty){
//      pairlessTuples.addBinding(primaryKey, inputTuple)
      return Seq(padWithNull(inputTuple))
    }

    joinedPositiveTuples
  }

  private def calculatePrimaryNegative(inputTuple: Tuple): Iterable[Tuple] = {
    val primaryKey = extract(inputTuple, primaryMask)

    primaryIndexer.get(primaryKey) match {
      case None => ;
      case Some(buffer) =>
        if (buffer.contains(inputTuple) && !secondaryIndexer.contains(primaryKey))
          return Iterable(padWithNull(inputTuple))
    }

    primaryIndexer.removeBinding(primaryKey, inputTuple)

    joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    val positiveUpdatesChangeSet = changeSet.positive.map(calculateSecondaryPositive).reduceOption(combineChangeSets).getOrElse(ChangeSet())
    val negativeUpdatesChangeSet = changeSet.negative.map(calculateSecondaryNegative).reduceOption(combineChangeSets).getOrElse(ChangeSet())
//
    forward(combineChangeSets(positiveUpdatesChangeSet, negativeUpdatesChangeSet))
  }

  private def calculateSecondaryPositive(inputTuple: Tuple): ChangeSet = {
    val joinedPositiveTuples = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)

    var negativeTuples: Vector[Tuple] = Vector()

    val secondaryKey = extract(inputTuple, secondaryMask)
    if (!secondaryIndexer.contains(secondaryKey))
    primaryIndexer.get(secondaryKey) match {
      case Some(buffer) => negativeTuples = buffer.map(padWithNull).toVector
      case None => ;
    }

    secondaryIndexer.addBinding(secondaryKey, inputTuple)

    ChangeSet(positive = joinedPositiveTuples.toVector, negative = negativeTuples)
  }

  private def calculateSecondaryNegative(inputTuple: Tuple): ChangeSet = {
    val joinedNegativeTuples = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)

    val joinAttributesTuple = extract(inputTuple, secondaryMask)
    val positiveTuples = if (
      joinedNegativeTuples.nonEmpty && secondaryIndexer(joinAttributesTuple).size <= 1){
      val pairsFromPrimary = primaryIndexer(joinAttributesTuple)
      pairsFromPrimary.map(padWithNull).toVector
    } else Vector.empty[Tuple]

    secondaryIndexer.removeBinding(joinAttributesTuple, inputTuple)

    ChangeSet(positive=positiveTuples, negative=joinedNegativeTuples.toVector)
  }

  private val nullFiller = Vector.fill(secondaryTupleWidth - secondaryMask.size)(null)
  private def padWithNull(inputTuple: Tuple): Tuple = {
    inputTuple ++ nullFiller
  }

  private def combineChangeSets(cs1: ChangeSet, cs2: ChangeSet): ChangeSet = {
    ChangeSet(positive = cs1.positive ++ cs2.positive, negative = cs1.negative ++ cs2.negative)
  }

}


class ThetaLeftOuterJoinNode(override val next: ReteMessage => Unit,
                        override val primaryTupleWidth: Int,
                        override val secondaryTupleWidth: Int,
                        override val primaryMask: Mask,
                        override val secondaryMask: Mask,
                        val theta: Tuple => Boolean)
  extends LeftOuterJoinNode(next, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask) {
  override def joinTuples(tuples: Vector[Tuple], otherIndexer: JoinCache,
                          slotMask: Mask, slot: Slot): Iterable[Tuple] = {
    super.joinTuples(tuples, otherIndexer, slotMask, slot).filter(theta(_))
  }
}
