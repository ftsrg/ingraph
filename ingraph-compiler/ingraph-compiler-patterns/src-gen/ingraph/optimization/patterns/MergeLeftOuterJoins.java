/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.LeftDeepTreeNodesMatcher;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.SelMatcher;
import ingraph.optimization.patterns.util.LeftDeepTreeNodesQuerySpecification;
import ingraph.optimization.patterns.util.LeftOuterJoinAndSelectionQuerySpecification;
import ingraph.optimization.patterns.util.SelQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in MergeLeftOuterJoins.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file MergeLeftOuterJoins.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.optimization.patterns, the group contains the definition of the following patterns: <ul>
 * <li>leftOuterJoinAndSelection</li>
 * <li>sel</li>
 * <li>leftDeepTreeNodes</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class MergeLeftOuterJoins extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static MergeLeftOuterJoins instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new MergeLeftOuterJoins();
    }
    return INSTANCE;
  }
  
  private static MergeLeftOuterJoins INSTANCE;
  
  private MergeLeftOuterJoins() throws ViatraQueryException {
    querySpecifications.add(LeftOuterJoinAndSelectionQuerySpecification.instance());
    querySpecifications.add(SelQuerySpecification.instance());
    querySpecifications.add(LeftDeepTreeNodesQuerySpecification.instance());
  }
  
  public LeftOuterJoinAndSelectionQuerySpecification getLeftOuterJoinAndSelection() throws ViatraQueryException {
    return LeftOuterJoinAndSelectionQuerySpecification.instance();
  }
  
  public LeftOuterJoinAndSelectionMatcher getLeftOuterJoinAndSelection(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftOuterJoinAndSelectionMatcher.on(engine);
  }
  
  public SelQuerySpecification getSel() throws ViatraQueryException {
    return SelQuerySpecification.instance();
  }
  
  public SelMatcher getSel(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SelMatcher.on(engine);
  }
  
  public LeftDeepTreeNodesQuerySpecification getLeftDeepTreeNodes() throws ViatraQueryException {
    return LeftDeepTreeNodesQuerySpecification.instance();
  }
  
  public LeftDeepTreeNodesMatcher getLeftDeepTreeNodes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftDeepTreeNodesMatcher.on(engine);
  }
}
