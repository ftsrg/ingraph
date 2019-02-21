package ingraph.compiler.sql

import java.io.File

import scala.reflect.io
import scala.util.Try

object SqlQueries {
  private val queries: Map[String, String] =
    getQueriesFromResourceFolder("/sql-queries", "sql").toMap

  val purge = queries("purge")

  val createTables = queries("createTables")

  val utilityFunctions = queries("utilityFunctions")

  def getQueriesFromResourceFolder(folder: String, extension: String): Seq[(String, String)] = {
    getQueriesFromFolder(folder, extension, getClass.getResource(_).getFile)
  }

  def getQueriesFromFolder(folder: String, extension: String, resourcePathResolver: String => String = identity)
  : Seq[(String, String)] = {
    new File(resourcePathResolver(folder))
      .listFiles()
      .filter(_.isFile)
      .map(io.File(_))
      .filter(f => f.hasExtension(extension))
      .sortBy { file =>
        val filename = file.name
        // sort by name then by ID
        filename.replaceAll("""\d+.*$""", "") ->
          Try(Integer.parseInt(filename.replaceAll("""\D""", ""))).getOrElse(0)
      }
      .map(f => f.stripExtension -> f.slurp())
      .toSeq
  }
}
