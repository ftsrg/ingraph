/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.VariablesInLogicalExpressionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.Expression;

/**
 * A match processor tailored for the ingraph.optimization.patterns.variablesInLogicalExpression pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class VariablesInLogicalExpressionProcessor implements IMatchProcessor<VariablesInLogicalExpressionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pExpression the value of pattern parameter expression in the currently processed match
   * 
   */
  public abstract void process(final Expression pExpression);
  
  @Override
  public void process(final VariablesInLogicalExpressionMatch match) {
    process(match.getExpression());
  }
}
