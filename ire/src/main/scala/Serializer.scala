import java.io.{FileInputStream, FileOutputStream}

import com.twitter.chill.{Output, ScalaKryoInstantiator}

/**
 * Created by Maginecz on 5/4/2015.
 */
object Serializer {
  def main(args: Array[String]) {
    val inputNode = new WildcardInput
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
