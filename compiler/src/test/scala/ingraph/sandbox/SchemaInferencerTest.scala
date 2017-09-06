package ingraph.sandbox

import ingraph.compiler.qplan2iplan.SchemaInferencer
import ingraph.model.expr._
import ingraph.model.iplan
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
import org.scalatest.FunSuite

class SchemaInferencerTest extends FunSuite {

//  test("infer schema #1") {
//    val v = VertexAttribute("v")
//    val gv = qplan.GetVertices(v)
//    val de = qplan.DuplicateElimination(gv)
//
//    val qp = de
//    val ip = QPlanToIPlan.transform(qp)
//    val ips = SchemaInferencer.transform(ip)
//
//    assert(ips.internalSchema.size == 1)
//    assert(ips.chn(0).internalSchema.size == 1)
//  }
//
//  test("infer schema #2") {
//    val vls = VertexLabelSet(Set("Person"), NonEmpty)
//
//
//    val n = VertexAttribute("n", vls)
//    val name = PropertyAttribute("name", n)
//    val age = PropertyAttribute("age", n)
//
//    val projectList = Seq(name)
//    val condition = GreaterThan(age, Literal(27))
//
//    val qp = qplan.Projection(
//      projectList,
//      qplan.Selection(
//        condition,
//        qplan.GetVertices(n)
//      )
//    )
//
//    val ip = QPlanToIPlan.transform(qp)
//    val ips = SchemaInferencer.transform(ip)
//
//    assert(ips.internalSchema.size == 1)
//    assert(ips.chn(0).internalSchema.size == 3)
//    assert(ips.chn(0).chn(0).internalSchema.size == 3)
//  }

  test("infer schema #3") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)
    val el = EdgeLabelSet(Set("KNOWS"), NonEmpty)

    val p1 = VertexAttribute("p1", vls)
    val name = PropertyAttribute("name", p1)

    val p2 = VertexAttribute("n", vls)
    val age = PropertyAttribute("age", p2)

    val e = EdgeAttribute("e", el)

    val gv = iplan.GetVertices(p1)
    val ge = iplan.GetEdges(p1, p2, e, Out)
    val join = iplan.Join(gv, ge)

    val inferred = SchemaInferencer.transform(join, Seq(name, age))

    assert(inferred.children(0).internalSchema.size == 2)
    assert(inferred.children(1).internalSchema.size == 4)
  }

//  val projectList = Seq(name)
//  val condition = GreaterThan(age, Literal(27))

}
