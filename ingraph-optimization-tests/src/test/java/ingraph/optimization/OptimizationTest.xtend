package ingraph.optimization

import ingraph.optimization.transformations.reteoptimization.ReteOptimization
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

abstract class OptimizationTest {

	protected extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
	protected extension ReteOptimization optimization = new ReteOptimization
	protected extension TestModelFactory testModelFactory = new TestModelFactory
	
}