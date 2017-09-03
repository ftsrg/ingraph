package ingraph.sre

import java.util.Collections

import clojure.java.api.Clojure
import clojure.lang.{Cons, IPersistentMap}
import com.google.common.collect.Lists
import ingraph.sre.estimation.{CostCalculator, Estimation}
import ingraph.sre.plan._
import ingraph.sre.util.Variable
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner
import sre.plan.compiler.SearchPlanCompiler
import sre.plan.dsl.constraint.ConstraintBinding
import sre.plan.lookup.ConstraintLookupFactory

import scala.language.implicitConversions

@RunWith(classOf[JUnitRunner])
class SearchPlanCompilerTest extends FreeSpec {

  // You shouldn't create defrecords directly, because they do not load
  // the namespace's implementation like gen-class generated classes.
  // Better create gen-class factories for every defrecord:
  // https://groups.google.com/forum/#!topic/clojure/3DekTQZfDTk

  val costCalculator = Estimation.createCostCalculator(0, 1)
  val weightCalculator = Estimation.createWeightCalculator()

  def v(o: Any) = Variable.createVar(o)
  def c(o: Any) = Variable.createConst(o)

  "Should compile plan #1" in {
    val constraints = Lists.newArrayList[ConstraintBinding](DirectedEdgeConstraintBinding.create(1, 2, 3))

    val plan = SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getOperations,
      ConstraintLookupFactory.fromFreeConstrs(constraints))

    assert(plan.cost_calculator.asInstanceOf[CostCalculator].c == 5)
    assert(plan
      .ops.asInstanceOf[Cons]
      .first().asInstanceOf[IPersistentMap]
      .valAt(Clojure.read(":name")) == GetEdgesOperationBinding.getName())
  }

  "Should compile plan #2" in {
    val notEquals = Clojure.read("not=")
    val equals = Clojure.read("=")

    val constraints = Lists.newArrayList[ConstraintBinding](
      DirectedEdgeConstraintBinding.create(v(1), v(2), v(3)),
      DirectedEdgeConstraintBinding.create(v(5), v(4), v(3)),
      HasLabelsConstraintBinding.create(v(1), c("Actor")),
      HasLabelsConstraintBinding.create(v(3), c("Movie")),
      HasLabelsConstraintBinding.create(v(5), c("Actor")),
      HasTypeConstraintBinding.create(v(2), c("ACTS_IN")),
      HasTypeConstraintBinding.create(v(4), c("ACTS_IN")),
      GenBinaryAssertionConstraintBinding.create(v(2), v(4), c(notEquals)),
      PropertyConstraintBinding.create(v(1), c("name"), v(6)),
      GenBinaryAssertionConstraintBinding.create(v(6), c("John Travolta"), c(equals))
    )

    val bound = Lists.newArrayList(
      KnownConstraintBinding.create(c("Actor")),
      KnownConstraintBinding.create(c("Movie")),
      KnownConstraintBinding.create(c("ACTS_IN")),
      KnownConstraintBinding.create(c("name")),
      KnownConstraintBinding.create(c("John Travolta")),
      KnownConstraintBinding.create(c(equals)),
      KnownConstraintBinding.create(c(notEquals))
    )


    SearchPlanCompiler.calculate(
      costCalculator,
      weightCalculator,
      5,
      IngraphConfig.getOperations,
      ConstraintLookupFactory.fromAllConstrsAndBoundConstrs(constraints, bound))
  }
}
