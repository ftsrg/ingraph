/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.TransitiveExpandOperatorMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.EdgeListVariable;
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * A match processor tailored for the ingraph.optimization.patterns.transitiveExpandOperator pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class TransitiveExpandOperatorProcessor implements IMatchProcessor<TransitiveExpandOperatorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pInputOperator the value of pattern parameter inputOperator in the currently processed match
   * @param pExpandOperator the value of pattern parameter expandOperator in the currently processed match
   * @param pParentOperator the value of pattern parameter parentOperator in the currently processed match
   * @param pEdgeListVariable the value of pattern parameter edgeListVariable in the currently processed match
   * 
   */
  public abstract void process(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable);
  
  @Override
  public void process(final TransitiveExpandOperatorMatch match) {
    process(match.getInputOperator(), match.getExpandOperator(), match.getParentOperator(), match.getEdgeListVariable());
  }
}
