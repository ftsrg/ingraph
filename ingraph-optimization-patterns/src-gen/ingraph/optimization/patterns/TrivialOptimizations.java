/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.CascadableSelectionMatcher;
import ingraph.optimization.patterns.SwappableSelectionMatcher;
import ingraph.optimization.patterns.util.CascadableSelectionQuerySpecification;
import ingraph.optimization.patterns.util.SwappableSelectionQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in TrivialOptimizations.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file TrivialOptimizations.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.optimization.patterns, the group contains the definition of the following patterns: <ul>
 * <li>CascadableSelection</li>
 * <li>SwappableSelection</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class TrivialOptimizations extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static TrivialOptimizations instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new TrivialOptimizations();
    }
    return INSTANCE;
  }
  
  private static TrivialOptimizations INSTANCE;
  
  private TrivialOptimizations() throws ViatraQueryException {
    querySpecifications.add(CascadableSelectionQuerySpecification.instance());
    querySpecifications.add(SwappableSelectionQuerySpecification.instance());
  }
  
  public CascadableSelectionQuerySpecification getCascadableSelection() throws ViatraQueryException {
    return CascadableSelectionQuerySpecification.instance();
  }
  
  public CascadableSelectionMatcher getCascadableSelection(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CascadableSelectionMatcher.on(engine);
  }
  
  public SwappableSelectionQuerySpecification getSwappableSelection() throws ViatraQueryException {
    return SwappableSelectionQuerySpecification.instance();
  }
  
  public SwappableSelectionMatcher getSwappableSelection(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwappableSelectionMatcher.on(engine);
  }
}
