package ingraph.relalg.calculators

import java.util.List
import relalg.AttributeVariable
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.UnaryOperator
import relalg.VariableExpression

class VariableExtractor {

  extension ExpressionToAttributes expressionToAttributes = new ExpressionToAttributes
 
  /**
   * extract extra attributes required by unary operators
   */
  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
    op.elements.map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
  }

//  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
//    op.entries // TODO this does not belong here
//  }

  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(SelectionOperator op) {
    getAttributes(op.condition)
  }

//  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
//    op.entries.map[variable]
//  }
//
//  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
//    #[op.sourceVariable]
//  }
  
  // rest of the unary operators - no extra requirements
  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
    #[]
  }

}