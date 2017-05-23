/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.ExpandOperatorWithDefaultEdgeVariableMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.ExpandOperator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.expandOperatorWithDefaultEdgeVariable pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandOperatorWithDefaultEdgeVariableProcessor implements IMatchProcessor<ExpandOperatorWithDefaultEdgeVariableMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pDefaultExpandOperator the value of pattern parameter defaultExpandOperator in the currently processed match
   * 
   */
  public abstract void process(final ExpandOperator pDefaultExpandOperator);
  
  @Override
  public void process(final ExpandOperatorWithDefaultEdgeVariableMatch match) {
    process(match.getDefaultExpandOperator());
  }
}
