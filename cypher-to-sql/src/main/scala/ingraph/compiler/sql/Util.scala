package ingraph.compiler.sql

import scala.util.control.NonFatal

object Util {

  // https://stackoverflow.com/a/34499015
  implicit class BoolToOption(val self: Boolean) extends AnyVal {
    def toOption[A](value: => A): Option[A] =
      if (self) Some(value)
      else None
  }

  // https://github.com/dkomanov/stuff/blob/1865d46fe193189153f57faafb63692dbddb83b7/src/com/komanov/io/package.scala
  def withResources[T <: AutoCloseable, V](r: => T)(f: T => V): V = {
    val resource: T = r
    require(resource != null, "resource is null")
    var exception: Throwable = null
    try {
      f(resource)
    } catch {
      case e: Throwable =>
        exception = e
        throw e
    } finally {
      closeAndAddSuppressed(exception, resource)
    }
  }

  private def closeAndAddSuppressed(e: Throwable, resource: AutoCloseable): Unit = {
    if (e != null) {
      try {
        resource.close()
      } catch {
        case NonFatal(suppressed) =>
          e.addSuppressed(suppressed)
        case fatal: Throwable if NonFatal(e) =>
          fatal.addSuppressed(e)
          throw fatal
        case fatal: InterruptedException =>
          fatal.addSuppressed(e)
          throw fatal
        case fatal: Throwable =>
          e.addSuppressed(fatal)
      }
    } else {
      resource.close()
    }
  }
}
