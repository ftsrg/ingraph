/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.ParentOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.parentOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ParentOperatorProcessor implements IMatchProcessor<ParentOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * @param pOperator the value of pattern parameter operator in the currently processed match
   * 
   */
  public abstract void process(final Operator pParentOperator, final Operator pOperator);
  
  @Override
  public void process(final ParentOperatorMatch match) {
    process(match.getParentOperator(), match.getOperator());
  }
}
