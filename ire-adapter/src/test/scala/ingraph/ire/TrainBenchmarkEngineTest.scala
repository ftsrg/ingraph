package ingraph.ire

import java.util.Collections

import com.esotericsoftware.kryo.Kryo
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer
import ingraph.nre.IngraphNaiveAdapter
import ingraph.tests.TrainBenchmarkTest
import org.objenesis.strategy.StdInstantiatorStrategy
import org.scalatest.Ignore

class TrainBenchmarkEngineTest extends TrainBenchmarkTest {

  override def runQuery(queryName: String, querySpecification: String) {
    val indexer = new Indexer()
    val adapter = new IngraphNaiveAdapter(querySpecification, queryName, indexer)

    val tran = adapter.newTransaction()
    adapter.readCsv(nodeFilenames, relationshipFilenames, tran)
    tran.close()

    val actualResults = adapter.engine.getResults()

    val kryo = new Kryo

    kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()))
    kryo.addDefaultSerializer(
        Collections.unmodifiableCollection( Collections.EMPTY_LIST ).getClass,
        classOf[UnmodifiableCollectionsSerializer])

    println(actualResults)
//    val expectedResults = kryo.readClassAndObject(new Input(new FileInputStream(queryResultPath(workload, queryNumber))))
//      .asInstanceOf[java.util.ArrayList[Record]]
//    assertResult(expectedResults.size)(actualResults.size)
    // do not assert on the result contents -- the results use Scala collections, while the reference output uses Java collections.
    // for assertions on the results, use the LdbcSnbBiDriverTest instead
  }

}
