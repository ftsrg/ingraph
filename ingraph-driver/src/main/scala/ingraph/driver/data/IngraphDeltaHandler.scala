package ingraph.driver.data

import java.util

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.ChangeListener
import org.neo4j.driver.v1.Record

abstract class IngraphDeltaHandler(val keys : Vector[String]) extends ChangeListener {
  val repackager = new Repackager(keys)

  def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record])

  final override def listener(positive: Iterable[Tuple], negative: Iterable[Tuple]): Unit = {
    val positiveRecords = repackager.repackageResult(positive)
    val negativeRecords = repackager.repackageResult(negative)
    onChange(positiveRecords, negativeRecords)
  }
}
