/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.EmptyOrSingleVariableAllDifferentOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.AllDifferentOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.emptyOrSingleVariableAllDifferentOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EmptyOrSingleVariableAllDifferentOperatorProcessor implements IMatchProcessor<EmptyOrSingleVariableAllDifferentOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pAllDifferentOperator the value of pattern parameter allDifferentOperator in the currently processed match
   * 
   */
  public abstract void process(final AllDifferentOperator pAllDifferentOperator);
  
  @Override
  public void process(final EmptyOrSingleVariableAllDifferentOperatorMatch match) {
    process(match.getAllDifferentOperator());
  }
}
