package ingraph.sandbox

import java.net.URI

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.compiler.qplan2iplan.QPlanToSqlPlan
import org.apache.spark.sql.catalyst.analysis.{Analyzer, FunctionRegistry, _}
import org.apache.spark.sql.catalyst.catalog.{CatalogDatabase, InMemoryCatalog, SessionCatalog}
import org.apache.spark.sql.catalyst.expressions.{AttributeReference, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.execution._
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.types.StringType
import org.scalatest.FunSuite


class SparkSqlSandbox extends FunSuite {

  def f(eaName: String, nameParts: Seq[String]): Option[String] = {
    if (nameParts(0) == eaName) {
      Some(nameParts(1))
    } else {
      None
    }
  }

  val conf = new SQLConf().copy(SQLConf.CASE_SENSITIVE -> true)
  val parser = new SparkSqlParser(conf)

  test("infer schema #1") {
//    val plan = parser.parsePlan("SELECT 1 AS x UNION ALL SELECT 2 AS x")
//    println(plan)
    val cypher = CypherParser.parseString("MATCH (p)-[e:LIVES]->(c) WHERE NOT (p)-[:KNOWS]->(c) RETURN p, c")
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

  test("infer schema #2") {
    val cypher = CypherParser.parseString("MATCH (p)-[e:LIVES]->(c) WHERE p.age > 25 RETURN p.name, e.since, c.country")
    val qplan = CypherToQPlan.build(cypher)


    //val x = QPlanResolver.resolveQPlan(qplan)
    val elementAttributes = QPlanResolver.gatherElementAttributes(qplan)
    val unresolvedAliases: Seq[NamedExpression] = QPlanResolver.gatherUnresolvedAliases(qplan)

    val catalog = new SessionCatalog(new InMemoryCatalog, FunctionRegistry.builtin, conf)
    catalog.createDatabase(CatalogDatabase("default", "", new URI("loc"), Map.empty), ignoreIfExists = false)

    for (ea <- elementAttributes) {
      val attributes: Seq[String] = unresolvedAliases.flatMap {
        case UnresolvedAttribute(nameParts) => f(ea.name, nameParts)
        case UnresolvedAlias(UnresolvedAttribute(nameParts), _) => f(ea.name, nameParts)
        case _ => None
      }.distinct
      val output: Seq[AttributeReference] = attributes.map(s => AttributeReference(s, StringType)())

      catalog.createTempView(ea.name, LocalRelation(output), overrideIfExists = true)
    }
    val analyzer = new Analyzer(catalog, conf)

    val iplan = QPlanToSqlPlan.transform(qplan)
//    val analyzer = makeAnalyzer(conf)
    val resolvedPlan = analyzer.execute(iplan)


    println(qplan)
    println(iplan)
    println(resolvedPlan)

  }

}
