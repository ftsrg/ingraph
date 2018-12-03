package ingraph.compiler.sql

import ingraph.compiler.sql.GTopTests._
import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.{SharedSqlDriver, SqlDriver, TckAdapter}
import ingraph.tck.{TckScenarioSet, TckTestRunner}
import org.cytosm.common.gtop.GTop
import org.scalatest.FunSuite

import scala.reflect.io

class GTopTests extends FunSuite with TckTestRunner with SharedSqlDriver {

  // based on
  //   https://github.com/cytosm/cytosm/blob/41e786f600724358836629fc3f70787834c85270/common/docs/movies.db
  //   https://github.com/cytosm/cytosm/blob/41e786f600724358836629fc3f70787834c85270/common/docs/movies.gtop
  val gTop: GTop = GTopExtension.loadFromResource("/gtop/movies.gtop")
  val sqlInitCommands: String = io.File(getClass.getResource("/gtop/movies.sql").getFile).slurp()

  override protected def init(): Unit = {
    super.init()

    withResources(session.beginTransaction()) { tx =>

      withResources(tx.rawSqlConnection.createStatement()) { statement =>
        statement.executeUpdate(sqlInitCommands)
      }
      tx.success()

    }
  }

  test("SQL") {
    withResources(session.beginTransaction()) { tx =>
      withResources(tx.rawSqlConnection.createStatement()) { statement =>
        withResources(statement.executeQuery("SELECT title FROM movie")) { resultSet =>

          val actualTitles = Stream.continually(resultSet.next().toOption(resultSet.getString(1))).takeWhile(_.isDefined).flatten.toSet
          val expectedTitles = Set("The Matrix", "The Devil s Advocate", "Monster")

          assertResult(expectedTitles)(actualTitles)
        }
      }
    }
  }

  override def initNewDriver(): SqlDriver =
    new SqlDriver(translateCreateQueries = true, Some(gTop), initializeDb = false)

  runTckTests(() => new TckAdapter(session, true), scenarioSet)
}

object GTopTests {
  val selectedScenarios: Set[String] = Set()
  val ignoredScenarios: Set[String] = Set()

  val scenarioSet = new TckScenarioSet(Set("GTopTests"), ignoredScenarios, selectedScenarios)
}
