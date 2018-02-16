package ingraph.sandbox

import ingraph.compiler.FPlanParser
import ingraph.compiler.cypher2qplan.QPlanResolver
import ingraph.compiler.qplan2jplan.{JPlanToFPlan, QPlanToJPlan}
import ingraph.model.expr._
import ingraph.model._
import ingraph.model.{jplan, fplan, qplan}
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
import org.scalatest.FunSuite

class JPlanToFPlanTest extends FunSuite {

  test("infer schema #1") {
    val v = VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val qp = de
    val jp = QPlanToJPlan.transform(qp)
    val fp = JPlanToFPlan.transform(jp)

    assert(fp.internalSchema.size == 1)
    assert(fp.children(0).internalSchema.size == 1)
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

    assert(fp.internalSchema.size == 1)
    assert(fp.children(0).internalSchema.size == 3)
    assert(fp.children(0).children(0).internalSchema.size == 3)
  }

  test("infer schema #3") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)

    val n = VertexAttribute("n", vls)
    val name = PropertyAttribute("name", n)
    val projectList = Seq(ReturnItem(name))

    val qp = qplan.UnresolvedProjection(
      projectList,
      qplan.GetVertices(n)
    )
    val rqp = QPlanResolver.resolveQPlan(qp)
    val jp = QPlanToJPlan.transform(rqp)
    val fp = JPlanToFPlan.transform(jp)

    assert(fp.internalSchema.size == 1)
    assert(fp.children(0).internalSchema.size == 2)
  }

  ignore("infer schema for joins") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)
    val el = EdgeLabelSet(Set("KNOWS"), NonEmpty)

    val p1 = VertexAttribute("p1", vls)
    val name = ReturnItem(PropertyAttribute("name", p1))
    val projectList = Seq(name)

    val p2 = VertexAttribute("p2", vls)
    val age = PropertyAttribute("age", p2)
    val condition = GreaterThan(age, Literal(27))

    val e = EdgeAttribute("e", el)

    val qp = jplan.Projection(
      projectList,
      jplan.Selection(
        condition,
        jplan.Join(
          jplan.GetVertices(p1),
          jplan.GetEdges(p1, p2, e, false)
        )
      )
    )
    val fp = JPlanToFPlan.transform(qp)
    val join = fp.children(0).children(0).asInstanceOf[fplan.Join]

    assert(join.leftMask == Seq(0))
    assert(join.rightMask == Seq(0))
    assert(join.children(0).internalSchema.size == 2)
    assert(join.children(1).internalSchema.size == 4)
  }

  test("infer schema for Projection") {
    val vls = VertexLabelSet(Set("Segment"), NonEmpty)

    val segment = VertexAttribute("segment", vls)
    val length = PropertyAttribute("length", segment)

    val projectList = Seq(ReturnItem(segment), ReturnItem(length))

    val qp = qplan.UnresolvedProjection(
      projectList,
      qplan.GetVertices(segment)
    )

    val rqp = QPlanResolver.resolveQPlan(qp)
    val jp = QPlanToJPlan.transform(rqp)
    val fp = JPlanToFPlan.transform(jp)

    assert(fp.internalSchema.size == 2)
    assert(fp.children(0).internalSchema.size == 2)
  }

  test("infer schema for PosLength from Cypher without filtering") {
    val fp = FPlanParser.parse(
      """MATCH (segment:Segment)
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    import fplan._
    fp match {
      case
        Production(_, _,
          Projection(_, _,
           AllDifferent(_, _, v: GetVertices)
          )) =>
        assert(v.internalSchema.map(_.name) == Seq("segment", "length"))
    }
  }

  test("infer schema for PosLength from Cypher with filtering") {
    val fp = FPlanParser.parse(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    import fplan._
    fp match {
      case
        Production(_, _,
          Projection(_, _,
            Selection(_, _,
              AllDifferent(_, _, v: GetVertices
            )))) =>
        assert(v.internalSchema.map(_.name) == Seq("segment", "length"))
    }
  }

  test("infer schema for RouteSensor from Cypher") {
    val fp = FPlanParser.parse(
      """MATCH (route:Route)
        |  -[:follows]->(swP:SwitchPosition)
        |  -[:target]->(sw:Switch)
        |  -[:monitoredBy]->(sensor:Sensor)
        |WHERE NOT (route)-[:requires]->(sensor)
        |RETURN route, sensor, swP, sw
        |""".stripMargin)

    val antijoin = fp.children(0).children(0).asInstanceOf[fplan.AntiJoin]
    assert(antijoin.leftMask  == List(0, 6))
    assert(antijoin.rightMask == List(0, 2))
  }

  test("infer schema for RouteSensorPositive from Cypher") {
    val fp = FPlanParser.parse(
      """MATCH (route:Route)
        |  -[:follows]->(swP:SwitchPosition)
        |  -[:target]->(sw:Switch)
        |  -[:monitoredBy]->(sensor:Sensor)
        |RETURN route, sensor, swP, sw
        |""".stripMargin)
    assert(fp.children(0).children(0).children(0).internalSchema.size == 7)
    assert(fp.children(0).children(0).children(0).children(0).internalSchema.size == 5)
    assert(fp.children(0).children(0).children(0).children(1).internalSchema.size == 3)
  }

  test("infer schema for SwitchMonitored from Cypher") {
    val fp = FPlanParser.parse(
      """MATCH (sw:Switch)
        |WHERE NOT (sw)-[:monitoredBy]->(:Sensor)
        |RETURN sw
        |""".stripMargin)
    // TODO: assert
  }

  ignore("infer schema for simple path") {
    val fp = FPlanParser.parse(
      """MATCH (a:A)-[:R1]->(b:B)-[:R2]->(c:C)
        |RETURN a, b, c
        |""".stripMargin)
    // TODO: assert
  }

  test("infer schema for SwitchMonitored") {
    val fp = FPlanParser.parse(
      """MATCH (sw:Switch)
        |WHERE NOT (sw)-[:monitoredBy]->(:Sensor)
        |RETURN sw
        |""".stripMargin)
    // TODO: assert
  }

  test("infer schema for ConnectedSegments") {
    val fp = FPlanParser.parse(
      """MATCH
        |  (sensor:Sensor)<-[mb1:monitoredBy]-(segment1:Segment),
        |  (segment1:Segment)-[ct1:connectsTo]->
        |  (segment2:Segment)-[ct2:connectsTo]->
        |  (segment3:Segment)-[ct3:connectsTo]->
        |  (segment4:Segment)-[ct4:connectsTo]->
        |  (segment5:Segment)-[ct5:connectsTo]->(segment6:Segment),
        |  (segment2:Segment)-[mb2:monitoredBy]->(sensor:Sensor),
        |  (segment3:Segment)-[mb3:monitoredBy]->(sensor:Sensor),
        |  (segment4:Segment)-[mb4:monitoredBy]->(sensor:Sensor),
        |  (segment5:Segment)-[mb5:monitoredBy]->(sensor:Sensor),
        |  (segment6:Segment)-[mb6:monitoredBy]->(sensor:Sensor)
        |RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
        |""".stripMargin)
    // TODO: assert
//    println(fp)
//    println(fp.children(0).children(0).children(0).internalSchema)
//    println(fp.children(0).children(0).children(0).asInstanceOf[Join].leftMask)
//    println(fp.children(0).children(0).children(0).asInstanceOf[Join].rightMask)
  }

  ignore("infer schema for Cartesian product") {
    val fp = FPlanParser.parse(
      """MATCH (n), (m)
        |RETURN n.value, m.value
      """.stripMargin
    )
  }

}
