package hu.bme.mit.incqueryds.trainbenchmark

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
 * Created by wafle on 10/11/2015.
 */
object Slave {
  def main(args: Array[String]) {
    val systemNumber =
      if (args.length > 0)
        args(0)
      else "1"
    ActorSystem(s"Slave${systemNumber}System", ConfigFactory.load("slave"))
  }
}
