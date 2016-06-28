package pkg

import co.paralleluniverse.actors.LocalActor
import co.paralleluniverse.kotlin.register
import co.paralleluniverse.kotlin.spawn

fun main(args: Array<String>) {
    val pong = spawn(register("pong", Pong()))
    val ping = spawn(Ping(3))
    LocalActor.join(pong)
    LocalActor.join(ping)
}
