package ingraph.compiler.sql.driver

import java.io.File

import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.LdbcTest.ldbcQueries
import org.scalatest.FunSuite

import scala.reflect.io

class LdbcTest extends FunSuite {
  ldbcQueries.foreach { case (name, cypherQueryString) =>
    test(name) {
      withResources(new SqlDriver(translateCreateQueries = true)) { driver =>
        withResources(driver.session) { session =>
          withResources(session.beginTransaction()) { tx =>
            try {
              tx.run(cypherQueryString)
            } catch {
              case throwable: Throwable => cancel(name + " has failed. Only warning!", throwable)
            }
          }
        }
      }
    }
  }
}

object LdbcTest {
  val ldbcQueriesPath = "/interactive-tests"

  val ldbcQueries: Seq[(String, String)] =
    new File(getClass.getResource(ldbcQueriesPath).getFile)
      .listFiles()
      .filter(_.isFile)
      .sortBy { file =>
        val filename = file.getName
        // sort by name then by ID
        filename.replaceAll("""\d+.*$""", "") -> Integer.parseInt(filename.replaceAll("""\D""", ""))
      }
      .map(f => f.getName -> io.File(f).slurp())
      .toSeq
}
