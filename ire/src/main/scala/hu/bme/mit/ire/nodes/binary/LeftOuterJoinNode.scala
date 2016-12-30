package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.TestUtil._

import scala.collection.mutable

class LeftOuterJoinNode(override val next: (ReteMessage) => Unit,
                        override val primaryTupleWidth: Int,
                        override val secondaryTupleWidth: Int,
                        override val primaryMask: Mask,
                        override val secondaryMask: Mask)
  extends JoinNodeBase with SingleForwarder {

  val pairlessTuples = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]

  override def onSizeRequest(): Long = 0

  override def onPrimary(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      positive = changeSet.positive.flatMap(calculatePrimaryPositive),
      negative = changeSet.negative.flatMap(calculatePrimaryNegative)
    ))
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
  }

  private def calculatePrimaryPositive(inputTuple: Tuple): Vector[Tuple] = {
    val joinedPositiveTuples: Vector[Tuple] = joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
    val joinAttributesTuple = extract(inputTuple, primaryMask)

    if(joinedPositiveTuples.isEmpty){
      pairlessTuples.addBinding(joinAttributesTuple, inputTuple)
      return Vector(combineNullTuple(inputTuple))
    }

    primaryIndexer.addBinding(joinAttributesTuple, inputTuple)

    joinedPositiveTuples
  }

  private def calculatePrimaryNegative(inputTuple: Tuple): Vector[Tuple] = {
    val joinAttributesTuple = extract(inputTuple, primaryMask)
    if(pairlessTuples.entryExists(joinAttributesTuple, _ == inputTuple)){
      pairlessTuples.removeBinding(joinAttributesTuple, inputTuple)
      return Vector(combineNullTuple(inputTuple))
    }

    primaryIndexer.removeBinding(joinAttributesTuple, inputTuple)

    joinTuples(Vector(inputTuple), secondaryIndexer, primaryMask, Primary)
  }

  private def calculateSecondaryPositive(inputTuple: Tuple): ChangeSet = {
    val joinedPositiveTuples: Vector[Tuple] = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)

    var negativeTuples: Vector[Tuple] = Vector()

    val joinAttributesTuple = extract(inputTuple, secondaryMask)
    if(pairlessTuples.contains(joinAttributesTuple)){
      negativeTuples = pairlessTuples.remove(joinAttributesTuple).get.map(combineNullTuple).toVector
    }

    secondaryIndexer.addBinding(joinAttributesTuple, inputTuple)

    ChangeSet(positive = joinedPositiveTuples, negative = negativeTuples)
  }

  private def calculateSecondaryNegative(inputTuple: Tuple): ChangeSet = {
    val joinedNegativeTuples: Vector[Tuple] = joinTuples(Vector(inputTuple), primaryIndexer, secondaryMask, Secondary)
    var positiveTuples: Vector[Tuple] = Vector()

    val joinAttributesTuple = extract(inputTuple, secondaryMask)
    if(joinedNegativeTuples.nonEmpty && secondaryIndexer.get(joinAttributesTuple).get.size <= 1){
      val pairsFromPrimary = primaryIndexer.get(joinAttributesTuple).get
      pairlessTuples.put(joinAttributesTuple, pairsFromPrimary)
      positiveTuples = pairsFromPrimary.map(combineNullTuple).toVector
    }

    secondaryIndexer.removeBinding(joinAttributesTuple, inputTuple)

    ChangeSet(positive = positiveTuples, negative = joinedNegativeTuples)
  }

  private def combineNullTuple(inputTuple: Tuple): Tuple = {
    var nullPart = tuple()
    for(i <- 1 to secondaryTupleWidth - secondaryMask.size)
      nullPart = nullPart ++ tuple(null)

    inputTuple ++ nullPart
  }

}
