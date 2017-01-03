package ingraph.ire

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.{SchemaInferencer, SchemaToMap, TupleInferencer}
import org.scalatest.{FlatSpec, WordSpec}
import relalg._
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}

class ExpressionParserTest extends WordSpec {
  val tupleInferencer = new TupleInferencer
  val schemaInferencer = new SchemaInferencer
  def getSelectionOperator(query: String): SelectionOperator = {
    val relalg = tupleInferencer.addDetailedSchemaInformation(schemaInferencer.addSchemaInformation(Cypher2Relalg.processString(query)))
    relalg.getRootExpression
      .asInstanceOf[ProjectionOperator].getInput
      .asInstanceOf[SelectionOperator]
  }

  import EngineFactory._

  val rocks = "\"emfrocks\""
  "ExpressionParser" should {
    "parse equal to" in {
      val op = getSelectionOperator("""MATCH (n) WHERE n="emfsucks" RETURN n""")
      val lookup = new SchemaToMap().schemaToMap(op)
      print(lookup)
      val func = ExpressionParser.parse(op.getCondition, lookup)
      assert(func(Vector("\"emfsucks\"")))
      assert(!func(Vector(rocks)))
    }

   "parse binary logical expressions" in {
      val op = getSelectionOperator("""MATCH (n) WHERE (1=1 and n=2) or (n="emfrocks" xor 2=3) RETURN n""")
      val lookup = new SchemaToMap().schemaToMap(op)
      val func = ExpressionParser.parse(op.getCondition, lookup)
      assert(func(Vector(2)))
      assert(!func(Vector(1)))
      assert(func(Vector(rocks)))
      assert(!func(Vector(s"not $rocks")))
    }

    "parse unary logical expressions" in {
      val op = getSelectionOperator("""MATCH (n) WHERE not n=2 RETURN n""")
      val lookup = new SchemaToMap().schemaToMap(op)
      val func = ExpressionParser.parse(op.getCondition, lookup)
      assert(!func(Vector(2)))
      assert(func(Vector(1)))
    }

    "parse arithmetic operations" in {
      val op = getSelectionOperator("""MATCH (n) WHERE n=2^2*1+2-4%5 RETURN n""")
      val lookup = new SchemaToMap().schemaToMap(op)
      val func = ExpressionParser.parse(op.getCondition, lookup)
      assert(func(Vector(2)))
      assert(!func(Vector(1)))
    }
  }

}
