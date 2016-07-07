package pkg

import co.paralleluniverse.actors.ActorRef

data class Msg(val txt: String, val from: ActorRef<Any?>)

