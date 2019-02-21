package ingraph.compiler.sql

import java.sql.DriverManager

import ingraph.compiler.sql.Util.withResources
import org.apache.commons.lang3.StringUtils

object PostgreSqlMain extends App {

  // https://github.com/yandex-qatools/postgresql-embedded/tree/ea26f6945478da8e8b48e382f8869896da2fda30#howto
  withResources(new EmbeddedPostgresWrapper) { postgres =>
    withResources(DriverManager.getConnection(postgres.Url)) { conn =>
      withResources(conn.createStatement()) {
        _.execute("CREATE TABLE films (code char(5));")
      }
      withResources(conn.createStatement()) {
        _.execute("INSERT INTO films VALUES ('movie');")
      }

      withResources(conn.createStatement) { statement =>
        assert(statement.execute("SELECT * FROM films;"))
        assert(statement.getResultSet().next())

        val code = statement.getResultSet().getString("code")

        val separator = StringUtils.repeat('=', 42)
        println(separator)
        println(code)
        println(separator)

        assert(code == "movie")
      }
    }
  }
}
