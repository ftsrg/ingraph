package ingraph.testrunners

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import ingraph.tests.{GraphMLData, TestCase}
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.kernel.api.exceptions.KernelException
import org.neo4j.kernel.impl.proc.Procedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.neo4j.test.TestGraphDatabaseFactory

import scala.collection.JavaConverters._

object Neo4jTestRunner {

  def run(tc: TestCase with GraphMLData): List[Map[String, Any]] = {
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

}
