package ingraph.ire

import org.supercsv.prefs.CsvPreference
import com.esotericsoftware.kryo.Kryo
import java.io.FileInputStream

import org.objenesis.strategy.StdInstantiatorStrategy
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer
import com.esotericsoftware.kryo.io.Input
import java.util.Collections

import org.neo4j.driver.v1.Record
import ingraph.tests.LdbcSnbBiTest

class LdbcSnbBiEngineTest extends LdbcSnbBiTest {

  override def runQuery(queryNumber : Int, queryName : String, querySpecification : String) {
    val adapter = new IngraphAdapter(querySpecification, queryName)

    converter.convert(adapter.plan, s"ldbc-snb-bi/query-${queryNumber}")

    val tran = adapter.newTransaction()
    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build()
    adapter.readCsv(nodeFilenames, relationshipFilenames, tran, csvPreference)
    tran.close()

    val actualResults = adapter.engine.getResults()

    val kryo = new Kryo
    kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()))
    kryo.addDefaultSerializer(
        Collections.unmodifiableCollection( Collections.EMPTY_LIST ).getClass,
        classOf[UnmodifiableCollectionsSerializer])

    val expectedResults = kryo.readClassAndObject(new Input(new FileInputStream(queryResultPath(queryNumber))))
      .asInstanceOf[java.util.ArrayList[Record]]
    import scala.collection.JavaConverters._
    assertResult(expectedResults.size)(actualResults.size)
    for ((expected, actual) <- expectedResults.asScala.zip(actualResults.toVector)) {
      val map = expected.asMap()
      val expectedTuple = adapter.resultNames().map(map.get)
      assertResult(expectedTuple)(actual)
    }
  }

}
