/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.TopAndProjectionOperatorMatch;
import ingraph.optimization.patterns.util.TopAndProjectionOperatorQuerySpecification;
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
import relalg.ProjectionOperator;
import relalg.TopOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.topAndProjectionOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TopAndProjectionOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // [2b] transformation for eliminating the remaining non-default expand operators
 * //pattern expandOperatorB(expandOperator : ExpandOperator, parentOperator : Operator) {
 * //  find parentOperator(expandOperator, parentOperator);
 * //  neg find defaultExpandOperator(expandOperator);
 * //}
 * 
 * pattern topAndProjectionOperator(topOperator : TopOperator, projectionOperator : ProjectionOperator, parentOperator : Operator) {
 *   find parentOperator(topOperator, parentOperator);
 *   TopOperator.input(topOperator, projectionOperator);
 * }
 * </pre></code>
 * 
 * @see TopAndProjectionOperatorMatch
 * @see TopAndProjectionOperatorProcessor
 * @see TopAndProjectionOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TopAndProjectionOperatorMatcher extends BaseMatcher<TopAndProjectionOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TopAndProjectionOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    TopAndProjectionOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (TopAndProjectionOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static TopAndProjectionOperatorMatcher create() throws ViatraQueryException {
    return new TopAndProjectionOperatorMatcher();
  }
  
  private final static int POSITION_TOPOPERATOR = 0;
  
  private final static int POSITION_PROJECTIONOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(TopAndProjectionOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private TopAndProjectionOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a TopAndProjectionOperatorMatch object.
   * 
   */
  public Collection<TopAndProjectionOperatorMatch> getAllMatches(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pTopOperator, pProjectionOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a TopAndProjectionOperatorMatch object, or null if no match is found.
   * 
   */
  public TopAndProjectionOperatorMatch getOneArbitraryMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pTopOperator, pProjectionOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pTopOperator, pProjectionOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pTopOperator, pProjectionOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator, final IMatchProcessor<? super TopAndProjectionOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pTopOperator, pProjectionOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator, final IMatchProcessor<? super TopAndProjectionOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTopOperator, pProjectionOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TopAndProjectionOperatorMatch newMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return TopAndProjectionOperatorMatch.newMatch(pTopOperator, pProjectionOperator, pParentOperator);
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
  public Set<TopOperator> getAllValuesOftopOperator(final TopAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOftopOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for topOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TopOperator> getAllValuesOftopOperator(final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOftopOperator(new Object[]{
    null, 
    pProjectionOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ProjectionOperator> rawAccumulateAllValuesOfprojectionOperator(final Object[] parameters) {
    Set<ProjectionOperator> results = new HashSet<ProjectionOperator>();
    rawAccumulateAllValues(POSITION_PROJECTIONOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator() {
    return rawAccumulateAllValuesOfprojectionOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator(final TopAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfprojectionOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator(final TopOperator pTopOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfprojectionOperator(new Object[]{
    pTopOperator, 
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
  public Set<Operator> getAllValuesOfparentOperator(final TopAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pTopOperator, 
    pProjectionOperator, 
    null
    });
  }
  
  @Override
  protected TopAndProjectionOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return TopAndProjectionOperatorMatch.newMatch((TopOperator) t.get(POSITION_TOPOPERATOR), (ProjectionOperator) t.get(POSITION_PROJECTIONOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TopAndProjectionOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return TopAndProjectionOperatorMatch.newMatch((TopOperator) match[POSITION_TOPOPERATOR], (ProjectionOperator) match[POSITION_PROJECTIONOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TopAndProjectionOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TopAndProjectionOperatorMatch.newMutableMatch((TopOperator) match[POSITION_TOPOPERATOR], (ProjectionOperator) match[POSITION_PROJECTIONOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<TopAndProjectionOperatorMatcher> querySpecification() throws ViatraQueryException {
    return TopAndProjectionOperatorQuerySpecification.instance();
  }
}
