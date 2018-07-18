package ingraph.compiler.sql

import java.sql.DriverManager

import ingraph.compiler.sql.Util.withResources
import org.apache.commons.lang3.StringUtils
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres
import ru.yandex.qatools.embed.postgresql.distribution.Version

object PostgreSqlMain extends App {

  // https://github.com/yandex-qatools/postgresql-embedded/tree/ea26f6945478da8e8b48e382f8869896da2fda30#howto
  val postgres = new EmbeddedPostgres(Version.V10_3)

  val url = postgres.start()

  try {
    withResources(DriverManager.getConnection(url)) { conn =>
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
  catch {
    case exception => throw exception
  }
  finally {
    postgres.stop()
  }
}
