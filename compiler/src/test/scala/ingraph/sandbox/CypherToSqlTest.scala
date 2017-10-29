package ingraph.sandbox

import org.apache.spark.sql.catalyst.analysis.UnresolvedRelation
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf
import org.scalatest.FunSuite

class CypherToSqlTest extends FunSuite {

  test("my") {
    val originalSql = "SELECT name FROM Person WHERE age > 25"

    val conf = new SQLConf
    val parser = new SparkSqlParser(conf)
    val plan = parser.parsePlan(originalSql)
    println(plan)

    val generatedSql = CatalystToSql.transform(plan)
    println(generatedSql)
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
