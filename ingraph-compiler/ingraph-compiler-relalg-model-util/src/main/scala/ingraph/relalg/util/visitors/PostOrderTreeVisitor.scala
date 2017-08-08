package ingraph.relalg.util.visitors

import org.eclipse.xtext.xbase.lib.Procedures
import relalg.{BinaryOperator, Operator, UnaryOperator}

/**
  * Traverses a relational algebra tree and applies a visitor method on each operator node.
  */
class PostOrderTreeVisitor {

  def traverse(op: Operator, visitor: Procedures.Procedure1[_ >: Operator]) {
    op match {
      case u: UnaryOperator => traverse(u.getInput, visitor)
      case b: BinaryOperator => {
        traverse(b.getLeftInput, visitor)
        traverse(b.getRightInput, visitor)
      }
      case _ => ;
    }

    visitor.apply(op)
  }

  def traverseScala(op: Operator, visitor: Operator => Unit) {
    op match {
      case u: UnaryOperator => traverseScala(u.getInput, visitor)
      case b: BinaryOperator => {
        traverseScala(b.getLeftInput, visitor)
        traverseScala(b.getRightInput, visitor)
      }
      case _ => ;
    }

    visitor.apply(op)
  }

}
