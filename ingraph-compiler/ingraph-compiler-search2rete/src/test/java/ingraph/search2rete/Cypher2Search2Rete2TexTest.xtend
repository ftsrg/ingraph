package ingraph.search2rete

import ingraph.Cypher2SearchAbstractTest
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.calculators.ExternalSchemaCalculator
import ingraph.relalg.calculators.ExtraVariablesCalculator
import ingraph.relalg.calculators.InternalSchemaCalculator
import ingraph.relalg.util.RelalgUtil
import relalg.RelalgContainer

abstract class Cypher2Search2Rete2TexTest extends Cypher2SearchAbstractTest {

	extension ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator
	extension ExtraVariablesCalculator extraVariablesCalculator = new ExtraVariablesCalculator
	extension InternalSchemaCalculator internalSchemaCalculator = new InternalSchemaCalculator

	protected def RelalgContainer transform(String querySpecification, String query) {
		val container = Cypher2Relalg.processString(querySpecification, query)
		val simplifyingTransformationRete = new SimplifyingTransformation(container)
		val Search2ReteTransformation transformation = new Search2ReteTransformation(container)
		
		simplifyingTransformationRete.simplify
		
		transformation.transformToRete
		container.calculateExternalSchema
		container.calculateExtraVariables
		container.calculateInternalSchema
		RelalgUtil.save(container, '''query-models/«query»-rete''')
		return container
	}

}
