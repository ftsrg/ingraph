package ingraph.sandbox

import ingraph.compiler.cypher2qplan.QPlanResolver
import ingraph.compiler.qplan2jplan.{FPlanToTPlan, JPlanToFPlan, QPlanToJPlan}
import ingraph.model.expr._
import ingraph.model.qplan
import ingraph.model.tplan
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
import org.scalatest.FunSuite

class FPlanToTPlanTest extends FunSuite {

  test("infer schema #1") {
    val v = VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val qp = de
    val jp = QPlanToJPlan.transform(qp)
    val fp = JPlanToFPlan.transform(jp)
    val tp = FPlanToTPlan.transform(fp)
  }

  test("infer schema #2") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)

    val n = VertexAttribute("n", vls)
    val name = PropertyAttribute("name", n)
    val age = PropertyAttribute("age", n)

    val projectList = Seq(ReturnItem(name))
    val condition = GreaterThan(age, Literal(27))

    val qp = qplan.UnresolvedProjection(
      projectList,
      qplan.Selection(
        condition,
        qplan.GetVertices(n)
      )
    )
    val rqp = QPlanResolver.resolveQPlan(qp)
    val jp = QPlanToJPlan.transform(rqp)
    val fp = JPlanToFPlan.transform(jp)
    val tp = FPlanToTPlan.transform(fp)
    assert(tp.asInstanceOf[tplan.Projection].projectionTuple(0).asInstanceOf[TupleIndexLiteralAttribute].index == 1)
  }

}
