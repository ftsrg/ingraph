/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.AllDifferentOperatorEdgeVariablesMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.AllDifferentOperator;
import relalg.EdgeVariable;

/**
 * A match processor tailored for the ingraph.optimization.patterns.allDifferentOperatorEdgeVariables pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class AllDifferentOperatorEdgeVariablesProcessor implements IMatchProcessor<AllDifferentOperatorEdgeVariablesMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pAllDifferentOperator the value of pattern parameter allDifferentOperator in the currently processed match
   * @param pEdgeVariable the value of pattern parameter edgeVariable in the currently processed match
   * 
   */
  public abstract void process(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable);
  
  @Override
  public void process(final AllDifferentOperatorEdgeVariablesMatch match) {
    process(match.getAllDifferentOperator(), match.getEdgeVariable());
  }
}
