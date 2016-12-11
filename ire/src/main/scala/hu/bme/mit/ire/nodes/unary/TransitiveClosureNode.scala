package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class TransitiveClosureNode(override val next: (ReteMessage) => Unit,
                            val src: Int,
                            val trg: Int,
                            val edge: Int
                           ) extends UnaryNode with SingleForwarder {

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Vector[Long]]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      positive = changeSet.positive.flatMap(newPathsWithEdge),
      negative = changeSet.negative.flatMap(removePathsWithEdge)
    ))
  }

  private def newPathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    var newPathsBuilder = new VectorBuilder[Tuple]

    for (source <- reachableFrom.getOrElse(sourceId, Set.empty).toStream #::: Stream(sourceId)) {
      if (!reachableVertices.contains(source)) {
        reachableVertices(source) = new mutable.HashMap
      }

      for (target <- reachableVertices.getOrElse(targetId, Map.empty).keys.toStream #::: Stream(targetId)) {
        if (!reachableFrom.contains(target)) {
          reachableFrom(target) = new mutable.HashSet
        }
        reachableFrom(target) += source

        if (!reachableVertices(source).contains(target)) {
          reachableVertices(source)(target) = new mutable.MutableList
        }

        for (sourcePath <- reachableVertices(source).getOrElse(sourceId, mutable.MutableList(Vector()))) {
          for (targetPath <- reachableVertices.getOrElse(targetId, mutable.HashMap()).getOrElse(target, mutable.MutableList(Vector()))) {
            val path = sourcePath ++ Vector(edgeId) ++ targetPath

            reachableVertices(source)(target) += path

            newPathsBuilder += tuple(source, target, path)
          }
        }
      }
    }

    newPathsBuilder.result
  }

  private def removePathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    var removedPathsBuilder = new VectorBuilder[Tuple]

    for (source <- reachableFrom.getOrElse(sourceId, Set.empty).toStream #::: Stream(sourceId)) {
      for (target <- reachableVertices.getOrElse(targetId, Map.empty).keys.toStream #::: Stream(targetId)) {
        val containingPaths = reachableVertices(source)(target).filter(_.contains(edgeId))
        removedPathsBuilder ++= containingPaths.map(tuple(source, target, _))

        reachableVertices(source)(target) = reachableVertices(source)(target).diff(containingPaths)
        if(reachableVertices(source)(target).isEmpty){
          reachableFrom(target) -= source
          if(reachableFrom(target).isEmpty){
            reachableFrom.remove(target)
          }

          reachableVertices(source).remove(target)
          if(reachableVertices(source).isEmpty){
            reachableVertices.remove(source)
          }
        }
      }
    }

    removedPathsBuilder.result
  }
}
