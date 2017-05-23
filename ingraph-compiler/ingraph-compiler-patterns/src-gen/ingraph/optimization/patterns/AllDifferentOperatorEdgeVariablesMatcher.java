/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.AllDifferentOperatorEdgeVariablesMatch;
import ingraph.optimization.patterns.util.AllDifferentOperatorEdgeVariablesQuerySpecification;
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
import relalg.AllDifferentOperator;
import relalg.EdgeVariable;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.allDifferentOperatorEdgeVariables pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link AllDifferentOperatorEdgeVariablesMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern allDifferentOperatorEdgeVariables(allDifferentOperator : AllDifferentOperator, edgeVariable : EdgeVariable) {
 * 	AllDifferentOperator.edgeVariables(allDifferentOperator, edgeVariable);
 * }
 * </pre></code>
 * 
 * @see AllDifferentOperatorEdgeVariablesMatch
 * @see AllDifferentOperatorEdgeVariablesProcessor
 * @see AllDifferentOperatorEdgeVariablesQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class AllDifferentOperatorEdgeVariablesMatcher extends BaseMatcher<AllDifferentOperatorEdgeVariablesMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AllDifferentOperatorEdgeVariablesMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    AllDifferentOperatorEdgeVariablesMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (AllDifferentOperatorEdgeVariablesMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static AllDifferentOperatorEdgeVariablesMatcher create() throws ViatraQueryException {
    return new AllDifferentOperatorEdgeVariablesMatcher();
  }
  
  private final static int POSITION_ALLDIFFERENTOPERATOR = 0;
  
  private final static int POSITION_EDGEVARIABLE = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AllDifferentOperatorEdgeVariablesMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private AllDifferentOperatorEdgeVariablesMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return matches represented as a AllDifferentOperatorEdgeVariablesMatch object.
   * 
   */
  public Collection<AllDifferentOperatorEdgeVariablesMatch> getAllMatches(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return rawGetAllMatches(new Object[]{pAllDifferentOperator, pEdgeVariable});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return a match represented as a AllDifferentOperatorEdgeVariablesMatch object, or null if no match is found.
   * 
   */
  public AllDifferentOperatorEdgeVariablesMatch getOneArbitraryMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return rawGetOneArbitraryMatch(new Object[]{pAllDifferentOperator, pEdgeVariable});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return rawHasMatch(new Object[]{pAllDifferentOperator, pEdgeVariable});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return rawCountMatches(new Object[]{pAllDifferentOperator, pEdgeVariable});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable, final IMatchProcessor<? super AllDifferentOperatorEdgeVariablesMatch> processor) {
    rawForEachMatch(new Object[]{pAllDifferentOperator, pEdgeVariable}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable, final IMatchProcessor<? super AllDifferentOperatorEdgeVariablesMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pAllDifferentOperator, pEdgeVariable}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public AllDifferentOperatorEdgeVariablesMatch newMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return AllDifferentOperatorEdgeVariablesMatch.newMatch(pAllDifferentOperator, pEdgeVariable);
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<AllDifferentOperator> rawAccumulateAllValuesOfallDifferentOperator(final Object[] parameters) {
    Set<AllDifferentOperator> results = new HashSet<AllDifferentOperator>();
    rawAccumulateAllValues(POSITION_ALLDIFFERENTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator() {
    return rawAccumulateAllValuesOfallDifferentOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator(final AllDifferentOperatorEdgeVariablesMatch partialMatch) {
    return rawAccumulateAllValuesOfallDifferentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator(final EdgeVariable pEdgeVariable) {
    return rawAccumulateAllValuesOfallDifferentOperator(new Object[]{
    null, 
    pEdgeVariable
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<EdgeVariable> rawAccumulateAllValuesOfedgeVariable(final Object[] parameters) {
    Set<EdgeVariable> results = new HashSet<EdgeVariable>();
    rawAccumulateAllValues(POSITION_EDGEVARIABLE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeVariable> getAllValuesOfedgeVariable() {
    return rawAccumulateAllValuesOfedgeVariable(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeVariable> getAllValuesOfedgeVariable(final AllDifferentOperatorEdgeVariablesMatch partialMatch) {
    return rawAccumulateAllValuesOfedgeVariable(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeVariable> getAllValuesOfedgeVariable(final AllDifferentOperator pAllDifferentOperator) {
    return rawAccumulateAllValuesOfedgeVariable(new Object[]{
    pAllDifferentOperator, 
    null
    });
  }
  
  @Override
  protected AllDifferentOperatorEdgeVariablesMatch tupleToMatch(final Tuple t) {
    try {
        return AllDifferentOperatorEdgeVariablesMatch.newMatch((AllDifferentOperator) t.get(POSITION_ALLDIFFERENTOPERATOR), (EdgeVariable) t.get(POSITION_EDGEVARIABLE));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AllDifferentOperatorEdgeVariablesMatch arrayToMatch(final Object[] match) {
    try {
        return AllDifferentOperatorEdgeVariablesMatch.newMatch((AllDifferentOperator) match[POSITION_ALLDIFFERENTOPERATOR], (EdgeVariable) match[POSITION_EDGEVARIABLE]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AllDifferentOperatorEdgeVariablesMatch arrayToMatchMutable(final Object[] match) {
    try {
        return AllDifferentOperatorEdgeVariablesMatch.newMutableMatch((AllDifferentOperator) match[POSITION_ALLDIFFERENTOPERATOR], (EdgeVariable) match[POSITION_EDGEVARIABLE]);
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
  public static IQuerySpecification<AllDifferentOperatorEdgeVariablesMatcher> querySpecification() throws ViatraQueryException {
    return AllDifferentOperatorEdgeVariablesQuerySpecification.instance();
  }
}
