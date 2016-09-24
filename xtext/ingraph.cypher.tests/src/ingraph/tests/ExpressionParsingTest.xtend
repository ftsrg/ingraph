package ingraph.tests

import com.google.inject.Inject
import ingraph.cypher.CypherQuery
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(CypherInjectorProvider)
class ExpressionParsingTest {

	@Inject
	ParseHelper<CypherQuery> parseHelper

	def void testAndPrint(String query) {
		val result = parseHelper.parse(query)
		println(query)
		println(PrettyPrinter.prettyPrint(result))
	}

	@Test
	def void test1() {
		testAndPrint('''
			MATCH (a)-[r1]-(b), (a)<-[r2]-(b)
			WHERE 1 < 2
			RETURN a
		''')
	}

}
