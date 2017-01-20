/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.DefaultExpandOperatorMatcher;
import ingraph.optimization.patterns.ExpandOperatorMatcher;
import ingraph.optimization.patterns.ExpandVertexAMatcher;
import ingraph.optimization.patterns.ExpandVertexBMatcher;
import ingraph.optimization.patterns.LeftDeepTreeNodesMatcher;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.SortAndTopOperatorMatcher;
import ingraph.optimization.patterns.VariablesInLogicalExpressionMatcher;
import ingraph.optimization.patterns.util.DefaultExpandOperatorQuerySpecification;
import ingraph.optimization.patterns.util.ExpandOperatorQuerySpecification;
import ingraph.optimization.patterns.util.ExpandVertexAQuerySpecification;
import ingraph.optimization.patterns.util.ExpandVertexBQuerySpecification;
import ingraph.optimization.patterns.util.LeftDeepTreeNodesQuerySpecification;
import ingraph.optimization.patterns.util.LeftOuterJoinAndSelectionQuerySpecification;
import ingraph.optimization.patterns.util.SortAndTopOperatorQuerySpecification;
import ingraph.optimization.patterns.util.VariablesInLogicalExpressionQuerySpecification;
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
 * <li>defaultExpandOperator</li>
 * <li>expandVertexA</li>
 * <li>expandVertexB</li>
 * <li>expandOperator</li>
 * <li>sortAndTopOperator</li>
 * <li>leftOuterJoinAndSelection</li>
 * <li>variablesInLogicalExpression</li>
 * <li>leftDeepTreeNodes</li>
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
    querySpecifications.add(DefaultExpandOperatorQuerySpecification.instance());
    querySpecifications.add(ExpandVertexAQuerySpecification.instance());
    querySpecifications.add(ExpandVertexBQuerySpecification.instance());
    querySpecifications.add(ExpandOperatorQuerySpecification.instance());
    querySpecifications.add(SortAndTopOperatorQuerySpecification.instance());
    querySpecifications.add(LeftOuterJoinAndSelectionQuerySpecification.instance());
    querySpecifications.add(VariablesInLogicalExpressionQuerySpecification.instance());
    querySpecifications.add(LeftDeepTreeNodesQuerySpecification.instance());
  }
  
  public DefaultExpandOperatorQuerySpecification getDefaultExpandOperator() throws ViatraQueryException {
    return DefaultExpandOperatorQuerySpecification.instance();
  }
  
  public DefaultExpandOperatorMatcher getDefaultExpandOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DefaultExpandOperatorMatcher.on(engine);
  }
  
  public ExpandVertexAQuerySpecification getExpandVertexA() throws ViatraQueryException {
    return ExpandVertexAQuerySpecification.instance();
  }
  
  public ExpandVertexAMatcher getExpandVertexA(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandVertexAMatcher.on(engine);
  }
  
  public ExpandVertexBQuerySpecification getExpandVertexB() throws ViatraQueryException {
    return ExpandVertexBQuerySpecification.instance();
  }
  
  public ExpandVertexBMatcher getExpandVertexB(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandVertexBMatcher.on(engine);
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
  
  public LeftOuterJoinAndSelectionQuerySpecification getLeftOuterJoinAndSelection() throws ViatraQueryException {
    return LeftOuterJoinAndSelectionQuerySpecification.instance();
  }
  
  public LeftOuterJoinAndSelectionMatcher getLeftOuterJoinAndSelection(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftOuterJoinAndSelectionMatcher.on(engine);
  }
  
  public VariablesInLogicalExpressionQuerySpecification getVariablesInLogicalExpression() throws ViatraQueryException {
    return VariablesInLogicalExpressionQuerySpecification.instance();
  }
  
  public VariablesInLogicalExpressionMatcher getVariablesInLogicalExpression(final ViatraQueryEngine engine) throws ViatraQueryException {
    return VariablesInLogicalExpressionMatcher.on(engine);
  }
  
  public LeftDeepTreeNodesQuerySpecification getLeftDeepTreeNodes() throws ViatraQueryException {
    return LeftDeepTreeNodesQuerySpecification.instance();
  }
  
  public LeftDeepTreeNodesMatcher getLeftDeepTreeNodes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftDeepTreeNodesMatcher.on(engine);
  }
}
