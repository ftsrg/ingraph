/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.UnnecessaryJoinMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.unnecessaryJoin pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class UnnecessaryJoinProcessor implements IMatchProcessor<UnnecessaryJoinMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pLeftInputOperator the value of pattern parameter leftInputOperator in the currently processed match
   * @param pEquiJoinLikeOperator the value of pattern parameter equiJoinLikeOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pLeftInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator);
  
  @Override
  public void process(final UnnecessaryJoinMatch match) {
    process(match.getLeftInputOperator(), match.getEquiJoinLikeOperator(), match.getParentOperator());
  }
}
