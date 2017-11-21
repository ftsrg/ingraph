package ingraph.driver.test

import java.io.FileInputStream
import java.util
import java.util.Collections

class LdbcSnbDriverTest extends LdbcSnbTest {

  override def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String) {
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

    val kryo = new Kryo
    kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()))
    kryo.addDefaultSerializer(
        Collections.unmodifiableCollection( Collections.EMPTY_LIST ).getClass,
        classOf[UnmodifiableCollectionsSerializer])

    queryHandler.registerDeltaHandler(new AssertionHandler(queryHandler.adapter.resultNames()))
    queryHandler.readCsv(nodeFilenames.mapValues(_.asJava).asJava, relationshipFilenames.asJava, csvPreference)

    val expectedResults = kryo.readClassAndObject(new Input(new FileInputStream(queryResultPath(workload, queryNumber))))
      .asInstanceOf[java.util.ArrayList[Record]]
    assertResult(expectedResults.size)(actualResults.size)
    for ((expected, actual) <- expectedResults.asScala.zip(actualResults.asScala.toVector)) {
      assertResult(expected.asMap())(actual.asMap())
    }
  }
}
