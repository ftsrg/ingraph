package ingraph.compiler.sql

import java.nio.file.{Path, Paths}

import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres
import ru.yandex.qatools.embed.postgresql.distribution.Version

class EmbeddedPostgresWrapper extends EmbeddedPostgres(Version.V10_3) with AutoCloseable {

  private val executablePath: Path = Paths.get(System.getProperty("user.home"), ".embedpostgresql/extracted")

  val Url: String = this.start(EmbeddedPostgres.cachedRuntimeConfig(executablePath))

  println(Url)

  override def close(): Unit = this.stop()
}
