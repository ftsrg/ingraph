/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.JoinOnDualMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.joinOnDual pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class JoinOnDualProcessor implements IMatchProcessor<JoinOnDualMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pOtherInputOperator the value of pattern parameter otherInputOperator in the currently processed match
   * @param pEquiJoinLikeOperator the value of pattern parameter equiJoinLikeOperator in the currently processed match
   * 
   */
  public abstract void process(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator);
  
  @Override
  public void process(final JoinOnDualMatch match) {
    process(match.getOtherInputOperator(), match.getEquiJoinLikeOperator());
  }
}
