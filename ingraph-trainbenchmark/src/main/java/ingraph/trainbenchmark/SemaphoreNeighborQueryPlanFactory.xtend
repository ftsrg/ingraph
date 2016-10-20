package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction
import relalg.BinaryArithmeticOperator
import relalg.ArithmeticComparisonOperator

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
	val semaphore = createVertexVariable => [
		name = "semaphore";
		vertexLabels.add(semaphoreLabel);
		container = semaphoreNeighbor
	]
	val sensor1 = createVertexVariable => [
		name = "sensor1";
		vertexLabels.add(sensorLabel);
		container = semaphoreNeighbor
	]
	val sensor2 = createVertexVariable =>
		[name = "sensor"; vertexLabels.add(sensorLabel); container = semaphoreNeighbor]
	val te1 = createVertexVariable => [name = "te1"; container = semaphoreNeighbor]
	val te2 = createVertexVariable => [name = "te2"; container = semaphoreNeighbor]

	// edge variables
	val gathers1 = createEdgeVariable => [name = "g1"; edgeLabel = gathersLabel; container = semaphoreNeighbor]
	val gathers2 = createEdgeVariable => [name = "g2"; edgeLabel = gathersLabel; container = semaphoreNeighbor]
	val monitoredBy1 = createEdgeVariable => [name = "mb1"; edgeLabel = monitoredByLabel; container = semaphoreNeighbor]
	val monitoredBy2 = createEdgeVariable => [name = "mb2"; edgeLabel = monitoredByLabel; container = semaphoreNeighbor]
	val connectsTo = createEdgeVariable => [name = "ct"; edgeLabel = connectsToLabel; container = semaphoreNeighbor]
	val entry = createEdgeVariable => [name = "entry"; edgeLabel = entryLabel; container = semaphoreNeighbor]
	val exit = createEdgeVariable => [name = "exit"; edgeLabel = exitLabel; container = semaphoreNeighbor]

	// inputs
	val getSemaphores = createGetVerticesOperator => [vertexVariable = semaphore; container = semaphoreNeighbor]
	val getRoute1s = createGetVerticesOperator => [vertexVariable = route1; container = semaphoreNeighbor]
	val getRoute2s = createGetVerticesOperator => [vertexVariable = route2; container = semaphoreNeighbor]
	val getSensor1s = createGetVerticesOperator => [vertexVariable = sensor1; container = semaphoreNeighbor]
	val getSensor2s = createGetVerticesOperator => [vertexVariable = sensor2; container = semaphoreNeighbor]
	val getTE1s = createGetVerticesOperator => [vertexVariable = te1; container = semaphoreNeighbor]
	val getTE2s = createGetVerticesOperator => [vertexVariable = te2; container = semaphoreNeighbor]

	def semaphoreNeighborA() {
		val expand1 = createExpandOperator => [
			input = getTE1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
			container = semaphoreNeighbor
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
			container = semaphoreNeighbor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
			container = semaphoreNeighbor
		]

		val expand4 = createExpandOperator => [
			input = getRoute1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
			container = semaphoreNeighbor
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.IN
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = gathers1
		]

		val expand6 = createExpandOperator => [
			input = getRoute2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = gathers2
			container = semaphoreNeighbor
		]

		val expand7 = createExpandOperator => [
			input = getRoute2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
			container = semaphoreNeighbor
		]

		val join1 = createJoinOperator => [
			leftInput = expand3
			rightInput = expand5
			container = semaphoreNeighbor
		]
		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand6
			container = semaphoreNeighbor
		]

		val filterCondition = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
			leftOperand = route1
			rightOperand = route2
			container = semaphoreNeighbor
		]
		val filter = createSelectionOperator => [
			input = join2
			condition = filterCondition
			container = semaphoreNeighbor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
			container = semaphoreNeighbor
		]

		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
			container = semaphoreNeighbor
		]
		val production = createProductionOperator => [
			input = trimmer
			container = semaphoreNeighbor
		]
		semaphoreNeighbor.rootExpression = production
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
