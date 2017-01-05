/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ExpandOperatorMatcher;
import ingraph.optimization.patterns.ExpandVertexMatcher;
import ingraph.optimization.patterns.SortAndTopOperatorMatcher;
import ingraph.optimization.patterns.util.ExpandOperatorQuerySpecification;
import ingraph.optimization.patterns.util.ExpandVertexQuerySpecification;
import ingraph.optimization.patterns.util.SortAndTopOperatorQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in Relalg2Rete.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Relalg2Rete.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.optimization.patterns, the group contains the definition of the following patterns: <ul>
 * <li>expandVertex</li>
 * <li>expandOperator</li>
 * <li>sortAndTopOperator</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Relalg2Rete extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Relalg2Rete instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new Relalg2Rete();
    }
    return INSTANCE;
  }
  
  private static Relalg2Rete INSTANCE;
  
  private Relalg2Rete() throws ViatraQueryException {
    querySpecifications.add(ExpandVertexQuerySpecification.instance());
    querySpecifications.add(ExpandOperatorQuerySpecification.instance());
    querySpecifications.add(SortAndTopOperatorQuerySpecification.instance());
  }
  
  public ExpandVertexQuerySpecification getExpandVertex() throws ViatraQueryException {
    return ExpandVertexQuerySpecification.instance();
  }
  
  public ExpandVertexMatcher getExpandVertex(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandVertexMatcher.on(engine);
  }
  
  public ExpandOperatorQuerySpecification getExpandOperator() throws ViatraQueryException {
    return ExpandOperatorQuerySpecification.instance();
  }
  
  public ExpandOperatorMatcher getExpandOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandOperatorMatcher.on(engine);
  }
  
  public SortAndTopOperatorQuerySpecification getSortAndTopOperator() throws ViatraQueryException {
    return SortAndTopOperatorQuerySpecification.instance();
  }
  
  public SortAndTopOperatorMatcher getSortAndTopOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SortAndTopOperatorMatcher.on(engine);
  }
}
