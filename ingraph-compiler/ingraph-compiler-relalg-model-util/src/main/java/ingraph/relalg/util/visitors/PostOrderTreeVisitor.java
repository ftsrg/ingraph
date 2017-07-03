package ingraph.relalg.util.visitors;

import java.util.Arrays;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.BinaryOperator;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.UnaryOperator;

/**
 * Traverses a relational algebra tree and applies a visitor method on each operator node.
 */
@SuppressWarnings("all")
public class PostOrderTreeVisitor {
  protected void _traverse(final NullaryOperator op, final Procedure1<? super Operator> visitor) {
    visitor.apply(op);
  }
  
  protected void _traverse(final UnaryOperator op, final Procedure1<? super Operator> visitor) {
    Operator _input = op.getInput();
    this.traverse(_input, visitor);
    visitor.apply(op);
  }
  
  protected void _traverse(final BinaryOperator op, final Procedure1<? super Operator> visitor) {
    Operator _leftInput = op.getLeftInput();
    this.traverse(_leftInput, visitor);
    Operator _rightInput = op.getRightInput();
    this.traverse(_rightInput, visitor);
    visitor.apply(op);
  }
  
  public void traverse(final Operator op, final Procedure1<? super Operator> visitor) {
    if (op instanceof BinaryOperator
         && visitor != null) {
      _traverse((BinaryOperator)op, visitor);
      return;
    } else if (op instanceof NullaryOperator
         && visitor != null) {
      _traverse((NullaryOperator)op, visitor);
      return;
    } else if (op instanceof UnaryOperator
         && visitor != null) {
      _traverse((UnaryOperator)op, visitor);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op, visitor).toString());
    }
  }
}
