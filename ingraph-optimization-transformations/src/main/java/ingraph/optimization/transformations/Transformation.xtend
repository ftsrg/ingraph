package ingraph.optimization.transformations

import ingraph.optimization.patterns.ExpandVertexMatcher
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory

class Transformation {

	def static void main(String[] args) {
		val f = new BatchTransformationRuleFactory
		val rule = f.createRule().precondition(ExpandVertexMatcher.querySpecification)
		
	}

}
