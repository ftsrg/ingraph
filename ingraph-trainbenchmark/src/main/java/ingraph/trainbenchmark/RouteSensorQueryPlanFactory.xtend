package ingraph.trainbenchmark

import relalg.Direction

class RouteSensorQueryPlanFactory extends QueryPlanFactory {

	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"]
	val sensorLabel = createVertexLabel => [name = "Sensor"]
	val switchLabel = createVertexLabel => [name = "Switch"]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"]

	// edge labels
	val followsLabel = createEdgeLabel => [name = "follows"]
	val gathersLabel = createEdgeLabel => [name = "gathers"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	val targetLabel = createEdgeLabel => [name = "target"]

	// vertex variables
	val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
	val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
	val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel]
	val sensor = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel]

	// edge variables
	val target = createEdgeVariable => [name = "_e1"; edgeLabel = targetLabel]
	val monitoredBy = createEdgeVariable => [name = "_e2"; edgeLabel = monitoredByLabel]
	val follows = createEdgeVariable => [name = "_e3"; edgeLabel = followsLabel]
	val gathers = createEdgeVariable => [name = "_e4"; edgeLabel = gathersLabel]

	// inputs
	val getRoutes = createGetVerticesOperator => [vertexVariable = route]
	val getSws = createGetVerticesOperator => [vertexVariable = sw]
	val getSwPs = createGetVerticesOperator => [vertexVariable = swP]
	val getSensor = createGetVerticesOperator => [vertexVariable = sensor]

	def routeSensorA() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
		]
		val production = createProductionOperator => [input = antiJoin]

		container.rootExpression = production
		container.addSchemaInformation
		return container
	}

	def routeSensorB() {
		val expand1 = createExpandOperator => [
			input = getSwPs
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.IN
			sourceVertexVariable = swP
			targetVertexVariable = route
			edgeVariable = follows
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
		]
		val production = createProductionOperator => [input = antiJoin]

		container.rootExpression = production
		container.addSchemaInformation
		return container
	}

	def routeSensorC() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
		]
		val expand2 = createExpandOperator => [
			input = getSws
			direction = Direction.IN
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand3 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = gathers
		]
		val expand4 = createExpandOperator => [
			input = getSwPs
			direction = Direction.IN
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand2
		]
		val antiJoin = createAntiJoinOperator => [
			leftInput = join1
			rightInput = expand3
		]
		val join2 = createJoinOperator => [
			leftInput = antiJoin
			rightInput = expand4
		]
		val production = createProductionOperator => [input = join2]

		container.rootExpression = production
		container.addSchemaInformation
		return container
	}

}
