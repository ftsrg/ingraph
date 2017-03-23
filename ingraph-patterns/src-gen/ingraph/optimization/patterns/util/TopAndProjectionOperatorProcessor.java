/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.TopAndProjectionOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.Operator;
import relalg.ProjectionOperator;
import relalg.TopOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.topAndProjectionOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class TopAndProjectionOperatorProcessor implements IMatchProcessor<TopAndProjectionOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pTopOperator the value of pattern parameter topOperator in the currently processed match
   * @param pProjectionOperator the value of pattern parameter projectionOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator);
  
  @Override
  public void process(final TopAndProjectionOperatorMatch match) {
    process(match.getTopOperator(), match.getProjectionOperator(), match.getParentOperator());
  }
}
