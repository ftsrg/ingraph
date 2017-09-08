package ingraph.sandbox

import ingraph.compiler.IPlanParser
import ingraph.compiler.qplan2iplan.{QPlanToIPlan, SchemaInferencer}
import ingraph.model.eplan._
import ingraph.model.expr._
import ingraph.model.qplan
import ingraph.model.iplan
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
import org.scalatest.FunSuite

class SchemaInferencerTest extends FunSuite {

  test("infer schema #1") {
    val v = VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val qp = de
    val ip = QPlanToIPlan.transform(qp)
    val ep = SchemaInferencer.transform(ip)

    assert(ep.internalSchema.size == 1)
    assert(ep.children(0).internalSchema.size == 1)
  }

  test("infer schema #2") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)

    val n = VertexAttribute("n", vls)
    val name = PropertyAttribute("name", n)
    val age = PropertyAttribute("age", n)

    val projectList = Seq(name)
    val condition = GreaterThan(age, Literal(27))

    val qp = qplan.Projection(
      projectList,
      qplan.Selection(
        condition,
        qplan.GetVertices(n)
      )
    )

    val ip = QPlanToIPlan.transform(qp)
    val ep = SchemaInferencer.transform(ip)

    assert(ep.internalSchema.size == 1)
    assert(ep.children(0).internalSchema.size == 3)
    assert(ep.children(0).children(0).internalSchema.size == 3)
  }

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

    val ep = SchemaInferencer.transform(join, Seq(name, age))

    assert(ep.asInstanceOf[Join].leftMask == Seq(0))
    assert(ep.asInstanceOf[Join].rightMask == Seq(0))
    assert(ep.children(0).internalSchema.size == 2)
    assert(ep.children(1).internalSchema.size == 4)
  }

  test("infer schema for PosLength") {
    val vls = VertexLabelSet(Set("Segment"), NonEmpty)

    val segment = VertexAttribute("segment", vls)
    val length = PropertyAttribute("length", segment)

    val projectList = Seq(segment, length)

    val qp = qplan.Projection(
      projectList,
      qplan.GetVertices(segment)
    )

    val ip = QPlanToIPlan.transform(qp)
    val ep = SchemaInferencer.transform(ip)

    assert(ep.internalSchema.size == 2)
    assert(ep.children(0).internalSchema.size == 2)
  }

  test("infer schema for PosLength from Cypher") {
    val ep = IPlanParser.parse("MATCH (segment:Segment) WHERE segment.length <= 0 RETURN DISTINCT segment, segment.length AS length")
    ep match {
      case Production(_, _, DuplicateElimination(_, _,
        Projection(_, _, Selection(_, _, AllDifferent(_, _, v: GetVertices))))) =>
          assert(v.internalSchema.map(_.name) == Seq("segment", "segment.length"))
    }
  }

}
