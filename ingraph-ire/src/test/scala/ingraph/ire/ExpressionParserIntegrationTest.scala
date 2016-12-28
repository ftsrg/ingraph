package ingraph.ire

import org.scalatest.FlatSpec
import relalg.ArithmeticComparisonOperator
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}
import relalg.RelalgFactory
import ingraph.cypher2relalg.Cypher2Relalg

class ExpressionParserIntegrationTest extends FlatSpec {
  
  "simple arithmetic comparison 0" should "be parsed" in {
    val cypher = Cypher2Relalg.processString("MATCH (n) WHERE 1 > 2 RETURN n")
    println(cypher.getRootExpression)
  }
  
  "simple arithmetic comparison 1" should "be parsed" in {
    val cypher = Cypher2Relalg.processString("MATCH (n) WHERE 1 < 2 RETURN n")
    println(cypher.getRootExpression)
  }

}
