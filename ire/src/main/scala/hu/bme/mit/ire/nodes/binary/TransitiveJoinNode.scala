package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Path, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.{BufferMultimap, CounterMultimap, SizeCounter}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable


class TransitiveJoinNode(override val next: (ReteMessage) => Unit,
                         override val primaryTupleWidth: Int,
                         override val secondaryTupleWidth: Int,
                         override val primaryMask: Mask,
                         override val secondaryMask: Mask,
                         val outputTupleWidth: Int,
                         val minHops: Long = 1,
                         var maxHops: Long = Long.MaxValue
                        ) extends JoinNodeBase with SingleForwarder {

  maxHops = if (maxHops >= 1) maxHops else Long.MaxValue

  val sourceLookup = new BufferMultimap[Long, Tuple]
  val targetLookup = new CounterMultimap[Long, Tuple]

  val sourceVertexIndex = primaryMask(0)
  val targetVertexIndex = 2 - secondaryMask(0)

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[PathData]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)

  override def onPrimary(changeSet: ChangeSet): Unit = {
    for (t <- changeSet.positive)
      sourceLookup.addBinding(t(sourceVertexIndex).asInstanceOf[Number].longValue, t)
    for (t <- changeSet.negative)
      sourceLookup.removeBinding(t(sourceVertexIndex).asInstanceOf[Number].longValue, t)

    forward(ChangeSet(
      positive = changeSet.positive.flatMap(pathsFromSourceVertex),
      negative = changeSet.negative.flatMap(pathsFromSourceVertex)
    ))
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      positive = changeSet.positive.flatMap(newPathsWithEdge),
      negative = changeSet.negative.flatMap(removePathsWithEdge)
    ))
  }

  private def newPathsWithEdge(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(2 - targetVertexIndex).asInstanceOf[Number].longValue
    val edgeId = inputTuple(1).asInstanceOf[Number].longValue
    val targetId = inputTuple(targetVertexIndex).asInstanceOf[Number].longValue

    var newPathsBuilder = new VectorBuilder[Tuple]

    val targetTuple = inputTuple(targetVertexIndex) +: inputTuple.drop(3)
    if(!targetLookup.containsEntry(targetId, targetTuple)){
      newPathsBuilder ++= pathsToTargetVertex(targetId, targetTuple)
    }
    targetLookup.addBinding(targetId, targetTuple)

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
        if (sourceLookup.contains(pathToSourceVertex._1) && isInRange(path)) {
          newPathsBuilder ++= composeTuple(pathToSourceVertex._1, path, pathFromTargetVertex._1)
        }
      }
    }

    newPathsBuilder.result
  }

  private def pathsToSourceVertex(sourceId: Long) = {
    reachableFrom.getOrElse(sourceId, Set.empty)
      .flatMap(startVertex => reachableVertices(startVertex)(sourceId).map(path => (startVertex, path)))
      .toList
      .:: (sourceId, PathData(Path(), mutable.HashSet[Long]()))
  }

  private def pathsFromTargetVertex(targetId: Long) = {
    reachableVertices.getOrElse(targetId, Map.empty)
      .flatten(entry => entry._2.map(path => (entry._1, path)))
      .toList
      .:: (targetId, PathData(Path(), mutable.HashSet[Long]()))
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
    val sourceId = inputTuple(2 - targetVertexIndex).asInstanceOf[Number].longValue
    val edgeId = inputTuple(1).asInstanceOf[Number].longValue
    val targetId = inputTuple(targetVertexIndex).asInstanceOf[Number].longValue

    var removedPathsBuilder = new VectorBuilder[Tuple]

    if(!reachableVertices.contains(sourceId) || !reachableFrom.contains(targetId)){
      return Vector.empty
    }

    val sourceVertices = reachableFrom.getOrElse(sourceId, Set.empty) ++ Set(sourceId)
    val targetVertices = reachableVertices.getOrElse(targetId, Map.empty).keySet ++ Set(targetId)
    for (source <- sourceVertices; target <- targetVertices) {
      val containingPaths = reachableVertices(source).getOrElse(target, mutable.MutableList.empty).filter(_.pathHash.contains(edgeId))
      if (sourceLookup.contains(source)) {
        removedPathsBuilder ++= containingPaths.filter(pathData => isInRange(pathData.path))
                                               .flatMap(pathData => composeTuple(source, pathData.path, target))
      }

      if (containingPaths.nonEmpty) {
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

    targetLookup.removeBinding(targetId, inputTuple(targetVertexIndex) +: inputTuple.drop(3))

    removedPathsBuilder.result
  }

  private def pathsFromSourceVertex(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(sourceVertexIndex).asInstanceOf[Number].longValue
    var pathsFromSourceVertexBuilder = new VectorBuilder[Tuple]

    if (minHops == 0) {
      pathsFromSourceVertexBuilder += buildResultTuple(inputTuple, Path(), Vector(sourceId))
    }

    val reachableFromSource = reachableVertices.getOrElse(sourceId, mutable.HashMap.empty)
    pathsFromSourceVertexBuilder ++= reachableFromSource.keysIterator.flatMap(
      targetId => pathTuplesToTrackedTarget(inputTuple, targetId, reachableFromSource(targetId))
    )

    pathsFromSourceVertexBuilder.result
  }

  private def pathTuplesToTrackedTarget(sourceTuple: Tuple, targetId: Long, paths: mutable.MutableList[PathData]): Iterable[Tuple] = {
    paths.filter(pathData => isInRange(pathData.path))
         .flatMap(pathData => composeTupleSingleSource(sourceTuple, pathData.path, targetId))
  }

  private def pathsToTargetVertex(targetId: Long, targetTuple: Tuple) = {
    reachableFrom.getOrElse(targetId, Set.empty)
      .filter(sourceLookup.contains)
      .flatMap(sourceId => pathTuplesFromTrackedSource(targetTuple, sourceId, reachableVertices(sourceId)(targetId)))
  }

  private def pathTuplesFromTrackedSource(targetTuple: Tuple, sourceId: Long, paths: mutable.MutableList[PathData]): Iterable[Tuple] = {
    paths.filter(pathData => isInRange(pathData.path))
         .flatMap(pathData => composeTupleSingleTarget(targetTuple, pathData.path, sourceId))
  }

  private def isInRange(path: Path): Boolean = {
    path.size >= minHops && path.size <= maxHops
  }

  private def buildResultTuple(sourceTuple: Tuple, path: Path, targetTuple: Tuple): Tuple = {
    var resultTupleBuilder = new VectorBuilder[Any]
    resultTupleBuilder ++= sourceTuple
    resultTupleBuilder += path
    resultTupleBuilder ++= targetTuple
    resultTupleBuilder.result
  }

  private def composeTuple(sourceId: Long, path: Path, targetId: Long) = {
    for(sourceTuple <- sourceLookup.getOrElse(sourceId, mutable.Buffer.empty[Tuple]); targetTuple <- targetLookup.values(targetId))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  private def composeTupleSingleSource(sourceTuple: Tuple, path: Path, targetId: Long) = {
    for(targetTuple <- targetLookup.values(targetId))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  private def composeTupleSingleTarget(targetTuple: Tuple, path: Path, sourceId: Long) = {
    for(sourceTuple <- sourceLookup.getOrElse(sourceId, mutable.Buffer.empty[Tuple]))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  case class PathData(path: Path, pathHash: mutable.HashSet[Long]) {
    override def equals(other: Any) = other match {
      case that: PathData => that.path.equals(this.path)
      case _ => false
    }

    override def hashCode = path.hashCode
  }
}
