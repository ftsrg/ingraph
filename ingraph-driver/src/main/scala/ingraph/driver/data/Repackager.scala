package ingraph.driver.data

import hu.bme.mit.ire.datatypes.Tuple
import org.neo4j.driver.v1.Record
import scala.collection.JavaConverters._
import java.util.Collection
import org.neo4j.driver.internal.InternalRecord
import org.neo4j.driver.v1.Value
import org.neo4j.driver.v1.Values

class Repackager(val keys : Vector[String]) {

  val keysJava = keys.asJava

  def repackageResult(tuples: Iterable[Tuple]) : Collection[_ <: Record] = {
    tuples.map { repackageTuple(_) }.asJavaCollection
  }

  def repackageTuple(tuple: Tuple) : Record = {
    val values = tuple.map { createValue(_) }
    new InternalRecord(keysJava, values.toArray)
  }

  def createValue(x : Any) : Value = {
    Values.value(x)
  }

}
