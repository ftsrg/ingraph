package hu.bme.mit.incqueryds
import java.io.FileOutputStream

import com.twitter.chill.{Output, ScalaKryoInstantiator}
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader

/**
 * Created by Maginecz on 5/4/2015.
 */
object Serializer {
  def main(args: Array[String]) {
  val specialValues = Map(
    "connectsTo" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "gathers" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "follows" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "exit" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "entry" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "target" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "sensor" -> ((v: Any) => utils.idStringToLong(v.toString))
  )
  val inputNode = new WildcardInput
  val reader = new TrainbenchmarkReader(inputNode)
  reader.read(args(0))
  val runtime = Runtime.getRuntime


  val instantiator = new ScalaKryoInstantiator
  instantiator.setRegistrationRequired(false)
  val kryo = instantiator.newKryo()
  val baos = new FileOutputStream(args(1))
  val output = new Output(baos, 4096)

  kryo.writeObject(output, inputNode)
  output.close()
  }
}
