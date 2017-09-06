package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.model.eplan._
import iplan.IPlanParser
import org.scalatest.WordSpec

class ExpressionParserTest extends WordSpec {

  def parseFilter(query: String): (Tuple) => Boolean = {
    val selection = IPlanParser.parse(query)
      .asInstanceOf[Production].child
      .asInstanceOf[Projection].child
      .asInstanceOf[Selection]
    val lookup = getSchema(selection.child)
    println(lookup)
    ExpressionParser.parse(selection.inode.condition, lookup)
  }

  def getSchema(op: ENode): Map[String, Int] = SchemaToMap.schemaToMapNames(op)

  val rocks = "emfrocks"
  "ExpressionParser" should {
    "parse equal to" in {
      val func = parseFilter("""MATCH (n) WHERE n.something="emfsucks" RETURN n.something""")
      assert(func(Vector(0, "emfsucks")))
      assert(!func(Vector(1, rocks)))
    }

   "parse binary logical expressions" in {
      val func = parseFilter("""MATCH (n) WHERE (1=1 and n.prop=2) or (n.prop="emfrocks" xor 2=3) RETURN n.prop""")
      assert(func(Vector(0, 2)))
      assert(!func(Vector(0, 1)))
      assert(func(Vector(0, rocks)))
      assert(!func(Vector(0, s"not $rocks")))
    }

    "parse unary logical expressions" in {
      val func = parseFilter("""MATCH (n) WHERE not n.x=2 RETURN n.x""")
      assert(!func(Vector(0, 2)))
      assert(func(Vector(0, 1)))
    }

    "parse arithmetic operations" in {
      val func = parseFilter("""MATCH (n) WHERE n.x=2^2*1+2-4%5 RETURN n.x""")
      assert(func(Vector(2, 2)))
      assert(!func(Vector(1, 1)))
    }

    "parse complicated routesensor expression" ignore {
      val func = parseFilter("""MATCH (n) WHERE n.a is null and n.e is not null RETURN n.a""")
      assert(func(Vector(null, 1, 1)))
      assert(!func(Vector(1, 1, 1)))
    }

    "parse functions" in {
      val func = parseFilter("""MATCH (n) WHERE cos(toFloat(n.x)) > sin(pi()) RETURN n.x""")
      assert(func(Vector(0, 0)))
    }

    "parse string functions" in {
      val func = parseFilter(
        """MATCH (n) WHERE substring(toString(1324), 2)=left("244", 2) RETURN n.id""")
      assert(func(Vector(24, 1)))
    }
//    "parse Case structures" in {
//      val plan = getPlan(
//        """MATCH (n)
//          |RETURN
//          |CASE n.eye
//          |WHEN 'blue'
//          |THEN 1
//          |WHEN 'brown'
//          |THEN 2 + 3
//          |ELSE 3 END AS result""".stripMargin)
//      val projection = plan.getRootExpression
//        .asInstanceOf[ProductionOperator].getInput
//        .asInstanceOf[ProjectionOperator]
//      val lookup = getSchema(projection.getInput)
//      val func = ExpressionParser.parseValue(projection.getElements.get(0).getExpression, lookup)
//      assert(func(Vector(1, "blue")) == 1)
//      assert(func(Vector(2, "brown")) == 5)
//      assert(func(Vector(3, "red")) == 3)
//    }
//    "parse exists" in {
//      val plan = getPlan(
//        """MATCH (n)
//          |RETURN exists(n.eye)""".stripMargin)
//      val projection = plan.getRootExpression
//        .asInstanceOf[ProductionOperator].getInput
//        .asInstanceOf[ProjectionOperator]
//      val lookup = getSchema(projection.getInput)
//      val func = ExpressionParser.parseValue(projection.getElements.get(0).getExpression, lookup)
//      assert(func(Vector(1, "blue")) == true)
//      assert(func(Vector(2, null)) == false)
//    }

  }
}
