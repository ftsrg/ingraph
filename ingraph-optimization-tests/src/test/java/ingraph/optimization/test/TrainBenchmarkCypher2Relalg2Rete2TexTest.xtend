package ingraph.optimization.test

import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import org.junit.Test

class TrainBenchmarkCypher2Relalg2Rete2TexTest extends Cypher2Relalg2Rete2TexTest {
		
	override protected directory() {
		"trainbenchmark"
	}

	def process(String query) {		
		val querySpecification = FileUtils.readFileToString(new File('''../queries/trainbenchmark/«query».cypher'''), Charset.forName("UTF-8"))
		process(query, querySpecification)
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
