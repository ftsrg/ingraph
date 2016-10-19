package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction

class SemaphoreNeighborQueryPlanFactory extends QueryPlanFactory {

//	val routeLabel = createVertexLabel => [name = "Route"; it.container = container]
//	val semaphoreLabel = createVertexLabel => [name = "Semaphore"; it.container = container]
//	val sensorLabel = createVertexLabel => [name = "Sensor"; it.container = container]
//
//	val connectsToLabel = createEdgeLabel => [name = "connectsTo"; it.container = container]
//	val entryLabel = createEdgeLabel => [name = "entry"; it.container = container]
//	val exitLabel = createEdgeLabel => [name = "exit"; it.container = container]
//	val gathersLabel = createEdgeLabel => [name = "gathers"; it.container = container]
//	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; it.container = container]
//
//	val route1 = createVertexVariable => [name = "route1"; vertexLabel = routeLabel; it.container = container]
//	val route2 = createVertexVariable => [name = "route2"; vertexLabel = routeLabel; it.container = container]
//	val semaphore = createVertexVariable => [
//		name = "semaphore";
//		vertexLabel = semaphoreLabel;
//		it.container = container
//	]
//	val sensor1 = createVertexVariable => [name = "sensor1"; vertexLabel = sensorLabel; it.container = container]
//	val sensor2 = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel; it.container = container]
//	val te1 = createVertexVariable => [name = "te1"; it.container = container]
//	val te2 = createVertexVariable => [name = "te2"; it.container = container]
//
//	def semaphoreNeighborA() {
//		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = exitLabel; dontCare = true; it.container = container]
//		val _e2 = createEdgeVariable => [
//			name = "_e2";
//			edgeLabel = gathersLabel;
//			dontCare = true;
//			it.container = container
//		]
//		val _e3 = createEdgeVariable => [
//			name = "_e3";
//			edgeLabel = monitoredByLabel;
//			dontCare = true;
//			it.container = container
//		]
//		val _e4 = createEdgeVariable => [
//			name = "_e4";
//			edgeLabel = connectsToLabel;
//			dontCare = true;
//			it.container = container
//		]
//		val _e5 = createEdgeVariable => [
//			name = "_e5";
//			edgeLabel = monitoredByLabel;
//			dontCare = true;
//			it.container = container
//		]
//		val _e6 = createEdgeVariable => [
//			name = "_e6";
//			edgeLabel = gathersLabel;
//			dontCare = true;
//			it.container = container
//		]
//		val _e7 = createEdgeVariable =>
//			[name = "_e7"; edgeLabel = entryLabel; dontCare = true; it.container = container]
//
//		val getVertices = createGetVerticesOperator => [vertexVariable = semaphore]
//
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
//		val allDifferent = createAllDifferentOperator => [
//			input = expand6
//			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3, _e4, _e5, _e6))
//		]
//
//		val expand7 = createExpandOperator => [
//			input = getVertices
//			direction = Direction.IN
//			sourceVertexVariable = semaphore
//			targetVertexVariable = route2
//			edgeVariable = _e7
//		]
//
//		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand7] // FIXME: [semaphore, route2]
//		val filter = createSelectionOperator => [input = antiJoin] // FIXME: route1 != route2
//		val trimmer = createProjectionOperator => [
//			input = filter
//			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
//		]
//		val de = createDuplicateEliminationOperator => [input = trimmer]
//		val production = createProductionOperator => [input = de]
//		container.rootExpression = production
//		container
//	}
//
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
