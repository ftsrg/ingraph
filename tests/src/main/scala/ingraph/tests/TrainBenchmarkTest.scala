package ingraph.tests

import org.scalatest.{FunSuite, Ignore}

import scala.io.Source

@Ignore
abstract class TrainBenchmarkTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/trainbenchmark/railway-repair-2-${entityName}.csv"

  def queryPath(query: String): String = s"../queries/trainbenchmark/${query}.cypher"

  //def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  val nodeFilenames: Map[String, List[String]] = Map(
    modelPath("Region") -> List("Region"),
    modelPath("Route") -> List("Route"),
    modelPath("Segment") -> List("Segment", "TrackElement"),
    modelPath("Semaphore") -> List("Semaphore"),
    modelPath("Sensor") -> List("Sensor"),
    modelPath("Switch") -> List("Switch", "TrackElement"),
    modelPath("SwitchPosition") -> List("SwitchPosition")
  )

  val relationshipFilenames: Map[String, String] = Map(
    modelPath("connectsTo") -> "connectsTo",
    modelPath("entry") -> "entry",
    modelPath("exit") -> "exit",
    modelPath("follows") -> "follows",
    modelPath("monitoredBy") -> "monitoredBy",
    modelPath("requires") -> "requires",
    modelPath("target") -> "target"
  )

  case class TestCase(name: String)

  val testCases = List("PosLength").map(new TestCase(_))

  testCases.filter(_ != null) //
    .foreach(
    t =>
      test(t.name) {

      val querySpecification = Source.fromFile(queryPath(t.name)).getLines().mkString("\n")

      runQuery(t.name, querySpecification)
    })

  def runQuery(queryName: String, querySpecification: String)
}
