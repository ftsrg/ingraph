package ingraph.tests

import scala.io.Source

// Test case trait, which can be loaded from GraphML
trait GraphMLData {
  def graphMLPath: String
}

// Test case trait, which can be loaded from CSV files
trait CSVData {
  // CSV related definitions
  def nodeCSVPaths: Map[String, List[String]]
  def relationshipCSVPaths: Map[String, String]
}

abstract class TestCase {
  def readToString(path: String): String = Source.fromFile(s"$path").getLines().mkString("\n")

  def name: String
  def query: String
}
