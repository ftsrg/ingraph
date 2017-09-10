package ingraph.sre

import clojure.java.api.Clojure
import com.google.common.collect.Lists
import ingraph.sre.plan._
import ingraph.sre.util.Variable

object SearchPlanFixtures {

  val ne = Clojure.read("not=")
  val eq = Clojure.read("=")

  def v(o: AnyRef) = Variable.createVar(o)
  def c(o: AnyRef) = Variable.createConst(o)

  object p1 {
    import ingraph.sre.{SearchPlanFixtures => f}

    val all = Lists.newArrayList(
      DirectedEdgeConstraintBinding.create(v(1), v(2), v(3)),
      DirectedEdgeConstraintBinding.create(v(5), v(4), v(3)),
      HasLabelsConstraintBinding.create(v(1), c("Actor")),
      HasLabelsConstraintBinding.create(v(3), c("Movie")),
      HasLabelsConstraintBinding.create(v(5), c("Actor")),
      HasTypeConstraintBinding.create(v(2), c("ACTS_IN")),
      HasTypeConstraintBinding.create(v(4), c("ACTS_IN")),
      GenBinaryAssertionConstraintBinding.create(v(2), v(4), c(f.ne)),
      PropertyConstraintBinding.create(v(1), c("name"), v(6)),
      GenBinaryAssertionConstraintBinding.create(v(6), c("John Travolta"), c(f.eq))
    )

    val bound = Lists.newArrayList(
      KnownConstraintBinding.create(c("Actor")),
      KnownConstraintBinding.create(c("Movie")),
      KnownConstraintBinding.create(c("ACTS_IN")),
      KnownConstraintBinding.create(c("name")),
      KnownConstraintBinding.create(c("John Travolta")),
      KnownConstraintBinding.create(c(f.eq)),
      KnownConstraintBinding.create(c(f.ne))
    )
  }
}
