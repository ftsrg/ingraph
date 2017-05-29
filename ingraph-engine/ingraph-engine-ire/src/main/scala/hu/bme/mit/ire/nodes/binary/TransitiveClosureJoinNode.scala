package hu.bme.mit.ire.nodes.binary

import scala.Vector
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.util.TestUtil.tuple
import scala.collection.mutable.HashSet
import hu.bme.mit.ire.util.BufferMultimap


class TransitiveClosureJoinNode(override val next: (ReteMessage) => Unit,
                                override val primaryTupleWidth: Int,
                                override val secondaryTupleWidth: Int,
                                override val primaryMask: Mask,
                                override val secondaryMask: Mask,
                                var minHops: Long = 0,
                                var maxHops: Long = Long.MaxValue
                                ) extends JoinNodeBase with SingleForwarder {

  minHops = if (minHops >= 0) minHops else 0
  maxHops = if (maxHops >= 1) maxHops else Long.MaxValue

  val sourceLookup = new mutable.HashMap[Any, Tuple]
  val targetLookup = new mutable.HashMap[Any, Tuple]

  def composeTuple(sourceId: Long, path: Path, targetId: Long): Tuple = {
    tuple(sourceId) ++ sourceLookup.getOrElse(sourceId, tuple()) ++
    tuple(path) ++ // TODO: there might be extra attributes for the path
    tuple(targetId) ++ targetLookup.getOrElse(targetId, tuple())
  }

  val sourceVerticesIndexer = new HashSet[Long]

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[PathData]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)

  override def onPrimary(changeSet: ChangeSet): Unit = {
    val positiveTuples = changeSet.positive.flatMap(pathsFromSourceVertex)
    val negativeTuples = changeSet.negative.flatMap(pathsFromSourceVertex)
    
    for (tuple <- changeSet.positive)
      sourceLookup.put(tuple(0), tuple.drop(1))
    for (tuple <- changeSet.negative)
      sourceLookup.remove(tuple(0))

    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.add(_))
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.remove(_))

    forward(ChangeSet(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    for (tuple <- changeSet.positive)
      targetLookup.put(tuple(2), tuple.drop(3))
    for (tuple <- changeSet.negative)
      targetLookup.remove(tuple(2))

    forward(ChangeSet(
      positive = changeSet.positive.flatMap(newPathsWithEdge),
      negative = changeSet.negative.flatMap(removePathsWithEdge)
    ))
  }

  private def newPathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue
    val targetId = inputTuple(2).asInstanceOf[Number].longValue
    val edgeId = inputTuple(1).asInstanceOf[Number].longValue
    val edgeAndTargetExtraVariables = inputTuple.drop(3)

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
      if(pathToSourceVertex._2.path.size + pathFromTargetVertex._2.path.size + 1 <= maxHops && areDistinctPaths(pathToSourceVertex._2.pathHash, pathFromTargetVertex._2.pathHash)){
        var pathBuilder = new VectorBuilder[Long]
        pathBuilder ++= pathToSourceVertex._2.path
        pathBuilder += edgeId
        pathBuilder ++= pathFromTargetVertex._2.path
        val path = pathBuilder.result

        if (!reachableVertices(pathToSourceVertex._1).contains(pathFromTargetVertex._1)) {
          reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) = new mutable.MutableList
        }
        reachableFrom(pathFromTargetVertex._1) += pathToSourceVertex._1
        reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) += PathData(path, mutable.HashSet(path: _*))
        if (sourceVerticesIndexer.contains(pathToSourceVertex._1) && isInRange(path)) {
          newPathsBuilder += composeTuple(pathToSourceVertex._1, path, pathFromTargetVertex._1)
        }
      }
    }

    newPathsBuilder.result
  }

  private def pathsToSourceVertex(sourceId: Long) = {
    reachableFrom.getOrElse(sourceId, Set.empty).flatMap(startVertex => reachableVertices(startVertex)(sourceId).map(path => (startVertex, path))).toList.:: (sourceId, PathData(Path(), mutable.HashSet[Long]()))
  }

  private def pathsFromTargetVertex(targetId: Long) = {
    reachableVertices.getOrElse(targetId, Map.empty).flatMap(entry => entry._2.map(path => (entry._1, path))).toList.:: (targetId, PathData(Path(), mutable.HashSet[Long]()))
  }

  private def areDistinctPaths(p1: mutable.HashSet[Long], p2: mutable.HashSet[Long]): Boolean = {
    if(p1.isEmpty || p2.isEmpty)
      return true

    for(edge <- p2){
      if(p1.contains(edge))
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
      val containingPaths = reachableVertices(source).getOrElse(target, mutable.MutableList.empty).filter(_.pathHash.contains(edgeId))
      if (sourceVerticesIndexer.contains(source))
        removedPathsBuilder ++= containingPaths.filter(pathData => isInRange(pathData.path)).map(pathData => composeTuple(source, pathData.path, target))

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
    var pathsFromSourceVertexBuilder = new VectorBuilder[Tuple]

    if(minHops == 0)
      pathsFromSourceVertexBuilder += composeTuple(sourceId, Path(), sourceId)

    val reachableFromSource = reachableVertices.getOrElse(sourceId, mutable.HashMap.empty)
    pathsFromSourceVertexBuilder ++= reachableFromSource.keysIterator.flatMap(targetId => pathTuplesToTrackedTarget(sourceId, targetId, reachableFromSource(targetId)))

    pathsFromSourceVertexBuilder.result
  }

  private def pathTuplesToTrackedTarget(sourceId: Long, targetId: Long, paths: mutable.MutableList[PathData]): Iterable[Tuple] = {
    paths.filter(pathData => isInRange(pathData.path)).map(pathData => composeTuple(sourceId, pathData.path, targetId))
  }

  private def isInRange(path: Path): Boolean = {
    path.size >= minHops && path.size <= maxHops
  }

  case class PathData(val path: Path, val pathHash: mutable.HashSet[Long]) {
    override def equals(other: Any) = other match {
      case that: PathData => that.path.equals(this.path)
      case _ => false
    }

    override def hashCode = path.hashCode
  }
}
