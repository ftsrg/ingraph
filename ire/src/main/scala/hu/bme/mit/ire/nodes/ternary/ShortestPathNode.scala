package hu.bme.mit.ire.nodes.ternary

import scala.collection.mutable

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.datatypes.Path
import scala.collection.mutable.HashSet
import scala.collection.immutable.TreeMap
import hu.bme.mit.ire.messages.Δ
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.TestUtil.tuple
import scala.collection.immutable.SortedMap
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable.MutableList

class ShortestPathNode(override val next: (ReteMessage) => Unit,
                            val src: Int,
                            val trg: Int,
                            val edge: Int,
                            val allShortestPaths: Boolean = false,
                            var minPathLength: Int = 1,
                            var maxPathLength: Int = Int.MaxValue
                           ) extends TernaryNode with SingleForwarder {

  minPathLength = if (minPathLength > 1) minPathLength else 1
  maxPathLength = if (maxPathLength > 1) maxPathLength else Int.MaxValue

  val shortestPathFunction: ((Long, Long, TreeMap[Int, mutable.MutableList[Path]]) => Iterable[Tuple]) = if (allShortestPaths) getAllShortestPaths else getShortestPath
  val removeShortestPathBetweenVericesFunction: (Long, Long, Long, PathChangeSet) => Unit = if (allShortestPaths) removeAllShortestPathsBetweenVerices else removeShortestPathBetweenVerices
  val addNewShortestPathBetweenVericesFunction: (PathChangeSet) => Unit = if (allShortestPaths) addAllNewShortestPathsBetweenVerices else addNewShortestPathBetweenVerices

  val sourceVerticesIndexer = new HashSet[Long]
  val targetVerticesIndexer = new HashSet[Long]

  val reachableVertices = new mutable.HashMap[Long, mutable.HashMap[Long, TreeMap[Int, mutable.MutableList[Path]]]]
  val reachableFrom = new mutable.HashMap[Long, mutable.HashSet[Long]]

  override def onSizeRequest(): Long = SizeCounter.count(reachableVertices.values, reachableFrom.values)

  def onPrimary(changeSet: Δ): Unit = {
    val positiveTuples = changeSet.positive.flatMap(shortestPathsFromSourceVertex)
    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.add(_))

    val negativeTuples = changeSet.negative.flatMap(shortestPathsFromSourceVertex)
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(sourceVerticesIndexer.remove(_))

    forward(Δ(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  def onSecondary(changeSet: Δ): Unit = {
    val positiveTuples = changeSet.positive.flatMap(shortestPathsToTargetVertex)
    changeSet.positive.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(targetVerticesIndexer.add(_))

    val negativeTuples = changeSet.negative.flatMap(shortestPathsToTargetVertex)
    changeSet.negative.map(inputTuple => inputTuple(0).asInstanceOf[Number].longValue).foreach(targetVerticesIndexer.remove(_))

    forward(Δ(
      positive = positiveTuples,
      negative = negativeTuples
    ))
  }

  def onTernary(changeSet: Δ): Unit = {
    val positivePathChangeSet = changeSet.positive.map(mapNewEdgeToNewShortestPaths).reduceOption(reducePathChangeSets).getOrElse(PathChangeSet())
    changeSet.positive.foreach(newPathsWithEdge)

    val negativePathChangeSet = PathChangeSet()
    changeSet.negative.foreach(oldEdgeToNewShortestPaths(_, negativePathChangeSet))
    addNewShortestPathBetweenVericesFunction(negativePathChangeSet)

    forward(convertPathChangeSetToChangeSet(positivePathChangeSet))
  }

  private def convertPathChangeSetToChangeSet(pcs: PathChangeSet): Δ = {
    Δ(
      positive = pcs.positive.iterator.flatMap(sourceEntry => sourceEntry._2.iterator.flatMap(targetEntry => targetEntry._2.map(path => tuple(sourceEntry._1, targetEntry._1, path)))).toVector,
      negative = pcs.negative.iterator.flatMap(sourceEntry => sourceEntry._2.iterator.flatMap(targetEntry => targetEntry._2.map(path => tuple(sourceEntry._1, targetEntry._1, path)))).toVector
    )
  }

  private def mapNewEdgeToNewShortestPaths(inputTuple: Tuple): PathChangeSet = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    val pathChangeSet = PathChangeSet()

    for(headPathSource <- reachableFrom.getOrElse(sourceId, mutable.HashSet.empty)  + sourceId; tailPathTarget <- reachableVertices.getOrElse(targetId, mutable.HashMap.empty).keysIterator ++ Iterator(targetId)){
      if(sourceVerticesIndexer.contains(headPathSource) && targetVerticesIndexer.contains(tailPathTarget))
        newShortestPathsBetweenVerices(headPathSource, sourceId, targetId, tailPathTarget, edgeId, pathChangeSet)
    }

    pathChangeSet
  }

  private def newShortestPathsBetweenVerices(headPathSource: Long, headPathTarget: Long, tailPathSource: Long, tailPathTarget: Long, edgeId: Long, pathChangeSet: PathChangeSet) = {
    val headPaths = reachableVertices(headPathSource).getOrElse(headPathTarget, TreeMap[Int, mutable.MutableList[Path]](0 -> MutableList(Path())))
    val tailPaths = reachableVertices.getOrElse(tailPathSource, mutable.HashMap.empty).getOrElse(tailPathTarget, TreeMap[Int, mutable.MutableList[Path]](0 -> MutableList(Path())))

    val prevPathLengthMin = reachableVertices(headPathSource).getOrElse(tailPathTarget, TreeMap[Int, mutable.MutableList[Path]](Int.MaxValue -> MutableList.empty)).firstKey

    var pathLengthMin = prevPathLengthMin
    val tailPathsArray = tailPaths.keysIterator.toArray
    var headLengthMin = 0
    for (headLength <- headPaths.keysIterator) {
      val newMinCandidate = findShortestPathLengthRecursion(headLength, 0, tailPathsArray.size - 1, tailPathsArray, pathLengthMin)
      if(newMinCandidate <= pathLengthMin) {
        pathLengthMin = newMinCandidate
        headLengthMin = headLength
      }
    }

    if(pathLengthMin < prevPathLengthMin) {
      val oldShortestPaths = reachableVertices(headPathSource)(tailPathTarget)
      pathChangeSet.negative.getOrElseUpdate(headPathSource, mutable.HashMap.empty).getOrElseUpdate(tailPathTarget, mutable.MutableList.empty) ++= oldShortestPaths(oldShortestPaths.firstKey)
    }

    if (allShortestPaths || pathLengthMin < prevPathLengthMin) {
      val tailLength = pathLengthMin - headLengthMin - 1

      for (headPath <- headPaths.getOrElse(headLengthMin, MutableList.empty); tailPath <- tailPaths.getOrElse(tailLength, MutableList.empty)) {
        if (areDistinctPaths(headPath, tailPath)) {
          pathChangeSet.positive.getOrElseUpdate(headPathSource, mutable.HashMap.empty).getOrElseUpdate(tailPathTarget, mutable.MutableList.empty) += (headPath ++ Vector(edgeId) ++ tailPath)
        }
      }

      if (headLengthMin != tailLength) {
        for (headPath <- headPaths.getOrElse(tailLength, MutableList.empty); tailPath <- tailPaths.getOrElse(headLengthMin, MutableList.empty)) {
          if (areDistinctPaths(headPath, tailPath)) {
            pathChangeSet.positive.getOrElseUpdate(headPathSource, mutable.HashMap.empty).getOrElseUpdate(tailPathTarget, mutable.MutableList.empty) += (headPath ++ Vector(edgeId) ++ tailPath)
          }
        }

        if (pathLengthMin % 2 == 1) {
          val halfLength = (pathLengthMin - 1) / 2
          for (headPath <- headPaths.getOrElse(halfLength, MutableList.empty); tailPath <- tailPaths.getOrElse(halfLength, MutableList.empty)) {
            if (areDistinctPaths(headPath, tailPath)) {
              pathChangeSet.positive.getOrElseUpdate(headPathSource, mutable.HashMap.empty).getOrElseUpdate(tailPathTarget, mutable.MutableList.empty) += (headPath ++ Vector(edgeId) ++ tailPath)
            }
          }
        }
      }
    }
  }

  private def findShortestPathLengthRecursion(headLength: Int, tailFrom: Int, tailTo: Int, tailPaths: Array[Int], currentPathLengthMin: Int): Int = {
    val atTailLength = tailFrom + (tailTo - tailFrom) / 2
    val pathLength = headLength + tailPaths(atTailLength) + 1
    var pathLengthMin = currentPathLengthMin

    if (pathLength < pathLengthMin && isInRange(pathLength)) {
      pathLengthMin = pathLength
    }

    if (atTailLength > tailFrom && pathLength > minPathLength) {
      pathLengthMin = findShortestPathLengthRecursion(headLength, tailFrom, atTailLength - 1, tailPaths, pathLengthMin)
    }

    if (atTailLength < tailTo && pathLength < maxPathLength && pathLengthMin == currentPathLengthMin) {
      pathLengthMin = findShortestPathLengthRecursion(headLength, atTailLength + 1, tailTo, tailPaths, pathLengthMin)
    }

    pathLengthMin
  }

  private def reducePathChangeSets(pcs1: PathChangeSet, pcs2: PathChangeSet): PathChangeSet = {
    for(source <- pcs2.positive.keysIterator) {
      if (!pcs1.positive.contains(source)) {
        pcs1.positive(source) = pcs2.positive(source)
        pcs1.negative(source) = pcs2.negative(source)
      } else {
        for (target <- pcs2.positive(source).keysIterator) {
          if (!pcs1.positive(source).contains(target) || pcs2.positive(source)(target)(0).size < pcs1.positive(source)(target)(0).size) {
            pcs1.positive(source)(target) = pcs2.positive(source)(target)
          }
        }
      }
    }

    pcs1
  }

  private def oldEdgeToNewShortestPaths(inputTuple: Tuple, pathChangeSet: PathChangeSet) = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    for(source <- reachableFrom.getOrElse(sourceId, mutable.HashSet.empty) + sourceId; target <- reachableVertices.getOrElse(targetId, mutable.HashMap.empty).keysIterator ++ Iterator(targetId)){
      if(sourceVerticesIndexer.contains(source) && targetVerticesIndexer.contains(target))
        removeShortestPathBetweenVericesFunction(source, target, edgeId, pathChangeSet)
    }

    removePathsWithEdge(inputTuple)
  }

  private def removeShortestPathBetweenVerices(sourceId: Long, targetId: Long, edgeId: Long, pathChangeSet: PathChangeSet) = {
    val paths = reachableVertices(sourceId)(targetId)
    val shortestPaths = paths(paths.firstKey)
    if(shortestPaths(0).contains(edgeId)){
      pathChangeSet.negative.getOrElseUpdate(sourceId, mutable.HashMap.empty).getOrElseUpdate(targetId, mutable.MutableList.empty) += shortestPaths(0)
    }
  }

  private def removeAllShortestPathsBetweenVerices(sourceId: Long, targetId: Long, edgeId: Long, pathChangeSet: PathChangeSet) = {
    val paths = reachableVertices(sourceId)(targetId)
    val shortestPaths = paths(paths.firstKey)
    val shortestPathsWithEdge = shortestPaths.filter(path => path.contains(edgeId)).toList
    if(shortestPathsWithEdge.nonEmpty){
      pathChangeSet.negative.getOrElseUpdate(sourceId, mutable.HashMap.empty).getOrElseUpdate(targetId, mutable.MutableList.empty) ++= shortestPathsWithEdge
    }
  }

  private def addNewShortestPathBetweenVerices(pcs: PathChangeSet) = {
    for(source <- pcs.negative.keysIterator) {
      for(target <- pcs.negative(source).keysIterator) {
        val pathMap = reachableVertices.getOrElse(source, mutable.HashMap.empty).getOrElse(target, TreeMap.empty[Int, mutable.MutableList[Path]])
        if(pathMap.nonEmpty) {
          pcs.positive.getOrElseUpdate(source, mutable.HashMap.empty).getOrElseUpdate(target, mutable.MutableList.empty) += pathMap(pathMap.firstKey)(0)
        }
      }
    }
  }

  private def addAllNewShortestPathsBetweenVerices(pcs: PathChangeSet) = {
    for(source <- pcs.negative.keysIterator) {
      for(target <- pcs.negative(source).keysIterator) {
        val pathMap = reachableVertices.getOrElse(source, mutable.HashMap.empty).getOrElse(target, TreeMap.empty[Int, mutable.MutableList[Path]])
        if(pathMap.nonEmpty) {
          pcs.positive.getOrElseUpdate(source, mutable.HashMap.empty).getOrElseUpdate(target, mutable.MutableList.empty) ++= pathMap(pathMap.firstKey)
        }
      }
    }
  }

  private def newPathsWithEdge(inputTuple: Tuple): Unit = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    if (!reachableVertices.contains(sourceId)) {
      reachableVertices(sourceId) = new mutable.HashMap
      reachableVertices(sourceId) += (sourceId -> TreeMap[Int, mutable.MutableList[Path]](0 -> MutableList(Path())))
    }

    if (!reachableFrom.contains(targetId)) {
      reachableFrom(targetId) = new mutable.HashSet
    }

    val pathsToSource = pathsToSourceVertex(sourceId)
    val pathsFromTarget = pathsFromTargetVertex(targetId)
    for(pathToSourceVertex <- pathsToSource; pathFromTargetVertex <- pathsFromTarget){
      val path = pathToSourceVertex._2 ++ Vector(edgeId) ++ pathFromTargetVertex._2
      if(path.size <= maxPathLength && areDistinctPaths(pathToSourceVertex._2, pathFromTargetVertex._2)){
        if (!reachableVertices(pathToSourceVertex._1).contains(pathFromTargetVertex._1)) {
          reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) = TreeMap.empty[Int, mutable.MutableList[Path]]
        }

        reachableFrom(pathFromTargetVertex._1) += pathToSourceVertex._1

        if (!reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1).contains(path.size)) {
          reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1) += (path.size -> mutable.MutableList.empty[Path])
        }
        reachableVertices(pathToSourceVertex._1)(pathFromTargetVertex._1)(path.size) += path
      }
    }
  }

  private def removePathsWithEdge(inputTuple: Tuple): Unit = {
    val sourceId = inputTuple(src).asInstanceOf[Number].longValue
    val targetId = inputTuple(trg).asInstanceOf[Number].longValue
    val edgeId = inputTuple(edge).asInstanceOf[Number].longValue

    val sourceVertices = reachableFrom.getOrElse(sourceId, Set.empty) ++ Set(sourceId)
    val targetVertices = reachableVertices.getOrElse(targetId, Map.empty).keySet ++ Set(targetId)
    for (source <- sourceVertices; target <- targetVertices) {
      var pathMap = reachableVertices(source)(target)

      for (pathLength <- pathMap.keys) {
        val containingPaths = pathMap(pathLength).filter(_.contains(edgeId))
        pathMap(pathLength).diff(containingPaths)
        if(pathMap(pathLength).isEmpty) pathMap -= pathLength
      }

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

  private def pathsToSourceVertex(sourceId: Long) = {
    reachableFrom.getOrElse(sourceId, Set.empty).flatMap(startVertex => reachableVertices(startVertex)(sourceId).flatMap(paths => paths._2.map(path => (startVertex, path)))).toList ++ List((sourceId, Path()))
  }

  private def pathsFromTargetVertex(targetId: Long) = {
    reachableVertices.getOrElse(targetId, Map.empty).flatMap(entry => entry._2.flatMap(paths => paths._2.map(path => (entry._1, path)))).toList ++ List((targetId, Path()))
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

  private def shortestPathsFromSourceVertex(inputTuple: Tuple): Vector[Tuple] = {
    val sourceId = inputTuple(0).asInstanceOf[Number].longValue

    var reachableFromSource = reachableVertices.getOrElse(sourceId, mutable.HashMap.empty)
    reachableFromSource.keys.filter(targetVerticesIndexer.contains(_)).flatMap(targetId => shortestPathFunction(sourceId, targetId, reachableFromSource(targetId))).toVector
  }

  private def shortestPathsToTargetVertex(inputTuple: Tuple): Vector[Tuple] = {
    val targetId = inputTuple(0).asInstanceOf[Number].longValue
    reachableFrom.getOrElse(targetId, Set.empty).filter(sourceVerticesIndexer.contains(_)).flatMap(sourceId => shortestPathFunction(sourceId, targetId, reachableVertices(sourceId)(targetId))).toVector
  }

  private def getAllShortestPaths(sourceId: Long, targetId: Long, paths: TreeMap[Int, mutable.MutableList[Path]]): Iterable[Tuple] = {
    val pathsInRange = paths.from(minPathLength)
    if(pathsInRange.firstKey <= maxPathLength) {
      pathsInRange.getOrElse(pathsInRange.firstKey, mutable.MutableList.empty[Path]).map(path => tuple(sourceId, targetId, path))
    }
    else {
      Iterable.empty
    }
  }

  private def getShortestPath(sourceId: Long, targetId: Long, paths: TreeMap[Int, mutable.MutableList[Path]]): Iterable[Tuple] = {
    val pathsInRange = paths.from(minPathLength)
    if(pathsInRange.firstKey <= maxPathLength) {
      pathsInRange.getOrElse(pathsInRange.firstKey, mutable.MutableList.empty[Path]).get(0).map(path => tuple(sourceId, targetId, path))
    }
    else {
      Iterable.empty
    }
  }

  private def isInRange(pathLength: Int): Boolean = {
    pathLength >= minPathLength && pathLength <= maxPathLength
  }
}

case class PathChangeSet(
    positive: mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Path]]] = mutable.HashMap.empty,
    negative: mutable.HashMap[Long, mutable.HashMap[Long, mutable.MutableList[Path]]] = mutable.HashMap.empty
)
