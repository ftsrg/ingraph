package ingraph.relalg2tex

import java.util.Arrays
import org.junit.Test
import relalg.Direction
import relalg.RelalgFactory

class RelalgTest {

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

		val filter1 = createFilterOperator => [parent = getVertices]	//FIXME: segment.length <= 0

		val trimmer = createProjectionOperator => [parent = filter1; variables.addAll(Arrays.asList(segment, segment_length))]	//FIXME: renaming
		val de = createDuplicateEliminationOperator => [parent = trimmer]
		val production = createProductionOperator => [parent = de]

		print(drawer.serialize(production))
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

		val expand1 = createExpandOperator => [parent = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = swP; edgeVariable = _e1]
		val expand2 = createExpandOperator => [parent = expand1; direction = Direction.OUT; sourceVertexVariable = swP; targetVertexVariable = sw; edgeVariable = _e2]
		val expand3 = createExpandOperator => [parent = expand2; direction = Direction.OUT; sourceVertexVariable = sw; targetVertexVariable = sensor; edgeVariable = _e3]
		
		val allDifferent = createAllDifferentOperator => [parent = expand3; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))]
		
		val expand4 = createExpandOperator => [parent = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = sensor; edgeVariable = _e4]

		val antiJoin = createAntiJoinOperator => [leftParent = allDifferent; rightParent = expand4]	//FIXME: [route, sensor]

		val trimmer = createProjectionOperator => [parent = antiJoin; variables.addAll(Arrays.asList(route, sensor, swP, sw))]
		val de = createDuplicateEliminationOperator => [parent = trimmer]
		val production = createProductionOperator => [parent = de]

		print(drawer.serialize(production))
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

		val expand1 = createExpandOperator => [parent = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route1; edgeVariable = _e1]
		val expand2 = createExpandOperator => [parent = expand1; direction = Direction.OUT; sourceVertexVariable = route1; targetVertexVariable = sensor1; edgeVariable = _e2]
		val expand3 = createExpandOperator => [parent = expand2; direction = Direction.IN; sourceVertexVariable = sensor1; targetVertexVariable = te1; edgeVariable = _e3]
		val expand4 = createExpandOperator => [parent = expand3; direction = Direction.OUT; sourceVertexVariable = te1; targetVertexVariable = te2; edgeVariable = _e4]
		val expand5 = createExpandOperator => [parent = expand4; direction = Direction.OUT; sourceVertexVariable = te2; targetVertexVariable = sensor2; edgeVariable = _e5]
		val expand6 = createExpandOperator => [parent = expand5; direction = Direction.IN; sourceVertexVariable = sensor2; targetVertexVariable = route2; edgeVariable = _e6]

		val allDifferent = createAllDifferentOperator => [parent = expand6; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3, _e4, _e5, _e6))]

		val expand7 = createExpandOperator => [parent = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route2; edgeVariable = _e7]

		val antiJoin = createAntiJoinOperator => [leftParent = allDifferent; rightParent = expand7]	//FIXME: [semaphore, route2]

		val filter = createFilterOperator => [parent = antiJoin]	//FIXME: route1 != route2

		val trimmer = createProjectionOperator => [parent = filter; variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))]
		val de = createDuplicateEliminationOperator => [parent = trimmer]
		val production = createProductionOperator => [parent = de]

		print(drawer.serialize(production))
	}

	@Test
	def void testSwitchMonitored() {
		val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
		val _sensor = createVertexVariable => [ name = "_sensor"; vertexLabel = sensorLabel; dontCare = true]

		val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = monitoredByLabel; dontCare = true]

		val getVertices = createGetVerticesOperator => [vertexVariable = sw];

		val expand1 = createExpandOperator => [parent = getVertices; direction = Direction.OUT; sourceVertexVariable = sw; targetVertexVariable = _sensor; edgeVariable = _e1]

		val antiJoin = createAntiJoinOperator => [leftParent = getVertices; rightParent = expand1]	//FIXME: [semaphore, route2]

		val de = createDuplicateEliminationOperator => [parent = antiJoin]
		val production = createProductionOperator => [parent = de]

		print(drawer.serialize(production))
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

		val expand1 = createExpandOperator => [parent = getVertices; direction = Direction.IN; sourceVertexVariable = semaphore; targetVertexVariable = route; edgeVariable = _e1]
		val expand2 = createExpandOperator => [parent = expand1; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = swP; edgeVariable = _e2]
		val expand3 = createExpandOperator => [parent = expand2; direction = Direction.OUT; sourceVertexVariable = swP; targetVertexVariable = sw; edgeVariable = _e3]
		
		val allDifferent = createAllDifferentOperator => [parent = expand3; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))]
		
		val filter1 = createFilterOperator => [parent = allDifferent]	//FIXME: semaphore.signal='GO'
		val filter2 = createFilterOperator => [parent = filter1 ]		//FIXME: sw.currentPosition != swP.position
		
		val trimmer = createProjectionOperator => [parent = filter2; variables.addAll(Arrays.asList(semaphore, route, swP, sw, sw_currentPosition, swP_position))]	//FIXME: renaming
		val de = createDuplicateEliminationOperator => [parent = trimmer]
		val production = createProductionOperator => [parent = de]

		print(drawer.serialize(production))
	}

}
