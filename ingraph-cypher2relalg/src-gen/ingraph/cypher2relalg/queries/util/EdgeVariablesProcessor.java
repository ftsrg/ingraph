/**
 * Generated from platform:/resource/ingraph-cypher2relalg/src/ingraph/cypher2relalg/queries/PatternQueries.vql
 */
package ingraph.cypher2relalg.queries.util;

import ingraph.cypher2relalg.queries.EdgeVariablesMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.slizaa.neo4j.opencypher.openCypher.Variable;

/**
 * A match processor tailored for the ingraph.cypher2relalg.queries.edgeVariables pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EdgeVariablesProcessor implements IMatchProcessor<EdgeVariablesMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pVariable the value of pattern parameter variable in the currently processed match
   * 
   */
  public abstract void process(final Variable pVariable);
  
  @Override
  public void process(final EdgeVariablesMatch match) {
    process(match.getVariable());
  }
}
