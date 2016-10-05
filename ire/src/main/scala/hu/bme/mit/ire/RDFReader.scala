package hu.bme.mit.ire

/**
 * Created by Maginecz on 3/4/2015.
 */

class RDFReader(func: (Long, String, Any) => Unit,
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

