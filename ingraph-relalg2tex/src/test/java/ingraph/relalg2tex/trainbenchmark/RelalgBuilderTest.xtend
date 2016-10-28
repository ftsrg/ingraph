package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.relalg2tex.RelalgTreeSerializer
import java.io.IOException
import org.junit.Test

class RelalgBuilderTest {
	
	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer
	
	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		val expression = Cypher2Relalg.processCypher(cypher)
		drawer.serialize(expression, "queries/" + query)
	}
	
	@Test
	def void testConnectedSegments() throws IOException {
		process("ConnectedSegments")
	}

	@Test
	def void testPosLength() throws IOException {
		process("PosLength")
	}

	@Test
	def void testRouteSensor() throws IOException {
		process("RouteSensor")
	}

	@Test
	def void testSwitchMonitored() throws IOException {
		process("SwitchMonitored")
	}

	@Test
	def void testSwitchSet() throws IOException {
		process("SwitchSet")
	}

	@Test
	def void testSemaphoreNeighbor() throws IOException {
		process("SemaphoreNeighbor")
	}
	
}
