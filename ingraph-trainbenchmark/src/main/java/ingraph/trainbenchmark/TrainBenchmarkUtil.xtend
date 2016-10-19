package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction
import relalg.RelalgFactory

class TrainBenchmarkUtil {

	static val extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def static posLength() {
		val container = createRelalgContainer

		val segmentLabel = createVertexLabel => [name = "Segment"; it.container = container]
		val segment = createVertexVariable => [
			name = "segment"
			vertexLabels.add(segmentLabel)
			it.container = container
		]
		val length = createAttributeVariable => [name = "length"; element = segment; it.container = container]	

		val getVertices = createGetVerticesOperator => [vertexVariable = segment]

//		val integerLiteral0 = createIntegerLiteral => [value = 0]
//		val condition = createArithmeticComparisonExpression => [
//			leftOperand = length
//			rightOperand = integerLiteral0
//			operator = ArithmeticComparisonOperator.LESS_THAN_OR_EQUAL
//		]
		
		val filter1 = createSelectionOperator => [
			input = getVertices
			conditionString = "segment.length <= 0"
//			it.condition = condition
		]
		val trimmer = createProjectionOperator => [
			input = filter1
			variables.addAll(Arrays.asList(segment, length))
		] // FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		container.rootExpression = production
		container
	}

	def static routeSensor() {
		val container = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; it.container = container]
		val sensorLabel = createVertexLabel => [name = "Sensor"; it.container = container]
		val switchLabel = createVertexLabel => [name = "Switch"; it.container = container]
		val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; it.container = container]

		val followsLabel = createEdgeLabel => [name = "follows"; it.container = container]
		val gathersLabel = createEdgeLabel => [name = "gathers"; it.container = container]
		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; it.container = container]
		val targetLabel = createEdgeLabel => [name = "target"; it.container = container]

		val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel); it.container = container]
		val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel); it.container = container]
		val swP = createVertexVariable => [name = "swP"; vertexLabels.add(switchPositionLabel); it.container = container]
		val sensor = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel); it.container = container]

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

	def static semaphoreNeighbor() {
		val container = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; it.container = container]
		val semaphoreLabel = createVertexLabel => [name = "Semaphore"; it.container = container]
		val sensorLabel = createVertexLabel => [name = "Sensor"; it.container = container]

		val connectsToLabel = createEdgeLabel => [name = "connectsTo"; it.container = container]
		val entryLabel = createEdgeLabel => [name = "entry"; it.container = container]
		val exitLabel = createEdgeLabel => [name = "exit"; it.container = container]
		val gathersLabel = createEdgeLabel => [name = "gathers"; it.container = container]
		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; it.container = container]

		val route1 = createVertexVariable => [name = "route1"; vertexLabels.add(routeLabel); it.container = container]
		val route2 = createVertexVariable => [name = "route2"; vertexLabels.add(routeLabel); it.container = container]
		val semaphore = createVertexVariable => [
			name = "semaphore";
			vertexLabels.add(semaphoreLabel);
			it.container = container
		]
		val sensor1 = createVertexVariable => [name = "sensor1"; vertexLabels.add(sensorLabel); it.container = container]
		val sensor2 = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel); it.container = container]
		val te1 = createVertexVariable => [name = "te1"; it.container = container]
		val te2 = createVertexVariable => [name = "te2"; it.container = container]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = exitLabel; dontCare = true; it.container = container]
		val _e2 = createEdgeVariable => [
			name = "_e2";
			edgeLabel = gathersLabel;
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
			edgeLabel = connectsToLabel;
			dontCare = true;
			it.container = container
		]
		val _e5 = createEdgeVariable => [
			name = "_e5";
			edgeLabel = monitoredByLabel;
			dontCare = true;
			it.container = container
		]
		val _e6 = createEdgeVariable => [
			name = "_e6";
			edgeLabel = gathersLabel;
			dontCare = true;
			it.container = container
		]
		val _e7 = createEdgeVariable =>
			[name = "_e7"; edgeLabel = entryLabel; dontCare = true; it.container = container]

		val getVertices1 = createGetVerticesOperator => [vertexVariable = semaphore]
		val getVertices2 = createGetVerticesOperator => [vertexVariable = semaphore]

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

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand7] // FIXME: [semaphore, route2]
		
