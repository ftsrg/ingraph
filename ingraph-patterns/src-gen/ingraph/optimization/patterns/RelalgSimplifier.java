/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.AllDifferentOperatorEdgeVariablesMatcher;
import ingraph.optimization.patterns.EmptyOrSingleVariableAllDifferentOperatorMatcher;
import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatcher;
import ingraph.optimization.patterns.UnnecessaryJoinMatcher;
import ingraph.optimization.patterns.util.AllDifferentOperatorEdgeVariablesQuerySpecification;
import ingraph.optimization.patterns.util.EmptyOrSingleVariableAllDifferentOperatorQuerySpecification;
import ingraph.optimization.patterns.util.UnnecessaryAllDifferentOperatorQuerySpecification;
import ingraph.optimization.patterns.util.UnnecessaryJoinQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in RelalgSimplifier.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RelalgSimplifier.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.optimization.patterns, the group contains the definition of the following patterns: <ul>
 * <li>unnecessaryJoin</li>
 * <li>unnecessaryAllDifferentOperator</li>
 * <li>emptyOrSingleVariableAllDifferentOperator</li>
 * <li>allDifferentOperatorEdgeVariables</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RelalgSimplifier extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RelalgSimplifier instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new RelalgSimplifier();
    }
    return INSTANCE;
  }
  
  private static RelalgSimplifier INSTANCE;
  
  private RelalgSimplifier() throws ViatraQueryException {
    querySpecifications.add(UnnecessaryJoinQuerySpecification.instance());
    querySpecifications.add(UnnecessaryAllDifferentOperatorQuerySpecification.instance());
    querySpecifications.add(EmptyOrSingleVariableAllDifferentOperatorQuerySpecification.instance());
    querySpecifications.add(AllDifferentOperatorEdgeVariablesQuerySpecification.instance());
  }
  
  public UnnecessaryJoinQuerySpecification getUnnecessaryJoin() throws ViatraQueryException {
    return UnnecessaryJoinQuerySpecification.instance();
  }
  
  public UnnecessaryJoinMatcher getUnnecessaryJoin(final ViatraQueryEngine engine) throws ViatraQueryException {
    return UnnecessaryJoinMatcher.on(engine);
  }
  
  public UnnecessaryAllDifferentOperatorQuerySpecification getUnnecessaryAllDifferentOperator() throws ViatraQueryException {
    return UnnecessaryAllDifferentOperatorQuerySpecification.instance();
  }
  
  public UnnecessaryAllDifferentOperatorMatcher getUnnecessaryAllDifferentOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return UnnecessaryAllDifferentOperatorMatcher.on(engine);
  }
  
  public EmptyOrSingleVariableAllDifferentOperatorQuerySpecification getEmptyOrSingleVariableAllDifferentOperator() throws ViatraQueryException {
    return EmptyOrSingleVariableAllDifferentOperatorQuerySpecification.instance();
  }
  
  public EmptyOrSingleVariableAllDifferentOperatorMatcher getEmptyOrSingleVariableAllDifferentOperator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EmptyOrSingleVariableAllDifferentOperatorMatcher.on(engine);
  }
  
  public AllDifferentOperatorEdgeVariablesQuerySpecification getAllDifferentOperatorEdgeVariables() throws ViatraQueryException {
    return AllDifferentOperatorEdgeVariablesQuerySpecification.instance();
  }
  
  public AllDifferentOperatorEdgeVariablesMatcher getAllDifferentOperatorEdgeVariables(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AllDifferentOperatorEdgeVariablesMatcher.on(engine);
  }
}
