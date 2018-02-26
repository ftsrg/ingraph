package ingraph.tests

import java.util.concurrent.TimeUnit

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import com.google.common.base.Stopwatch
import ingraph.driver.CypherDriverFactory
import ingraph.ire.IngraphOneTimeAdapter
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.kernel.api.exceptions.KernelException
import org.neo4j.kernel.impl.proc.Procedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.neo4j.test.TestGraphDatabaseFactory
import org.supercsv.prefs.CsvPreference

import scala.collection.JavaConverters._

object TestRunners {
  type Result = List[Map[String, Any]]
  type TestRunner = (TestCase) => Result

  def neo4jTestRunner(tc: TestCase with GraphMLData): Result = {
    @throws[KernelException]
    def registerProcedure(db: GraphDatabaseService, procedures: Class[_]*): Unit = {
      val proceduresService = db.asInstanceOf[GraphDatabaseAPI].getDependencyResolver.resolveDependency(classOf[Procedures])
      for (procedure <- procedures) {
        proceduresService.registerProcedure(procedure)
        proceduresService.registerFunction(procedure)
      }
    }

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
    val graphml = s"CALL apoc.import.graphml('${tc.graphMLPath}', {batchSize: 10000, readLabels: true})"
    println(graphml)
    println(tc.query)
    try {
      gds.execute(graphml)
      gds
        .execute(tc.query)
        .asScala
        .map(map => map.asScala.toMap)
        .toList
    } finally {
      trans.close()
      gds.shutdown()
    }
  }

  def ingraphTestRunner(tc: LdbcSnbTestCase) : Result = {
    val driver = CypherDriverFactory.createIngraphDriver
    try {
      val session = driver.session
      val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
      val queryHandler = session.registerQuery(tc.name, tc.query)
      queryHandler.readCsv(
        tc.nodeCSVPaths.mapValues(_.asJava).asJava,
        tc.relationshipCSVPaths.asJava,
        csvPreference
      )
      val res = queryHandler.result
      println(res.size)

      val indexer = queryHandler.adapter.indexer

//      val loader = new LdbcUpdateToIngraphLoader(indexer, "../graphs/ldbc-snb-bi/sf-tiny/")
//      loader.load()

//      val s = Stopwatch.createStarted()
//      val onetime = new IngraphOneTimeAdapter(
//        // sftiny: 8796093022246
//        // sf01: 32985348834423
//        // sf03: 13194139533500
//        // sf1: 4398046516185
//        """
//          |MATCH (p:Person {id: 32985348834423})
//          |DETACH DELETE p
//        """.stripMargin,
//        "del", indexer)
//      onetime.terminate()
//      val res2 = queryHandler.result
//      println(res2.size)
//      println("Update time: " + s.elapsed(TimeUnit.MILLISECONDS))

      res
    } finally if (driver != null) {
      driver.close()
    }
  }
}
