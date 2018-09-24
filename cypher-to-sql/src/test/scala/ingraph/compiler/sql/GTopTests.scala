package ingraph.compiler.sql

import java.io.File

import ingraph.compiler.sql.GTopTests._
import ingraph.compiler.sql.Util._
import ingraph.compiler.sql.driver.{SharedSqlDriver, SqlDriver, TckAdapter}
import ingraph.tck.{TckScenarioSet, TckTestRunner}
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.io.SerializationInterface
import org.scalatest.FunSuite

import scala.reflect.io

class GTopTests extends FunSuite with TckTestRunner with SharedSqlDriver {

  lazy val gTop: GTop = SerializationInterface.read(new File(getClass.getResource("/gtop/movies.gtop").getFile))

  test("Load GTop and data") {
    // based on
    //   https://github.com/cytosm/cytosm/blob/41e786f600724358836629fc3f70787834c85270/common/docs/movies.db
    //   https://github.com/cytosm/cytosm/blob/41e786f600724358836629fc3f70787834c85270/common/docs/movies.gtop
    gTop
    val sqlInitCommands = io.File(getClass.getResource("/gtop/movies.sql").getFile).slurp()

    withResources(beginTransaction()) { tx =>

      withResources(tx.rawSqlConnection.createStatement()) { statement =>
        statement.executeUpdate(SqlQueries.purge)
        statement.executeUpdate(sqlInitCommands)
      }
      tx.success()

    }
    withResources(beginTransaction()) { tx =>
      withResources(tx.rawSqlConnection.createStatement()) { statement =>
        withResources(statement.executeQuery("SELECT title FROM movie")) { resultSet =>

          val actualTitles = Stream.continually(resultSet.next().toOption(resultSet.getString(1))).takeWhile(_.isDefined).flatten.toSet
          val expectedTitles = Set("The Matrix", "The Devil s Advocate", "Monster")

          assertResult(expectedTitles)(actualTitles)
        }
      }
    }
  }

  override def driver(): SqlDriver = new SqlDriver(translateCreateQueries = true, Some(gTop))

  runTckTests(() => new TckAdapter(beginTransaction()), scenarioSet)
}

object GTopTests {
  val scenarioSet = new TckScenarioSet(Set("GTopTests"))
}
