import java.io.StringReader
import org.scalatest.FlatSpec
import hu.bme.mit.incquerydcore.{WorkingTBRDFReader, JenaRDFReader, utils}


class RdfReaderTest extends FlatSpec {
  "JenaRailwayReader" should "read simple values" in {
    val values = new StringReader("" +
      "@prefix : <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#> ." +
      "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> ." +
      ":_1 a :Signal ;\n\t:Signal_currentState :SIGNALSTATE_GO ." +
      ":_3 a :Route ;\n\t:Route_exit :_2 .")


    val reader = new JenaRDFReader(lookup, subjectPreprocessor = (v: AnyRef) => utils.idStringToLong(v.toString) )

    reader.read(values)
  }
  "Tbreader" should "read simple values" in {
    val values = "" +
      "@prefix : <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#> .\n" +
      "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .\n" +
      ":_1 a :Signal ;\n\t:Signal_currentState :SIGNALSTATE_GO .\n" +
      "\ta :TrackElement ;\n" +
      ":_3 a :Route ;\n\t:Route_exit :_2 .\n" +
      ":_3 length \"358\"^^xsd:int ."

    val reader = new WorkingTBRDFReader(lookup, subjectPreprocessor = (v: AnyRef) => utils.idStringToLong(v.toString))
    reader.read(scala.io.Source.fromString(values))


  }
  def lookup(id: Long, pred: String, obj: Any): Unit = {
    if (pred == "Signal_currentState") signalStateChecker(id, obj)
    if (pred == "Route_exit") routeExitChecker(id,obj)
    if (pred == "type") typeChecker(id,obj)
    if (pred == "length") assert(obj == 358)
  }

  def signalStateChecker = (a:Long, b: Any) => {
    assert(a == 1L && b == "SIGNALSTATE_GO")
  }

  def routeExitChecker = (a: Long, b: Any) => {
    assert(a == 3L && b == "_2")
  }

  def typeChecker = (a: Long, b: Any) => {
    if (a == 1L)
      assert(b == "Signal" || b=="TrackElement")
    else if (a == 3L)
      assert(b == "Route")

    else
      fail("invalid type")
  }
}
