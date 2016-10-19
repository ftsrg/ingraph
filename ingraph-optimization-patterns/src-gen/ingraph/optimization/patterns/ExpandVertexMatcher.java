/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Optimization.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ExpandVertexMatch;
import ingraph.optimization.patterns.util.ExpandVertexQuerySpecification;
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

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.expandVertex pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ExpandVertexMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // 1st transformation
 * pattern expandVertex(getVerticesOperator : GetVerticesOperator, expandOperator : ExpandOperator) {
 * 	ExpandOperator.input(expandOperator, getVerticesOperator);
 * 	GetVerticesOperator(getVerticesOperator);
 * }
 * </pre></code>
 * 
 * @see ExpandVertexMatch
 * @see ExpandVertexProcessor
 * @see ExpandVertexQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ExpandVertexMatcher extends BaseMatcher<ExpandVertexMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExpandVertexMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ExpandVertexMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (ExpandVertexMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ExpandVertexMatcher create() throws ViatraQueryException {
    return new ExpandVertexMatcher();
  }
  
  private final static int POSITION_GETVERTICESOPERATOR = 0;
  
  private final static int POSITION_EXPANDOPERATOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ExpandVertexMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ExpandVertexMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return matches represented as a ExpandVertexMatch object.
   * 
   */
  public Collection<ExpandVertexMatch> getAllMatches(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return rawGetAllMatches(new Object[]{pGetVerticesOperator, pExpandOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return a match represented as a ExpandVertexMatch object, or null if no match is found.
   * 
   */
  public ExpandVertexMatch getOneArbitraryMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pGetVerticesOperator, pExpandOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return rawHasMatch(new Object[]{pGetVerticesOperator, pExpandOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return rawCountMatches(new Object[]{pGetVerticesOperator, pExpandOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final IMatchProcessor<? super ExpandVertexMatch> processor) {
    rawForEachMatch(new Object[]{pGetVerticesOperator, pExpandOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final IMatchProcessor<? super ExpandVertexMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pGetVerticesOperator, pExpandOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ExpandVertexMatch newMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator) {
    return ExpandVertexMatch.newMatch(pGetVerticesOperator, pExpandOperator);
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
  public Set<GetVerticesOperator> getAllValuesOfgetVerticesOperator(final ExpandVertexMatch partialMatch) {
    return rawAccumulateAllValuesOfgetVerticesOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for getVerticesOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GetVerticesOperator> getAllValuesOfgetVerticesOperator(final ExpandOperator pExpandOperator) {
    return rawAccumulateAllValuesOfgetVerticesOperator(new Object[]{
    null, 
    pExpandOperator
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
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final ExpandVertexMatch partialMatch) {
    return rawAccumulateAllValuesOfexpandOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final GetVerticesOperator pGetVerticesOperator) {
    return rawAccumulateAllValuesOfexpandOperator(new Object[]{
    pGetVerticesOperator, 
    null
    });
  }
  
  @Override
  protected ExpandVertexMatch tupleToMatch(final Tuple t) {
    try {
    	return ExpandVertexMatch.newMatch((GetVerticesOperator) t.get(POSITION_GETVERTICESOPERATOR), (ExpandOperator) t.get(POSITION_EXPANDOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandVertexMatch arrayToMatch(final Object[] match) {
    try {
    	return ExpandVertexMatch.newMatch((GetVerticesOperator) match[POSITION_GETVERTICESOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandVertexMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ExpandVertexMatch.newMutableMatch((GetVerticesOperator) match[POSITION_GETVERTICESOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR]);
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
  public static IQuerySpecification<ExpandVertexMatcher> querySpecification() throws ViatraQueryException {
    return ExpandVertexQuerySpecification.instance();
  }
}
