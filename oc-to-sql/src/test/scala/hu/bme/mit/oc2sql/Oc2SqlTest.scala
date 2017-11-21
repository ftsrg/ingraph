package hu.bme.mit.oc2sql

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import org.scalatest.FunSuite

class Oc2SqlTest extends FunSuite {

  test("PosLength") {
    val cypher = CypherParser.parseString(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

  test("RouteSensor") {
    val cypher = CypherParser.parseString(
      """MATCH (route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)-[:monitoredBy]->(sensor:Sensor)
        |WHERE NOT (route)-[g:requires]->(sensor)
        |RETURN route, sensor, swP, sw
        |""".stripMargin)
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

  test("SwitchSensor") {
    val cypher = CypherParser.parseString(
      """MATCH (sw:Switch)
        |WHERE NOT (sw)-[:monitoredBy]->(:Sensor)
        |RETURN sw
        |""".stripMargin)
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

  test("SwitchSet") {
    val cypher = CypherParser.parseString(
      """MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
        |WHERE semaphore.signal = "GO"
        |  AND sw.currentPosition <> swP.position
        |RETURN semaphore, route, swP, sw, sw.currentPosition AS currentPosition, swP.position AS position
        |""".stripMargin)
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

}
