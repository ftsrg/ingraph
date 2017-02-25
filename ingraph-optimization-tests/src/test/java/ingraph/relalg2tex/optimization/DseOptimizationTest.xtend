package ingraph.relalg2tex.optimization

import ingraph.optimization.transformations.reteoptimization.ReteOptimization
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter
import org.junit.Test

class DseOptimizationTest {

	val extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
	val extension ReteOptimization optimization = new ReteOptimization
	val extension TestModelFactory testModelFactory = new TestModelFactory

	@Test
	def void test1() {
		// arrange
		val ctr = testModel1
		println(ctr.convert)

		// act
		ctr.performDseOptimization
		println(ctr.convert)
	}

	@Test
	def void test2() {
		// arrange
		val ctr = testModel2
		println(ctr.convert)

		// act
		ctr.performDseOptimization
		println(ctr.convert)
	}

	@Test
	def void test3() {
		// arrange
		val ctr = testModel3
		println(ctr.convert)

		// act
		ctr.performDseOptimization
		println(ctr.convert)
	}
	
}
