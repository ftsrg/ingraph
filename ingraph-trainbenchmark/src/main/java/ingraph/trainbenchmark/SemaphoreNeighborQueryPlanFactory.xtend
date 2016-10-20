package ingraph.trainbenchmark

import java.util.Arrays
import relalg.ArithmeticComparisonOperator
import relalg.Direction

class SemaphoreNeighborQueryPlanFactory extends QueryPlanFactory {

	// container
	val semaphoreNeighbor = createRelalgContainer

	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"; container = semaphoreNeighbor]
	val semaphoreLabel = createVertexLabel => [name = "Semaphore"; container = semaphoreNeighbor]
	val sensorLabel = createVertexLabel => [name = "Sensor"; container = semaphoreNeighbor]
	val teLabel = createVertexLabel => [name = "TrackElement"; container = semaphoreNeighbor]

	// edge labels
	val connectsToLabel = createEdgeLabel => [name = "connectsTo"; container = semaphoreNeighbor]
	val entryLabel = createEdgeLabel => [name = "entry"; container = semaphoreNeighbor]
	val exitLabel = createEdgeLabel => [name = "exit"; container = semaphoreNeighbor]
	val gathersLabel = createEdgeLabel => [name = "gathers"; container = semaphoreNeighbor]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"; container = semaphoreNeighbor]

	// vertex variables
	val route1 = createVertexVariable => [name = "route1"; vertexLabels.add(routeLabel); container = semaphoreNeighbor]
	val route2 = createVertexVariable => [name = "route2"; vertexLabels.add(routeLabel); container = semaphoreNeighbor]
	val semaphore = createVertexVariable => [
		name = "semaphore";
		vertexLabels.add(semaphoreLabel);
		container = semaphoreNeighbor
	]
	val sensor1 = createVertexVariable => [
		name = "sensor1";
		vertexLabels.add(sensorLabel);
		container = semaphoreNeighbor
	]
	val sensor2 = createVertexVariable => [
		name = "sensor2";
		vertexLabels.add(sensorLabel);
		container = semaphoreNeighbor
	]
	val te1 = createVertexVariable => [name = "te1"; container = semaphoreNeighbor; vertexLabels.add(teLabel)]
	val te2 = createVertexVariable => [name = "te2"; container = semaphoreNeighbor; vertexLabels.add(teLabel)]

	// edge variables
	val gathers1 = createEdgeVariable => [name = "g1"; edgeLabels.add(gathersLabel); container = semaphoreNeighbor]
	val gathers2 = createEdgeVariable => [name = "g2"; edgeLabels.add(gathersLabel); container = semaphoreNeighbor]
	val monitoredBy1 = createEdgeVariable => [name = "mb1"; edgeLabels.add(monitoredByLabel); container = semaphoreNeighbor]
	val monitoredBy2 = createEdgeVariable => [name = "mb2"; edgeLabels.add(monitoredByLabel); container = semaphoreNeighbor]
	val connectsTo = createEdgeVariable => [name = "ct"; edgeLabels.add(connectsToLabel); container = semaphoreNeighbor]
	val entry = createEdgeVariable => [name = "entry"; edgeLabels.add(entryLabel); container = semaphoreNeighbor]
	val exit = createEdgeVariable => [name = "exit"; edgeLabels.add(exitLabel); container = semaphoreNeighbor]

	// inputs
	val semaphores = createGetVerticesOperator => [vertexVariable = semaphore; container = semaphoreNeighbor]
	val route1s = createGetVerticesOperator => [vertexVariable = route1; container = semaphoreNeighbor]
	val route2s = createGetVerticesOperator => [vertexVariable = route2; container = semaphoreNeighbor]
	val sensor1s = createGetVerticesOperator => [vertexVariable = sensor1; container = semaphoreNeighbor]
	val sensor2s = createGetVerticesOperator => [vertexVariable = sensor2; container = semaphoreNeighbor]
	val te1s = createGetVerticesOperator => [vertexVariable = te1; container = semaphoreNeighbor]
	val te2s = createGetVerticesOperator => [vertexVariable = te2; container = semaphoreNeighbor]

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborA() {
		// (sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
			container = semaphoreNeighbor
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
			container = semaphoreNeighbor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
			container = semaphoreNeighbor
		]

