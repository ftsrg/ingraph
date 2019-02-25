package ingraph.testrunners

trait TestRunner extends AutoCloseable{

  def run(): (Seq[Seq[Map[String, Any]]], Seq[Long])

  def close(): Unit
}
