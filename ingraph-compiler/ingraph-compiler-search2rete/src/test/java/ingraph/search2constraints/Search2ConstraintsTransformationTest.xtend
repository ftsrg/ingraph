package ingraph.search2constraints

import ingraph.Cypher2SearchAbstractTest
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.util.RelalgUtil
import relalg.RelalgContainer
import ingraph.search2constraints.Search2ConstraintTransformation

import org.junit.*
import ingraph.search2constraints.constraints.Projection
import relalg.Variable
import relalg.RelalgFactory
import ingraph.search2constraints.constraints.Constraint
import java.util.Set

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

		val Set<Constraint> expectedConstraintSetForPoslength = #{
			new Projection(#[RelalgFactory::eINSTANCE.createVertexVariable])
		}
		val expectedResults = newLinkedHashMap(
			'poslength' -> expectedConstraintSetForPoslength,
			'switchset' -> expectedConstraintSetForPoslength,
			'routesensorpositive' -> expectedConstraintSetForPoslength,
			'routesensor' -> expectedConstraintSetForPoslength
		)
		
		
//		Assert::assertTrue(constraints.equals(expectedResults.get(querySpecification)))

		println('''
			================= START ====================
			Compiled constraint set for query "«query»":
		''')
		for (constraint : constraints) {
			print(constraint)
		}
		println('''
			================= END   ====================
		''')

		RelalgUtil.save(containerSearchBased, '''query-models/«query»-rete''')
		return containerSearchBased
	}

}
