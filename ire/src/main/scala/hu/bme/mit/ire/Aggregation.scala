package hu.bme.mit.ire
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

trait Aggregator {

}

class CountNode(override val next: (ReteMessage) => Unit,
                val keys: Vector[Any], as: String,
                override val expectedTerminatorCount:Int = 1) extends UnaryNode with SingleForwarder {
  val counts = new mutable.HashMap[Vector[Any], Int].withDefault(d => 0)
  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val oldValues = new mutable.HashMap[Vector[Any], Int]

    for (tuple <- changeSet.positive;
         key = keys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = counts(key)
      }
      counts(key) += 1
    }

    for (tuple <- changeSet.negative;
         key = keys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = counts(key)
      }
      counts(key) -= 1
    }


    val positive = new VectorBuilder[TupleType]
    val negative = new VectorBuilder[TupleType]
    for ((key, oldValue) <- oldValues) {
      if (oldValue != 0) {
        negative += Map(as -> oldValues(key)) ++ key.zipWithIndex.map(kv => keys(kv._2) -> kv._1).toMap
      }
      if (counts(key) != 0) {
        positive += Map(as -> counts(key)) ++ key.zipWithIndex.map(kv => keys(kv._2) -> kv._1).toMap
      }
    }

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }
}

class AverageNode(override val next: (ReteMessage) => Unit,
                  val keys: Vector[Any],
                  override val expectedTerminatorCount:Int = 1) extends UnaryNode with SingleForwarder {
  var count = 0
  override def onChangeSet(changeSet: ChangeSet): Unit = {

  }

}

class SumNode(override val next: (ReteMessage) => Unit,
              val aggregationKeys: Vector[Any], val sumKey: Any, as: String,
              override val expectedTerminatorCount:Int = 1) extends UnaryNode with SingleForwarder {
  val sums = new mutable.HashMap[Vector[Any], Any].withDefault(d => 0)
  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val oldValues = new mutable.HashMap[Vector[Any], Any]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.plus(sums(key), tuple(sumKey))
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.minus(sums(key), tuple(sumKey))
    }

    val positive = new VectorBuilder[TupleType]
    val negative = new VectorBuilder[TupleType]
    for ((key, oldValue) <- oldValues) {
      if (oldValue != 0) {
        negative += Map(as -> oldValues(key)) ++ key.zipWithIndex.map(kv => aggregationKeys(kv._2) -> kv._1).toMap
      }
      if (sums(key) != 0) {
        positive += Map(as -> sums(key)) ++ key.zipWithIndex.map(kv => aggregationKeys(kv._2) -> kv._1).toMap
      }
    }

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }
}
