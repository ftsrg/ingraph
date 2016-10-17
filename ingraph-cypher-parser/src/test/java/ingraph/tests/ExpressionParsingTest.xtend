package ingraph.tests

import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.tests.OpenCypherInjectorProvider

@RunWith(XtextRunner)
@InjectWith(OpenCypherInjectorProvider)
class ExpressionParsingTest {

	@Inject
	ParseHelper<Cypher> parseHelper

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

	@Test
	def void test2() {
		testAndPrint('''
			MATCH (a)-[r1]-(b), (a)<-[r2]-(b)
			WHERE 1 < 2 + 3
			RETURN a
		''')
	}

}
