/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

import ingraph.optimization.patterns.AssociativeOperatorMatch;
import relalg.JoinOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.AssociativeOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class AssociativeOperatorProcessor implements IMatchProcessor<AssociativeOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pOp1 the value of pattern parameter op1 in the currently processed match
   * @param pOp2 the value of pattern parameter op2 in the currently processed match
   * @param pA the value of pattern parameter a in the currently processed match
   * @param pB the value of pattern parameter b in the currently processed match
   * @param pC the value of pattern parameter c in the currently processed match
   * 
   */
  public abstract void process(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC);
  
  @Override
  public void process(final AssociativeOperatorMatch match) {
    process(match.getOp1(), match.getOp2(), match.getA(), match.getB(), match.getC());
  }
}
