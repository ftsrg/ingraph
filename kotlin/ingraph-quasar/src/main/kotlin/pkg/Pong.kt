package pkg

import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.Actor
import java.util.concurrent.TimeUnit

class Pong() : Actor() {
    @Suspendable override fun doRun() {
        while (true) {
            // snippet Kotlin Actors example
            receive(1000, TimeUnit.MILLISECONDS) {  // Fiber-blocking
                when (it) {
                    is Msg -> {
                        if (it.txt == "ping")
                            it.from.send("pong")    // Fiber-blocking
                    }
                    "finished" -> {
                        println("main.Pong received 'finished', exiting")
                        return                      // Non-local return, exit actor
                    }
                    is Timeout -> {
                        println("main.Pong timeout in 'receive', exiting")
                        return                      // Non-local return, exit actor
                    }
                    else -> defer()
                }
            }
            // end of snippet
        }
    }
}
