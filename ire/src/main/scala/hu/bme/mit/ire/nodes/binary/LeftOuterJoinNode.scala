package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{Δ, ReteMessage}
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.TestUtil._

import scala.collection.mutable

class LeftOuterJoinNode(override val next: (ReteMessage) => Unit,
                        override val primaryTupleWidth: Int,
                        override val secondaryTupleWidth: Int,
                        override val primaryMask: Mask,
                        override val secondaryMask: Mask)
  extends JoinNodeBase with SingleForwarder {

  val pairlessTuples = new BufferMultimap[Tuple, Tuple]

  override def onSizeRequest(): Long = 0

  override def onPrimary(changeSet: Δ): Unit = {
    forward(Δ(
      positive = changeSet.positive.flatMap(calculatePrimaryPositive),
      negative = changeSet.negative.flatMap(calculatePrimaryNegative)
    ))
  }

  override def onSecondary(changeSet: Δ): Unit = {
    val positiveUpdatesChangeSet = changeSet.positive.map(calculateSecondaryPositive).reduceOption(combineChangeSets).getOrElse(Δ())
    val negativeUpdatesChangeSet = changeSet.negative.map(calculateSecondaryNegative).reduceOption(combineChangeSets).getOrElse(Δ())

    forward(combineChangeSets(positiveUpdatesChangeSet, negativeUpdatesChangeSet))
  }

  private def calculatePrimaryPositive(inputTuple: Tuple): Iterable[Tuple] = {
    val joinedPositiveTuples: Iterable[Tuple] = joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
    val joinAttributesTuple = extract(inputTuple, primaryMask)

    primaryIndexer.addBinding(joinAttributesTuple, inputTuple)

    if(joinedPositiveTuples.isEmpty){
      pairlessTuples.addBinding(joinAttributesTuple, inputTuple)
      return Vector(combineNullTuple(inputTuple))
    }

    joinedPositiveTuples
  }

  private def calculatePrimaryNegative(inputTuple: Tuple): Iterable[Tuple] = {
    val joinAttributesTuple = extract(inputTuple, primaryMask)

    primaryIndexer.removeBinding(joinAttributesTuple, inputTuple)

    if(pairlessTuples.entryExists(joinAttributesTuple, _ == inputTuple)){
      pairlessTuples.removeBinding(joinAttributesTuple, inputTuple)
      return Vector(combineNullTuple(inputTuple))
    }

    joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
  }

  private def calculateSecondaryPositive(inputTuple: Tuple): Δ = {
    val joinedPositiveTuples: Iterable[Tuple] = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)

    var negativeTuples: Vector[Tuple] = Vector()

    val joinAttributesTuple = extract(inputTuple, secondaryMask)
    if(pairlessTuples.contains(joinAttributesTuple)){
      negativeTuples = pairlessTuples.remove(joinAttributesTuple).get.map(combineNullTuple).toVector
    }

    secondaryIndexer.addBinding(joinAttributesTuple, inputTuple)

    Δ(positive = joinedPositiveTuples, negative = negativeTuples)
  }

  private def calculateSecondaryNegative(inputTuple: Tuple): Δ = {
    val joinedNegativeTuples: Iterable[Tuple] = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)
    var positiveTuples: Vector[Tuple] = Vector()

    val joinAttributesTuple = extract(inputTuple, secondaryMask)
    if(joinedNegativeTuples.nonEmpty && secondaryIndexer.get(joinAttributesTuple).get.size <= 1){
      val pairsFromPrimary = primaryIndexer.get(joinAttributesTuple).get
      pairlessTuples.put(joinAttributesTuple, pairsFromPrimary)
      positiveTuples = pairsFromPrimary.map(combineNullTuple).toVector
    }

    secondaryIndexer.removeBinding(joinAttributesTuple, inputTuple)

    Δ(positive = positiveTuples, negative = joinedNegativeTuples)
  }

  private def combineNullTuple(inputTuple: Tuple): Tuple = {
    var nullPart = tuple()
    for(i <- 1 to secondaryTupleWidth - secondaryMask.size)
      nullPart = nullPart ++ tuple(null)

    inputTuple ++ nullPart
  }

  private def combineChangeSets(cs1: Δ, cs2: Δ): Δ = {
    Δ(positive = cs1.positive ++ cs2.positive, negative = cs1.negative ++ cs2.negative)
  }

}
