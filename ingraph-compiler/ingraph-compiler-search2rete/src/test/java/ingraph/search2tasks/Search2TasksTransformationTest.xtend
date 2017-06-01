package ingraph.search2tasks

import ingraph.Cypher2SearchAbstractTest
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.util.RelalgUtil
import relalg.RelalgContainer

abstract class Search2TasksTransformationTest extends Cypher2SearchAbstractTest {

	override protected def RelalgContainer transform(String querySpecification, String query, RelalgContainer containerSearchBased) {
		val container = Cypher2Relalg.processString(querySpecification, query)
		val simplifyingTransformationRete = new SimplifyingTransformation(container)
		val Search2TasksTransformation transformation = new Search2TasksTransformation(container)
		
		simplifyingTransformationRete.simplify
		
		transformation.transformToTasks
		container.calculateExternalSchema
		container.calculateExtraVariables
		container.calculateInternalSchema
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-rete''')
		return containerSearchBased
	}

}
