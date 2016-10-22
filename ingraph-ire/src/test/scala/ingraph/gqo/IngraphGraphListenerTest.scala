package ingraph.gqo

import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.scalatest.FlatSpec

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph

import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import relalg.ProductionOperator

class IngraphGraphListenerTest extends FlatSpec {
  val modelPath = "../trainbenchmark/models/railway-repair-1-tinkerpop.graphml"
  "semaphoreneighbour" should "work" in {
    val rete = new Relalg2ReteTransformation
    val inferencer = new SchemaInferencer
    val relAlgContainer = inferencer.addSchemaInformation(rete.transformToRete(TrainBenchmarkUtil.switchSet()))
    val engine = EngineFactory.createQueryEngine(relAlgContainer.getRootExpression.asInstanceOf[ProductionOperator])
    val listener = new IngraphGraphChangedListener(
      engine.vertexConverters.toMap, engine.edgeConverters.toMap, engine.inputLookup)
    val graph = new EventGraph[Graph](TinkerGraph.open())
    graph.addListener(listener)
    val io = graph.io(IoCore.graphml)
    io.readGraph(modelPath)
    println("well, i'm waiting")
    println(engine.getResults().size)
  }
}
