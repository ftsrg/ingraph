/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.UnnecessaryLeftOuterJoinMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.unnecessaryLeftOuterJoin pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class UnnecessaryLeftOuterJoinProcessor implements IMatchProcessor<UnnecessaryLeftOuterJoinMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pInputOperator the value of pattern parameter inputOperator in the currently processed match
   * @param pLeftOuterJoinOperator the value of pattern parameter leftOuterJoinOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator);
  
  @Override
  public void process(final UnnecessaryLeftOuterJoinMatch match) {
    process(match.getInputOperator(), match.getLeftOuterJoinOperator(), match.getParentOperator());
  }
}
