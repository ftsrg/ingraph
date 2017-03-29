package hu.bme.mit.ire.nodes.unary

import scala.Vector
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.Δ
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.nodes.ternary.TernaryNode
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.util.TestUtil.tuple
import scala.collection.mutable.HashSet

class TransitiveClosureNode(override val next: (ReteMessage) => Unit,
                            val src: Int,
                            val trg: Int,
                            val edge: Int,
                            var min: Long = 1,
                            var max: Long = Long.MaxValue
                           ) extends TernaryNode with SingleForwarder {

  min = if (min > 1) min else 1
  max = if (max > 1) max else Long.MaxValue

  val sourceVerticesIndexer = new HashSet[Long]
  val targetVerticesIndexer = new HashSet[Long]

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Path]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)

  def onPrimary(changeSet: Δ): Unit = {
    val positiveTuples = changeSet.positive.flatMap(pathsFromSourceVertex)
    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.add(_))

    val negativeTuples = changeSet.negative.flatMap(pathsFromSourceVertex)
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.remove(_))

    forward(Δ(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  def onSecondary(changeSet: Δ): Unit = {
    val positiveTuples = changeSet.positive.flatMap(pathsToTargetVertex)
    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(targetVerticesIndexer.add(_))

    val negativeTuples = changeSet.negative.flatMap(pathsToTargetVertex)
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(targetVerticesIndexer.remove(_))

    forward(Δ(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  def onTernary(changeSet: Δ): Unit = {
    forward(Δ(
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
        if (sourceVerticesIndexer.contains(pathToSourceVertex._1) && targetVerticesIndexer.contains(pathFromTargetVertex._1) && isInRange(path))
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
      if (sourceVerticesIndexer.contains(source) && targetVerticesIndexer.contains(target)) removedPathsBuilder ++= containingPaths.filter(isInRange).map(tuple(source, target, _))

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

  private def pathsFromSourceVertex(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue

    var reachableFromSource = reachableVertices.getOrElse(sourceId, new mutable.HashMap)
    reachableFromSource.keys.filter(targetVerticesIndexer.contains(_)).flatMap(targetId => pathTuplesToTrackedTarget(sourceId, targetId, reachableFromSource(targetId))).toVector
  }

  private def pathsToTargetVertex(inputTuple: Tuple): Vector[Tuple] = {
    val targetId = inputTuple(0).asInstanceOf[Number].longValue
    reachableFrom.getOrElse(targetId, Set.empty).filter(sourceVerticesIndexer.contains(_)).flatMap(sourceId => pathTuplesToTrackedTarget(sourceId, targetId, reachableVertices(sourceId)(targetId))).toVector
  }

  private def pathTuplesToTrackedTarget(sourceId: Long, targetId: Long, paths: mutable.MutableList[Path]): Iterable[Tuple] = {
    paths.filter(isInRange).map(path => tuple(sourceId, targetId, path))
  }

  private def isInRange(path: Path): Boolean = {
    path.size >= min && path.size <= max
  }

}
