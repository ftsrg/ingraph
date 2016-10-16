/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ExpandOperatorMatch;
import ingraph.optimization.patterns.util.ExpandOperatorQuerySpecification;
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
 * Generated pattern matcher API of the ingraph.optimization.patterns.expandOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ExpandOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // 2nd transformation
 * pattern expandOperator(expandOperator) {
 * 	ExpandOperator(expandOperator);
 * }
 * </pre></code>
 * 
 * @see ExpandOperatorMatch
 * @see ExpandOperatorProcessor
 * @see ExpandOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ExpandOperatorMatcher extends BaseMatcher<ExpandOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExpandOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ExpandOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (ExpandOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ExpandOperatorMatcher create() throws ViatraQueryException {
    return new ExpandOperatorMatcher();
  }
  
  private final static int POSITION_EXPANDOPERATOR = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ExpandOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ExpandOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return matches represented as a ExpandOperatorMatch object.
   * 
   */
  public Collection<ExpandOperatorMatch> getAllMatches(final ExpandOperator pExpandOperator) {
    return rawGetAllMatches(new Object[]{pExpandOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return a match represented as a ExpandOperatorMatch object, or null if no match is found.
   * 
   */
  public ExpandOperatorMatch getOneArbitraryMatch(final ExpandOperator pExpandOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pExpandOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final ExpandOperator pExpandOperator) {
    return rawHasMatch(new Object[]{pExpandOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ExpandOperator pExpandOperator) {
    return rawCountMatches(new Object[]{pExpandOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ExpandOperator pExpandOperator, final IMatchProcessor<? super ExpandOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pExpandOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final ExpandOperator pExpandOperator, final IMatchProcessor<? super ExpandOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pExpandOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ExpandOperatorMatch newMatch(final ExpandOperator pExpandOperator) {
    return ExpandOperatorMatch.newMatch(pExpandOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ExpandOperator> rawAccumulateAllValuesOfexpandOperator(final Object[] parameters) {
    Set<ExpandOperator> results = new HashSet<ExpandOperator>();
    rawAccumulateAllValues(POSITION_EXPANDOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator() {
    return rawAccumulateAllValuesOfexpandOperator(emptyArray());
  }
  
  @Override
  protected ExpandOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return ExpandOperatorMatch.newMatch((ExpandOperator) t.get(POSITION_EXPANDOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return ExpandOperatorMatch.newMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ExpandOperatorMatch.newMutableMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR]);
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
  public static IQuerySpecification<ExpandOperatorMatcher> querySpecification() throws ViatraQueryException {
    return ExpandOperatorQuerySpecification.instance();
  }
}
