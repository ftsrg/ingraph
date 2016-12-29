package ingraph.ire

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import org.scalatest.{FlatSpec, WordSpec}
import relalg._
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}

class ExpressionParserTest extends WordSpec {
  def getCondition(query: String): LogicalExpression = {

    val relalg = Cypher2Relalg.processString(query)
    relalg.getRootExpression
      .asInstanceOf[ProjectionOperator].getInput
      .asInstanceOf[SelectionOperator].getCondition
  }

  val rocks = "\"emfrocks\""

  "ExpressionParser" should {
    "parse equal to" in {
      val cond = getCondition("""MATCH (n) WHERE n="emfsucks" RETURN n""")
      val func = ExpressionParser.parse(cond)
      assert(func(Map("n" -> "\"emfsucks\"")))
      assert(!func(Map("n" -> rocks)))
    }

   "parse logical expressions" in {
      val cond = getCondition("""MATCH (n) WHERE (1=1 and n=2) or (n="emfrocks" xor 2=3) RETURN n""")
      val func = ExpressionParser.parse(cond)
      assert(func(Map("n" -> 2)))
      assert(!func(Map("n" -> 1)))
      assert(func(Map("n" -> rocks)))
      assert(!func(Map("n" -> s"not $rocks")))
    }
  }

}
