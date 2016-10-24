/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.CommutativeOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.CommutativeBinaryOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.CommutativeOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class CommutativeOperatorProcessor implements IMatchProcessor<CommutativeOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pOp the value of pattern parameter op in the currently processed match
   * @param pLeftInput the value of pattern parameter leftInput in the currently processed match
   * @param pRightInput the value of pattern parameter rightInput in the currently processed match
   * 
   */
  public abstract void process(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput);
  
  @Override
  public void process(final CommutativeOperatorMatch match) {
    process(match.getOp(), match.getLeftInput(), match.getRightInput());
  }
}
