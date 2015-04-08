import java.io.{InputStream, StringBufferInputStream, StringReader}

import org.scalatest.FlatSpec

import scala.collection.immutable.HashMap
import scala.collection.mutable.Stack


class RdfReaderTest extends FlatSpec {
  "JenaRailwayReader" should "read simple values" in {
    val values = new StringReader("" +
      "@prefix : <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#> ." +
      "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> ." +
      ":_1 a :Signal ;\n\t:Signal_currentState :SIGNALSTATE_GO ." +
      ":_3 a :Route ;\n\t:Route_exit :_2 .")
    val signalStateChecker = (a:Any) => a match {
      case (a:String,b:String) => assert(a=="_1" && b=="SIGNALSTATE_GO")
    }
    val routeExitChecker = (a:Any) => a match {
      case (a:String,b:String) => assert(a=="_3" && b=="_2")
    }
    val typeChecker = (a:Any) => a match {
      case (a:String,b:String) => {
        if (a=="_1")
        assert(b=="Signal")
        else if (a=="_3")
          assert(b=="Route")
        else
          fail("invalid type")
      }
    }
    val noop = (a:Any) => ()
    val lookup = HashMap(
      "Signal_currentState" -> signalStateChecker,
      "Route_exit" -> routeExitChecker,
      "type" -> typeChecker
    )
    val reader = new JenaRDFReader(lookup)
    reader.read(values)
  }
}
