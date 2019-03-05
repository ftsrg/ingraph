package ingraph.debugger.backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import ingraph.compiler.exceptions.CompilerException
import ingraph.debugger.Database
import spray.json.{DefaultJsonProtocol, PrettyPrinter, RootJsonFormat}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

// Required trait to make JSON serialization/deserialization automatic
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val registerSuccessMessageFormat: RootJsonFormat[RegisterSuccessMessage] = jsonFormat1(RegisterSuccessMessage)
  implicit val errorMessageFormat: RootJsonFormat[ErrorMessage] = jsonFormat1(ErrorMessage)
}

object Webserver extends JsonSupport {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("ingraph-debug-backend")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    println("Starting server")

    val route = {
      path("registerQuery") {
        parameter("query") { query =>
          try {
            val id = Database.registerQuery(query)
            complete(RegisterSuccessMessage(id))
          } catch {
            case ex: CompilerException => complete(ErrorMessage(ex.message))
          }
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}
