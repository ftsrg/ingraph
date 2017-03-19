package ingraph.ire

import java.util

import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet

import relalg._

import scala.collection.mutable

class EntityToTupleMapper(vertexConverters: Map[Set[String], Set[GetVerticesOperator]],
                          edgeConverters: Map[Set[String], Set[GetEdgesOperator]],
                          inputLookup: Map[String, (ChangeSet) => Unit],
                          indexer: Indexer) extends IdParser {
  val vertices = mutable.HashMap[String, Set[Tuple]]()
  val edges = mutable.HashMap[String, Set[Tuple]]()

  override def idParser(obj: Any): Any = obj
  var transaction: Transaction = _
  import Conversions._

  def elementToNode(element: IngraphVertex, required: Vector[String]): Tuple =
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.properties(key).asInstanceOf[Any])

  def getVerticesForLabels(labels: Set[String]) = {
    // We return all vertices that have all the labels using intersection
    println(labels)
    labels.tail.foldLeft(indexer.vertexLabelLookup(labels.head))(
      (previous, label) => previous.intersect(indexer.vertexLabelLookup(label)))
  }

  def getEdgesForLabels(labels: Set[String], sourceLabels: Set[String], targetLabels: Set[String]) = {
    labels.foldLeft(Vector[IngraphEdge]())(
      (results, label) => results ++ indexer.edgesByLabel(label).filter(
        e => sourceLabels.forall(e.sourceVertex.labels.contains(_) &&
          targetLabels.forall(e.targetVertex.labels.contains(_))))) // 297
  }

  def edgeToTupleType(edge: IngraphEdge, operator: GetEdgesOperator): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.getFullSchema.drop(3).map( a => {
        val element = a.asInstanceOf[AttributeVariable].getElement
        element match {
          case _ if element == operator.getSourceVertexVariable => edge.sourceVertex.properties(a.getName).asInstanceOf[Any]
          case _ if element == operator.getTargetVertexVariable => edge.targetVertex.properties(a.getName).asInstanceOf[Any]
          case _ if element == operator.getEdgeVariable => edge.properties(a.getName).asInstanceOf[Any]
        }
      }
    )
  }

  def loadEverythingFromIndexer(transaction: Transaction): Unit = {
    for ((labels, operators) <- vertexConverters;
          operator: GetVerticesOperator <- operators) {
      for (tuple <- getVerticesForLabels(labels).map(e => elementToNode(e, operator.getFullSchema.map(_.getName))))
        transaction.add(operator.getVertexVariable.getName, tuple)
    }

    for ((labels, operators) <- edgeConverters;
         operator: GetEdgesOperator <- operators) {
      val sourceLabels = operator.getSourceVertexVariable.getVertexLabelSet.getVertexLabels.map(_.getName).toSet
      val targetLabels = operator.getTargetVertexVariable.getVertexLabelSet.getVertexLabels.map(_.getName).toSet
      for (tuple <- getEdgesForLabels(labels, sourceLabels, targetLabels).map(e => edgeToTupleType(e, operator)))
        transaction.add(operator.getEdgeVariable.getName, tuple)
    }
  }
}
