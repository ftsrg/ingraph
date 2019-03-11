package ingraph.compiler.sql.driver

import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.LdbcTest.{expectedToSucceed, ldbcQueries, ldbcSqlQueries}
import ingraph.compiler.sql.driver.SqlDriver.ExternalDatabase
import ingraph.compiler.sql.{CompilerOptions, GTopExtension, SqlQueries}
import org.cytosm.common.gtop.GTop
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class LdbcTest extends FunSuite {
  test("SQL") {
    withResources(
      new SqlDriver(
        translateCreateQueries = true,
        gTop = Some(LdbcTestResources.gTop),
        database = ExternalDatabase(LdbcTestResources.url),
        initializeDb = false)) {
      driver =>
        withResources(driver.session) { session =>
          withResources(session.beginTransaction()) { tx =>
            withResources(tx.rawSqlConnection.createStatement()) { statement =>
              SqlDriver.dump(statement, "SELECT * FROM person LIMIT 20")

              withResources(statement.executeQuery("SELECT count(*) FROM person")) { resultSet =>
                assert(resultSet.next())
                val count = resultSet.getInt(1)
                assert(!resultSet.next())

                assertResult(46)(count)
              }
            }
          }
        }
    }
  }

  ldbcQueries.foreach { case (name, cypherQueryString) =>
    test(name) {
      withResources(
        new SqlDriver(
          translateCreateQueries = true,
          gTop = Some(LdbcTestResources.gTop),
          database = ExternalDatabase(LdbcTestResources.url),
          initializeDb = false,
          initialCompilerOptions = CompilerOptions(trimSql = true))) {
        driver =>
          withResources(driver.session) { session =>
            withResources(session.beginTransaction()) { tx =>
              val limit = 20
              val referenceSqlQuery = ldbcSqlQueries.find(_._1 == name).map(_._2)
              val stringBuilder = StringBuilder.newBuilder
              val printlnFunc: String => Unit = str => {
                stringBuilder.append(str)
                stringBuilder.append('\n')
              }

              val rowCount = referenceSqlQuery.map(query =>
                withResources(tx.rawSqlConnection.createStatement()) { statement =>
                  SqlDriver.dump(statement, query, Some(limit), printlnFunc)
                })

              try {
                println()

                val limit = 20
                val result = tx.run(cypherQueryString).list().asScala

                for (record <- result.take(limit)) {
                  for ((key, value) <- record.asMap().asScala) {
                    println(s"""$key = $value""")
                  }
                  println("---")
                }

                println(s"Totally: ${result.size} rows (only first $limit)")
                println("----------------------------------")

                if (referenceSqlQuery.isDefined) {
                  println("vvvvvv REFERENCE RESULT vvvvvv")
                  print(stringBuilder)

                  assertResult(rowCount.get)(result.size)
                }

              } catch {
                case throwable: Throwable if !expectedToSucceed.contains(name) => cancel(name + " has failed. Only warning!", throwable)
              }
            }
          }
      }
    }
  }
}

object LdbcTest {
  val ldbcQueriesPath = "/interactive-tests"

  val expectedToSucceed: Set[String] = Set(
    "SQL",
    "simple-test",
    "cities-with-residents",
    "knows-undirected",
    "tag-tagclass",
    "transitive-join",
    "transitive-join_reversed",
    "getedges-post",
    "getedges-comment",
    "getedges-message",
    "getvertices-post",
    "getvertices-comment",
    "getvertices-message",
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
    "interactive-short-3",
    "interactive-short-4",
    "interactive-short-5",
    "interactive-short-6",
    "interactive-short-7",
    ""
  )

  val ldbcQueries: Seq[(String, String)] = getQueries("cypher")
  val ldbcSqlQueries: Seq[(String, String)] = getQueries("sql")

  private def getQueries(extension: String): Seq[(String, String)] = {
    SqlQueries.getQueriesFromResourceFolder(ldbcQueriesPath, extension)
  }
}
