/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.SortAndTopOperatorMatch;
import ingraph.optimization.patterns.util.SortAndTopOperatorQuerySpecification;
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
import relalg.Operator;
import relalg.SortOperator;
import relalg.TopOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.sortAndTopOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SortAndTopOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * parentOperator
 *           |
 *           | input
 *           V
 *      topOperator (skips/limits a given amount of tuples)
 *           |
 *           | input
 *           V
 *      sortOperator (orders the tuples according to some variables and asc/desc directions)
 * 
 * // [3] transformation for combining adjacent top and sort operators to a single sortAndTop operator
 * pattern sortAndTopOperator(parentOperator : Operator, sortOperator : SortOperator, topOperator : TopOperator) {
 *   find parentOperator(parentOperator, topOperator);
 *   TopOperator.input(topOperator, sortOperator);
 * }
 * </pre></code>
 * 
 * @see SortAndTopOperatorMatch
 * @see SortAndTopOperatorProcessor
 * @see SortAndTopOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SortAndTopOperatorMatcher extends BaseMatcher<SortAndTopOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SortAndTopOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    SortAndTopOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (SortAndTopOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static SortAndTopOperatorMatcher create() throws ViatraQueryException {
    return new SortAndTopOperatorMatcher();
  }
  
  private final static int POSITION_PARENTOPERATOR = 0;
  
  private final static int POSITION_SORTOPERATOR = 1;
  
  private final static int POSITION_TOPOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SortAndTopOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private SortAndTopOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return matches represented as a SortAndTopOperatorMatch object.
   * 
   */
  public Collection<SortAndTopOperatorMatch> getAllMatches(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return rawGetAllMatches(new Object[]{pParentOperator, pSortOperator, pTopOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return a match represented as a SortAndTopOperatorMatch object, or null if no match is found.
   * 
   */
  public SortAndTopOperatorMatch getOneArbitraryMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pParentOperator, pSortOperator, pTopOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return rawHasMatch(new Object[]{pParentOperator, pSortOperator, pTopOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return rawCountMatches(new Object[]{pParentOperator, pSortOperator, pTopOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator, final IMatchProcessor<? super SortAndTopOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pParentOperator, pSortOperator, pTopOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator, final IMatchProcessor<? super SortAndTopOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pParentOperator, pSortOperator, pTopOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SortAndTopOperatorMatch newMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return SortAndTopOperatorMatch.newMatch(pParentOperator, pSortOperator, pTopOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfparentOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_PARENTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator() {
    return rawAccumulateAllValuesOfparentOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final SortAndTopOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    null, 
    pSortOperator, 
    pTopOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sortOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SortOperator> rawAccumulateAllValuesOfsortOperator(final Object[] parameters) {
    Set<SortOperator> results = new HashSet<SortOperator>();
    rawAccumulateAllValues(POSITION_SORTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sortOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SortOperator> getAllValuesOfsortOperator() {
    return rawAccumulateAllValuesOfsortOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sortOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SortOperator> getAllValuesOfsortOperator(final SortAndTopOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfsortOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sortOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SortOperator> getAllValuesOfsortOperator(final Operator pParentOperator, final TopOperator pTopOperator) {
    return rawAccumulateAllValuesOfsortOperator(new Object[]{
    pParentOperator, 
    null, 
    pTopOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for topOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<TopOperator> rawAccumulateAllValuesOftopOperator(final Object[] parameters) {
    Set<TopOperator> results = new HashSet<TopOperator>();
    rawAccumulateAllValues(POSITION_TOPOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for topOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TopOperator> getAllValuesOftopOperator() {
    return rawAccumulateAllValuesOftopOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for topOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TopOperator> getAllValuesOftopOperator(final SortAndTopOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOftopOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for topOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TopOperator> getAllValuesOftopOperator(final Operator pParentOperator, final SortOperator pSortOperator) {
    return rawAccumulateAllValuesOftopOperator(new Object[]{
    pParentOperator, 
    pSortOperator, 
    null
    });
  }
  
  @Override
  protected SortAndTopOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return SortAndTopOperatorMatch.newMatch((Operator) t.get(POSITION_PARENTOPERATOR), (SortOperator) t.get(POSITION_SORTOPERATOR), (TopOperator) t.get(POSITION_TOPOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SortAndTopOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return SortAndTopOperatorMatch.newMatch((Operator) match[POSITION_PARENTOPERATOR], (SortOperator) match[POSITION_SORTOPERATOR], (TopOperator) match[POSITION_TOPOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SortAndTopOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SortAndTopOperatorMatch.newMutableMatch((Operator) match[POSITION_PARENTOPERATOR], (SortOperator) match[POSITION_SORTOPERATOR], (TopOperator) match[POSITION_TOPOPERATOR]);
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
  public static IQuerySpecification<SortAndTopOperatorMatcher> querySpecification() throws ViatraQueryException {
    return SortAndTopOperatorQuerySpecification.instance();
  }
}
