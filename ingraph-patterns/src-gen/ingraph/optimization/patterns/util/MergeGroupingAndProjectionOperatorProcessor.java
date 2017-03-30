/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.MergeGroupingAndProjectionOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.GroupingOperator;
import relalg.Operator;
import relalg.ProjectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.mergeGroupingAndProjectionOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class MergeGroupingAndProjectionOperatorProcessor implements IMatchProcessor<MergeGroupingAndProjectionOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pProjectionOperator the value of pattern parameter projectionOperator in the currently processed match
   * @param pGroupingOperator the value of pattern parameter groupingOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator);
  
  @Override
  public void process(final MergeGroupingAndProjectionOperatorMatch match) {
    process(match.getProjectionOperator(), match.getGroupingOperator(), match.getParentOperator());
  }
}
