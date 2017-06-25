/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.leftOuterJoinAndSelection pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class LeftOuterJoinAndSelectionProcessor implements IMatchProcessor<LeftOuterJoinAndSelectionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * @param pSelectionOperator the value of pattern parameter selectionOperator in the currently processed match
   * @param pLeftOuterJoinOperator the value of pattern parameter leftOuterJoinOperator in the currently processed match
   * @param pLeftInputOperator the value of pattern parameter leftInputOperator in the currently processed match
   * @param pRightInputOperator the value of pattern parameter rightInputOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator);
  
  @Override
  public void process(final LeftOuterJoinAndSelectionMatch match) {
    process(match.getParentOperator(), match.getSelectionOperator(), match.getLeftOuterJoinOperator(), match.getLeftInputOperator(), match.getRightInputOperator());
  }
}
