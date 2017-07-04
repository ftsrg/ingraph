package ingraph.relalg.calculators

import java.util

import com.google.common.collect.{ImmutableList, Iterables, Lists}
import ingraph.relalg.collectors.CollectionHelper
import relalg._

import scala.collection.mutable

class ExpressionToVariables {

  val fae = new FunctionArgumentExtractor
  val ch = new CollectionHelper

  import scala.collection.JavaConverters._

  def getAttributes(expression: Expression): java.util.List[_ <: Variable] = {
    expression match {
      case expression: UnaryLogicalExpression => getAttributes(expression.getOperand)
      case expression: ArithmeticComparisonExpression =>
        List(
          getAttributes(expression.getLeftOperand).asScala.toList,
          getAttributes(expression.getRightOperand).asScala.toList
        ).flatten.asJava
      case expression: BinaryLogicalExpression =>
        List(
          getAttributes(expression.getLeftOperand).asScala.toList,
          getAttributes(expression.getRightOperand).asScala.toList
        ).flatten.asJava
      case expression: VariableExpression =>
        expression.getVariable match {
          case v: AttributeVariable => ImmutableList.of(v)
          case _ => ImmutableList.of()
        }
      case expression: FunctionExpression => fae.extractFunctionArguments(expression)
      case expression: SimpleCaseExpression => ch.union(
        getAttributes(expression.getTest),
        expression.getCases.asScala.map(c => extractAttributes(c).asScala.toList).flatten.asJava,
        getAttributes(expression.getFallback)
      )
      case expression: GenericCaseExpression => ???
      case _ => ImmutableList.of()
    }
  }

  def extractAttributes(c: Case): java.util.List[_ <: Variable] = {
    ch.union(getAttributes(c.getWhen), getAttributes(c.getThen))
  }

}
