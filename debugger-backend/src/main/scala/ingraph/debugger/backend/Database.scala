package ingraph.debugger

import java.util.UUID

import ingraph.driver.data.IngraphQueryHandler
import ingraph.driver.ingraph.IngraphDriver

import scala.collection.mutable

object Database {

  println("Starting database")
  private val driver = new IngraphDriver()
  private val session = driver.session()

  private val sessionStore = new mutable.HashMap[String, IngraphQueryHandler]

  def registerQuery(query: String): String = {
    val id = UUID.randomUUID().toString
    val queryHandler = session.registerQuery(id, query)
    sessionStore += ((id, queryHandler))

    id
  }

  def asd(id: String): Unit = {

    val session: IngraphQueryHandler = sessionStore.get(id)
    session.registerDeltaHandler {
      
    }

    return
  }
}
