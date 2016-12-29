package ingraph.relalg.util

import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test
import relalg.ArithmeticComparisonOperator
import relalg.RelalgFactory

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
    val personNameAttribute = createAttributeVariable => [element = personVariable; name = "name"]
    val number20Literal = createIntegerLiteral => [value = 20]

    val returnedNameAttribute = createReturnableElement => [expression = personNameAttribute; alias = "name"]

    val ageComparison = createArithmeticComparisonExpression => [
      operator = ArithmeticComparisonOperator.GREATER_THAN
      leftOperand = personAgeAttribute
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
    container.inferTuples
    container.serialize("../visualization/sandbox/tuple-inferencer-test")
  }

}
