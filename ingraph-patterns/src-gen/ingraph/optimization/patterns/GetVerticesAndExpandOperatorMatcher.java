/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatch;
import ingraph.optimization.patterns.util.GetVerticesAndExpandOperatorQuerySpecification;
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
import relalg.GetVerticesOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.getVerticesAndExpandOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link GetVerticesAndExpandOperatorMatch}.
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
 * // [1] transformation for eliminating standard expand operators connected to a getVerticesOperator
 * pattern getVerticesAndExpandOperator(getVerticesOperator : GetVerticesOperator, expandOperator : ExpandOperator, parentOperator : Operator) {
 *   find parentOperator(expandOperator, parentOperator);
 * 	find expandOperatorWithDefaultEdgeVariable(expandOperator);
 * 	ExpandOperator.input(expandOperator, getVerticesOperator);
 *   GetVerticesOperator(getVerticesOperator);
 * }
 * </pre></code>
 * 
 * @see GetVerticesAndExpandOperatorMatch
 * @see GetVerticesAndExpandOperatorProcessor
 * @see GetVerticesAndExpandOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class GetVerticesAndExpandOperatorMatcher extends BaseMatcher<GetVerticesAndExpandOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static GetVerticesAndExpandOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    GetVerticesAndExpandOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (GetVerticesAndExpandOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static GetVerticesAndExpandOperatorMatcher create() throws ViatraQueryException {
    return new GetVerticesAndExpandOperatorMatcher();
  }
  
  private final static int POSITION_GETVERTICESOPERATOR = 0;
  
  private final static int POSITION_EXPANDOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(GetVerticesAndExpandOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private GetVerticesAndExpandOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a GetVerticesAndExpandOperatorMatch object.
   * 
   */
  public Collection<GetVerticesAndExpandOperatorMatch> getAllMatches(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a GetVerticesAndExpandOperatorMatch object, or null if no match is found.
   * 
   */
  public GetVerticesAndExpandOperatorMatch getOneArbitraryMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final IMatchProcessor<? super GetVerticesAndExpandOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final IMatchProcessor<? super GetVerticesAndExpandOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pGetVerticesOperator, pExpandOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public GetVerticesAndExpandOperatorMatch newMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return GetVerticesAndExpandOperatorMatch.newMatch(pGetVerticesOperator, pExpandOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for getVerticesOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<GetVerticesOperator> rawAccumulateAllValuesOfgetVerticesOperator(final Object[] parameters) {
    Set<GetVerticesOperator> results = new HashSet<GetVerticesOperator>();
    rawAccumulateAllValues(POSITION_GETVERTICESOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for getVerticesOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GetVerticesOperator> getAllValuesOfgetVerticesOperator() {
    return rawAccumulateAllValuesOfgetVerticesOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for getVerticesOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GetVerticesOperator> getAllValuesOfgetVerticesOperator(final GetVerticesAndExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfgetVerticesOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for getVerticesOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GetVerticesOperator> getAllValuesOfgetVerticesOperator(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfgetVerticesOperator(new Object[]{
    null, 
    pExpandOperator, 
    pParentOperator
    });
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
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final GetVerticesAndExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfexpandOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final GetVerticesOperator pGetVerticesOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfexpandOperator(new Object[]{
    pGetVerticesOperator, 
    null, 
    pParentOperator
    });
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
  public Set<Operator> getAllValuesOfparentOperator(final GetVerticesAndExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pGetVerticesOperator, 
    pExpandOperator, 
    null
    });
  }
  
  @Override
  protected GetVerticesAndExpandOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return GetVerticesAndExpandOperatorMatch.newMatch((GetVerticesOperator) t.get(POSITION_GETVERTICESOPERATOR), (ExpandOperator) t.get(POSITION_EXPANDOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected GetVerticesAndExpandOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return GetVerticesAndExpandOperatorMatch.newMatch((GetVerticesOperator) match[POSITION_GETVERTICESOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected GetVerticesAndExpandOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return GetVerticesAndExpandOperatorMatch.newMutableMatch((GetVerticesOperator) match[POSITION_GETVERTICESOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<GetVerticesAndExpandOperatorMatcher> querySpecification() throws ViatraQueryException {
    return GetVerticesAndExpandOperatorQuerySpecification.instance();
  }
}
