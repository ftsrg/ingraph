package ingraph.ire

import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import com.tinkerpop.blueprints.util.wrappers.event.EventGraph
import hu.bme.mit.ire.Transaction
import ingraph.cypher2relalg.Cypher2Relalg
import relalg.RelalgContainer
import ingraph.relalg.inferencers.OneStepSchemaInferencer
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformationAndInferencer

class IngraphAdapter(plan: String) {
  private val retePlan = Relalg2ReteTransformationAndInferencer.apply(Cypher2Relalg.processString(plan))

  val engine = EngineFactory.createQueryEngine(retePlan.getRootExpression)

  private val listener = new IngraphGraphChangedListener(
    engine.vertexConverters.toMap, engine.edgeConverters.toMap, engine.inputLookup) with LongIdParser

  def readGraph(path: String, transaction: Transaction) {
    val graph = new EventGraph[Graph](TinkerGraph.open())
    graph.addListener(listener)
    listener.transaction = transaction
    val reader = graph.io(IoCore.graphml)
    reader.readGraph(path)
  }
}
