package ingraph.trainbenchmark

import relalg.Direction

class RouteSensorQueryPlanFactory extends QueryPlanFactory {

	// container
	val routeSensor = createRelalgContainer

	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"; container = routeSensor]
	val sensorLabel = createVertexLabel => [name = "Sensor"; container = routeSensor]
	val switchLabel = createVertexLabel => [name = "Switch"; container = routeSensor]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; container = routeSensor]

	// edge labels
	val followsLabel = createEdgeLabel => [name = "follows"; container = routeSensor]
	val gathersLabel = createEdgeLabel => [name = "gathers"; container = routeSensor]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = routeSensor]
	val targetLabel = createEdgeLabel => [name = "target"; container = routeSensor]

	// vertex variables
	val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel); container = routeSensor]
	val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel); container = routeSensor]
	val swP = createVertexVariable => [name = "swP"; vertexLabels.add(switchPositionLabel); container = routeSensor]
	val sensor = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel); container = routeSensor]

	// edge variables
	val target = createEdgeVariable => [name = "_e1"; edgeLabel = targetLabel; container = routeSensor]
	val monitoredBy = createEdgeVariable => [name = "_e2"; edgeLabel = monitoredByLabel; container = routeSensor]
	val follows = createEdgeVariable => [name = "_e3"; edgeLabel = followsLabel; container = routeSensor]
	val gathers = createEdgeVariable => [name = "_e4"; edgeLabel = gathersLabel; container = routeSensor]

	// inputs
	val getRoutes = createGetVerticesOperator => [vertexVariable = route; container = routeSensor]
	val getSws = createGetVerticesOperator => [vertexVariable = sw; container = routeSensor]
	val getSwPs = createGetVerticesOperator => [vertexVariable = swP; container = routeSensor]
	val getSensor = createGetVerticesOperator => [vertexVariable = sensor; container = routeSensor]

	def routeSensorA() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
			container = routeSensor
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
			container = routeSensor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
			container = routeSensor
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
			container = routeSensor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
			container = routeSensor
		]
		val production = createProductionOperator => [
			input = antiJoin
			container = routeSensor
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

	def routeSensorB() {
		val expand1 = createExpandOperator => [
			input = getSwPs
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
			container = routeSensor
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
			container = routeSensor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.IN
			sourceVertexVariable = swP
			targetVertexVariable = route
			edgeVariable = follows
			container = routeSensor
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
			container = routeSensor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
			container = routeSensor
		]
		val production = createProductionOperator => [
			input = antiJoin
			container = routeSensor
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

	def routeSensorC() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
			container = routeSensor
		]
		val expand2 = createExpandOperator => [
			input = getSws
			direction = Direction.IN
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
			container = routeSensor
		]
		val expand3 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
			container = routeSensor
		]
		val expand4 = createExpandOperator => [
			input = getSwPs
			direction = Direction.IN
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
			container = routeSensor
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand2
			container = routeSensor
		]
		val antiJoin = createAntiJoinOperator => [
			leftInput = join1
			rightInput = expand3
			container = routeSensor
		]
		val join2 = createJoinOperator => [
			leftInput = antiJoin
			rightInput = expand4
			container = routeSensor
		]
		val production = createProductionOperator => [
			input = join2
			container = routeSensor
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

}
