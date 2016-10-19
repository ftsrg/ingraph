/**
 * Generated from platform:/resource/ingraph-cypher2relalg/src/ingraph/cypher2relalg/queries/PatternQueries.vql
 */
package ingraph.cypher2relalg.queries;

import ingraph.cypher2relalg.queries.EdgeVariablesMatcher;
import ingraph.cypher2relalg.queries.NodeVariablesMatcher;
import ingraph.cypher2relalg.queries.util.EdgeVariablesQuerySpecification;
import ingraph.cypher2relalg.queries.util.NodeVariablesQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in PatternQueries.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PatternQueries.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package ingraph.cypher2relalg.queries, the group contains the definition of the following patterns: <ul>
 * <li>nodeVariables</li>
 * <li>edgeVariables</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PatternQueries extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PatternQueries instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new PatternQueries();
    }
    return INSTANCE;
  }
  
  private static PatternQueries INSTANCE;
  
  private PatternQueries() throws ViatraQueryException {
    querySpecifications.add(NodeVariablesQuerySpecification.instance());
    querySpecifications.add(EdgeVariablesQuerySpecification.instance());
  }
  
  public NodeVariablesQuerySpecification getNodeVariables() throws ViatraQueryException {
    return NodeVariablesQuerySpecification.instance();
  }
  
  public NodeVariablesMatcher getNodeVariables(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NodeVariablesMatcher.on(engine);
  }
  
  public EdgeVariablesQuerySpecification getEdgeVariables() throws ViatraQueryException {
    return EdgeVariablesQuerySpecification.instance();
  }
  
  public EdgeVariablesMatcher getEdgeVariables(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EdgeVariablesMatcher.on(engine);
  }
}
