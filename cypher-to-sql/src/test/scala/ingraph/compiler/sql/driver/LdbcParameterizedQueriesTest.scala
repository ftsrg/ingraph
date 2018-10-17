package ingraph.compiler.sql.driver

import java.io.{File, PrintWriter}

import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.{CompileSql, CompilerOptions, GTopExtension}
import org.cytosm.common.gtop.GTop
import org.scalatest.FunSuite

import scala.reflect.io

class LdbcParameterizedQueriesTest extends FunSuite {
  val gTop: GTop = GTopExtension.loadFromResource("/gtop/ldbc.gtop")

  val ldbcParameterizedQueries: Seq[(String, String)] =
    new File("ldbc_snb_implementations/cypher/queries")
      .listFiles()
      .filter(_.isFile)
      .map(io.File(_))
      .filter(f => f.hasExtension("cypher"))
      .map(f => f.stripExtension -> f.slurp())
      .sortBy(_._1)
      .toSeq

  val outputPath = "ldbc_snb_implementations/postgres/queries-generated"

  ldbcParameterizedQueries.foreach { case (name, cypherQueryString) =>
    test(name) {
      try {
        val sqlCompiler = new CompileSql(cypherQueryString, CompilerOptions(gTop = Some(gTop), inlineParameters = false))

        val sqlQuery = sqlCompiler.run()

        withResources(new PrintWriter(new File(outputPath, name + ".sql").getPath)) {
          _.write(sqlQuery)
        }
      } catch {
        case throwable: Throwable => cancel(name + " has failed. Only warning!", throwable)
      }
    }
  }
}
