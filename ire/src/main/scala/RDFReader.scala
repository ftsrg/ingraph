
import java.io.{InputStream, Reader}

import com.hp.hpl.jena.graph.Node_Literal
import org.w3.banana.jena.JenaModule
package hu.bme.mit.IQDcore {

/**
 * Created by Maginecz on 3/4/2015.
 */

class JenaRDFReader(func: (Long, String, Any) => Unit,
          subjectPreprocessor: (String)=>Long,
          objectPreprocessor: Map[String, (AnyRef)=>(Any)] = Map.empty[String, (AnyRef)=>AnyVal]
           ) extends JenaModule {
  import ops._

  def read(stream: Reader) {
  val graph: Rdf#Graph = turtleReader.read(stream, base = "") getOrElse sys.error("couldn't read stream")
  readGraph(graph)
  }

  def read(stream: InputStream) {
  val graph: Rdf#Graph = turtleReader.read(stream, base = "") getOrElse sys.error("couldn't read stream")
  readGraph(graph)
  }

  private def readGraph(graph: Rdf#Graph) {
  graph.triples.foreach {
    case ops.Triple(rawSubj, pred, rawObj: Node_Literal) => {
    val subject = subjectPreprocessor(rawSubj.getLocalName)
    val obj = if (objectPreprocessor.contains(pred.getLocalName))
            objectPreprocessor(pred.getLocalName)(rawObj.getLiteralValue)
            else rawObj.getLiteralValue
    func(subject, pred.getLocalName, obj)
    }
    case ops.Triple(rawSubj, pred, rawObj) => {
    val subject = subjectPreprocessor(rawSubj.getLocalName)
    val obj = if (objectPreprocessor.contains(pred.getLocalName))
      objectPreprocessor(pred.getLocalName)(rawObj.getLocalName)
    else rawObj.getLocalName
    func(subject, pred.getLocalName, obj)
    }
  }
  graph.close()
  }
}

}
