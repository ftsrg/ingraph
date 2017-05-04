/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.AllDifferentOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.unnecessaryAllDifferentOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class UnnecessaryAllDifferentOperatorProcessor implements IMatchProcessor<UnnecessaryAllDifferentOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pInputOperator the value of pattern parameter inputOperator in the currently processed match
   * @param pAllDifferentOperator the value of pattern parameter allDifferentOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator);
  
  @Override
  public void process(final UnnecessaryAllDifferentOperatorMatch match) {
    process(match.getInputOperator(), match.getAllDifferentOperator(), match.getParentOperator());
  }
}
