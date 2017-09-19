package ingraph.sandbox

import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf
import org.scalatest.FunSuite

class SparkSqlSandbox extends FunSuite {

  test("infer schema #1") {
    val conf = new SQLConf
    val parser = new SparkSqlParser(conf)

    println(parser.parsePlan("SELECT 1 UNION ALL SELECT 1"))
    println(parser.parsePlan("SELECT 1 UNION DISTINCT SELECT 1"))
  }

}
