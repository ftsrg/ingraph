package ingraph.search2constraints

import ingraph.Cypher2SearchAbstractTest
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import relalg.RelalgContainer

abstract class Search2ConstraintsTransformationTest extends Cypher2SearchAbstractTest {

	override protected def RelalgContainer transform(String querySpecification, String query, RelalgContainer containerSearchBased) {
		val container = Cypher2Relalg.processString(querySpecification, query)
		val simplifyingTransformationRete = new SimplifyingTransformation(container)
		val Search2ConstraintTransformation transformation = new Search2ConstraintTransformation(container)
		 
		simplifyingTransformationRete.simplify
		
		transformation.transformToConstraints

		// Currently schema calculation is not necessary
		// container.calculateExternalSchema
		// container.calculateExtraVariables
		// container.calculateInternalSchema

		return containerSearchBased
	}

}
