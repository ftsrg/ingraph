package ingraph.search2constraints

import org.junit.Test

class ConstraintsSandboxTest extends Search2ConstraintsTransformationTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void connectedsegments() {
		process('connectedsegments', '''
			MATCH
			  (sensor:Sensor)<-[:monitoredBy]-(segment1:Segment),
			  (segment1:Segment)-[:connectsTo]->
			  (segment2:Segment)-[:connectsTo]->
			  (segment3:Segment)-[:connectsTo]->
			  (segment4:Segment)-[:connectsTo]->
			  (segment5:Segment)-[:connectsTo]->(segment6:Segment),
			  (segment2:Segment)-[:monitoredBy]->(sensor:Sensor),
			  (segment3:Segment)-[:monitoredBy]->(sensor:Sensor),
			  (segment4:Segment)-[:monitoredBy]->(sensor:Sensor),
			  (segment5:Segment)-[:monitoredBy]->(sensor:Sensor),
			  (segment6:Segment)-[:monitoredBy]->(sensor:Sensor)
			RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
		''')
	}
	

	@Test
	def void poslength() {
		process('poslength', '''
			MATCH (segment:Segment)
			WHERE segment.length <= 0
			RETURN DISTINCT segment, segment.length AS length
		''')
	}

	@Test
	def void routesensor() {
		process('routesensor', '''
			MATCH (route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)-[:monitoredBy]->(sensor:Sensor)
			WHERE NOT ((route)-[g:requires]->(sensor))
			RETURN DISTINCT route, sensor, swP, sw
		''')
	}
	
	@Test
	def void semaphoreneighbor() {
		process('semaphoreneighbor', '''
			MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:requires]->(sensor1:Sensor)<-[:monitoredBy]-(te1)-[:connectsTo]->(te2)-[:monitoredBy]->(sensor2:Sensor)<-[:requires]-(route2:Route)
			WHERE NOT ((semaphore)<-[:entry]-(route2))
			      AND route1 <> route2
			RETURN DISTINCT semaphore, route1, route2, sensor1, sensor2, te1, te2
		''')
	}
	
	@Test
	def void switchmonitored() {
		process('switchmonitored', '''
			MATCH (sw:Switch)
			WHERE NOT ((sw)-[:monitoredBy]->(:Sensor))
			RETURN DISTINCT sw
		''')
	}
	
	@Test
	def void switchset() {
		process('switchset', '''
			MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
			WHERE semaphore.signal = "GO"
			  AND sw.currentPosition <> swP.position
			RETURN DISTINCT semaphore, route, swP, sw, sw.currentPosition AS currentPosition, swP.position AS position
		''')
	}
	

}