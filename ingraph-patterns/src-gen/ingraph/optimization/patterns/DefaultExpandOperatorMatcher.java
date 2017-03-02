/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import ingraph.optimization.patterns.util.DefaultExpandOperatorQuerySpecification;
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
 * Generated pattern matcher API of the ingraph.optimization.patterns.defaultExpandOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link DefaultExpandOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * parentOperator
 *           |
 *           | input
 *           V
 *     expandOperator
 *           |
 *           | input
 *           V
 *   getVerticesOperator
 * 
 * 
 * pattern defaultExpandOperator(expandOperator : ExpandOperator) {
 *   ExpandOperator.minHops(expandOperator, 1);
 *   ExpandOperator.maxHops(expandOperator, maxHops);
 *   MaxHops.maxHopsType(maxHops, ::LIMITED);
 *   MaxHops.hops(maxHops, 1);
 * }
 * </pre></code>
 * 
 * @see DefaultExpandOperatorMatch
 * @see DefaultExpandOperatorProcessor
 * @see DefaultExpandOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class DefaultExpandOperatorMatcher extends BaseMatcher<DefaultExpandOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static DefaultExpandOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    DefaultExpandOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (DefaultExpandOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static DefaultExpandOperatorMatcher create() throws ViatraQueryException {
    return new DefaultExpandOperatorMatcher();
  }
  
  private final static int POSITION_EXPANDOPERATOR = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(DefaultExpandOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private DefaultExpandOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return matches represented as a DefaultExpandOperatorMatch object.
   * 
   */
  public Collection<DefaultExpandOperatorMatch> getAllMatches(final ExpandOperator pExpandOperator) {
    return rawGetAllMatches(new Object[]{pExpandOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return a match represented as a DefaultExpandOperatorMatch object, or null if no match is found.
   * 
   */
  public DefaultExpandOperatorMatch getOneArbitraryMatch(final ExpandOperator pExpandOperator) {
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
  public void forEachMatch(final ExpandOperator pExpandOperator, final IMatchProcessor<? super DefaultExpandOperatorMatch> processor) {
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
  public boolean forOneArbitraryMatch(final ExpandOperator pExpandOperator, final IMatchProcessor<? super DefaultExpandOperatorMatch> processor) {
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
  public DefaultExpandOperatorMatch newMatch(final ExpandOperator pExpandOperator) {
    return DefaultExpandOperatorMatch.newMatch(pExpandOperator);
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
  protected DefaultExpandOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return DefaultExpandOperatorMatch.newMatch((ExpandOperator) t.get(POSITION_EXPANDOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected DefaultExpandOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return DefaultExpandOperatorMatch.newMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected DefaultExpandOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return DefaultExpandOperatorMatch.newMutableMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR]);
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
  public static IQuerySpecification<DefaultExpandOperatorMatcher> querySpecification() throws ViatraQueryException {
    return DefaultExpandOperatorQuerySpecification.instance();
  }
}
