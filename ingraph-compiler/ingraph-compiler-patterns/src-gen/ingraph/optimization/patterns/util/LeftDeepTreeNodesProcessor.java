/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns.util;

import ingraph.optimization.patterns.LeftDeepTreeNodesMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.BinaryLogicalExpression;

/**
 * A match processor tailored for the ingraph.optimization.patterns.leftDeepTreeNodes pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class LeftDeepTreeNodesProcessor implements IMatchProcessor<LeftDeepTreeNodesMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pParent the value of pattern parameter parent in the currently processed match
   * @param pChild the value of pattern parameter child in the currently processed match
   * 
   */
  public abstract void process(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild);
  
  @Override
  public void process(final LeftDeepTreeNodesMatch match) {
    process(match.getParent(), match.getChild());
  }
}
