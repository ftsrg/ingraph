package hu.bme.mit.ire.nodes.binary

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.SizeCounter
import scala.collection.mutable.HashSet
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.SetMultimap


class TransitiveClosureJoinNode(override val next: (ReteMessage) => Unit,
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
  val targetLookup = new SetMultimap[Long, Tuple]

  val sourceVertexIndex = primaryMask(0)
  val targetVertexIndex = secondaryMask(0)

  def buildResultTuple(sourceTuple: Tuple, path: Path, targetTuple: Tuple): Tuple = {
    var resultTupleBuilder = new VectorBuilder[Any]
    resultTupleBuilder ++= sourceTuple
    resultTupleBuilder += path
    resultTupleBuilder ++= targetTuple
    resultTupleBuilder.result
  }

  def composeTuple(sourceId: Long, path: Path, targetId: Long) = {
    println(sourceId + "->" + targetId + " through " + path)

    for(sourceTuple <- sourceLookup.getOrElse(sourceId, mutable.Buffer.empty[Tuple]); targetTuple <- targetLookup.getOrElse(targetId, HashSet.empty[Tuple]))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  def composeTupleSingleSource(sourceTuple: Tuple, path: Path, targetId: Long) = {
    for(targetTuple <- targetLookup.getOrElse(targetId, HashSet.empty[Tuple]))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  def composeTupleSingleTarget(targetTuple: Tuple, path: Path, sourceId: Long) = {
    for(sourceTuple <- sourceLookup.getOrElse(sourceId, mutable.Buffer.empty[Tuple]))
      yield buildResultTuple(sourceTuple, path, targetTuple)
  }

  val sourceVerticesIndexer = new HashSet[Long]

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[PathData]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)

  override def onPrimary(changeSet: ChangeSet): Unit = {
    for (t <- changeSet.positive)
      sourceLookup.addBinding(t(sourceVertexIndex).asInstanceOf[Number].longValue, t)
    for (t <- changeSet.negative)
      sourceLookup.removeBinding(t(sourceVertexIndex).asInstanceOf[Number].longValue, t)

//    println("sl: " + sourceLookup)
//    changeSet.positive.map(x => println(s"pri> +${x.length} ${x}"))
//    changeSet.negative.map(x => println(s"pri> -${x.length} ${x}"))

    val positiveTuples = changeSet.positive.flatMap(pathsFromSourceVertex)
    val negativeTuples = changeSet.negative.flatMap(pathsFromSourceVertex)

    changeSet.positive.map(inputTuple => inputTuple(sourceVertexIndex).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.add(_))
    changeSet.negative.map(inputTuple => inputTuple(sourceVertexIndex).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.remove(_))

//    positiveTuples.map(x => println(s"pri-out> +${x.length} ${x}"))
//    positiveTuples.foreach(x => if (x.length != outputTupleWidth) {throw new IllegalStateException})
//    negativeTuples.map(x => println(s"pri-out> -${x.length} ${x}"))
//    negativeTuples.foreach(x => if (x.length != outputTupleWidth) {throw new IllegalStateException})

    forward(ChangeSet(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    // in the current implementation we can assume that the secondary input is a GetEdges operator
//    for (t <- changeSet.positive)
//      targetLookup.addBinding(t(targetVertexIndex).asInstanceOf[Number].longValue, t(targetVertexIndex) +: t.drop(3))
//    for (t <- changeSet.negative)
//      targetLookup.removeBinding(t(targetVertexIndex).asInstanceOf[Number].longValue, t(targetVertexIndex) +: t.drop(3))

//    println("tl: " + targetLookup)
//    changeSet.positive.map(x => println(s"sec> +${x.length} ${x}"))
//    changeSet.negative.map(x => println(s"sec> -${x.length} ${x}"))

    val positiveTuples = changeSet.positive.flatMap(newPathsWithEdge)
    val negativeTuples = changeSet.negative.flatMap(removePathsWithEdge)

//    positiveTuples.map(x => println(s"sec-out> +${x.length} ${x}"))
//    positiveTuples.foreach(x => if (x.length != outputTupleWidth) {throw new IllegalStateException})
//    negativeTuples.map(x => println(s"sec-out> -${x.length} ${x}"))
//    negativeTuples.foreach(x => if (x.length != outputTupleWidth) {throw new IllegalStateException})

    forward(ChangeSet(
      positive = positiveTuples,
      negative = negativeTuples
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
      targetLookup.addBinding(targetId, targetTuple)
    }

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
    val targetTuple: Tuple = inputTuple(targetVertexIndex) +: inputTuple.drop(3)

    var removedPathsBuilder = new VectorBuilder[Tuple]

    if(!reachableVertices.contains(sourceId) || !reachableFrom.contains(targetId)){
      return Vector.empty
    }

    val sourceVertices = reachableFrom.getOrElse(sourceId, Set.empty) ++ Set(sourceId)
    val targetVertices = reachableVertices.getOrElse(targetId, Map.empty).keySet ++ Set(targetId)
    for (source <- sourceVertices; target <- targetVertices) {
      val containingPaths = reachableVertices(source).getOrElse(target, mutable.MutableList.empty).filter(_.pathHash.contains(edgeId))
      if (sourceVerticesIndexer.contains(source)) {
        removedPathsBuilder ++= containingPaths.filter(pathData => isInRange(pathData.path))
                                               .flatMap(pathData => composeTuple(source, pathData.path, target))
      }

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

    //targetLookup.removeBinding(targetId, targetTuple)

    removedPathsBuilder.result
  }

  private def pathsFromSourceVertex(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(sourceVertexIndex).asInstanceOf[Number].longValue
    var pathsFromSourceVertexBuilder = new VectorBuilder[Tuple]

    if (minHops == 0)
      pathsFromSourceVertexBuilder += buildResultTuple(inputTuple, Path(), inputTuple) // is it OK after this one is already added to lookup? I think yes.

    val reachableFromSource = reachableVertices.getOrElse(sourceId, mutable.HashMap.empty)
    pathsFromSourceVertexBuilder ++= reachableFromSource.keysIterator.flatMap(
      targetId => pathTuplesToTrackedTarget(inputTuple, targetId, reachableFromSource(targetId))
    )

    pathsFromSourceVertexBuilder.result
  }

  private def pathTuplesToTrackedTarget(sourceTuple: Tuple, targetId: Long, paths: mutable.MutableList[PathData]): Iterable[Tuple] = {
    paths.filter(pathData => isInRange(pathData.path))
         .map(pathData => composeTupleSingleSource(sourceTuple, pathData.path, targetId))
         .flatten
  }

  private def pathsToTargetVertex(targetId: Long, targetTuple: Tuple) = {
    reachableFrom.getOrElse(targetId, Set.empty)
      .filter(sourceVerticesIndexer.contains(_))
      .flatMap(sourceId => pathTuplesFromTrackedSource(targetTuple, sourceId, reachableVertices(sourceId)(targetId)))
  }

  private def pathTuplesFromTrackedSource(targetTuple: Tuple, sourceId: Long, paths: mutable.MutableList[PathData]): Iterable[Tuple] = {
    paths.filter(pathData => isInRange(pathData.path))
         .flatMap(pathData => composeTupleSingleTarget(targetTuple, pathData.path, sourceId))
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
