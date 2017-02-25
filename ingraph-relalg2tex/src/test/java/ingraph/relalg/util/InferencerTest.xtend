package ingraph.relalg.util

import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter
import org.junit.Test
import relalg.ArithmeticComparisonOperatorType
import relalg.RelalgFactory
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg.inferencers.ExtraVariableInferencer

class InferencerTest {

	val RelalgConverterConfig config = RelalgConverterConfig.builder.consoleOutput(true).standaloneDocument(true).build

	extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter(config)
	
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraAttributeInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	@Test
	def void test() {
		// arrange
		val container = createRelalgContainer => []

		val personLabel = createVertexLabel => [name = "Person"]
		val personLabelSet = createVertexLabelSet => [vertexLabels.add(personLabel)]
		val personVariable = createVertexVariable => [
			namedElementContainer = container;
			name = "p";
			vertexLabelSet = personLabelSet
		]

		val personAgeAttribute = createAttributeVariable => [element = personVariable; name = "age"]
		val personAgeExpression = createVariableComparableExpression => [variable = personAgeAttribute]
		
		val personNameAttribute = createAttributeVariable => [element = personVariable; name = "name"]
		val personNameExpression = createVariableComparableExpression => [variable = personNameAttribute]
		
		val number20Literal = createIntegerLiteral => [value = 20]

		val returnedNameAttribute = createExpressionVariable => [expression = personNameExpression; name = "name"; hasInferredName = false]

		val ageComparison = createArithmeticComparisonExpression => [
			operator = ArithmeticComparisonOperatorType.GREATER_THAN
			leftOperand = personAgeExpression
			rightOperand = number20Literal
		]

		val getVertices = createGetVerticesOperator => [vertexVariable = personVariable]
		val selection = createSelectionOperator => [input = getVertices; condition = ageComparison]
		val projection = createProjectionOperator => [input = selection; elements.add(returnedNameAttribute)]
		val production = createProductionOperator => [input = projection]

		container.rootExpression = production

		// act
		container.inferBasicSchema
		container.inferExtraAttributes
		container.inferFullSchema
		container.convert("../visualization/sandbox/inferencer-test")
	}

}
