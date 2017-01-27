package ingraph.ire

import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import com.tinkerpop.blueprints.util.wrappers.event.EventGraph
import hu.bme.mit.ire.Transaction
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.{DetailedSchemaInferencer, ExtraAttributeInferencer, SchemaInferencer}
import relalg.RelalgContainer

class IngraphAdapter(plan: String) {
  private val retePlan = Inferencer(Cypher2Relalg.processString(plan))

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

object Inferencer {
  val relalg2rete = new Relalg2ReteTransformation
  val schemaInferencer = new SchemaInferencer
  val tupleInferencer = new ExtraAttributeInferencer
  val detailedSchemaInferencer = new DetailedSchemaInferencer

  def apply(relalg: RelalgContainer): RelalgContainer =
    detailedSchemaInferencer.addDetailedSchemaInformation(
      tupleInferencer.addExtraAttributes(
        schemaInferencer.addSchemaInformation(
          relalg2rete.transformToRete(relalg))))
}
