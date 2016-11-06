package ingraph.optimization.data

import java.util.Arrays
import relalg.ArithmeticComparisonOperator
import relalg.Direction
import relalg.RelalgFactory

class TrainBenchmarkUtil {

	static val extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def static posLength() {
		val posLength = createRelalgContainer

		val segmentLabel = createVertexLabel => [name = "Segment"; container = posLength]
		val segment = createVertexVariable => [
			name = "segment"
			vertexLabels.add(segmentLabel)
			container = posLength
		]
		val length = createAttributeVariable => [name = "length"; element = segment; container = posLength]

		val getVertices = createGetVerticesOperator => [vertexVariable = segment]

		val integerLiteral0 = createIntegerLiteral => [value = 0]
		val condition = createArithmeticComparisonExpression => [
			leftOperand = length
			rightOperand = integerLiteral0
			operator = ArithmeticComparisonOperator.LESS_THAN_OR_EQUAL
		]
		val filter1 = createSelectionOperator => [
			input = getVertices
			conditionString = "segment.length <= 0"
			it.condition = condition
		]
		val projection = createProjectionOperator => [
			input = filter1
			variables.addAll(#[segment, length])
		]
		val de = createDuplicateEliminationOperator => [input = projection]
		val production = createProductionOperator => [input = de]
		posLength.rootExpression = production
		posLength
	}

	def static routeSensor() {
		val routeSensor = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; container = routeSensor]
		val sensorLabel = createVertexLabel => [name = "Sensor"; container = routeSensor]
		val switchLabel = createVertexLabel => [name = "Switch"; container = routeSensor]
		val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; container = routeSensor]

