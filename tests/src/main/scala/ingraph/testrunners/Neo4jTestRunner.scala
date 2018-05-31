package ingraph.testrunners

import java.io.File
import java.util.concurrent.TimeUnit

import apoc.export.graphml.ExportGraphML
import apoc.graph.Graphs
import com.google.common.base.Stopwatch
import ingraph.tests.LdbcSnbTestCase
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.kernel.api.exceptions.KernelException
import org.neo4j.kernel.impl.proc.Procedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.neo4j.test.TestGraphDatabaseFactory

import scala.collection.JavaConverters._

class Neo4jTestRunner(tc: LdbcSnbTestCase, neo4jDir: Option[String]) extends AutoCloseable {

  val bolt = GraphDatabaseSettings.boltConnector("0")
  val gdsBuilder = if (neo4jDir.isDefined) {
    new TestGraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(neo4jDir.get))
  } else {
    new TestGraphDatabaseFactory().newImpermanentDatabaseBuilder()
  }

  val gds = gdsBuilder
    .setConfig("apoc.import.file.enabled", "true")
    .setConfig(bolt.`type`, "BOLT")
    .setConfig(bolt.enabled, "true")
    .setConfig(bolt.address, "localhost:7688")
    .newGraphDatabase

  def load(graphMLPath: String): Unit = {
    registerProcedure(gds, classOf[ExportGraphML], classOf[Graphs])

    val trans = gds.beginTx()
    val loadCommand = s"CALL apoc.import.graphml('${graphMLPath}', {batchSize: 10000, readLabels: true})"
    gds.execute(loadCommand)
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

  def run(): List[Map[String, Any]] = {
    val sLoad = Stopwatch.createStarted()
    val results1 = gds.execute(tc.querySpecification).asScala.map(_.asScala.toMap
      .map {case (k, v) => (k, v match {
        case v: java.util.List[AnyRef] => v.asScala
        case _ => v
      })}
    ).toList
    println("neo4j   => Initial evaluation:       " + sLoad.elapsed(TimeUnit.NANOSECONDS))

    val tx = gds.beginTx()

    tc.updateQuerySpecifications.foreach { q =>
      val s = Stopwatch.createStarted()
      gds.execute(q)
      val results2 = gds.execute(tc.querySpecification).asScala.map(_.asScala.toMap).toList
      println("neo4j   => Update and re-evaluation: " + s.elapsed(TimeUnit.NANOSECONDS))
    }
    tx.failure()
    tx.close()

    results1
  }

  override def close(): Unit = {
    gds.shutdown()
  }

}
