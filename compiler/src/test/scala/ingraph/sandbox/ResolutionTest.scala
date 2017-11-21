package ingraph.sandbox

import java.net.URI

import org.apache.spark.sql.catalyst.analysis.{Analyzer, FunctionRegistry}
import org.apache.spark.sql.catalyst.catalog.{CatalogDatabase, InMemoryCatalog, SessionCatalog}
import org.apache.spark.sql.catalyst.expressions.AttributeReference
import org.apache.spark.sql.catalyst.plans.logical.LocalRelation
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.types.IntegerType
import org.scalatest.FunSuite

class ResolutionTest extends FunSuite {

  test("Observe what happens during query resolution") {
    val originalSql = "SELECT y AS z FROM (SELECT x AS y FROM t)"

    val conf = new SQLConf
    val parser = new SparkSqlParser(conf)
    val plan = parser.parsePlan(originalSql)

    println(plan)
//    'Project ['y AS z#1]
//    +- 'Project ['x AS y#0]
//        +- 'UnresolvedRelation `t`

    val catalog = new SessionCatalog(new InMemoryCatalog, FunctionRegistry.builtin, conf)
    catalog.createDatabase(CatalogDatabase("default", "", new URI("loc"), Map.empty), ignoreIfExists = false)
    val attributes: Seq[AttributeReference] = Seq(AttributeReference("x", IntegerType)())
    catalog.createTempView("t", LocalRelation(attributes), overrideIfExists = true)

    val analyzer = new Analyzer(catalog, conf)
    val resolvedPlan = analyzer.execute(plan)

    println(resolvedPlan)
//    Project [y#0 AS z#1]
//    +- Project [x#2 AS y#0]
//       +- SubqueryAlias t
//          +- LocalRelation <empty>, [x#2]
   }

}
