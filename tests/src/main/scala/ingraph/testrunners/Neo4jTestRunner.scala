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

/**
  * @param tc test case
  * @param neo4jDir Neo4j database to use. If set to None, the test will fire up a new ImpermanentDatabase.
  */
class Neo4jTestRunner(tc: LdbcSnbTestCase, neo4jDir: Option[String]) extends AutoCloseable {

  val bolt = GraphDatabaseSettings.boltConnector("0")
  val gdsBuilder = if (neo4jDir.isDefined) {
    new TestGraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(neo4jDir.get))
  } else {
    new TestGraphDatabaseFactory().newImpermanentDatabaseBuilder()
  }

  val gds = gdsBuilder
    .setConfig("apoc.import.file.enabled", "true")
    .setConfig("apoc.export.file.enabled", "true")
    .setConfig("apoc.import.file.use_neo4j_config", "true")
    .setConfig("dbms.security.allow_csv_import_from_file_urls","true")
    .setConfig("dbms.directories.import", "../graphs/")
    .newGraphDatabase

  def load(graphMLPath: String): Unit = {
    registerProcedure(gds, classOf[ExportGraphML], classOf[Graphs])

    val tx = gds.beginTx()
    val loadCommand = s"CALL apoc.import.graphml('${graphMLPath}', {batchSize: 10000, readLabels: true})"
    gds.execute(loadCommand)
    tx.close()
  }

  @throws[KernelException]
  private def registerProcedure(db: GraphDatabaseService, procedures: Class[_]*): Unit = {
    val proceduresService = db.asInstanceOf[GraphDatabaseAPI].getDependencyResolver.resolveDependency(classOf[Procedures])
    for (procedure <- procedures) {
      proceduresService.registerProcedure(procedure)
      proceduresService.registerFunction(procedure)
    }
  }

  def executeQuery(gds: GraphDatabaseService, querySpecification: String) = {
    gds.execute(tc.querySpecification).asScala.map(_.asScala.toMap
      .map {case (k, v) => (k, v match {
        case v: java.util.List[AnyRef] => v.asScala
        case _ => v
      })}
    ).toList
  }

  def run(): (Iterable[Seq[Map[String, Any]]], Iterable[Long]) = {
    val iStopwatch = Stopwatch.createStarted()
    val iResult = executeQuery(gds, tc.querySpecification)
    val iTime = iStopwatch.elapsed(TimeUnit.NANOSECONDS)

    val tx = gds.beginTx()
    val runInfo: (Iterable[Seq[Map[String, Any]]], Iterable[Long]) =
      (Seq((iResult, iTime)) ++ tc.updates.map { updateQuery =>
        val uStopwatch = Stopwatch.createStarted()
        gds.execute(updateQuery)
        val uResult = executeQuery(gds, tc.querySpecification)
        val uTime = uStopwatch.elapsed(TimeUnit.NANOSECONDS)
        (iResult, iTime)
      }).unzip

    tx.failure()
    tx.close()

    val results = runInfo._1
    val times = runInfo._2
    println(tc.sf + "," + tc.query + ",neo4j,times," + times.mkString(","))
    println(tc.sf + "," + tc.query + ",neo4j,results," + results.map(_.length).mkString(","))

    return runInfo
  }

  override def close(): Unit = {
    gds.shutdown()
  }

}
