package ingraph.ire

import ingraph.ire.datatypes.Tuple
import ingraph.compiler.FPlanParser
import ingraph.model.fplan.Production
import scala.collection.JavaConverters._

abstract class AbstractQueryAdapter extends AutoCloseable {

  val querySpecification: String
  val queryName: String
  val indexer: Indexer = new Indexer()

  protected val plan = FPlanParser.parse(querySpecification)
  protected val engine = EngineFactory.createQueryEngine(plan, indexer)

  def getProductionNode = plan.asInstanceOf[Production]

  def results(): Iterable[Tuple]

  def resultNames(): Iterable[String] = getProductionNode.outputNames

  def resultNamesJava(): java.lang.Iterable[String] = getProductionNode.outputNames.asJava

  override def close(): Unit = {
    engine.shutdown()
  }

}
