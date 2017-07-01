package ingraph.driver.test

import java.io.FileInputStream
import java.util
import java.util.Collections

import org.neo4j.driver.v1.Record
import org.objenesis.strategy.StdInstantiatorStrategy
import org.supercsv.prefs.CsvPreference
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input

import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer
import ingraph.driver.data.IngraphDeltaHandler
import ingraph.driver.ingraph.IngraphDriver
import ingraph.tests.LdbcSnbTest
import org.scalatest.FunSuite
import scala.io.Source

class TransitiveTest extends FunSuite {

  def modelPath(entityName: String) = s"../../graphs/snb_small/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"../../queries/ldbc-snb-$workload/$workload-$query.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-small.bin"

  val nodeFilenames: Map[String, List[String]] = Map(
    modelPath("person") -> List("Person")
  )

  val relationshipFilenames: Map[String, String] = Map(
    modelPath("person_knows_person") -> "knows"
  )

  case class TestCase(workload: String, number: Int)

  val testCases =
    List(7).map(new TestCase("simple", _))

  testCases.filter(_ != null) //
    .foreach(
    t =>
      test(s"${t.workload}-${t.number}-size-1") {

      val queryName = s"ldbc-snb-${t.workload}-${t.number}"
      val querySpecification = Source.fromFile(queryPath(t.workload, t.number)).getLines().mkString("\n")

      runQuery(t.workload, t.number, queryName, querySpecification)
    })


  def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String) {
    val driver = new IngraphDriver()
    val session = driver.session()

    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build()

    val queryHandler = session.registerQuery(queryName, querySpecification)
    var actualResults: util.List[_ <: Record] = null
    class AssertionHandler(override val keys: Vector[String]) extends IngraphDeltaHandler {
      override def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record]): Unit = {
        actualResults = positiveRecords
      }
    }

    import scala.collection.JavaConverters._

    queryHandler.registerDeltaHandler(new AssertionHandler(queryHandler.adapter.resultNames()))
    queryHandler.readCsv(nodeFilenames.mapValues(_.asJava).asJava, relationshipFilenames.asJava, csvPreference)

    actualResults.asScala.toVector.map(println(_))
  }
}
