package hu.bme.mit.ire.nodes.binary

import scala.Vector
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.util.TestUtil.tuple
import scala.collection.mutable.HashSet


class TransitiveClosureJoinNode(override val next: (ReteMessage) => Unit,    
                            var minHops: Long = 1, 
                            var maxHops: Long = Long.MaxValue
                           ) extends BinaryNode with SingleForwarder {
  
  minHops = if (minHops >= 1) minHops else 1
  maxHops = if (maxHops >= 1) maxHops else Long.MaxValue
  
  val sourceVerticesIndexer = new HashSet[Long]

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Path]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)
  
  override def onPrimary(changeSet: ChangeSet): Unit = {
    val positiveTuples = changeSet.positive.flatMap(pathsFromSourceVertex)
    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.add(_))
    
    val negativeTuples = changeSet.negative.flatMap(pathsFromSourceVertex)
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.remove(_))
    
    forward(ChangeSet(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }
  
  override def onSecondary(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      positive = changeSet.positive.flatMap(newPathsWithEdge),
      negative = changeSet.negative.flatMap(removePathsWithEdge)
    ))
  }

  private def newPathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue
    val targetId = inputTuple(2).asInstanceOf[Number].longValue
    val edgeId = inputTuple(1).asInstanceOf[Number].longValue

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
    	val path = pathToSourceVertex._2 ++ Vector(edgeId) ++ pathFromTargetVertex._2
      if(path.size <= maxHops && areDistinctPaths(pathToSourceVertex._2, pathFromTargetVertex._2)){
        if (!reachableVertices(pathToSourceVertex._1).contains(pathFromTargetVertex._1)) {
          reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) = new mutable.MutableList
        }
        reachableFrom(pathFromTargetVertex._1) += pathToSourceVertex._1
        reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) += path
        if (sourceVerticesIndexer.contains(pathToSourceVertex._1) && isInRange(path)) {
        	newPathsBuilder += tuple(pathToSourceVertex._1, path, pathFromTargetVertex._1)
        }
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
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue
    val targetId = inputTuple(2).asInstanceOf[Number].longValue
    val edgeId = inputTuple(1).asInstanceOf[Number].longValue

    var removedPathsBuilder = new VectorBuilder[Tuple]

    if(!reachableVertices.contains(sourceId) || !reachableFrom.contains(targetId)){
      return Vector.empty
    }

    val sourceVertices = reachableFrom.getOrElse(sourceId, Set.empty) ++ Set(sourceId)
    val targetVertices = reachableVertices.getOrElse(targetId, Map.empty).keySet ++ Set(targetId)
    for (source <- sourceVertices; target <- targetVertices) {
      val containingPaths = reachableVertices(source).getOrElse(target, mutable.MutableList.empty).filter(_.contains(edgeId))
      if (sourceVerticesIndexer.contains(source)) removedPathsBuilder ++= containingPaths.filter(isInRange).map(tuple(source, _, target))

      if (containingPaths.size > 0) {
        reachableVertices(source)(target) = reachableVertices(source)(target).diff(containingPaths)
        if (reachableVertices(source)(target).isEmpty) {
          reachableFrom(target) -= source
          if (reachableFrom(target).isEmpty) {
            reachableFrom.remove(target)
          }

          reachableVertices(source).remove(target)
          if (reachableVertices(source).isEmpty) {
            reachableVertices.remove(source)
          }
        }
      }
    }

    removedPathsBuilder.result
  }
  
  private def pathsFromSourceVertex(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue
    
    var reachableFromSource = reachableVertices.getOrElse(sourceId, mutable.HashMap.empty)
    reachableFromSource.keysIterator.flatMap(targetId => pathTuplesToTrackedTarget(sourceId, targetId, reachableFromSource(targetId))).toVector
  }
  
  private def pathTuplesToTrackedTarget(sourceId: Long, targetId: Long, paths: mutable.MutableList[Path]): Iterable[Tuple] = {
    paths.filter(isInRange).map(path => tuple(sourceId, path, targetId))
  }
  
  private def isInRange(path: Path): Boolean = {
    path.size >= minHops && path.size <= maxHops
  }
}