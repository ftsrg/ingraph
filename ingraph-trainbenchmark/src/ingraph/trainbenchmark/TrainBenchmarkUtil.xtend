package ingraph.trainbenchmark

import java.util.Arrays
import relalg.Direction
import relalg.RelalgFactory

class TrainBenchmarkUtil {

	static val extension RelalgFactory factory = RelalgFactory.eINSTANCE

	static val routeLabel = createVertexLabel => [name = "Route"]
	static val segmentLabel = createVertexLabel => [name = "Segment"]
	static val semaphoreLabel = createVertexLabel => [name = "Semaphore"]
	static val sensorLabel = createVertexLabel => [name = "Sensor"]
	static val switchLabel = createVertexLabel => [name = "Switch"]
	static val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"]

	static val connectsToLabel = createEdgeLabel => [name = "connectsTo"]
	static val entryLabel = createEdgeLabel => [name = "entry"]
	static val exitLabel = createEdgeLabel => [name = "exit"]
	static val followsLabel = createEdgeLabel => [name = "follows"]
	static val gathersLabel = createEdgeLabel => [name = "gathers"]
	static val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	static val targetLabel = createEdgeLabel => [name = "target"]

	def static posLength() {
		val segment_length = createAttributeVariable => [name = "length"]

		val segment = createVertexVariable => [
			name = "segment";
			vertexLabel = segmentLabel;
			attributeVariables.add(segment_length)
		]

		val getVertices = createGetVerticesOperator => [vertexVariable = segment];

		val filter1 = createFilterOperator => [input = getVertices] // FIXME: segment.length <= 0
		val trimmer = createProjectionOperator => [
			input = filter1;
			variables.addAll(Arrays.asList(segment, segment_length))
		] // FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		production
	}

