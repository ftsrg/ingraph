package ingraph.sre

import java.util.Collections

import clojure.java.api.Clojure
import clojure.lang.{Cons, IPersistentMap}
import ingraph.sre.estimation.{CostCalculator, Estimation}
import ingraph.sre.plan.{DirectedEdgeConstraintBinding, GetEdgesOperationBinding, IngraphConfig}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner
import sre.plan.compiler.SearchPlanCompiler
import sre.plan.lookup.ConstraintLookupFactory

@RunWith(classOf[JUnitRunner])
class SearchPlanCompilerTest extends FreeSpec {

  // You shouldn't create defrecords directly, because they do not load
  // the namespace's implementation like gen-class generated classes.
  // Better create gen-class factories for every defrecord:
  // https://groups.google.com/forum/#!topic/clojure/3DekTQZfDTk

  val costCalculator = Estimation.createCostCalculator(0, 1)
  val weightCalculator = Estimation.createWeightCalculator()


  val plan1 = {
    Collections.singletonList(DirectedEdgeConstraintBinding.create(1, 2, 3))
  }

  "Should compile plan #1" in {
    val binding = DirectedEdgeConstraintBinding.create(1, 2, 3)
    val bindings = Collections.singletonList(binding)
    val plan = SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getOperations(),
      ConstraintLookupFactory.fromFreeConstrs(bindings))

    assert(plan.cost_calculator.asInstanceOf[CostCalculator].c == 5)
    assert(plan
      .ops.asInstanceOf[Cons]
      .first().asInstanceOf[IPersistentMap]
      .valAt(Clojure.read(":name")) == GetEdgesOperationBinding.getName())
  }

  val plan2 = {
  }

  "Should compile plan #2" in {
    val binding = DirectedEdgeConstraintBinding.create(1, 2, 3)
    val bindings = Collections.singletonList(binding)
    val plan = SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getOperations(),
      ConstraintLookupFactory.fromFreeConstrs(bindings))

    assert(plan.cost_calculator.asInstanceOf[CostCalculator].c == 5)
    assert(plan
      .ops.asInstanceOf[Cons]
      .first().asInstanceOf[IPersistentMap]
      .valAt(Clojure.read(":name")) == GetEdgesOperationBinding.getName())
  }
}
