package ingraph.gqo

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph
import ingraph.optimization.transformations.{Relalg2ReteTransformation, SchemaInferencer}
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.scalatest.FlatSpec
import relalg.ProductionOperator


class GrapListenerTest extends FlatSpec {
  val modelPath = "../trainbenchmark/models/railway-repair-1-tinkerpop.graphml"
  "semaphoreneighbour" should "work" in {
    val rete = new Relalg2ReteTransformation
    val inferencer = new SchemaInferencer
    val sn = inferencer.addSchemaInformation(rete.transform(TrainBenchmarkUtil.switchSet()))
    val engine = EngineMaker.createQueryEngine(sn.getRootExpression.asInstanceOf[ProductionOperator])
    val listener = new MentalGraphChangedListener(
      engine.vertexConverters.toMap, engine.edgeConverters.toMap, engine.inputLookup)
    val graph = new EventGraph[Graph](TinkerGraph.open())
    graph.addListener(listener)
    val io = graph.io(IoCore.graphml)
    io.readGraph(modelPath)
    println("well, i'm waiting")
    println(engine.getResults().size)
  }
}
