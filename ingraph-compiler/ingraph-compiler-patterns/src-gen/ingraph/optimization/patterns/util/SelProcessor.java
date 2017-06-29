/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.SelMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.LeftOuterJoinOperator;
import relalg.SelectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.sel pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SelProcessor implements IMatchProcessor<SelMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pLojOperator the value of pattern parameter lojOperator in the currently processed match
   * @param pSelectionOperator the value of pattern parameter selectionOperator in the currently processed match
   * 
   */
  public abstract void process(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator);
  
  @Override
  public void process(final SelMatch match) {
    process(match.getLojOperator(), match.getSelectionOperator());
  }
}
