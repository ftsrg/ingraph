/**
 * Generated from platform:/resource/ingraph-cypher2relalg/src/main/java/ingraph/cypher2relalg/queries/PatternQueries.vql
 */
package ingraph.cypher2relalg.queries;

import ingraph.cypher2relalg.queries.EdgeVariablesMatch;
import ingraph.cypher2relalg.queries.util.EdgeVariablesQuerySpecification;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.slizaa.neo4j.opencypher.openCypher.Variable;

/**
 * Generated pattern matcher API of the ingraph.cypher2relalg.queries.edgeVariables pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EdgeVariablesMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern edgeVariables(variable) {
 * 	RelationshipPattern.detail.variable(_, variable);
 * }
 * </pre></code>
 * 
 * @see EdgeVariablesMatch
 * @see EdgeVariablesProcessor
 * @see EdgeVariablesQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class EdgeVariablesMatcher extends BaseMatcher<EdgeVariablesMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EdgeVariablesMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    EdgeVariablesMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (EdgeVariablesMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static EdgeVariablesMatcher create() throws ViatraQueryException {
    return new EdgeVariablesMatcher();
  }
  
  private final static int POSITION_VARIABLE = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EdgeVariablesMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private EdgeVariablesMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return matches represented as a EdgeVariablesMatch object.
   * 
   */
  public Collection<EdgeVariablesMatch> getAllMatches(final Variable pVariable) {
    return rawGetAllMatches(new Object[]{pVariable});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return a match represented as a EdgeVariablesMatch object, or null if no match is found.
   * 
   */
  public EdgeVariablesMatch getOneArbitraryMatch(final Variable pVariable) {
    return rawGetOneArbitraryMatch(new Object[]{pVariable});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Variable pVariable) {
    return rawHasMatch(new Object[]{pVariable});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Variable pVariable) {
    return rawCountMatches(new Object[]{pVariable});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Variable pVariable, final IMatchProcessor<? super EdgeVariablesMatch> processor) {
    rawForEachMatch(new Object[]{pVariable}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Variable pVariable, final IMatchProcessor<? super EdgeVariablesMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pVariable}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EdgeVariablesMatch newMatch(final Variable pVariable) {
    return EdgeVariablesMatch.newMatch(pVariable);
  }
  
  /**
   * Retrieve the set of values that occur in matches for variable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Variable> rawAccumulateAllValuesOfvariable(final Object[] parameters) {
    Set<Variable> results = new HashSet<Variable>();
    rawAccumulateAllValues(POSITION_VARIABLE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for variable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Variable> getAllValuesOfvariable() {
    return rawAccumulateAllValuesOfvariable(emptyArray());
  }
  
  @Override
  protected EdgeVariablesMatch tupleToMatch(final Tuple t) {
    try {
    	return EdgeVariablesMatch.newMatch((Variable) t.get(POSITION_VARIABLE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EdgeVariablesMatch arrayToMatch(final Object[] match) {
    try {
    	return EdgeVariablesMatch.newMatch((Variable) match[POSITION_VARIABLE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EdgeVariablesMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return EdgeVariablesMatch.newMutableMatch((Variable) match[POSITION_VARIABLE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<EdgeVariablesMatcher> querySpecification() throws ViatraQueryException {
    return EdgeVariablesQuerySpecification.instance();
  }
}