		val expand4 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
			container = semaphoreNeighbor
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.IN
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = gathers1
		]

		val expand6 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = gathers2
			container = semaphoreNeighbor
		]

		val expand7 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
			container = semaphoreNeighbor
		]

		val join1 = createJoinOperator => [
			leftInput = expand3
			rightInput = expand5
			container = semaphoreNeighbor
		]
		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand6
			container = semaphoreNeighbor
		]

		val filterCondition = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
			leftOperand = route1
			rightOperand = route2
			container = semaphoreNeighbor
		]
		val filter = createSelectionOperator => [
			input = join2
			condition = filterCondition
			container = semaphoreNeighbor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
			container = semaphoreNeighbor
		]

		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
			container = semaphoreNeighbor
		]
		val production = createProductionOperator => [
			input = trimmer
			container = semaphoreNeighbor
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor

	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborB() {
		// (te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
			container = semaphoreNeighbor
		]

		// (te1:TrackElement)-[:MONITORED_BY]->(sensor1:Sensor)<-[:GATHERS]-(route1:Route)
		val expand2 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
			container = semaphoreNeighbor
		]
		val expand3 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = gathers1
			container = semaphoreNeighbor
		]
		val join1 = createJoinOperator => [
			leftInput = expand2
			rightInput = expand3
			container = semaphoreNeighbor
		]

		// (te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)<-[:GATHERS]-(route2:Route)
		val expand4 = createExpandOperator => [
			input = te2s
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
			container = semaphoreNeighbor
		]
		val expand5 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = gathers2
			container = semaphoreNeighbor
		]
		val join2 = createJoinOperator => [
			leftInput = expand4
			rightInput = expand5
			container = semaphoreNeighbor
		]

		// (route2:Route)-[:GATHERS]->(sensor2:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)
		// -[:CONNECTS_TO]->
		// (te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)<-[:GATHERS]-(route2:Route)
		val join3 = createJoinOperator => [
			leftInput = expand1
			rightInput = join1
			container = semaphoreNeighbor
		]

		// (route1:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand6 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
			container = semaphoreNeighbor
		]

		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
			container = semaphoreNeighbor
		]

		val join4 = createJoinOperator => [
			leftInput = join3
			rightInput = join2
			container = semaphoreNeighbor
		]

		val filterCondition = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
			leftOperand = route1
			rightOperand = route2
			container = semaphoreNeighbor
		]
		val filter = createSelectionOperator => [
			input = join4
			condition = filterCondition
			container = semaphoreNeighbor
		]

		val join5 = createJoinOperator => [
			leftInput = filter
			rightInput = expand6
			container = semaphoreNeighbor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = join5
			rightInput = expand7
			container = semaphoreNeighbor
		]

		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
			container = semaphoreNeighbor
		]
		val production = createProductionOperator => [
			input = trimmer
			container = semaphoreNeighbor
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborC() {
		// (sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensors)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
			container = semaphoreNeighbor
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
			container = semaphoreNeighbor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
			container = semaphoreNeighbor
		]
	
		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:GATHERS]->(sensor1:Sensor)
		val expand4 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = gathers1
			container = semaphoreNeighbor
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
			container = semaphoreNeighbor
		]

		val join1 = createJoinOperator => [
			leftInput = expand3
			rightInput = expand5
			container = semaphoreNeighbor
		]

		// (route2:Route)-[:GATHERS]->(sensor2:Sensor)
		val expand6 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = gathers2
			container = semaphoreNeighbor
		]

		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand6
			container = semaphoreNeighbor
		]

		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
			container = semaphoreNeighbor
		]

		val filterCondition = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
			leftOperand = route1
			rightOperand = route2
			container = semaphoreNeighbor
		]
		val filter = createSelectionOperator => [
			input = join2
			condition = filterCondition
			container = semaphoreNeighbor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
			container = semaphoreNeighbor
		]

		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
			container = semaphoreNeighbor
		]
		val production = createProductionOperator => [
			input = trimmer
			container = semaphoreNeighbor
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborD() {
		// (te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
			container = semaphoreNeighbor
		]
		
		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:GATHERS]->(sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)
		val expand2 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
			container = semaphoreNeighbor
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = gathers1
			container = semaphoreNeighbor
		]
		val expand4 = createExpandOperator => [
			input = expand3
			direction = Direction.IN
			sourceVertexVariable = sensor1
			targetVertexVariable = te1
			edgeVariable = monitoredBy1
			container = semaphoreNeighbor
		]
	
		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:GATHERS]->(sensor1:Sensor)
		val expand5 = createExpandOperator => [
			input = te2s
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
			container = semaphoreNeighbor
		]

		// (route2:Route)-[:GATHERS]->(sensor2:Sensor)
		val expand6 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = gathers2
			container = semaphoreNeighbor
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand4
			container = semaphoreNeighbor
		]

		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand5
			container = semaphoreNeighbor
		]
		
		val join3 = createJoinOperator => [
			leftInput = join2
			rightInput = expand6
			container = semaphoreNeighbor
		]

		val filterCondition = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
			leftOperand = route1
			rightOperand = route2
			container = semaphoreNeighbor
		]
		val filter = createSelectionOperator => [
			input = join3
			condition = filterCondition
			container = semaphoreNeighbor
		]
		
		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2s
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
			container = semaphoreNeighbor
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
			container = semaphoreNeighbor
		]

		val trimmer = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
			container = semaphoreNeighbor
		]
		val production = createProductionOperator => [
			input = trimmer
			container = semaphoreNeighbor
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborE() {
		semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborF() {
		semaphoreNeighbor
	}
}
