/**
 * Generated from platform:/resource/hello-patterns/src/relalg/my.vql
 */
package relalg;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.TrimmerUpMatcher;
import relalg.util.TrimmerUpQuerySpecification;

/**
 * A pattern group formed of all patterns defined in my.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file my.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package relalg, the group contains the definition of the following patterns: <ul>
 * <li>trimmerUp</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class My extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static My instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new My();
    }
    return INSTANCE;
  }
  
  private static My INSTANCE;
  
  private My() throws ViatraQueryException {
    querySpecifications.add(TrimmerUpQuerySpecification.instance());
  }
  
  public TrimmerUpQuerySpecification getTrimmerUp() throws ViatraQueryException {
    return TrimmerUpQuerySpecification.instance();
  }
  
  public TrimmerUpMatcher getTrimmerUp(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TrimmerUpMatcher.on(engine);
  }
}
