package ingraph.tests

import java.util

import com.google.gson.Gson

import scala.io.Source

object LdbcSnbTestCase {
  private val gson = new Gson()

  def modelPath(entityName: String)(implicit number: Int) = s"../graphs/bi/$number/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"../queries/ldbc-snb-${workload}/${workload}-${query}.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  def parameterPath(number: String) = s"../graphs/bi/$number/parameters"

  def readToString(path: String): String = Source.fromFile(s"$path").getLines().mkString("\n")

  def convert(v: Any): String = {
    v match {
      case d: Double => f"$d%.0f"
      case s: String => "'" + s.toString + "'"
      case seq: util.ArrayList[Any] => "[" + seq.toArray.map(convert).mkString(",") + "]"
      case _ => v.toString
    }
  }
}

case class LdbcSnbTestCase(workload: String, number: Int) {

  import LdbcSnbTestCase._

  import scala.collection.JavaConverters._

  val id = f"$number%02d"

  val parameters: Map[String, String] = gson
    .fromJson(readToString(parameterPath(id)), classOf[java.util.Map[String, Object]])
    .asScala
    .toMap
    .map { case (k, v) => (k, convert(v)) }

  val baseQuerySpecification: String = readToString(queryPath(workload, number))

  val querySpecification: String = parameters.foldLeft(baseQuerySpecification)((a, b) =>
    a.replaceAllLiterally("$" + b._1.toString, b._2.toString))
}
