package ingraph

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val filename = args(0)
    val querySpecification = Source.fromFile(filename).getLines.mkString("\n")

    println("Query specification:")
    println(querySpecification)

    val compiler = new ContainerCompiler(querySpecification)
    compiler.compile()
  }

}
