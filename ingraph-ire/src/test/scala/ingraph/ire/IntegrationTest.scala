package ingraph.ire

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container
import org.scalatest.FlatSpec
import relalg.{ProductionOperator, RelalgContainer}


class IntegrationTest extends FlatSpec {
  val modelPath = "../trainbenchmark/models/railway-repair-1-tinkerpop.graphml"
  case class TestCase(name: String, network: RelalgContainer, expectedResultSize: Int)

  Vector(
    TestCase("PosLength", TrainBenchmarkUtil.posLength(), 95),
    TestCase("RouteSensor", TrainBenchmarkUtil.routeSensor(), 18),
    TestCase("SemaphoreNeighbor", TrainBenchmarkUtil.semaphoreNeighbor(), 3),
    TestCase("SwitchMonitored", TrainBenchmarkUtil.switchMonitored(), 0),
    TestCase("SwitchSet", TrainBenchmarkUtil.switchSet(), 5)
  ).foreach(
    t => t.name should "work" in {
      val rete = new Relalg2ReteTransformation
      val inferencer = new SchemaInferencer
      val sn = inferencer.addSchemaInformation(rete.transformToRete(t.network))
      val engine = EngineFactory.createQueryEngine(sn.getRootExpression.asInstanceOf[ProductionOperator])
      val listener = new IngraphGraphChangedListener(
        engine.vertexConverters.toMap, engine.edgeConverters.toMap, engine.inputLookup)
      val graph = new EventGraph[Graph](TinkerGraph.open())
      graph.addListener(listener)
      val io = graph.io(IoCore.graphml)
      io.readGraph(modelPath)
      val results = engine.getResults().size
      assert(results == t.expectedResultSize)
    }
  )
}
