package pkg

import co.paralleluniverse.actors.ActorRef
import co.paralleluniverse.actors.ActorRegistry
import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.Actor

class Ping(val n: Int) : Actor() {
    @Suspendable override fun doRun() {
        val pong: ActorRef<Any> = ActorRegistry.getActor("pong")
        for(i in 1..n) {
            pong.send(Msg("ping", self()))          // Fiber-blocking
            receive {                               // Fiber-blocking, always consume the message
                when (it) {
                    "pong" -> println("main.Ping received pong")
                    else -> null                    // Discard
                }
            }
        }
        pong.send("finished")                       // Fiber-blocking
        println("main.Ping exiting")
    }
}
