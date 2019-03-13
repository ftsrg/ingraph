package ingraph.compiler.sql.driver

import java.io.{File, PrintWriter}

import ingraph.compiler.sql.SqlQueries.getQueriesFromFolder
import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.LdbcParameterizedQueriesTest.expectedToSucceed
import ingraph.compiler.sql.{CompilerOptions, GTopExtension, SqlCompiler}
import org.cytosm.common.gtop.GTop
import org.scalatest.FunSuite

class LdbcParameterizedQueriesTest extends FunSuite {
  val ldbcParameterizedQueries: Seq[(String, String)] =
    getQueriesFromFolder("ldbc_snb_implementations/cypher/queries", "cypher")

  val outputPath = "ldbc_snb_implementations/postgres/queries-generated"

  ldbcParameterizedQueries.foreach { case (name, cypherQueryString) =>
    test(name) {
      try {
        val sqlCompiler = SqlCompiler(cypherQueryString,
          CompilerOptions(gTop = Some(LdbcTestResources.gTop), inlineParameters = false, trimSql = true))

        val sqlQuery = sqlCompiler.sql

        if (LdbcTest.expectedToSucceed.contains(name)) {
          withResources(new PrintWriter(new File(outputPath, name + ".sql").getPath)) {
            _.write(sqlQuery)
          }
        }
      } catch {
        case throwable: Throwable if !expectedToSucceed.contains(name) => cancel(name + " has failed. Only warning!", throwable)
      }
    }
  }
}

object LdbcParameterizedQueriesTest {
  val expectedToSucceed: Set[String] = Set(
    "bi-2",
    "bi-3",
    "bi-4",
    "bi-5",
    "bi-6",
    "bi-7",
    "bi-8",
    "bi-9",
    "bi-12",
    "bi-14",
    "bi-15",
    "bi-17",
    "bi-18",
    "bi-19",
    "bi-20",
    "bi-23",
    "bi-24",
    "interactive-complex-1",
    "interactive-complex-2",
    "interactive-complex-3",
    "interactive-complex-4",
    "interactive-complex-5",
    "interactive-complex-6",
    "interactive-complex-8",
    "interactive-complex-9",
    "interactive-complex-11",
    "interactive-complex-12",
    "interactive-short-1",
    "interactive-short-2",
    "interactive-short-3",
    "interactive-short-4",
    "interactive-short-5",
    "interactive-short-6",
    "interactive-short-7",
    "interactive-update-2",
    "interactive-update-3",
    "interactive-update-5",
    "interactive-update-8",
    ""
  )
}
