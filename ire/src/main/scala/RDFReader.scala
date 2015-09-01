
import java.io.{InputStream, Reader}

import com.hp.hpl.jena.graph.Node_Literal
import org.w3.banana.jena.JenaModule
package hu.bme.mit.IQDcore {

/**
 * Created by Maginecz on 3/4/2015.
 */

class JenaRDFReader(func: (Long, String, AnyRef) => Unit,
          subjectPreprocessor: Map[String, (String)=>Long] = Map.empty[String, (String)=>Long]
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
    case ops.Triple(rawSubj, pred, obj: Node_Literal) => {
    val subject = if (subjectPreprocessor.contains(pred.getLocalName))
            subjectPreprocessor(pred.getLocalName)(rawSubj.getLocalName)
            else rawSubj.getLocalName.toLong
    func(subject, pred.getLocalName, obj.getLiteralValue)
    }
    case ops.Triple(rawSubj, pred, obj) => {
    val subject = if (subjectPreprocessor.contains(pred.getLocalName))
      subjectPreprocessor(pred.getLocalName)(rawSubj.getLocalName)
    else rawSubj.getLocalName.toLong
    func(subject, pred.getLocalName, obj.getLocalName)
    }
  }
  graph.close()
  }
}

}
