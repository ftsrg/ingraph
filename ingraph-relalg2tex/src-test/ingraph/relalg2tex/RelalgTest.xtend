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
	val switchLabel = createVertexLabel => [name = "Switch"]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"]
	val semaphoreLabel = createVertexLabel => [name = "Semaphore"]
	val sensorLabel = createVertexLabel => [name = "Sensor"]

	val entryLabel = createEdgeLabel => [name = "entry"]
	val followsLabel = createEdgeLabel => [name = "follows"]
	val targetLabel = createEdgeLabel => [name = "target"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	val gathersLabel = createEdgeLabel => [name = "gathers"]

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

		val antiJoin = createAntiJoinOperator => [leftParent = allDifferent; rightParent = expand4]

		val trimmer = createProjectionOperator => [parent = antiJoin; variables.addAll(Arrays.asList(route, sensor, swP, sw))]
		val de = createDuplicateEliminationOperator => [parent = trimmer]
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
