/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.SwappableSelectionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.SwappableSelection pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwappableSelectionProcessor implements IMatchProcessor<SwappableSelectionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * @param pSelectionOperator1 the value of pattern parameter selectionOperator1 in the currently processed match
   * @param pSelectionOperator2 the value of pattern parameter selectionOperator2 in the currently processed match
   * 
   */
  public abstract void process(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2);
  
  @Override
  public void process(final SwappableSelectionMatch match) {
    process(match.getParentOperator(), match.getSelectionOperator1(), match.getSelectionOperator2());
  }
}
