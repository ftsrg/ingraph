/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

import ingraph.optimization.patterns.ExpandVertexMatch;
import relalg.ExpandOperator;
import relalg.GetVerticesOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.expandVertex pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandVertexProcessor implements IMatchProcessor<ExpandVertexMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pGetVerticesOperator the value of pattern parameter getVerticesOperator in the currently processed match
   * @param pExpandOperator the value of pattern parameter expandOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * 
   */
  public abstract void process(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator);
  
  @Override
  public void process(final ExpandVertexMatch match) {
    process(match.getGetVerticesOperator(), match.getExpandOperator(), match.getParentOperator());
  }
}
