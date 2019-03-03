package ingraph.compiler.sql

import java.io.File
import java.nio.file._
import java.util.Collections

import ingraph.compiler.sql.Util._

import scala.collection.JavaConverters._
import scala.io.Source
import scala.reflect.io.{File => ScalaFile, Path => ScalaPath}
import scala.util.Try


object SqlQueries {
  private val queries: Map[String, String] =
    getQueriesFromResourceFolder("/sql-queries", "sql").toMap

  val purge = queries("purge")

  val createTables = queries("createTables")

  val utilityFunctions = queries("utilityFunctions")

  def getQueriesFromFolder(folder: String, extension: String): Seq[(String, String)] = {
    val paths = new File(folder).listFiles().map(_.toPath)

    loadFiles(paths, extension, path => ScalaFile(ScalaPath(path.toFile)).slurp())
  }

  // https://stackoverflow.com/a/28057735
  def getQueriesFromResourceFolder(folder: String, extension: String)
  : Seq[(String, String)] = {

    val uri = getClass.getResource(folder).toURI
    val paths: Seq[Path] =
      if (uri.getScheme == "jar") {
        val listFunc: Path => Seq[Path] = Files.list(_).iterator.asScala.toSeq

        try {
          listFunc(FileSystems.getFileSystem(uri).getPath(folder))
        }
        catch {
          case _: FileSystemNotFoundException =>
            withResources(FileSystems.newFileSystem(uri, Collections.emptyMap[String, Object]())) {
              fileSystem => listFunc(fileSystem.getPath(folder))
            }
        }
      }
      else {
        val relativeFolderPath = Paths.get(folder)
        val absoluteFolder = new File(getClass.getResource(folder).getFile)

        absoluteFolder.listFiles().map {
          file =>
            val relativeFilePathFromFolder = absoluteFolder.toPath.relativize(file.toPath)
            relativeFolderPath.resolve(relativeFilePathFromFolder)
        }
      }

    loadFiles(paths, extension,
      path => withResources(getClass.getResourceAsStream(path.toString))(
        stream => Source.fromInputStream(stream).mkString))
  }

  private def loadFiles(paths: Seq[Path], extension: String, loadFile: Path => String) = {
    paths
      .filter(_.getFileName.toString.endsWith("." + extension))
      .toStream
      .sortBy { path =>
        val filename = path.getFileName.toString
        // sort by name then by ID
        filename.replaceAll("""\d+.*$""", "") ->
          Try(Integer.parseInt(filename.replaceAll("""\D""", ""))).getOrElse(0)
      }
      .map { path =>
        val content =
          loadFile(path)

        val filenameWithoutExtension = path.getFileName.toString.replaceFirst("\\.[^.]+$", "")
        filenameWithoutExtension -> content
      }
  }
}
