/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ExpandOperatorWithDefaultEdgeVariableMatch;
import ingraph.optimization.patterns.util.ExpandOperatorWithDefaultEdgeVariableQuerySpecification;
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
import relalg.ExpandOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.expandOperatorWithDefaultEdgeVariable pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ExpandOperatorWithDefaultEdgeVariableMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern expandOperatorWithDefaultEdgeVariable(defaultExpandOperator : ExpandOperator) {
 * 	ExpandOperator.edgeVariable(defaultExpandOperator, edgeVariable);
 * 	EdgeVariable(edgeVariable);
 * }
 * </pre></code>
 * 
 * @see ExpandOperatorWithDefaultEdgeVariableMatch
 * @see ExpandOperatorWithDefaultEdgeVariableProcessor
 * @see ExpandOperatorWithDefaultEdgeVariableQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ExpandOperatorWithDefaultEdgeVariableMatcher extends BaseMatcher<ExpandOperatorWithDefaultEdgeVariableMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExpandOperatorWithDefaultEdgeVariableMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ExpandOperatorWithDefaultEdgeVariableMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (ExpandOperatorWithDefaultEdgeVariableMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ExpandOperatorWithDefaultEdgeVariableMatcher create() throws ViatraQueryException {
    return new ExpandOperatorWithDefaultEdgeVariableMatcher();
  }
  
  private final static int POSITION_DEFAULTEXPANDOPERATOR = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ExpandOperatorWithDefaultEdgeVariableMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ExpandOperatorWithDefaultEdgeVariableMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return matches represented as a ExpandOperatorWithDefaultEdgeVariableMatch object.
   * 
   */
  public Collection<ExpandOperatorWithDefaultEdgeVariableMatch> getAllMatches(final ExpandOperator pDefaultExpandOperator) {
    return rawGetAllMatches(new Object[]{pDefaultExpandOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return a match represented as a ExpandOperatorWithDefaultEdgeVariableMatch object, or null if no match is found.
   * 
   */
  public ExpandOperatorWithDefaultEdgeVariableMatch getOneArbitraryMatch(final ExpandOperator pDefaultExpandOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pDefaultExpandOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final ExpandOperator pDefaultExpandOperator) {
    return rawHasMatch(new Object[]{pDefaultExpandOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ExpandOperator pDefaultExpandOperator) {
    return rawCountMatches(new Object[]{pDefaultExpandOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ExpandOperator pDefaultExpandOperator, final IMatchProcessor<? super ExpandOperatorWithDefaultEdgeVariableMatch> processor) {
    rawForEachMatch(new Object[]{pDefaultExpandOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final ExpandOperator pDefaultExpandOperator, final IMatchProcessor<? super ExpandOperatorWithDefaultEdgeVariableMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pDefaultExpandOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ExpandOperatorWithDefaultEdgeVariableMatch newMatch(final ExpandOperator pDefaultExpandOperator) {
    return ExpandOperatorWithDefaultEdgeVariableMatch.newMatch(pDefaultExpandOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for defaultExpandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ExpandOperator> rawAccumulateAllValuesOfdefaultExpandOperator(final Object[] parameters) {
    Set<ExpandOperator> results = new HashSet<ExpandOperator>();
    rawAccumulateAllValues(POSITION_DEFAULTEXPANDOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for defaultExpandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfdefaultExpandOperator() {
    return rawAccumulateAllValuesOfdefaultExpandOperator(emptyArray());
  }
  
  @Override
  protected ExpandOperatorWithDefaultEdgeVariableMatch tupleToMatch(final Tuple t) {
    try {
    	return ExpandOperatorWithDefaultEdgeVariableMatch.newMatch((ExpandOperator) t.get(POSITION_DEFAULTEXPANDOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandOperatorWithDefaultEdgeVariableMatch arrayToMatch(final Object[] match) {
    try {
    	return ExpandOperatorWithDefaultEdgeVariableMatch.newMatch((ExpandOperator) match[POSITION_DEFAULTEXPANDOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandOperatorWithDefaultEdgeVariableMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ExpandOperatorWithDefaultEdgeVariableMatch.newMutableMatch((ExpandOperator) match[POSITION_DEFAULTEXPANDOPERATOR]);
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
  public static IQuerySpecification<ExpandOperatorWithDefaultEdgeVariableMatcher> querySpecification() throws ViatraQueryException {
    return ExpandOperatorWithDefaultEdgeVariableQuerySpecification.instance();
  }
}
