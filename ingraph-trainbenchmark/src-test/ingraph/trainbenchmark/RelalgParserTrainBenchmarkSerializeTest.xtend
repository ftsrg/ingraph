package ingraph.trainbenchmark

import ingraph.relalg.util.RelalgUtil
import java.io.IOException
import org.junit.Test

class RelalgParserTrainBenchmarkSerializeTest {

	@Test
	def void testPosLength() throws IOException {
		RelalgUtil.save(TrainBenchmarkUtil.posLength, "relalgs/poslength-handcrafted")
	}

	@Test
	def void testRouteSensor() throws IOException {
		RelalgUtil.save(TrainBenchmarkUtil.routeSensor, "relalgs/routesensor-handcrafted")
	}

	@Test
	def void testSwitchMonitored() throws IOException {
		RelalgUtil.save(TrainBenchmarkUtil.switchMonitored, "relalgs/switchmonitored-handcrafted")
	}

	@Test
	def void testSwitchSet() throws IOException {
		RelalgUtil.save(TrainBenchmarkUtil.switchSet, "relalgs/switchset-handcrafted")
	}

	@Test
	def void testSemaphoreNeighbor() throws IOException {
		RelalgUtil.save(TrainBenchmarkUtil.semaphoreNeighbor, "relalgs/semaphoreneighbor-handcrafted")
	}
}
