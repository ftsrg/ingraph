/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
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
import relalg.AlgebraExpression;
import relalg.ExpandOperator;

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
 * pattern expandVertex(v, e) {
 * 	ExpandOperator.input(e, v);
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
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExpandVertexMatcher create() throws ViatraQueryException {
    return new ExpandVertexMatcher();
  }
  
  private final static int POSITION_V = 0;
  
  private final static int POSITION_E = 1;
  
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
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return matches represented as a ExpandVertexMatch object.
   * 
   */
  public Collection<ExpandVertexMatch> getAllMatches(final AlgebraExpression pV, final ExpandOperator pE) {
    return rawGetAllMatches(new Object[]{pV, pE});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return a match represented as a ExpandVertexMatch object, or null if no match is found.
   * 
   */
  public ExpandVertexMatch getOneArbitraryMatch(final AlgebraExpression pV, final ExpandOperator pE) {
    return rawGetOneArbitraryMatch(new Object[]{pV, pE});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final AlgebraExpression pV, final ExpandOperator pE) {
    return rawHasMatch(new Object[]{pV, pE});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final AlgebraExpression pV, final ExpandOperator pE) {
    return rawCountMatches(new Object[]{pV, pE});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final AlgebraExpression pV, final ExpandOperator pE, final IMatchProcessor<? super ExpandVertexMatch> processor) {
    rawForEachMatch(new Object[]{pV, pE}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final AlgebraExpression pV, final ExpandOperator pE, final IMatchProcessor<? super ExpandVertexMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pV, pE}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ExpandVertexMatch newMatch(final AlgebraExpression pV, final ExpandOperator pE) {
    return ExpandVertexMatch.newMatch(pV, pE);
  }
  
  /**
   * Retrieve the set of values that occur in matches for v.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<AlgebraExpression> rawAccumulateAllValuesOfv(final Object[] parameters) {
    Set<AlgebraExpression> results = new HashSet<AlgebraExpression>();
    rawAccumulateAllValues(POSITION_V, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for v.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AlgebraExpression> getAllValuesOfv() {
    return rawAccumulateAllValuesOfv(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AlgebraExpression> getAllValuesOfv(final ExpandVertexMatch partialMatch) {
    return rawAccumulateAllValuesOfv(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AlgebraExpression> getAllValuesOfv(final ExpandOperator pE) {
    return rawAccumulateAllValuesOfv(new Object[]{
    null, 
    pE
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ExpandOperator> rawAccumulateAllValuesOfe(final Object[] parameters) {
    Set<ExpandOperator> results = new HashSet<ExpandOperator>();
    rawAccumulateAllValues(POSITION_E, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfe() {
    return rawAccumulateAllValuesOfe(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfe(final ExpandVertexMatch partialMatch) {
    return rawAccumulateAllValuesOfe(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfe(final AlgebraExpression pV) {
    return rawAccumulateAllValuesOfe(new Object[]{
    pV, 
    null
    });
  }
  
  @Override
  protected ExpandVertexMatch tupleToMatch(final Tuple t) {
    try {
    	return ExpandVertexMatch.newMatch((AlgebraExpression) t.get(POSITION_V), (ExpandOperator) t.get(POSITION_E));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandVertexMatch arrayToMatch(final Object[] match) {
    try {
    	return ExpandVertexMatch.newMatch((AlgebraExpression) match[POSITION_V], (ExpandOperator) match[POSITION_E]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ExpandVertexMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ExpandVertexMatch.newMutableMatch((AlgebraExpression) match[POSITION_V], (ExpandOperator) match[POSITION_E]);
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
