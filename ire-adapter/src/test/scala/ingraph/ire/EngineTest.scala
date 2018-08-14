package ingraph.ire

import org.scalatest.FunSuite

abstract class EngineTest extends FunSuite {

  val queryDir: String

  def run(readQuery: String): Unit = {
    val indexer = new Indexer()
    val readAdapter = new IncrementalQueryAdapter(readQuery, "read", indexer)
    val result = readAdapter.results()
  }

  def runFromFile(fileBaseName: String): Unit = {
    val source = scala.io.Source.fromFile(s"../queries/${queryDir}/${fileBaseName}.cypher")
    val queryString = try source.getLines.mkString("\n") finally source.close()
    run(queryString)
  }

}
