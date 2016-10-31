package ingraph.ire

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import relalg.{ProductionOperator, RelalgContainer}

class IngraphAdapter(plan: RelalgContainer) {
  private val rete = new Relalg2ReteTransformation
  private val inferencer = new SchemaInferencer
  private val sn = inferencer.addSchemaInformation(rete.transformToRete(plan))

  val engine = EngineFactory.createQueryEngine(sn.getRootExpression.asInstanceOf[ProductionOperator])
  private val listener = new IngraphGraphChangedListener(
    engine.vertexConverters.toMap, engine.edgeConverters.toMap, engine.inputLookup)

  private val graph = new EventGraph[Graph](TinkerGraph.open())
  graph.addListener(listener)
  val reader = graph.io(IoCore.graphml)
}
