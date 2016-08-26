package ingraph.relalg2tex

import java.util.Arrays
import org.junit.Test
import relalg.Direction
import relalg.RelalgFactory

class RelalgVisualizationTest {

	val static ExpressionSerializer serializer = new ExpressionSerializer(false, false)
	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
	val extension static RelalgFactory factory = RelalgFactory.eINSTANCE

	val routeLabel = createVertexLabel => [name = "Route"]
	val segmentLabel = createVertexLabel => [name = "Segment"]
	val semaphoreLabel = createVertexLabel => [name = "Semaphore"]
	val sensorLabel = createVertexLabel => [name = "Sensor"]
	val switchLabel = createVertexLabel => [name = "Switch"]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"]

	val connectsToLabel = createEdgeLabel => [name = "connectsTo"]
	val entryLabel = createEdgeLabel => [name = "entry"]
	val exitLabel = createEdgeLabel => [name = "exit"]
	val followsLabel = createEdgeLabel => [name = "follows"]
	val gathersLabel = createEdgeLabel => [name = "gathers"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	val targetLabel = createEdgeLabel => [name = "target"]

	@Test
	def void testPosLength() {
		val segment_length = createAttributeVariable => [name = "length"]

		val segment = createVertexVariable => [name = "segment"; vertexLabel = segmentLabel; attributeVariables.add(segment_length)]

		val getVertices = createGetVerticesOperator => [vertexVariable = segment];

		val filter1 = createFilterOperator => [input = getVertices]	//FIXME: segment.length <= 0

		val trimmer = createProjectionOperator => [input = filter1; variables.addAll(Arrays.asList(segment, segment_length))]	//FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]

		print(drawer.serialize(production, "poslength"))
	}

	@Test
	def void testRouteSensor() {
		val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
		val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel]
		val sensor = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = followsLabel; dontCare = true]
		val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = targetLabel; dontCare = true]
		val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = monitoredByLabel; dontCare = true]
		val _e4 = createEdgeVariable => [name = "_e4"; edgeLabel = gathersLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = route];

		val expand1 = createExpandOperator => [input = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = swP; edgeVariable = _e1]
		val expand2 = createExpandOperator => [input = expand1; direction = Direction.OUT; sourceVertexVariable = swP; targetVertexVariable = sw; edgeVariable = _e2]
		val expand3 = createExpandOperator => [input = expand2; direction = Direction.OUT; sourceVertexVariable = sw; targetVertexVariable = sensor; edgeVariable = _e3]
		
		val allDifferent = createAllDifferentOperator => [input = expand3; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))]
		
		val expand4 = createExpandOperator => [input = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = sensor; edgeVariable = _e4]

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand4]	//FIXME: [route, sensor]

		val trimmer = createProjectionOperator => [input = antiJoin; variables.addAll(Arrays.asList(route, sensor, swP, sw))]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]

		print(drawer.serialize(production, "routesensor"))
	}

	@Test
	def void testSemaphoreNeighbor() {
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

		val expand1 = createExpandOperator => [input = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route1; edgeVariable = _e1]
		val expand2 = createExpandOperator => [input = expand1; direction = Direction.OUT; sourceVertexVariable = route1; targetVertexVariable = sensor1; edgeVariable = _e2]
		val expand3 = createExpandOperator => [input = expand2; direction = Direction.IN; sourceVertexVariable = sensor1; targetVertexVariable = te1; edgeVariable = _e3]
		val expand4 = createExpandOperator => [input = expand3; direction = Direction.OUT; sourceVertexVariable = te1; targetVertexVariable = te2; edgeVariable = _e4]
		val expand5 = createExpandOperator => [input = expand4; direction = Direction.OUT; sourceVertexVariable = te2; targetVertexVariable = sensor2; edgeVariable = _e5]
		val expand6 = createExpandOperator => [input = expand5; direction = Direction.IN; sourceVertexVariable = sensor2; targetVertexVariable = route2; edgeVariable = _e6]

		val allDifferent = createAllDifferentOperator => [input = expand6; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3, _e4, _e5, _e6))]

		val expand7 = createExpandOperator => [input = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route2; edgeVariable = _e7]

		val antiJoin = createAntiJoinOperator => [leftInput = allDifferent; rightInput = expand7]	//FIXME: [semaphore, route2]

		val filter = createFilterOperator => [input = antiJoin]	//FIXME: route1 != route2

		val trimmer = createProjectionOperator => [input = filter; variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))]
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]

		print(drawer.serialize(production, "semaphoreneighbor"))
	}

	@Test
	def void testSwitchMonitored() {
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
		val _sensor = createVertexVariable => [ name = "_sensor"; vertexLabel = sensorLabel; dontCare = true]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = monitoredByLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = sw];

		val expand1 = createExpandOperator => [input = getVertices; direction = Direction.OUT; sourceVertexVariable = sw; targetVertexVariable = _sensor; edgeVariable = _e1]

		val antiJoin = createAntiJoinOperator => [leftInput = getVertices; rightInput = expand1]	//FIXME: [semaphore, route2]

		val de = createDuplicateEliminationOperator => [input = antiJoin]
		val production = createProductionOperator => [input = de]

		print(drawer.serialize(production, "switchmonitored"))
	}

	@Test
	def void testSwitchSet() {
		val sw_currentPosition = createAttributeVariable => [name = "currentPosition"]
		val swP_position = createAttributeVariable => [name = "position"]

		val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel; attributeVariables.add(sw_currentPosition)]
		val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel; attributeVariables.add(swP_position)]
		val semaphore = createVertexVariable => [name = "semaphore"; vertexLabel = semaphoreLabel]
		
		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = entryLabel; dontCare = true]
		val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = followsLabel; dontCare = true]
		val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = targetLabel; dontCare = true]
		
		val getVertices = createGetVerticesOperator => [vertexVariable = semaphore];

		val expand1 = createExpandOperator => [input = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route; edgeVariable = _e1]
		val expand2 = createExpandOperator => [input = expand1; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = swP; edgeVariable = _e2]
		val expand3 = createExpandOperator => [input = expand2; direction = Direction.OUT; sourceVertexVariable = swP; targetVertexVariable = sw; edgeVariable = _e3]
		
		val allDifferent = createAllDifferentOperator => [input = expand3; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))]
		
		val filter1 = createFilterOperator => [input = allDifferent]	//FIXME: semaphore.signal='GO'
		val filter2 = createFilterOperator => [input = filter1 ]		//FIXME: sw.currentPosition != swP.position
		
		val trimmer = createProjectionOperator => [input = filter2; variables.addAll(Arrays.asList(semaphore, route, swP, sw, sw_currentPosition, swP_position))]	//FIXME: renaming
		val de = createDuplicateEliminationOperator => [input = trimmer]
		val production = createProductionOperator => [input = de]

		print(drawer.serialize(production, "switchset"))
	}

}
