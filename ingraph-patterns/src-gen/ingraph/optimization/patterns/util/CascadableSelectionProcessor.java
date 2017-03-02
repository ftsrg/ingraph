/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.CascadableSelectionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.LogicalExpression;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.CascadableSelection pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class CascadableSelectionProcessor implements IMatchProcessor<CascadableSelectionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * @param pSelectionOperator the value of pattern parameter selectionOperator in the currently processed match
   * @param pLeftOperand the value of pattern parameter leftOperand in the currently processed match
   * @param pRightOperand the value of pattern parameter rightOperand in the currently processed match
   * 
   */
  public abstract void process(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand);
  
  @Override
  public void process(final CascadableSelectionMatch match) {
    process(match.getParentOperator(), match.getSelectionOperator(), match.getLeftOperand(), match.getRightOperand());
  }
}