	def static routeSensor() {
		val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
		val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel]
		val sensor = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = followsLabel; dontCare = true]
		val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = targetLabel; dontCare = true]
		val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = monitoredByLabel; dontCare = true]
		val _e4 = createEdgeVariable => [name = "_e4"; edgeLabel = gathersLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = route];

		val expand1 = createExpandOperator => [
			input = getVertices;
			direction = Direction.OUT;
			sourceVertexVariable = route;
			targetVertexVariable = swP;
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1;
			direction = Direction.OUT;
			sourceVertexVariable = swP;
			targetVertexVariable = sw;
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2;
			direction = Direction.OUT;
			sourceVertexVariable = sw;
			targetVertexVariable = sensor;
			edgeVariable = _e3
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand3;
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))
		]

		val expand4 = createExpandOperator => [
			input = getVertices;
			direction = Direction.OUT;
			sourceVertexVariable = route;
			targetVertexVariable = sensor;
			edgeVariable = _e4
		]

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand4] // FIXME: [route, sensor]
		val trimmer = createProjectionOperator => [
			input = antiJoin;
			variables.addAll(Arrays.asList(route, sensor, swP, sw))
		]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		production
	}

	def static semaphoreNeighbor() {
		val route1 = createVertexVariable => [name = "route1"; vertexLabel = routeLabel]
		val route2 = createVertexVariable => [name = "route2"; vertexLabel = routeLabel]
		val semaphore = createVertexVariable => [name = "semaphore"; vertexLabel = semaphoreLabel]
		val sensor1 = createVertexVariable => [name = "sensor1"; vertexLabel = sensorLabel]
		val sensor2 = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel]
		val te1 = createVertexVariable => [name = "te1"]
		val te2 = createVertexVariable => [name = "te2"]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = exitLabel; dontCare = true]
		val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = gathersLabel; dontCare = true]
		val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = monitoredByLabel; dontCare = true]
		val _e4 = createEdgeVariable => [name = "_e4"; edgeLabel = connectsToLabel; dontCare = true]
		val _e5 = createEdgeVariable => [name = "_e5"; edgeLabel = monitoredByLabel; dontCare = true]
		val _e6 = createEdgeVariable => [name = "_e6"; edgeLabel = gathersLabel; dontCare = true]
		val _e7 = createEdgeVariable => [name = "_e7"; edgeLabel = entryLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = semaphore];

		val expand1 = createExpandOperator => [
			input = getVertices;
			direction = Direction.IN;
			sourceVertexVariable = semaphore;
			targetVertexVariable = route1;
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1;
			direction = Direction.OUT;
			sourceVertexVariable = route1;
			targetVertexVariable = sensor1;
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2;
			direction = Direction.IN;
			sourceVertexVariable = sensor1;
			targetVertexVariable = te1;
			edgeVariable = _e3
		]
		val expand4 = createExpandOperator => [
			input = expand3;
			direction = Direction.OUT;
			sourceVertexVariable = te1;
			targetVertexVariable = te2;
			edgeVariable = _e4
		]
		val expand5 = createExpandOperator => [
			input = expand4;
			direction = Direction.OUT;
			sourceVertexVariable = te2;
			targetVertexVariable = sensor2;
			edgeVariable = _e5
		]
		val expand6 = createExpandOperator => [
			input = expand5;
			direction = Direction.IN;
			sourceVertexVariable = sensor2;
			targetVertexVariable = route2;
			edgeVariable = _e6
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand6;
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3, _e4, _e5, _e6))
		]

		val expand7 = createExpandOperator => [
			input = getVertices;
			direction = Direction.IN;
			sourceVertexVariable = semaphore;
			targetVertexVariable = route2;
			edgeVariable = _e7
		]

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand7] // FIXME: [semaphore, route2]
		val filter = createFilterOperator => [input = antiJoin] // FIXME: route1 != route2
		val trimmer = createProjectionOperator => [
			input = filter;
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		production
	}

	def static switchMonitored() {
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
		val _sensor = createVertexVariable => [name = "_sensor"; vertexLabel = sensorLabel; dontCare = true]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = monitoredByLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = sw];

		val expand1 = createExpandOperator => [
			input = getVertices;
			direction = Direction.OUT;
			sourceVertexVariable = sw;
			targetVertexVariable = _sensor;
			edgeVariable = _e1
		]

		val antiJoin = createAntiJoinOperator => [leftInput = getVertices; rightInput = expand1] // FIXME: [semaphore, route2]
		val de = createDuplicateEliminationOperator => [input = antiJoin]
		val production = createProductionOperator => [input = de]
		production
	}

	def static switchSet() {
		val sw_currentPosition = createAttributeVariable => [name = "currentPosition"]
		val swP_position = createAttributeVariable => [name = "position"]

		val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
		val sw = createVertexVariable => [
			name = "sw";
			vertexLabel = switchLabel;
			attributeVariables.add(sw_currentPosition)
		]
		val swP = createVertexVariable => [
			name = "swP";
			vertexLabel = switchPositionLabel;
			attributeVariables.add(swP_position)
		]
		val semaphore = createVertexVariable => [name = "semaphore"; vertexLabel = semaphoreLabel]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = entryLabel; dontCare = true]
		val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = followsLabel; dontCare = true]
		val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = targetLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = semaphore];

		val expand1 = createExpandOperator => [
			input = getVertices;
			direction = Direction.IN;
			sourceVertexVariable = semaphore;
			targetVertexVariable = route;
			edgeVariable = _e1
		]
		val expand2 = createExpandOperator => [
			input = expand1;
			direction = Direction.OUT;
			sourceVertexVariable = route;
			targetVertexVariable = swP;
			edgeVariable = _e2
		]
		val expand3 = createExpandOperator => [
			input = expand2;
			direction = Direction.OUT;
			sourceVertexVariable = swP;
			targetVertexVariable = sw;
			edgeVariable = _e3
		]

		val allDifferent = createAllDifferentOperator => [
			input = expand3;
			edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))
		]

		val filter1 = createFilterOperator => [input = allDifferent] // FIXME: semaphore.signal='GO'
		val filter2 = createFilterOperator => [input = filter1] // FIXME: sw.currentPosition != swP.position
		val trimmer = createProjectionOperator => [
			input = filter2;
			variables.addAll(Arrays.asList(semaphore, route, swP, sw, sw_currentPosition, swP_position))
		] // FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]
		production
	}

}
