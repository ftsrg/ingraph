package ingraph.relalg.util

import relalg.BinaryOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.TernaryOperator
import relalg.UnaryOperator

class PostOrderTreeVisitor {
  
  /**
   * traverse tree and apply a visitor method
   */
  def dispatch void traverse(NullaryOperator op, (Operator) => void visitor) {
    visitor.apply(op)
  }
  
  def dispatch void traverse(UnaryOperator op, (Operator) => void visitor) {
    op.input.traverse(visitor)
    
    visitor.apply(op)
  }
  
  def dispatch void traverse(BinaryOperator op, (Operator) => void visitor) {
    op.leftInput.traverse(visitor)
    op.rightInput.traverse(visitor)
    
    visitor.apply(op)
  }
  
  def dispatch void traverse(TernaryOperator op, (Operator) => void visitor) {
    op.leftInput.traverse(visitor)
    op.middleInput.traverse(visitor)
    op.rightInput.traverse(visitor)
    
    visitor.apply(op)
  }
  
}
