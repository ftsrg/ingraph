package ingraph.tests

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import hu.bme.mit.ire.nodes.unary.ProductionNode
import ingraph.driver.CypherDriverFactory
import ingraph.model.fplan.Production
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
    try {
      gds.execute(s"CALL apoc.import.graphml('${tc.graphMLPath}', {batchSize: 10000, readLabels: true})")
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

  def ingraphTestRunner(tc: TestCase with CSVData) : Result = {
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
      val res = queryHandler.adapter.result()
      val prod = queryHandler.adapter.plan.asInstanceOf[Production]
      res.map(t => t.zip(prod.outputNames).map(kv => kv._2 -> kv._1).toMap).toList
    } finally if (driver != null) {
      driver.close()
    }
  }
}
