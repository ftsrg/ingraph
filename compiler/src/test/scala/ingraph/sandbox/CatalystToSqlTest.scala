package ingraph.sandbox

import java.net.URI
import java.util.concurrent.atomic.AtomicLong

import ingraph.sandbox.QPlanToSqlPlan.{getPropertyAttributeName, transform}
import org.apache.spark.sql.catalyst.{InternalRow, SQLBuilder}
import org.apache.spark.sql.catalyst.analysis.{Analyzer, FunctionRegistry, UnresolvedAlias, UnresolvedAttribute, UnresolvedRelation}
import org.apache.spark.sql.catalyst.catalog.{CatalogDatabase, InMemoryCatalog, SessionCatalog}
import org.apache.spark.sql.catalyst.expressions.AttributeReference
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.types.{IntegerType, StringType}
import org.scalatest.FunSuite

import scala.collection.mutable

class CatalystToSqlTest extends FunSuite {

  test("Convert SQL query back and forth") {
    val originalSql = "SELECT name FROM Person WHERE age > 25"

    val conf = new SQLConf
    val parser = new SparkSqlParser(conf)
    val plan = parser.parsePlan(originalSql)

    println(plan)

    val catalog = new SessionCatalog(new InMemoryCatalog, FunctionRegistry.builtin, conf)
    catalog.createDatabase(CatalogDatabase("default", "", new URI("loc"), Map.empty), ignoreIfExists = false)
    val attributes: Seq[AttributeReference] = Seq(
      AttributeReference("name", StringType)(),
      AttributeReference("age", IntegerType)()
    )
    catalog.createTempView("Person", LocalRelation(attributes, Seq(InternalRow("John", 25))), overrideIfExists = true)

    val analyzer = new Analyzer(catalog, conf)
    val resolvedPlan = analyzer.execute(plan)

    val sqlBuilder = new SQLBuilder(resolvedPlan, new AtomicLong(1L), new AtomicLong(1L), mutable.Map.empty)
    println(sqlBuilder.toSQL)



    //    val generatedSql = CatalystToSql.transform(plan)
    //    println(generatedSql)
  }

}

object CatalystToSql {
  def transform(plan: LogicalPlan): String = {
    plan match {
      case Project(projectList, child) => "SELECT " + projectList.map(_.toString()).mkString(", ") + "\n" + transform(child)
      case Filter(condition, child) => "WHERE " + condition.sql + "\n" + transform(child)
      case UnresolvedRelation(tableIdentifier) => "FROM " + tableIdentifier
    }
  }
}
