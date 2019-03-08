package ingraph.debugger.backend

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import ingraph.debugger.Database
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

// Required trait to make JSON serialization/deserialization automatic
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val registerMessage: RootJsonFormat[RegisterMessage] = jsonFormat1(RegisterMessage)
  implicit val registerSuccessMessageFormat: RootJsonFormat[RegisterSuccessMessage] = jsonFormat2(RegisterSuccessMessage)
  implicit val errorMessageFormat: RootJsonFormat[ErrorMessage] = jsonFormat2(ErrorMessage)
}

object Webserver extends JsonSupport {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("ingraph-debug-backend")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    println("Starting server")

    val exceptionHandler: ExceptionHandler = ExceptionHandler {
      case e: Exception =>
        println(e.getMessage)
        complete(ErrorMessage("FAIL", e.getMessage))
    }

    val registerPath = {
      path("registerQuery") {
        post {
          entity(as[RegisterMessage]) { reg =>
            println(s"Registration request for ${reg.definition}")
            val id = Database.registerQuery(reg.definition)
            complete(RegisterSuccessMessage("OK", id))
          }
        }
      }
    }

    val notificationPath = {
      path("notification") {
        parameter('id, 'id) { id =>
          Database.asd(id)

          Source.
        }
      }
    }

    val route: Route = cors() {
      handleExceptions(exceptionHandler) {
        registerPath
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8081)
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}
