package ingraph.testrunners

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.SqlDriver.ExternalDatabase
import ingraph.compiler.sql.driver.{LdbcTestResources, SqlDriver, SqlSession}
import ingraph.tests.LdbcSnbTestCase
import org.neo4j.driver.v1.Transaction

import scala.collection.JavaConverters._

class C2STestRunner(tc: LdbcSnbTestCase) extends TestRunner {

  var driver: SqlDriver = new SqlDriver(
    translateCreateQueries = true,
    gTop = Some(LdbcTestResources.gTop),
    database = ExternalDatabase(LdbcTestResources.url),
    initializeDb = false)
  var session: SqlSession = driver.session

  def executeQuery(tx: Transaction, querySpecification: String): Seq[Map[String, AnyRef]] = {
    tx.run(tc.querySpecification).list().asScala.map(_.asMap().asScala.toMap
      .map { case (k, v) => (k, v match {
        case v: java.util.List[AnyRef] => v.asScala
        case _ => v
      })
      }
    )
  }

  override def run(): (Seq[Seq[Map[String, Any]]], Seq[Long]) = {
    withResources(session.beginTransaction()) { tx =>
      val numberOfNodes = tx.run("MATCH (n) RETURN count(n) AS numberOfNodes")
        .single()
        .get("numberOfNodes").asLong()
      assert(numberOfNodes != 0)

      // initial
      val iStopwatch = Stopwatch.createStarted()
      val iResult = executeQuery(tx, tc.querySpecification)
      val iTime = iStopwatch.elapsed(TimeUnit.NANOSECONDS)

      val aResult: Seq[Map[String, AnyRef]] = Seq.empty
      val aTime = 0

      val dResult: Seq[Map[String, AnyRef]] = Seq.empty
      val dTime = 0

      val results: Seq[Seq[Map[String, AnyRef]]] = Seq(iResult, aResult, dResult)
      val times: Seq[Long] = Seq(iTime, aTime, dTime)

      val resultType = "c2s"
      println(tc.sf + "," + tc.query + "," + resultType + ",results," + results.map(_.length).mkString(","))
      println(tc.sf + "," + tc.query + "," + resultType + ",times," + times.mkString(","))

      return (results, times)
    }
  }

  override def close(): Unit = {
    session.close()
    driver.close()
  }

}
