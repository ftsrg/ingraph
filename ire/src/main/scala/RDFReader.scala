
import java.io.{InputStream, Reader}

import com.hp.hpl.jena.graph.Node_Literal
import org.w3.banana.jena.JenaModule

/**
 * Created by Maginecz on 3/4/2015.
 */

class JenaRDFReader(val lookup: Map[String, Any=> Unit]) extends JenaModule{
  // reading from a stream can fail so in real life, you would have to deal with the Try[Rdf#Graph]
  import ops._
  def read(stream: Reader)
  {
    val graph: Rdf#Graph = turtleReader.read(stream, base = "https://raw.githubusercontent.com/FTSRG/trainbenchmark/master/models/railway-test-1.ttl") getOrElse sys.error("couldn't read TimBL's card")

    graph.triples.foreach {
      case ops.Triple(subj, pred, obj: Node_Literal) => {


        lookup(pred.getLocalName)(subj.getLocalName, obj.getLiteralValue)
      }
      case ops.Triple(subj, pred, obj) => {
        lookup(pred.getLocalName)(subj.getLocalName, obj.getLocalName)
      }


    }
  }
  def read(stream: InputStream)
  {
    val graph: Rdf#Graph = turtleReader.read(stream, base = "https://raw.githubusercontent.com/FTSRG/trainbenchmark/master/models/railway-test-1.ttl") getOrElse sys.error("couldn't read TimBL's card")

    graph.triples.foreach {
      case ops.Triple(subj, pred, obj: Node_Literal) => {


        lookup(pred.getLocalName)(subj.getLocalName, obj.getLiteralValue)
      }
      case ops.Triple(subj, pred, obj) => {
        lookup(pred.getLocalName)(subj.getLocalName, obj.getLocalName)
      }


    }
  }
}
