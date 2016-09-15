/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.ExpandVertexMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.AlgebraExpression;
import relalg.ExpandOperator;

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
   * @param pV the value of pattern parameter v in the currently processed match
   * @param pE the value of pattern parameter e in the currently processed match
   * 
   */
  public abstract void process(final AlgebraExpression pV, final ExpandOperator pE);
  
  @Override
  public void process(final ExpandVertexMatch match) {
    process(match.getV(), match.getE());
  }
}
