package ingraph.relalg2tex.optimization

import ingraph.optimization.transformations.reteoptimization.ReteOptimization
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test

class BasicOptimizationTest {

	val extension RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension ReteOptimization optimization = new ReteOptimization
	val extension TestModelFactory testModelFactory = new TestModelFactory

	@Test
	def void test1() {
		// arrange
		val ctr = testModel1
		println(ctr.serialize)

		// act
		ctr.performSimpleOptimization
		println(ctr.serialize)
	}

	@Test
	def void test2() {
		// arrange
		val ctr = testModel2
		println(ctr.serialize)

		// act
		ctr.performSimpleOptimization
		println(ctr.serialize)
	}

	@Test
	def void test3() {
		// arrange
		val ctr = testModel3
		println(ctr.serialize)

		// act
		ctr.performSimpleOptimization
		println(ctr.serialize)
	}

}
