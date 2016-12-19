package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.TestUtil._

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class TransitiveClosureNode(override val next: (ReteMessage) => Unit,
                            val src: Int,
                            val trg: Int,
                            val edge: Int
                           ) extends UnaryNode with SingleForwarder {

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Path]]]
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

    if (!reachableVertices.contains(sourceId)) {
      reachableVertices(sourceId) = new mutable.HashMap
    }

    if (!reachableFrom.contains(targetId)) {
      reachableFrom(targetId) = new mutable.HashSet
    }

    val pathsToSource = pathsToSourceVertex(sourceId)
    val pathsFromTarget = pathsFromTargetVertex(targetId)
    for(pathToSourceVertex <- pathsToSource; pathFromTargetVertex <- pathsFromTarget){
      if(areDistinctPaths(pathToSourceVertex._2, pathFromTargetVertex._2)){
        if (!reachableVertices(pathToSourceVertex._1).contains(pathFromTargetVertex._1)) {
          reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) = new mutable.MutableList
        }

        reachableFrom(pathFromTargetVertex._1) += pathToSourceVertex._1

        val path = pathToSourceVertex._2 ++ Vector(edgeId) ++ pathFromTargetVertex._2
        reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) += path
        newPathsBuilder += tuple(pathToSourceVertex._1, pathFromTargetVertex._1, path)
      }
    }

    newPathsBuilder.result
  }

  private def pathsToSourceVertex(sourceId: Long) = {
    reachableFrom.getOrElse(sourceId, Set.empty).flatMap(startVertex => reachableVertices(startVertex)(sourceId).map(path => (startVertex, path))).toList ++ List((sourceId, Path()))
  }

  private def pathsFromTargetVertex(targetId: Long) = {
    reachableVertices.getOrElse(targetId, Map.empty).flatMap(entry => entry._2.map(path => (entry._1, path))).toList ++ List((targetId, Path()))
  }

  private def areDistinctPaths(p1: Path, p2: Path): Boolean = {
    if(p1.isEmpty || p2.isEmpty)
      return true

    val pathHash: mutable.HashSet[Long] = mutable.HashSet(p1: _*)

    for(edge <- p2){
      if(pathHash.contains(edge))
        return false
    }

    true
  }

  private def removePathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    var removedPathsBuilder = new VectorBuilder[Tuple]

    if(!reachableVertices.contains(sourceId) || !reachableFrom.contains(targetId)){
      return Vector.empty
    }

    val sourceVertices = reachableFrom.getOrElse(sourceId, Set.empty) ++ Set(sourceId)
    val targetVertices = reachableVertices.getOrElse(targetId, Map.empty).keySet ++ Set(targetId)
    for (source <- sourceVertices; target <- targetVertices) {
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

    removedPathsBuilder.result
  }
}