		val followsLabel = createEdgeLabel => [name = "follows"; container = routeSensor]
		val gathersLabel = createEdgeLabel => [name = "gathers"; container = routeSensor]
		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = routeSensor]
		val targetLabel = createEdgeLabel => [name = "target"; container = routeSensor]

		val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel); container = routeSensor]
		val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel); container = routeSensor]
		val swP = createVertexVariable => [name = "swP"; vertexLabels.add(switchPositionLabel); container = routeSensor]
		val sensor = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel); container = routeSensor]

		val _e1 = createEdgeVariable => [
			name = "_e1"
			edgeLabels.add(followsLabel)
			dontCare = true
			container = routeSensor
		]
		val _e2 = createEdgeVariable => [
			name = "_e2"
			edgeLabels.add(targetLabel)
			dontCare = true
			container = routeSensor
		]
		val _e3 = createEdgeVariable => [
			name = "_e3"
			edgeLabels.add(monitoredByLabel)
			dontCare = true
			container = routeSensor
		]
		val _e4 = createEdgeVariable => [
			name = "_e4"
			edgeLabels.add(gathersLabel)
			dontCare = true
			container = routeSensor
		]

		val getVerticesRoute1 = createGetVerticesOperator => [vertexVariable = route]
		val getVerticesRoute2 = createGetVerticesOperator => [vertexVariable = route]

		val expand1 = createExpandOperator => [
			input = getVerticesRoute1
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
			edgeVariables.addAll(#[_e1, _e2, _e3])
		]

		val expand4 = createExpandOperator => [
			input = getVerticesRoute2
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = _e4
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = allDifferent
			rightInput = expand4
		]
		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(route, sensor, swP, sw))
		]
		val de = createDuplicateEliminationOperator => [
			input = projection
		]
		val production = createProductionOperator => [
			input = de
		]
		routeSensor.rootExpression = production
		routeSensor
	}

	def static semaphoreNeighbor() {
		val semaphoreNeighbor = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; container = semaphoreNeighbor]
		val semaphoreLabel = createVertexLabel => [name = "Semaphore"; container = semaphoreNeighbor]
		val sensorLabel = createVertexLabel => [name = "Sensor"; container = semaphoreNeighbor]

		val connectsToLabel = createEdgeLabel => [name = "connectsTo"; container = semaphoreNeighbor]
		val entryLabel = createEdgeLabel => [name = "entry"; container = semaphoreNeighbor]
		val exitLabel = createEdgeLabel => [name = "exit"; container = semaphoreNeighbor]
		val gathersLabel = createEdgeLabel => [name = "gathers"; container = semaphoreNeighbor]
		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = semaphoreNeighbor]

		val route1 = createVertexVariable => [
			name = "route1";
			vertexLabels.add(routeLabel);
			container = semaphoreNeighbor
		]
		val route2 = createVertexVariable => [
			name = "route2";
			vertexLabels.add(routeLabel);
			container = semaphoreNeighbor
		]
		val semaphore = createVertexVariable => [
			name = "semaphore"
			vertexLabels.add(semaphoreLabel)
			container = semaphoreNeighbor
		]
		val sensor1 = createVertexVariable => [
			name = "sensor1";
			vertexLabels.add(sensorLabel);
			container = semaphoreNeighbor
		]
		val sensor2 = createVertexVariable => [
			name = "sensor";
			vertexLabels.add(sensorLabel);
			container = semaphoreNeighbor
		]
		val te1 = createVertexVariable => [name = "te1"; container = semaphoreNeighbor]
		val te2 = createVertexVariable => [name = "te2"; container = semaphoreNeighbor]

		val _e1 = createEdgeVariable => [
			name = "_e1"
			edgeLabels.add(exitLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e2 = createEdgeVariable => [
			name = "_e2"
			edgeLabels.add(gathersLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e3 = createEdgeVariable => [
			name = "_e3"
			edgeLabels.add(monitoredByLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e4 = createEdgeVariable => [
			name = "_e4"
			edgeLabels.add(connectsToLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e5 = createEdgeVariable => [
			name = "_e5"
			edgeLabels.add(monitoredByLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e6 = createEdgeVariable => [
			name = "_e6"
			edgeLabels.add(gathersLabel)
			dontCare = true
			container = semaphoreNeighbor
		]
		val _e7 = createEdgeVariable => [
			name = "_e7"
			edgeLabels.add(entryLabel)
			dontCare = true
			container = semaphoreNeighbor
		]

		val getVertices1 = createGetVerticesOperator => [
			vertexVariable = semaphore
		]
		val getVertices2 = createGetVerticesOperator => [
			vertexVariable = semaphore
		]

		val expand1 = createExpandOperator => [
			input = getVertices1
			direction = Direction.IN
			sourceVertexVariable = semaphore
			targetVertexVariable = route1
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.IN
			sourceVertexVariable = sensor1
			targetVertexVariable = te1
			edgeVariable = _e3
		]
		val expand4 = createExpandOperator => [
			input = expand3
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = _e4
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = _e5
		]
		val expand6 = createExpandOperator => [
			input = expand5
			direction = Direction.IN
			sourceVertexVariable = sensor2
			targetVertexVariable = route2
			edgeVariable = _e6
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand6
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3, _e4, _e5, _e6))
		]

		val expand7 = createExpandOperator => [
			input = getVertices2
			direction = Direction.IN
			sourceVertexVariable = semaphore
			targetVertexVariable = route2
			edgeVariable = _e7
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = allDifferent
			rightInput = expand7
		]
		val condition = createArithmeticComparisonExpression => [
			leftOperand = route1
			rightOperand = route2
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO			
		]		
		val filter = createSelectionOperator => [
			input = antiJoin
			conditionString = "route1 != route2"
			it.condition = condition
		]
		val projection = createProjectionOperator => [
			input = filter
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val de = createDuplicateEliminationOperator => [
			input = projection
		]
		val production = createProductionOperator => [
			input = de
		]
		semaphoreNeighbor.rootExpression = production
		semaphoreNeighbor
	}

	def static switchMonitored() {
		val switchMonitored = createRelalgContainer

		val sensorLabel = createVertexLabel => [name = "Sensor"; container = switchMonitored]
		val switchLabel = createVertexLabel => [name = "Switch"; container = switchMonitored]

		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = switchMonitored]

		val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel); container = switchMonitored]
		val _sensor = createVertexVariable => [
			name = "_sensor"
			vertexLabels.add(sensorLabel)
			dontCare = true
			container = switchMonitored
		]

		val _e1 = createEdgeVariable => [
			name = "_e1"
			edgeLabels.add(monitoredByLabel)
			dontCare = true
			container = switchMonitored
		]

		val getVertices1 = createGetVerticesOperator => [
			vertexVariable = sw
		]
		val getVertices2 = createGetVerticesOperator => [
			vertexVariable = sw
		]
		
		val expand1 = createExpandOperator => [
			input = getVertices2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = _sensor
			edgeVariable = _e1
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = getVertices1
			rightInput = expand1
		]
		val de = createDuplicateEliminationOperator => [
			input = antiJoin
		]
		val production = createProductionOperator => [
			input = de
		]
		switchMonitored.rootExpression = production
		switchMonitored
	}

	def static switchSet() {
		val switchSet = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; container = switchSet]
		val semaphoreLabel = createVertexLabel => [name = "Semaphore"; container = switchSet]
		val switchLabel = createVertexLabel => [name = "Switch"; container = switchSet]
		val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; container = switchSet]

		val entryLabel = createEdgeLabel => [name = "entry"; container = switchSet]
		val followsLabel = createEdgeLabel => [name = "follows"; container = switchSet]
		val targetLabel = createEdgeLabel => [name = "target"; container = switchSet]

		val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel); container = switchSet]
		val sw = createVertexVariable => [
			name = "sw"
			vertexLabels.add(switchLabel)
			container = switchSet
		]
		val swP = createVertexVariable => [
			name = "swP"
			vertexLabels.add(switchPositionLabel)
			container = switchSet
		]
		val semaphore = createVertexVariable => [
			name = "semaphore"
			vertexLabels.add(semaphoreLabel)
			container = switchSet
		]

		val currentPosition = createAttributeVariable => [name = "currentPosition"; element = sw; container = switchSet]
		val position = createAttributeVariable => [name = "position"; element = swP; container = switchSet]
		val signal = createAttributeVariable => [name = "signal"; element = semaphore; container = switchSet]

		val _e1 = createEdgeVariable => [
			name = "_e1"
			edgeLabels.add(entryLabel)
			dontCare = true
			container = switchSet
		]
		val _e2 = createEdgeVariable => [
			name = "_e2"
			edgeLabels.add(followsLabel)
			dontCare = true
			container = switchSet
		]
		val _e3 = createEdgeVariable => [
			name = "_e3"
			edgeLabels.add(targetLabel)
			dontCare = true
			container = switchSet
		]

		val getVertices = createGetVerticesOperator => [
			vertexVariable = semaphore
		]

		val expand1 = createExpandOperator => [
			input = getVertices
			direction = Direction.IN
			sourceVertexVariable = semaphore
			targetVertexVariable = route
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = _e3
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand3
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))
		]

		val stringLiteralGO = createStringLiteral => [value = "GO"]
		val condition1 = createArithmeticComparisonExpression => [
			leftOperand = signal
			rightOperand = stringLiteralGO
			operator = ArithmeticComparisonOperator.EQUAL_TO
		]
		val filter1 = createSelectionOperator => [
			input = allDifferent
			conditionString = "semaphore.signal = 'GO'"
			it.condition = condition1
		]

		val condition2 = createArithmeticComparisonExpression => [
			leftOperand = currentPosition 
			rightOperand = position
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
		]
		val filter2 = createSelectionOperator => [
			input = filter1
			conditionString = "sw.currentPosition != swP.position"
			it.condition = condition2
		]
		val projection = createProjectionOperator => [
			input = filter2
			variables.addAll(Arrays.asList(semaphore, route, swP, sw, currentPosition, position))
		]
		val de = createDuplicateEliminationOperator => [
			input = projection
		]
		val production = createProductionOperator => [
			input = de
		]
		switchSet.rootExpression = production
		switchSet
	}

}
