/**
 * Generated from platform:/resource/hello-patterns/src/relalg/my.vql
 */
package relalg.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import relalg.JoinNode;
import relalg.ReteNode;
import relalg.TrimmerNode;
import relalg.TrimmerUpMatch;

/**
 * A match processor tailored for the relalg.trimmerUp pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class TrimmerUpProcessor implements IMatchProcessor<TrimmerUpMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pJoin the value of pattern parameter join in the currently processed match
   * @param pTrimmer the value of pattern parameter trimmer in the currently processed match
   * @param pRn1 the value of pattern parameter rn1 in the currently processed match
   * @param pRn2 the value of pattern parameter rn2 in the currently processed match
   * 
   */
  public abstract void process(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2);
  
  @Override
  public void process(final TrimmerUpMatch match) {
    process(match.getJoin(), match.getTrimmer(), match.getRn1(), match.getRn2());
  }
}
