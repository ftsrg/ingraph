package hu.bme.mit.incqueryds

import java.io.{FileInputStream, InputStream}

import akka.actor.Props
import com.twitter.util.Eval
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery
import org.yaml.snakeyaml.Yaml

import scala.io.Source
/**
  * Created by wafle on 5/14/2016.
  */
object ConfigReader {
  def parse(name: String, configStream: InputStream): TrainbenchmarkQuery = {
  val yaml = new Yaml()
  val all = yaml.load(configStream).asInstanceOf[java.util.Map[String, Object]]
  import collection.JavaConversions._
  val queryInputJava = all("input").asInstanceOf[java.util.Map[String, java.util.Map[String, java.util.List[String]]]]
  val queryInput = queryInputJava.map(kv => kv._1 -> kv._2.map(kv => kv._1 -> kv._2.toList).toMap).toMap
  val network = all("nodes").asInstanceOf[java.util.List[java.util.Map[String, java.util.Map[String, String]]]]
  val queryString =
    s"""${generatePreamble(name)}
    |${(for (node <- network.reverse;
          (nodeName, params) <- node)
          yield generateNode(nodeName, params.toMap)).mkString("")}
    |${generateInputLookup(queryInput)}
    |${generateInputNodes(queryInput)}
    |${generateEnding()}
    """.stripMargin

  print(queryString)
  return compile(name, queryString)
  }

  def main(args: Array[String]) {
  val data = new FileInputStream("config.yaml")
  val yaml = new Yaml
  import collection.JavaConversions._
  val javaObj = yaml.load(data).asInstanceOf[java.util.Map[String, java.util.Map[String, String]]]
  val scalaObj = javaObj.toMap.map(v => v._1 -> v._2.toMap)

  }

  def generateNode(name: String, params: Map[String, String]): String = {
  val functionParams = params - "type" map (kv => s"${kv._1}=${kv._2}") mkString ", "
  s"""  val $name = newLocal(Props(new ${params("type")}($functionParams)), "$name")\n"""
  }

  def generateInputLookup(input: Map[String, Map[String, List[String]]]): String = {
  "  override val inputLookup = Map(" +
    ((for ((_, values) <- input - "types";
      (name, nodes) <- values)
    yield s""""$name" -> ((cs: ChangeSet) => {""" +
    (for (node <- nodes)
      yield s"$node(cs)").mkString("\n") +
    "})"
    ) ++
    (input.filter(kv => kv._1 == "types").map(a =>
    s""""type" -> TrainbenchmarkQuery.generateTypeHandler(Map[String, ChangeSet => Unit](""" +
      (for ((name, nodes) <- input("types"))
      yield
        s""""$name" -> ((cs: ChangeSet) => {""" +
        (for (node <- nodes)
          yield s"$node(cs)").mkString("\n") +
        "}))"
      ).mkString(",\n") +
      ")"
    ))).mkString(",\n") +
  ")\n"
  }

  def generateInputNodes(input: Map[String, Map[String, List[String]]]): String = {
  s"  val inputNodes: List[ReteMessage => Unit] = ${input.flatMap(kv => kv._2.flatMap(kv => kv._2))}\n"
  }
  def generatePreamble(name: String): String = {
  s"""import hu.bme.mit.incqueryds._
    |import hu.bme.mit.incqueryds.utils.conversions._
    |import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery
    |import akka.actor.Props
    |
    |new TrainbenchmarkQuery {
    |  val production = newLocal(Props(new Production("$name")))
    |""".stripMargin
  }

  def generateEnding(): String = {
  """override val terminator = Terminator(inputNodes, production)
    |}
  """.stripMargin
  }

  val importStr = "import hu.bme.mit.incqueryds.nodeType\n"
  def compile(name: String, str: String): TrainbenchmarkQuery = {
  val eval = new Eval()
  eval[TrainbenchmarkQuery](str)
  }
}
