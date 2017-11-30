package ingraph.tests.runner

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import ingraph.tests.LdbcSnbTestCase
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.kernel.api.exceptions.KernelException
import org.neo4j.kernel.impl.proc.Procedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.neo4j.test.TestGraphDatabaseFactory

import scala.collection.JavaConversions
import scala.collection.JavaConversions.asScalaIterator

object Neo4jTestRunner {
  @throws[KernelException]
  def registerProcedure(db: GraphDatabaseService, procedures: Class[_]*): Unit = {
    val proceduresService = db.asInstanceOf[GraphDatabaseAPI].getDependencyResolver.resolveDependency(classOf[Procedures])
    for (procedure <- procedures) {
      proceduresService.registerProcedure(procedure)
      proceduresService.registerFunction(procedure)
    }
  }

  def graphmlPath(tc: LdbcSnbTestCase) = s"../graphs/bi/graphmls/${tc.workload}-${tc.id}.graphml"
}

class Neo4jTestRunner(tc:LdbcSnbTestCase) extends TestRunner(tc) {
  import Neo4jTestRunner._

  override def getResults = {
    import scala.collection.JavaConverters._

    val bolt = GraphDatabaseSettings
      .boltConnector("0")

    val gds = new TestGraphDatabaseFactory()
      .newImpermanentDatabaseBuilder
      .setConfig("apoc.import.file.enabled", "true")
      .setConfig(bolt.`type`, "BOLT")
      .setConfig(bolt.enabled, "true")
      .setConfig(bolt.address, "localhost:7688")
      .newGraphDatabase

    Neo4jTestRunner.registerProcedure(gds, classOf[ExportGraphML], classOf[Graphs])

    val trans = gds.beginTx()
    try {
      gds.execute(s"CALL apoc.import.graphml('${graphmlPath(tc)}', {batchSize: 10000, readLabels: true})")
      gds
        .execute(tc.querySpecification, JavaConversions.mapAsJavaMap(tc.parameters))
        .toStream
        .map(map => map.asScala.toMap)
        .toList
    } finally {
      println(tc.querySpecification)
      trans.close()
      gds.shutdown()
    }
  }
}
