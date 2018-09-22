package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.csv.EdgeMetaData
import org.supercsv.prefs.CsvPreference

import scala.io.Source

object TrainBenchmarkUtils {
  def modelPath(size: Int, entityName: String): String = {
    getClass.getResource(s"/trainbenchmark/railway-repair-$size-$entityName.csv").getPath
  }

  def readQueryFromResources(query: String): String = {
    val stream = getClass.getResourceAsStream(s"/trainbenchmark-simple/$query.cypher")
    Source.fromInputStream(stream).getLines().mkString("\n")
  }

  def nodeFilenames(size: Int): Map[String, List[String]] = Map(
    modelPath(size, "Region") -> List("Region"),
    modelPath(size, "Route") -> List("Route"),
    modelPath(size, "Segment") -> List("Segment", "TrackElement"),
    modelPath(size, "Semaphore") -> List("Semaphore"),
    modelPath(size, "Sensor") -> List("Sensor"),
    modelPath(size, "Switch") -> List("Switch", "TrackElement"),
    modelPath(size, "SwitchPosition") -> List("SwitchPosition")
  )

  def relationshipFilenames(size: Int): Map[String, EdgeMetaData] = Map(
    modelPath(size, "connectsTo") -> EdgeMetaData("TrackElement", "connectsTo", "TrackElement"),
    modelPath(size, "entry") -> EdgeMetaData("Route", "entry", "Semaphore"),
    modelPath(size, "exit") -> EdgeMetaData("Route", "exit", "Semaphore"),
    modelPath(size, "follows") -> EdgeMetaData("Route", "follows", "SwitchPosition"),
    modelPath(size, "monitoredBy") -> EdgeMetaData("TrackElement", "monitoredBy", "Sensor"),
    modelPath(size, "requires") -> EdgeMetaData("Route", "requires", "Sensor"),
    modelPath(size, "target") -> EdgeMetaData("SwitchPosition", "target", "Switch")
  )

  def readModelAndGetResults(querySpec: String, size: Int): Iterable[Tuple] = {
    val adapter = new IncrementalQueryAdapter(querySpec, "")
    adapter.readCsv(nodeFilenames(size), relationshipFilenames(size), CsvPreference.STANDARD_PREFERENCE)
    adapter.results()
  }
}
