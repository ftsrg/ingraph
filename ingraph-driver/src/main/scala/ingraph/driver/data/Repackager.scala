package ingraph.driver.data

import hu.bme.mit.ire.datatypes.Tuple
import org.neo4j.driver.internal.InternalRecord
import org.neo4j.driver.v1.{Record, Value, Values}

import scala.collection.JavaConverters._
import scala.collection.immutable.Vector

class Repackager(val keys : Vector[String]) {

  val keysJava = keys.asJava

  def repackageResult(tuples: Iterable[Tuple]) : java.util.List[_ <: Record] = {
    tuples.map { repackageTuple(_) }.toList.asJava
  }

  def repackageTuple(tuple: Tuple) : Record = {
    val values = tuple.map { convertToValue(_) }
    val record = new InternalRecord(keysJava, values.toArray)
    record
  }

  def convertToValue(x : Any) : Value = {
    x match {
      case x: Vector[Any] => Values.value(x.map { convertToValue(_) }.asJava)
      case _ => Values.value(x)
    }
  }
}
