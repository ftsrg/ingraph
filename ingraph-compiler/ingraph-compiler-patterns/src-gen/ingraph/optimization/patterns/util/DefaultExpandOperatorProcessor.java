/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.defaultExpandOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class DefaultExpandOperatorProcessor implements IMatchProcessor<DefaultExpandOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pExpandOperator the value of pattern parameter expandOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final ExpandOperator pExpandOperator, final Operator pParentOperator);
  
  @Override
  public void process(final DefaultExpandOperatorMatch match) {
    process(match.getExpandOperator(), match.getParentOperator());
  }
}
