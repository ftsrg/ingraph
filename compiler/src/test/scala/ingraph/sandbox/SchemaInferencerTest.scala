package ingraph.sandbox

import ingraph.compiler.FPlanParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.expr._
import ingraph.model.fplan._
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

    val projectList = Seq(ReturnItem(name))
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

    val projectList = Seq(ReturnItem(segment), ReturnItem(length))

    val qp = qplan.Projection(
      projectList,
      qplan.GetVertices(segment)
    )

    val ip = QPlanToJPlan.transform(qp)
    val ep = SchemaInferencer.transform(ip)

    assert(ep.internalSchema.size == 2)
    assert(ep.children(0).internalSchema.size == 2)
  }

  ignore("infer schema for PosLength from Cypher without filtering") {
    val ep = FPlanParser.parse(
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

  ignore("infer schema for PosLength from Cypher with filtering") {
    val ep = FPlanParser.parse(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    ep match {
      case
        Production(_, _,
          Projection(_, _,
            Selection(_, _,
              AllDifferent(_, _, v: GetVertices)))) =>
        assert(v.internalSchema.map(_.name) == Seq("segment", "segment.length"))
    }
  }

  test("infer schema for RouteSensor from Cypher") {
    val ep = FPlanParser.parse(
      """MATCH (route:Route)
        |  -[:follows]->(swP:SwitchPosition)
        |  -[:target]->(sw:Switch)
        |  -[:monitoredBy]->(sensor:Sensor)
        |WHERE NOT (route)-[:requires]->(sensor)
        |RETURN route, sensor, swP, sw
        |""".stripMargin)
    println(ep)

    val antijoin = ep.children(0).children(0).asInstanceOf[AntiJoin]
    assert(antijoin.leftMask == List(0, 6))
    assert(antijoin.rightMask == List(0, 2))
  }

  test("infer schema for RouteSensorPositive from Cypher") {
    val ep = FPlanParser.parse(
      """MATCH (route:Route)
        |  -[:follows]->(swP:SwitchPosition)
        |  -[:target]->(sw:Switch)
        |  -[:monitoredBy]->(sensor:Sensor)
        |RETURN route, sensor, swP, sw
        |""".stripMargin)
    println(ep)
    assert(ep.children(0).children(0).children(0).internalSchema.size == 7)
    assert(ep.children(0).children(0).children(0).children(0).internalSchema.size == 5)
    assert(ep.children(0).children(0).children(0).children(1).internalSchema.size == 3)
  }

  test("infer schema for SwitchMonitored from Cypher") {
    val ep = FPlanParser.parse(
      """MATCH (sw:Switch)
        |WHERE NOT (sw)-[:monitoredBy]->(:Sensor)
        |RETURN sw
        |""".stripMargin)
  }

  test("infer schema for simple path") {
    val ep = FPlanParser.parse(
      """MATCH (a:A)-[:R1]->(b:B)-[:R2]->(c:C)
        |RETURN a, b, c
        |""".stripMargin)
    //    println(ep.inode)
    //    println(ep.children(0).internalSchema)
    //    println(ep.children(0).children(0).internalSchema)
    //    println(ep.children(0).children(0).children(0).internalSchema)
    //    println(ep.children(0).children(0).children(0).children(0).internalSchema)
    //    println(ep.children(0).children(0).children(0).children(1).internalSchema)
  }

  test("infer schema for SwitchMonitored") {
    val ep = FPlanParser.parse(
      """MATCH (sw:Switch)
        |WHERE NOT (sw)-[:monitoredBy]->(:Sensor)
        |RETURN sw
        |""".stripMargin)
  }

  test("infer schema for ConnectedSegments") {
    val ep = FPlanParser.parse(
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
    println(ep)
    println(ep.children(0).children(0).children(0).internalSchema)
    println(ep.children(0).children(0).children(0).asInstanceOf[Join].leftMask)
    println(ep.children(0).children(0).children(0).asInstanceOf[Join].rightMask)
  }

}
