/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.EmptyAllDifferentOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.AllDifferentOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.emptyAllDifferentOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EmptyAllDifferentOperatorProcessor implements IMatchProcessor<EmptyAllDifferentOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pInputOperator the value of pattern parameter inputOperator in the currently processed match
   * @param pAllDifferentOperator the value of pattern parameter allDifferentOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator);
  
  @Override
  public void process(final EmptyAllDifferentOperatorMatch match) {
    process(match.getInputOperator(), match.getAllDifferentOperator(), match.getParentOperator());
  }
}
