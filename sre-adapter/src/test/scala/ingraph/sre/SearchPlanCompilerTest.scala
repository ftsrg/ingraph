package ingraph.sre

import java.util.Collections

import clojure.java.api.Clojure
import clojure.lang.{Cons, IPersistentMap, IPersistentSet}
import com.google.common.collect.Lists
import ingraph.sre.estimation.{CostCalculator, Estimation}
import ingraph.sre.config._
import ingraph.sre.util.Variable
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

  private val costCalculator = Estimation.costCalculator()
  private val weightCalculator = Estimation.weightCalculator()

  "Should compile plan #1" in {
    val constraints = Collections.singletonList(DirectedEdgeConstraintBinding.create(1, 2, 3))

    val plan = SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getInstance().operations().asInstanceOf[IPersistentSet],
      ConstraintLookupFactory.fromFreeConstrs(constraints))

    assert(plan.cost_calculator.asInstanceOf[CostCalculator].c == 5)
    assert(plan
      .ops.asInstanceOf[Cons]
      .first().asInstanceOf[IPersistentMap]
      .valAt(Clojure.read(":type")) == GetEdgesOperationBinding.getType)
  }

  "Should compile plan #2" in {
    val notEquals = Clojure.read("not=")
    val equals = Clojure.read("=")

    val constraints = SearchPlanFixtures.p1.all

    val bound = SearchPlanFixtures.p1.bound

    SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getInstance().operations().asInstanceOf[IPersistentSet],
      ConstraintLookupFactory.fromAllConstrsAndBoundConstrs(constraints, bound))
  }
}
