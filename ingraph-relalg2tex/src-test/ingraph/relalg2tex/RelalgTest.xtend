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
	val sensorLabel = createVertexLabel => [name = "Sensor"]

	val followsLabel = createEdgeLabel => [name = "follows"]
	val targetLabel = createEdgeLabel => [name = "target"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	val gathersLabel = createEdgeLabel => [name = "gathers"]

	val route = createVertexVariable => [name = "route"; vertexLabel = routeLabel]
	val sw = createVertexVariable => [name = "sw"; vertexLabel = switchLabel]
	val swP = createVertexVariable => [name = "swP"; vertexLabel = switchPositionLabel]
	val sensor = createVertexVariable => [name = "sensor"; vertexLabel = sensorLabel]
	
	val _e1 = createEdgeVariable => [name = "_e1"; edgeLabel = followsLabel; dontCare = true]
	val _e2 = createEdgeVariable => [name = "_e2"; edgeLabel = targetLabel; dontCare = true]
	val _e3 = createEdgeVariable => [name = "_e3"; edgeLabel = monitoredByLabel; dontCare = true]
	val _e4 = createEdgeVariable => [name = "_e4"; edgeLabel = gathersLabel; dontCare = true]

	@Test
	def void test1() {
		val getVertices = createGetVerticesOperator => [vertexVariable = route; vertexLabel = routeLabel];

		val expand1 = createExpandOperator => [parent = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = swP; edgeVariable = _e1]
		val expand2 = createExpandOperator => [parent = expand1; direction = Direction.OUT; sourceVertexVariable = swP; targetVertexVariable = sw; edgeVariable = _e2]
		val expand3 = createExpandOperator => [parent = expand2; direction = Direction.OUT; sourceVertexVariable = sw; targetVertexVariable = sensor; edgeVariable = _e3]
		
		val allDifferent = createAllDifferentOperator => [parent = expand3; edgeVariables.addAll(Arrays.asList(_e1, _e2, _e3))]
		
		val expand4 = createExpandOperator => [parent = getVertices; direction = Direction.OUT; sourceVertexVariable = route; targetVertexVariable = sensor; edgeVariable = _e4]

		val antiJoin = createAntiJoinOperator => [leftParent = allDifferent; rightParent = expand4]

		val de = createDuplicateEliminationOperator => [parent = antiJoin]
		val trimmer = createProjectionOperator => [parent = de; variables.addAll(Arrays.asList(route, sensor, swP, sw))]
		val production = createProductionOperator => [parent = trimmer]

		print(drawer.serialize(production))
	}

}
