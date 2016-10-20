package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction

class SemaphoreNeighborQueryPlanFactory extends QueryPlanFactory {

	// container
	val semaphoreNeighbor = createRelalgContainer
	
	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"; container = semaphoreNeighbor]
	val semaphoreLabel = createVertexLabel => [name = "Semaphore"; container = semaphoreNeighbor]
	val sensorLabel = createVertexLabel => [name = "Sensor"; container = semaphoreNeighbor]

	// edge labels
	val connectsToLabel = createEdgeLabel => [name = "connectsTo"; container = semaphoreNeighbor]
	val entryLabel = createEdgeLabel => [name = "entry"; container = semaphoreNeighbor]
	val exitLabel = createEdgeLabel => [name = "exit"; container = semaphoreNeighbor]
	val gathersLabel = createEdgeLabel => [name = "gathers"; container = semaphoreNeighbor]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = semaphoreNeighbor]

	// vertex variables
	val route1 = createVertexVariable => [name = "route1"; vertexLabels.add(routeLabel); container = semaphoreNeighbor]
	val route2 = createVertexVariable => [name = "route2"; vertexLabels.add(routeLabel); container = semaphoreNeighbor]
	val semaphore = createVertexVariable => [name = "semaphore"; vertexLabels.add(semaphoreLabel); container = semaphoreNeighbor]
	val sensor1 = createVertexVariable => [name = "sensor1"; vertexLabels.add(sensorLabel); container = semaphoreNeighbor]
	val sensor2 = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel); container = semaphoreNeighbor]
	val te1 = createVertexVariable => [name = "te1"; container = semaphoreNeighbor]
	val te2 = createVertexVariable => [name = "te2"; container = semaphoreNeighbor]

	// edge variables
	val gathers = createEdgeVariable => [name = "_e1"; edgeLabel = gathersLabel; container = semaphoreNeighbor]
	val monitoredBy = createEdgeVariable => [name = "_e2"; edgeLabel = monitoredByLabel; container = semaphoreNeighbor]
	val connectsTo = createEdgeVariable => [name = "_e3"; edgeLabel = connectsToLabel; container = semaphoreNeighbor]
	val entry = createEdgeVariable => [name = "_e4"; edgeLabel = entryLabel; container = semaphoreNeighbor]
	val exit = createEdgeVariable => [name = "_e4"; edgeLabel = exitLabel; container = semaphoreNeighbor]
	
	// inputs
	val getSemaphores = createGetVerticesOperator => [vertexVariable = semaphore; container = semaphoreNeighbor]
	val getRoute1s = createGetVerticesOperator => [vertexVariable = route1; container = semaphoreNeighbor]
	val getRoute2s = createGetVerticesOperator => [vertexVariable = route2; container = semaphoreNeighbor]
	val getSensor1s = createGetVerticesOperator => [vertexVariable = sensor1; container = semaphoreNeighbor]
	val getSensor2s = createGetVerticesOperator => [vertexVariable = sensor2; container = semaphoreNeighbor]
	val getTE1s = createGetVerticesOperator => [vertexVariable = te1; container = semaphoreNeighbor]
	val getTE2s = createGetVerticesOperator => [vertexVariable = te2; container = semaphoreNeighbor]

	def semaphoreNeighborA() {
//		val expand1 = createExpandOperator => [
//			input = getVertices
//			direction = Direction.IN
//			sourceVertexVariable = semaphore
//			targetVertexVariable = route1
//			edgeVariable = _e1
//		]
//		val expand2 = createExpandOperator => [
//			input = expand1
//			direction = Direction.OUT
//			sourceVertexVariable = route1
//			targetVertexVariable = sensor1
//			edgeVariable = _e2
//		]
//		val expand3 = createExpandOperator => [
//			input = expand2
//			direction = Direction.IN
//			sourceVertexVariable = sensor1
//			targetVertexVariable = te1
//			edgeVariable = _e3
//		]
//		val expand4 = createExpandOperator => [
//			input = expand3
//			direction = Direction.OUT
//			sourceVertexVariable = te1
//			targetVertexVariable = te2
//			edgeVariable = _e4
//		]
//		val expand5 = createExpandOperator => [
//			input = expand4
//			direction = Direction.OUT
//			sourceVertexVariable = te2
//			targetVertexVariable = sensor2
//			edgeVariable = _e5
//		]
//		val expand6 = createExpandOperator => [
//			input = expand5
//			direction = Direction.IN
//			sourceVertexVariable = sensor2
//			targetVertexVariable = route2
//			edgeVariable = _e6
//		]
//
//
//		val expand7 = createExpandOperator => [
//			input = getVertices
//			direction = Direction.IN
//			sourceVertexVariable = semaphore
//			targetVertexVariable = route2
//			edgeVariable = _e7
//		]
//
//		val antiJoin = createAntiJoinOperator => [leftInput = ; rightInput = expand7] // FIXME: [semaphore, route2]
//		val filter = createSelectionOperator => [input = antiJoin] // FIXME: route1 != route2
//		val trimmer = createProjectionOperator => [
//			input = filter
//			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
//		]
//		val de = createDuplicateEliminationOperator => [input = trimmer]
//		val production = createProductionOperator => [input = de]
//		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

//	def semaphoreNeighborB() {
//		container
//	}
//
//	def semaphoreNeighborC() {
//		container
//	}
//
//	def semaphoreNeighborD() {
//		container
//	}
//
//	def semaphoreNeighborE() {
//		container
//	}
//
//	def semaphoreNeighborF() {
//		container
//	}

}