//		val condition = createArithmeticComparisonExpression => [
//			leftOperand = route1
//			rightOperand = route2
//			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO			
//		]		
		val filter = createSelectionOperator => [
			input = antiJoin
			conditionString = "route1 != route2"
//			it.condition = condition
		] // FIXME: route1 != route2
		val trimmer = createProjectionOperator => [
			input = filter
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		container.rootExpression = production
		container
	}

	def static switchMonitored() {
		val container = createRelalgContainer

		val sensorLabel = createVertexLabel => [name = "Sensor"; it.container = container]
		val switchLabel = createVertexLabel => [name = "Switch"; it.container = container]

		val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; it.container = container]

		val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel); it.container = container]
		val _sensor = createVertexVariable => [
			name = "_sensor";
			vertexLabels.add(sensorLabel);
			dontCare = true;
			it.container = container
		]

		val _e1 = createEdgeVariable => [
			name = "_e1";
			edgeLabel = monitoredByLabel;
			dontCare = true;
			it.container = container
		]

		val getVertices1 = createGetVerticesOperator => [vertexVariable = sw]
		val getVertices2 = createGetVerticesOperator => [vertexVariable = sw]

		val expand1 = createExpandOperator => [
			input = getVertices2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = _sensor
			edgeVariable = _e1
		]

		val antiJoin = createAntiJoinOperator => [leftInput = getVertices1; rightInput = expand1]
		val de = createDuplicateEliminationOperator => [input = antiJoin]
		val production = createProductionOperator => [input = de]
		container.rootExpression = production
		container
	}

	def static switchSet() {
		val container = createRelalgContainer

		val routeLabel = createVertexLabel => [name = "Route"; it.container = container]
		val semaphoreLabel = createVertexLabel => [name = "Semaphore"; it.container = container]
		val switchLabel = createVertexLabel => [name = "Switch"; it.container = container]
		val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"; it.container = container]

		val entryLabel = createEdgeLabel => [name = "entry"; it.container = container]
		val followsLabel = createEdgeLabel => [name = "follows"; it.container = container]
		val targetLabel = createEdgeLabel => [name = "target"; it.container = container]

		val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel); it.container = container]
		val sw = createVertexVariable => [
			name = "sw";
			vertexLabels.add(switchLabel);
			it.container = container
		]
		val swP = createVertexVariable => [
			name = "swP";
			vertexLabels.add(switchPositionLabel);
			it.container = container
		]
		val semaphore = createVertexVariable => [
			name = "semaphore";
			vertexLabels.add(semaphoreLabel);
			it.container = container
		]
		
		val currentPosition = createAttributeVariable => [name = "currentPosition"; element = sw; it.container = container]
		val position = createAttributeVariable => [name = "position"; element = swP; it.container = container]
		val signal = createAttributeVariable => [name = "signal"; element = semaphore; it.container = container]
		
		val _e1 = createEdgeVariable => [
			name = "_e1"; 
			edgeLabel = entryLabel;
			dontCare = true; it.container = container
		]
		val _e2 = createEdgeVariable => [
			name = "_e2";
			edgeLabel = followsLabel;
			dontCare = true;
			it.container = container
		]
		val _e3 = createEdgeVariable => [
			name = "_e3";
			edgeLabel = targetLabel;
			dontCare = true;
			it.container = container
		]

		val getVertices = createGetVerticesOperator => [vertexVariable = semaphore]

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
		
//		val stringLiteralGO = createStringLiteral => [value = "GO"]
//		val condition1 = createArithmeticComparisonExpression => [
//			leftOperand = signal
//			rightOperand = stringLiteralGO
//			operator = ArithmeticComparisonOperator.EQUAL_TO
//		]
		val filter1 = createSelectionOperator => [
			input = allDifferent
			conditionString = "semaphore.signal = 'GO'"
//			it.condition = condition1
		]
		
//		val condition2 = createArithmeticComparisonExpression => [
//			leftOperand = currentPosition 
//			rightOperand = position
//			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
//		]
		val filter2 = createSelectionOperator => [
			input = filter1
			conditionString = "sw.currentPosition != swP.position"
//			it.condition = condition2
		]
		val trimmer = createProjectionOperator => [
			input = filter2
			variables.addAll(Arrays.asList(semaphore, route, swP, sw, currentPosition, position))
		] // FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		container.rootExpression = production
		container
	}

}
