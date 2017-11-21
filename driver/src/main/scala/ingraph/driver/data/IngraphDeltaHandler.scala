package ingraph.driver.data

import java.util

abstract class IngraphDeltaHandler() extends ChangeListener {
  val keys : Vector[String]
  val repackager = new Repackager(keys)

  def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record])

  final override def listener(positive: Iterable[Tuple], negative: Iterable[Tuple]): Unit = {

    val positiveRecords = repackager.repackageResult(positive)
    val negativeRecords = repackager.repackageResult(negative)
    onChange(positiveRecords, negativeRecords)
  }
}

class PrintDeltaHandler(override val keys : Vector[String]) extends IngraphDeltaHandler {
  override def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record]): Unit = {
    positiveRecords.asScala.foreach(p => println(s"-$p"))
    negativeRecords.asScala.foreach(n => println(s"-$n"))
  }
}
