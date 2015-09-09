import java.io.StringReader

import hu.bme.mit.IQDcore.{JenaRDFReader, utils}
import org.scalatest.FlatSpec


class RdfReaderTest extends FlatSpec {
  "JenaRailwayReader" should "read simple values" in {
  val values = new StringReader("" +
    "@prefix : <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#> ." +
    "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> ." +
    ":_1 a :Signal ;\n\t:Signal_currentState :SIGNALSTATE_GO ." +
    ":_3 a :Route ;\n\t:Route_exit :_2 .")
  val signalStateChecker = (a:Long, b: Any) => {
    assert(a == 1L && b == "SIGNALSTATE_GO")
  }
  val routeExitChecker = (a: Long, b: Any) => {
    assert(a == 3L && b == "_2")
  }
  val typeChecker = (a: Long, b: Any) => {
    if (a == 1L)
      assert(b == "Signal")
    else if (a == 3L)
      assert(b == "Route")
    else
      fail("invalid type")
  }
  val noop = (a: Any) => ()
  def lookup(id: Long, pred: String, obj: Any): Unit = {
    if (pred == "Signal_currentState") signalStateChecker(id, obj)
    if (pred == "Route_exit") routeExitChecker(id,obj)
    if (pred == "type") typeChecker(id,obj)
  }
  val reader = new JenaRDFReader(lookup, subjectPreprocessor = (v: AnyRef) => utils.idStringToLong(v.toString) )

  reader.read(values)
  }
}
