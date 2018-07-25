package ingraph.util.tex

import ingraph.compiler.CypherToGPlan
import ingraph.compiler.cypher2gplan.{CypherParser, GPlanBeautifier, GPlanResolver}
import ingraph.compiler.plantransformers.{GPlanToNPlan, NPlanToFPlan}
import ingraph.model.gplan.GNode
import ingraph.model.nplan.NNode
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.scalatest.FunSuite

class TexTest extends FunSuite {
  def totex[T <: LogicalPlan](plan: LogicalPlan, texFile: String, comment: String, tc: TexConverter[T]): Unit = {
    val ingraphDir = "./visualization"
    val tex = tc.toTex(plan)
    tc.toFile(List(tex), ingraphDir, texFile, comment)
    tc.compile(texFile)
  }

  /*test("query-1") {
    val vls = VertexLabelSet(Set("Person"), NonEmpty)

    val n = VertexAttribute("n", vls)
    val name = PropertyAttribute("name", n)
    val age = PropertyAttribute("age", n)

    val projectList = Seq(ReturnItem(name))
    val condition = GreaterThan(age, Literal(27))

    val qp = gplan.UnresolvedProjection(
      projectList,
      gplan.Selection(
        condition,
        gplan.GetVertices(n)
      )
    )
    val rqp = GPlanResolver.resolveGPlan(qp)
    val jp = GPlanToNPlan.transform(rqp)
    val fp = NPlanToFPlan.transform(jp)

    assert(fp.flatSchema.size == 1)
    assert(fp.children(0).flatSchema.size == 3)
    assert(fp.children(0).children(0).flatSchema.size == 3)
    println(rqp)
    totex(rqp, "rqp.tex", "")
    //totex(jp, "jp.tex")
    //totex(fp, "fp.tex")
  }*/

  def cypherTest(prefix: String, query: String): Unit = {
    println(s"${prefix} query: ${query}")
    val cypher = CypherParser.parseString(query)
    val qp = CypherToGPlan.compileToGPlan(cypher)

    val rqp = GPlanBeautifier.beautifyResolvedGPlan(GPlanResolver.resolveGPlan(qp))
    val jp = GPlanToNPlan.transform(rqp)
    val fp = NPlanToFPlan.transform(jp)

    totex[GNode](rqp, s"${prefix}g.tex", query, new GTexConverter)
    totex[NNode](jp, s"${prefix}n.tex", query, new NTexConverter)
    totex(fp, s"${prefix}fp.tex", query, new FTexConverter)
  }

  test("cypher-1") {
    cypherTest("cypher-1-",
      "MATCH (n:Person) \n" +
      "WHERE n.age > 28 \n" +
      "RETURN DISTINCT n.name")
  }

  test("cypher-2") {
    cypherTest("cypher-2-",
      "MATCH (r1:Operator:Test)<-[:CHILD {side: 'left'}]-(j1:Join)-[:CHILD {side: 'right'}]->(r2:Operator) \n" +
      "WHERE j1.schema IS NULL AND r1.schema IS NOT NULL AND r2.schema IS NOT NULL \n" +
      "RETURN r1,r2")
  }

  test("cypher-3") {
    cypherTest("cypher-3-",
      "MATCH (parent:Operator)-[pe:CHILD]->(proj:Projection)-[ce:CHILD]->(child:Operator) \n" +
      "WHERE child.schema = proj.schema \n" +
      "CREATE (parent)-[:CHILD]->(child) \n" +
      "RETURN parent, child")
  }

  test("cypher-4") {
    cypherTest("cypher-4-",
      "MATCH (parent:Operator)-[pe:CHILD]-(proj:Projection)-[ce:CHILD]->(child:Operator) \n" +
      "WHERE child.schema = ['a', 'b'] or child.schema = ['c'] \n" +
        "and not 'a' in child.schema \n" +
      "RETURN parent, child")
  }

  test("cypher-5") {
    cypherTest("cypher-5-", "MATCH (parent)-[edge]-(proj) \n" +
      "WHERE not (proj)-[]->(parent) \n" +
      "and proj.a > 5 and proj.b < proj.c \n" +
      "RETURN parent")
  }

  test("cypher-6") {
    cypherTest("cypher-6-",
      "MATCH (david { name: 'David' })--(otherPerson)-->() \n" +
      "WITH david, otherPerson, count(*) AS foaf \n" +
      "WHERE foaf > 1 \n" +
      "RETURN otherPerson.name")
  }

  test("cypher-7") {
    cypherTest("cypher-7-",
      "MATCH (n) \n" +
      "WITH n \n" +
      "ORDER BY n.name DESC LIMIT 3 \n" +
      "RETURN collect(n.name)")
  }

  test("cypher-8") {
    cypherTest("cypher-8-",
      "MATCH (n:Actor) \n" +
      "RETURN n.name AS name \n" +
      "UNION ALL MATCH (n:Movie) \n" +
      "RETURN n.title AS name")
  }

  test("cypher-9") {
    cypherTest("cypher-9-",
      "MATCH (user:User {login:'heller.perry'})-[:KNOWS*2..3]->(foaf)\n " +
      "WHERE NOT((user)-[:KNOWS]->(foaf))\n " +
      "RETURN user, foaf")
  }
}
