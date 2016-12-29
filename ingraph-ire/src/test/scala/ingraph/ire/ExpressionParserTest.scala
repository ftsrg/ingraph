package ingraph.ire

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import org.scalatest.FlatSpec
import relalg._
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}

class ExpressionParserTest extends FlatSpec {
  def getCondition(query: String): LogicalExpression = {

    val relalg = Cypher2Relalg.processString(query)
    relalg.getRootExpression
      .asInstanceOf[ProjectionOperator].getInput
      .asInstanceOf[SelectionOperator].getCondition
  }

  "equal to" should "be parsed" in {
    val cond = getCondition("""MATCH (n) WHERE n="emfsucks" RETURN n""")
    val func = ExpressionParser.parse(cond)
    assert(func(Map("n" -> "\"emfsucks\"")))
    assert(!func(Map("n" -> "\"emfrocks\"")))
  }

}
