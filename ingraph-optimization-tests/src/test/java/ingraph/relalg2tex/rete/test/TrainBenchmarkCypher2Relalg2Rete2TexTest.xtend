package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import java.io.IOException
import org.junit.Test

class TrainBenchmarkCypher2Relalg2Rete2TexTest {
	
	protected extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	val config = RelalgSerializerConfig.builder.consoleOutput(false).build
	val drawer = new RelalgTreeSerializer(config)
	
	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		val container = Cypher2Relalg.processCypher(cypher)
		
		container.transformToRete
		RelalgUtil.save(container, "query-models/" + query)
		//drawer.serialize(container, "queries/" + query)
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