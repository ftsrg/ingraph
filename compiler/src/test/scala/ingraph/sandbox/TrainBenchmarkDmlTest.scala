package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import org.scalatest.FunSuite

class TrainBenchmarkDmlTest extends FunSuite {

  test("ConnectedSegment Inject") {
    parse(
      """MATCH
        |  (sensor {id: $sensor}),
        |  (segment1 {id: $segment1})-[c:connectsTo]->(segment3 {id: $segment3})
        |CREATE
        |  (segment2:Segment {length: $length})-[:monitoredBy]->(sensor),
        |  (segment1)-[:connectsTo]->(segment2),
        |  (segment2)-[:connectsTo]->(segment3)
        |DELETE c
        |""".stripMargin
    )
  }

  test("ConnectedSegment Repair") {
    parse(
      """MATCH (segment2 {id: $segment2})
        |DETACH DELETE segment2
        |""".stripMargin
    )
  }

  test("PosLength Inject") {
    parse(
      """MATCH (segment {id: $segment})
        |SET segment.length = 0
        |""".stripMargin
    )
  }

  test("PosLength Repair") {
    parse(
      """MATCH (segment {id: $segment})
        |SET segment.length = -segment.length + 1
        |""".stripMargin
    )
  }

  test("RouteSensor Inject") {
    parse(
      """MATCH (route {id: $route})-[g:requires]->(sensor {id: $sensor})
        |DELETE g
        |""".stripMargin
    )
  }

  test("RouteSensor Repair") {
    parse(
      """MATCH (route {id: $route}), (sensor {id: $sensor})
        |CREATE (route)-[:requires]->(sensor)
        |""".stripMargin
    )
  }

  test("SemaphoreNeighbor Inject") {
    parse(
      """MATCH (route {id: $route})-[e:entry]->(semaphore {id: $semaphore})
        |DELETE e
        |""".stripMargin
    )
  }

  test("SemaphoreNeighbor Repair") {
    parse(
      """MATCH (route2 {id: $route2}), (semaphore {id: $semaphore})
        |CREATE (route2)-[:entry]->(semaphore)
        |""".stripMargin
    )
  }

  test("SwitchMonitored Inject") {
    parse(
      """MATCH (sw {id: $sw})-[m:monitoredBy]->(:Sensor)
        |DELETE m
        |""".stripMargin
    )
  }

  test("SwitchMonitored Repair") {
    parse(
      """MATCH (sw {id: $sw})
        |CREATE (sw)-[:monitoredBy]->(:Sensor)
        |""".stripMargin
    )
  }

  test("SwitchSet Inject") {
    parse(
      """
        |MATCH (sw {id: $sw})
        |SET sw.currentPosition = $currentPosition
        |""".stripMargin
    )
  }

  test("SwitchSet Repair") {
    parse(
      """
        |MATCH (sw {id: $sw}), (swP {id: $swP})
        |SET sw.currentPosition = swP.position
      """.stripMargin
    )
  }

  def parse(q: String): Unit = {
    val cypher = CypherParser.parseString("MATCH (n) SET n:Actor:Director")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }
}
