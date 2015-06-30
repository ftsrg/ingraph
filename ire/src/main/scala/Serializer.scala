import java.io.{FileInputStream, FileOutputStream}

import com.twitter.chill.{Output, ScalaKryoInstantiator}

/**
 * Created by Maginecz on 5/4/2015.
 */
object Serializer {
  def main(args: Array[String]) {
    val specialValues = Map(
      "connectsTo" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "definedBy" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "follows" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "exit" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "entry" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "switch" -> ((v:Any) => utils.idStringToLong(v.toString)),
      "sensor" -> ((v:Any) => utils.idStringToLong(v.toString))
      )
    val inputNode = new WildcardInput(specialFunction = specialValues, multiValue = Set("connectsTo","definedBy", "follows"))
    val reader = new JenaRDFReader(inputNode.addAttribute _)
    reader.read(new FileInputStream(args(0)))

    val instantiator = new ScalaKryoInstantiator
    instantiator.setRegistrationRequired(false)
    val kryo = instantiator.newKryo()
    val baos = new FileOutputStream(args(1))
    val output = new Output(baos, 4096)

    kryo.writeObject(output, inputNode)
    output.close()
  }
}
