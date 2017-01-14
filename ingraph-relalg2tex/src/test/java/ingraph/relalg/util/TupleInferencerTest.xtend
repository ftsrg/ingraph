package ingraph.relalg.util

import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test
import relalg.RelalgFactory
import relalg.ArithmeticComparisonOperatorType

class TupleInferencerTest {

  val RelalgSerializerConfig config = RelalgSerializerConfig.builder.consoleOutput(true).standaloneDocument(true).build
  extension RelalgTreeSerializer serializer = new RelalgTreeSerializer(config)
  extension SchemaInferencer schemaInferencer = new SchemaInferencer
  extension TupleInferencer tupleInferencer = new TupleInferencer
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
    container.addSchemaInformation
    container.addDetailedSchemaInformation
    container.serialize("../visualization/sandbox/tuple-inferencer-test")
  }

}
