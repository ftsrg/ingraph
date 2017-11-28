package ingraph.tests

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import com.google.gson.Gson
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.kernel.api.exceptions.KernelException
import org.neo4j.kernel.impl.proc.Procedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.neo4j.test.TestGraphDatabaseFactory
import org.scalatest.FunSuite

import scala.collection.JavaConversions
import scala.io.Source

class LdbcSnbTest extends FunSuite {

  val testCases: Seq[TestCase] = 1 to 25 map (TestCase("bi", _))

  testCases.foreach(
    t =>
      test(s"${t.workload}-${t.number}-size-1") {
        val parameters = t.parameters

        val queryName = s"ldbc-snb-${t.workload}-${t.number}"
        println(queryName)

        val baseQuerySpecification = readToString(queryPath(t.workload, t.n))
        println(parameters)
        val querySpecification = parameters.foldLeft(baseQuerySpecification)((a, b) =>
          a.replaceAllLiterally("$" + b._1.toString, b._2.toString))

        runQuery(t.workload, t.number, queryName, querySpecification, parameters.toMap)
      }
  )

  def modelPath(entityName: String)(implicit number: Int) = s"../graphs/bi/$number/${entityName}_0_0.csv"

  def parameterPath(number: String) = s"../graphs/bi/$number/parameters"

  def queryPath(workload: String, query: Int): String = s"../queries/ldbc-snb-${workload}/${workload}-${query}.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  @throws[KernelException]
  def registerProcedure(db: GraphDatabaseService, procedures: Class[_]*): Unit = {
    val proceduresService = db.asInstanceOf[GraphDatabaseAPI].getDependencyResolver.resolveDependency(classOf[Procedures])
    for (procedure <- procedures) {
      proceduresService.registerProcedure(procedure)
      proceduresService.registerFunction(procedure)
    }
  }

  case class TestCase(workload: String, n: Int) {
    val number = f"$n%02d"

    import scala.collection.JavaConverters._

    val gson = new Gson()
    val parameters = gson
      .fromJson(readToString(parameterPath(number)), classOf[java.util.Map[String, Object]])
      .asScala
      .map { case (k, v) => (k, convert(v)) }
  }

  def convert(v: Any): String = {
    v match {
      case d: Double => f"$d%.0f"
      case s: String => "'" + s.toString + "'"
      case _ => v.toString
    }
  }

  def readToString(path: String): String = Source.fromFile(s"$path").getLines().mkString("\n")

  def runQuery(workload: String, queryNumber: String, queryName: String, querySpecification: String, parameters: Map[String, Object]): Unit = {
    val bolt = GraphDatabaseSettings
      .boltConnector("0")

    val gds = new TestGraphDatabaseFactory()
      .newImpermanentDatabaseBuilder
      .setConfig("apoc.import.file.enabled", "true")
      .setConfig(bolt.`type`, "BOLT")
      .setConfig(bolt.enabled, "true")
      .setConfig(bolt.address, "localhost:7688")
      .newGraphDatabase

    registerProcedure(gds, classOf[ExportGraphML], classOf[Graphs])

    val trans = gds.beginTx()
    try {
      gds.execute(s"CALL apoc.import.graphml('../graphs/bi/graphmls/$workload-$queryNumber.graphml', {batchSize: 10000, readLabels: true})")
      val results = gds.execute(querySpecification)
      while(results.hasNext) println(results.next())
      gds.execute(querySpecification, JavaConversions.mapAsJavaMap(parameters))
    } finally {
      trans.close()
      gds.shutdown()
    }
  }
}
