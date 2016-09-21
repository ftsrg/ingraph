package ingraph.relalg2tex.btw

import ingraph.cypher2relalg.RelalgParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test

class BtwTest {

	val RelalgTreeSerializer serializer = new RelalgTreeSerializer
	extension SchemaInferencer schemaInferencer = new SchemaInferencer

	@Test
	def void test1() {
		val container = RelalgParser.parse('''
			MATCH (n)
			WHERE n.p1 = 12 OR n.p2 = 13
			RETURN n
		''')
		serializer.serialize(container.addSchemaInformation, "btw-query-plan-1")
	}

	@Test
	def void test2() {
		val container = RelalgParser.parse('''
			MATCH ()-[rel:KNOWS]->(x)
			RETURN x
		''')
		serializer.serialize(container.addSchemaInformation, "btw-query-plan-2")
	}

}
