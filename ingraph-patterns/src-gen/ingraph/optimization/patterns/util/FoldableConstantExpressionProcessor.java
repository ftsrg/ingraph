/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.FoldableConstantExpressionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.Expression;
import relalg.Literal;

/**
 * A match processor tailored for the ingraph.optimization.patterns.FoldableConstantExpression pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class FoldableConstantExpressionProcessor implements IMatchProcessor<FoldableConstantExpressionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pE the value of pattern parameter e in the currently processed match
   * @param pV1 the value of pattern parameter v1 in the currently processed match
   * @param pV2 the value of pattern parameter v2 in the currently processed match
   * 
   */
  public abstract void process(final Expression pE, final Literal pV1, final Literal pV2);
  
  @Override
  public void process(final FoldableConstantExpressionMatch match) {
    process(match.getE(), match.getV1(), match.getV2());
  }
}
