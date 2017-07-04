package ingraph.relalg.calculators

import java.util

import com.google.common.collect.{ImmutableList, Iterables}
import ingraph.relalg.util.ListExpressionUtil
import relalg._

class FunctionArgumentExtractor {

  import scala.collection.JavaConverters._

  def extractFunctionArguments(fe: FunctionExpression): java.util.List[_ <: Variable] = {
    fe.getArguments.asScala.map(x => extractVariableFromExpression(x).asScala.toList).flatten.asJava

  }

  // extractVariableFromExpression
  def extractVariableFromExpression(e: Expression): java.util.List[_ <: Variable] = {
    e match {
      case fe: FunctionExpression => extractFunctionArguments(fe)
      case ve: VariableExpression => ImmutableList.of(ve.getVariable)
      case ue: UnaryArithmeticOperationExpression => extractVariableFromExpression(ue.getOperand)
      case be: BinaryArithmeticOperationExpression =>
        List(
          extractVariableFromExpression(be.getLeftOperand).asScala.toList,
          extractVariableFromExpression(be.getRightOperand).asScala.toList
        ).flatten.asJava
      case l: Literal => ImmutableList.of()
      case le: ListExpression =>
        ListExpressionUtil.toList(le).asScala.map(e => extractVariableFromExpression(e).asScala.toList).toList.flatten.asJava
    }
  }

}
