package ingraph.optimization

import org.junit.Test

class BasicOptimizationTest extends OptimizationTest {

	@Test
	def void test1() {
		// arrange
		val ctr = testModel1
		println(ctr.convert)

		// act
		ctr.performSimpleOptimization
		println(ctr.convert)
	}

	@Test
	def void test2() {
		// arrange
		val ctr = testModel2
		println(ctr.convert)

		// act
		ctr.performSimpleOptimization
		println(ctr.convert)
	}

	@Test
	def void test3() {
		// arrange
		val ctr = testModel3
		println(ctr.convert)

		// act
		ctr.performSimpleOptimization
		println(ctr.convert)
	}

}
