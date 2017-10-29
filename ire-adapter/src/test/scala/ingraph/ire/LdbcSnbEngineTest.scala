package ingraph.ire

import java.io.FileInputStream
import java.util.Collections

import org.scalatest.Ignore
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer
import ingraph.tests.LdbcSnbTest
import org.neo4j.driver.v1.Record
import org.objenesis.strategy.StdInstantiatorStrategy
import org.supercsv.prefs.CsvPreference

@Ignore
class LdbcSnbEngineTest extends LdbcSnbTest {

  override def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String) {
    val indexer = new Indexer()
    val adapter = new IngraphIncrementalAdapter(querySpecification, queryName, indexer)

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

    val expectedResults = kryo.readClassAndObject(new Input(new FileInputStream(queryResultPath(workload, queryNumber))))
      .asInstanceOf[java.util.ArrayList[Record]]
    assertResult(expectedResults.size)(actualResults.size)
    // do not assert on the result contents -- the results use Scala collections, while the reference output uses Java collections.
    // for assertions on the results, use the LdbcSnbBiDriverTest instead
  }

}
