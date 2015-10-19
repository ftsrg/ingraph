package hu.bme.mit.incquerydcore
import java.io.{InputStream, Reader}

import com.hp.hpl.jena.graph.Node_Literal
import org.w3.banana.jena.JenaModule

/**
 * Created by Maginecz on 3/4/2015.
 */

class WorkingTBRDFReader(func: (Long, String, Any) => Unit,
             subjectPreprocessor: (String)=>Long,
             objectPreprocessor: Map[String, (Any)=>(Any)] = Map.empty[String, (Any)=>AnyVal]
              ) {
  def read(stream: io.Source): Unit = {
  var lastSubj : Long = 1L
  val regex = "([^\\s]*|\t) ?(a|[^\\s]*) ([^\\s\\.;]*).*".r
  val intParser = "\"(-?[\\d]*)\"\\^\\^xsd:int".r
  for (
    line <- stream.getLines()
    if !line.startsWith("@prefix")
  ) {
    val elements = line.split(" ")
    regex.findFirstMatchIn(line).foreach {
    m => {
      val subj =
      if (m.group(1) == "\t")
        lastSubj
      else {
        subjectPreprocessor(m.group(1).drop(1))
      }
      lastSubj = subj
      val pred = if (m.group(2) == "a") "type" else m.group(2).drop(1)
      val intMatch = intParser.findFirstMatchIn(m.group(3))
      val rawObj = if (intMatch.isDefined) {
       intMatch.get.group(1).toInt
      } else  m.group(3).drop(1)
      val obj = if (objectPreprocessor.contains(pred))
      objectPreprocessor(pred)(rawObj)
      else rawObj
      func(subj, pred, obj)
    }
    }
  }
  }
}

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
