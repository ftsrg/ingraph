package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction

class RouteSensorQueryPlanFactory extends QueryPlanFactory {

	val routeLabel = createVertexLabel => [name = "Route"; it.container = container]
	val sensorLabel = createVertexLabel => [name = "Sensor"; it.container = container]
	val switchLabel = createVertexLabel => [name = "Switch"; it.container = container]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; it.container = container]

	val followsLabel = createEdgeLabel => [name = "follows"; it.container = container]
	val gathersLabel = createEdgeLabel => [name = "gathers"; it.container = container]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; it.container = container]
	val targetLabel = createEdgeLabel => [name = "target"; it.container = container]

	val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel; it.container = container]
	val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel; it.container = container]
	val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel; it.container = container]
	val sensor = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel; it.container = container]

	def routeSensorA() {
		val _e1 = createEdgeVariable => [
			name = "_e1";
			edgeLabel = followsLabel;
			dontCare = true;
			it.container = container
		]
		val _e2 = createEdgeVariable => [
			name = "_e2";
			edgeLabel = targetLabel;
			dontCare = true;
			it.container = container
		]
		val _e3 = createEdgeVariable => [
			name = "_e3";
			edgeLabel = monitoredByLabel;
			dontCare = true;
			it.container = container
		]
		val _e4 = createEdgeVariable => [
			name = "_e4";
			edgeLabel = gathersLabel;
			dontCare = true;
			it.container = container
		]

		val getVertices = createGetVerticesOperator => [vertexVariable = route]

		val expand1 = createExpandOperator => [
			input = getVertices
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = _e3
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand3
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))
		]

		val expand4 = createExpandOperator => [
			input = getVertices
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = _e4
		]

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand4] // FIXME: [route, sensor]
		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(route, sensor, swP, sw))
		]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		container.rootExpression = production
		container
	}

	def routeSensorB() {
		container
	}

	def routeSensorC() {
		container
	}

}
