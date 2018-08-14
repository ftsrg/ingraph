package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.inputs.InputMultiplexerFactory
import ingraph.ire.adapters.tuplecreators.PullTupleCreator

class OneTimeQueryAdapter(
    val querySpecification: String,
    val queryName: String,
    override val indexer: Indexer = new Indexer()
  ) extends AbstractQueryAdapter {

  val inputMultiplexerFactory = new InputMultiplexerFactory
  inputMultiplexerFactory.subscribe(engine.inputLookup)
  val inputMultiplexer = inputMultiplexerFactory.newInputMultiplexer
  new PullTupleCreator(
    engine.vertexConverters.values.flatten.toVector.distinct,
    engine.edgeConverters.values.flatten.toVector.distinct,
    indexer, inputMultiplexer, LongIdParser
  )

  override def results(): Iterable[Tuple] = {
    inputMultiplexer.sendAll()
    engine.getResults()
  }

  override def close {
    engine.shutdown()
  }

}
