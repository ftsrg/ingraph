
import java.io.{InputStream, Reader}

import com.hp.hpl.jena.graph.Node_Literal
import org.w3.banana.jena.JenaModule

/**
 * Created by Maginecz on 3/4/2015.
 */

class JenaRDFReader(func: (String, String, AnyRef) => Unit) extends JenaModule {
  // reading from a stream can fail so in real life, you would have to deal with the Try[Rdf#Graph]
  def this(lookup: Map[String, (String, AnyRef) => Unit]) { //ok, this is pretty neat
    this(
      (obj: String, pred:String, subj:AnyRef) => lookup(pred)(obj,subj)
    )
  }
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
      case ops.Triple(subj, pred, obj: Node_Literal) => {
        func(subj.getLocalName, pred.getLocalName, obj.getLiteralValue)
      }
      case ops.Triple(subj, pred, obj) => {
        func(subj.getLocalName, pred.getLocalName, obj.getLocalName)
      }
    }
    graph.close()
  }
}
