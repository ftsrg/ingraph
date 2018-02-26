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

class Neo4jTestRunner extends AutoCloseable {

  val bolt = GraphDatabaseSettings.boltConnector("0")
  val gds = new TestGraphDatabaseFactory()
    .newImpermanentDatabaseBuilder
    .setConfig("apoc.import.file.enabled", "true")
    .setConfig(bolt.`type`, "BOLT")
    .setConfig(bolt.enabled, "true")
    .setConfig(bolt.address, "localhost:7688")
    .newGraphDatabase

  def load(graphMLPath: String): Unit = {
    registerProcedure(gds, classOf[ExportGraphML], classOf[Graphs])

    val trans = gds.beginTx()
    val graphml = s"CALL apoc.import.graphml('${graphMLPath}', {batchSize: 10000, readLabels: true})"
    gds.execute(graphml)
    trans.close()
  }

  @throws[KernelException]
  private def registerProcedure(db: GraphDatabaseService, procedures: Class[_]*): Unit = {
    val proceduresService = db.asInstanceOf[GraphDatabaseAPI].getDependencyResolver.resolveDependency(classOf[Procedures])
    for (procedure <- procedures) {
      proceduresService.registerProcedure(procedure)
      proceduresService.registerFunction(procedure)
    }
  }

  def run(querySpecification: String): List[Map[String, Any]] = {
    gds.execute(querySpecification).asScala.map(_.asScala.toMap).toList
  }

  override def close(): Unit = {
    gds.shutdown()
  }

}
