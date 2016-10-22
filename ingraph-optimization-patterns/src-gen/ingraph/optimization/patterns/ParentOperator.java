/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/ParentOperator.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ParentOperatorMatcher;
import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in ParentOperator.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ParentOperator.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.optimization.patterns, the group contains the definition of the following patterns: <ul>
 * <li>parentOperator</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ParentOperator extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ParentOperator instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new ParentOperator();
    }
    return INSTANCE;
  }
  
  private static ParentOperator INSTANCE;
  
  private ParentOperator() throws ViatraQueryException {
    querySpecifications.add(ParentOperatorQuerySpecification.instance());
  }
  
  public ParentOperatorQuerySpecification getParentOperator() throws ViatraQueryException {
    return ParentOperatorQuerySpecification.instance();
  }
  
  public ParentOperatorMatcher getParentOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ParentOperatorMatcher.on(engine);
  }
}
