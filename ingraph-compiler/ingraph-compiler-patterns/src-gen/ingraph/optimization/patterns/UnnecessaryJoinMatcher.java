/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.UnnecessaryJoinMatch;
import ingraph.optimization.patterns.util.UnnecessaryJoinQuerySpecification;
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
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.unnecessaryJoin pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link UnnecessaryJoinMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // [a] transformation for eliminating left outer joins that join the result of an arbitrary operator (inputOperator) 
 * // to the Dual table
 * pattern unnecessaryJoin(otherInputOperator : Operator, equiJoinLikeOperator : EquiJoinLikeOperator, parentOperator : Operator) {
 * 	find parentOperator(equiJoinLikeOperator, parentOperator);
 * 	find joinOnDual(otherInputOperator, equiJoinLikeOperator);
 * }
 * </pre></code>
 * 
 * @see UnnecessaryJoinMatch
 * @see UnnecessaryJoinProcessor
 * @see UnnecessaryJoinQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class UnnecessaryJoinMatcher extends BaseMatcher<UnnecessaryJoinMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static UnnecessaryJoinMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    UnnecessaryJoinMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (UnnecessaryJoinMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static UnnecessaryJoinMatcher create() throws ViatraQueryException {
    return new UnnecessaryJoinMatcher();
  }
  
  private final static int POSITION_OTHERINPUTOPERATOR = 0;
  
  private final static int POSITION_EQUIJOINLIKEOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(UnnecessaryJoinMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private UnnecessaryJoinMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a UnnecessaryJoinMatch object.
   * 
   */
  public Collection<UnnecessaryJoinMatch> getAllMatches(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a UnnecessaryJoinMatch object, or null if no match is found.
   * 
   */
  public UnnecessaryJoinMatch getOneArbitraryMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator, final IMatchProcessor<? super UnnecessaryJoinMatch> processor) {
    rawForEachMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator, final IMatchProcessor<? super UnnecessaryJoinMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public UnnecessaryJoinMatch newMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return UnnecessaryJoinMatch.newMatch(pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfotherInputOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_OTHERINPUTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator() {
    return rawAccumulateAllValuesOfotherInputOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator(final UnnecessaryJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfotherInputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator(final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfotherInputOperator(new Object[]{
    null, 
    pEquiJoinLikeOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<EquiJoinLikeOperator> rawAccumulateAllValuesOfequiJoinLikeOperator(final Object[] parameters) {
    Set<EquiJoinLikeOperator> results = new HashSet<EquiJoinLikeOperator>();
    rawAccumulateAllValues(POSITION_EQUIJOINLIKEOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator() {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator(final UnnecessaryJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator(final Operator pOtherInputOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(new Object[]{
    pOtherInputOperator, 
    null, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfparentOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_PARENTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator() {
    return rawAccumulateAllValuesOfparentOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final UnnecessaryJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pOtherInputOperator, 
    pEquiJoinLikeOperator, 
    null
    });
  }
  
  @Override
  protected UnnecessaryJoinMatch tupleToMatch(final Tuple t) {
    try {
        return UnnecessaryJoinMatch.newMatch((Operator) t.get(POSITION_OTHERINPUTOPERATOR), (EquiJoinLikeOperator) t.get(POSITION_EQUIJOINLIKEOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected UnnecessaryJoinMatch arrayToMatch(final Object[] match) {
    try {
        return UnnecessaryJoinMatch.newMatch((Operator) match[POSITION_OTHERINPUTOPERATOR], (EquiJoinLikeOperator) match[POSITION_EQUIJOINLIKEOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected UnnecessaryJoinMatch arrayToMatchMutable(final Object[] match) {
    try {
        return UnnecessaryJoinMatch.newMutableMatch((Operator) match[POSITION_OTHERINPUTOPERATOR], (EquiJoinLikeOperator) match[POSITION_EQUIJOINLIKEOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<UnnecessaryJoinMatcher> querySpecification() throws ViatraQueryException {
    return UnnecessaryJoinQuerySpecification.instance();
  }
}
