package ingraph.ire

import hu.bme.mit.ire.DataSourceFactory
import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire.adapters.tuplecreators.PullTupleCreator

class OneTimeQueryAdapter(
    val querySpecification: String,
    val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractQueryAdapter {

  val dataSourceFactory = new DataSourceFactory
  dataSourceFactory.subscribe(engine.inputLookup)
  val dataSource = dataSourceFactory.newDataSource
  new PullTupleCreator(
    engine.vertexConverters.values.flatten.toVector.distinct,
    engine.edgeConverters.values.flatten.toVector.distinct,
    indexer, dataSource, LongIdParser
  )

  override def results(): Iterable[Tuple] = {
    dataSource.close
    engine.getResults
  }

  override def close {
    engine.shutdown
  }

}
