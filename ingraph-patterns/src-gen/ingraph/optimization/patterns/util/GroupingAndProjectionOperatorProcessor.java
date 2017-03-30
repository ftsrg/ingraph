/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.GroupingAndProjectionOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.GroupingAndProjectionOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.groupingAndProjectionOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class GroupingAndProjectionOperatorProcessor implements IMatchProcessor<GroupingAndProjectionOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pGroupingAndProjectionOperator the value of pattern parameter groupingAndProjectionOperator in the currently processed match
   * 
   */
  public abstract void process(final GroupingAndProjectionOperator pGroupingAndProjectionOperator);
  
  @Override
  public void process(final GroupingAndProjectionOperatorMatch match) {
    process(match.getGroupingAndProjectionOperator());
  }
}
