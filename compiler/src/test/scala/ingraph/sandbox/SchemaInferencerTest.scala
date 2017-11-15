package ingraph.sandbox

import ingraph.compiler.JPlanParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan._
import ingraph.model.expr._
import ingraph.model.{jplan, qplan}
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
import org.scalatest.FunSuite

class SchemaInferencerTest extends FunSuite {

  test("infer schema #1") {
    val v = VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val qp = de
    val ip = QPlanToJPlan.transform(qp)
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

    val ip = QPlanToJPlan.transform(qp)
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

    val gv = jplan.GetVertices(p1)
    val ge = jplan.GetEdges(p1, p2, e, Out)
    val join = jplan.Join(gv, ge)

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

    val ip = QPlanToJPlan.transform(qp)
    val ep = SchemaInferencer.transform(ip)

    assert(ep.internalSchema.size == 2)
    assert(ep.children(0).internalSchema.size == 2)
  }

  test("infer schema for PosLength from Cypher without filtering") {
    val ep = JPlanParser.parse(
      """MATCH (segment:Segment)
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    ep match {
      case
        Production(_, _,
          Projection(_, _,
            AllDifferent(_, _, v: GetVertices)
          )
        ) =>
        assert(v.internalSchema.map(_.name) == Seq("segment", "segment.length"))
    }
  }

  test("infer schema for PosLength from Cypher") {
    val ep = JPlanParser.parse(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |RETURN DISTINCT segment, segment.length AS length
        |""".stripMargin)
    ep match {
      case Production(_, _, DuplicateElimination(_, _,
      Projection(_, _, Selection(_, _, AllDifferent(_, _, v: GetVertices))))) =>
        assert(v.internalSchema.map(_.name) == Seq("segment", "segment.length"))
    }
  }

}
