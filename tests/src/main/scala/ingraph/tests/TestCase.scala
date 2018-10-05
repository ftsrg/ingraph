package ingraph.tests

import ingraph.csv.EdgeMetaData

import scala.io.Source

// Test case trait, which can be loaded from GraphML
trait GraphMLData {
  def graphMLPath: String
}

// Test case trait, which can be loaded from CSV files
trait CSVData {
  // CSV related definitions
  def vertexCsvPaths: Map[String, List[String]]
  def edgeCsvPaths: Map[String, EdgeMetaData]
}

abstract class TestCase {
  def readToString(path: String): String = Source.fromFile(path).getLines().mkString("\n")

  def name: String
  def querySpecification: String
}
