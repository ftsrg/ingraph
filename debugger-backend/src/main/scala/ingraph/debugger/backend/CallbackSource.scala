package ingraph.debugger.backend

import java.util

import akka.stream.stage.{GraphStage, GraphStageLogic}
import akka.stream.{Attributes, Outlet, SourceShape}
import ingraph.driver.data.{IngraphDeltaHandler, IngraphQueryHandler}
import org.neo4j.driver.v1.Record

final class CallbackSource(queryHandler: IngraphQueryHandler) extends GraphStage[SourceShape[String]] {

  val deltaHandler: IngraphDeltaHandler = new IngraphDeltaHandler {
    override val keys: Iterable[String] = _

    override def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record]): Unit = {

    }
  }

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic =
    new GraphStageLogic(shape) {

      def bufferDelta(incoming: java.util.List[_ <: Record], outgoing: java.util.List[_ <: Record]): Unit = {

      }

      getAsyncCallback()
    }


}
