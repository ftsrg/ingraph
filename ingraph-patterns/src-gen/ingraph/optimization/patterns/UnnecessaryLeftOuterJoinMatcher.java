/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.UnnecessaryLeftOuterJoinMatch;
import ingraph.optimization.patterns.util.UnnecessaryLeftOuterJoinQuerySpecification;
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
import relalg.DualObjectSourceOperator;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.unnecessaryLeftOuterJoin pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link UnnecessaryLeftOuterJoinMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // [0] transformation for eliminating left outer joins on the Dual table
 * pattern unnecessaryLeftOuterJoin(leftInputOperator : Operator, dualOperator : DualObjectSourceOperator, leftOuterJoinOperator : LeftOuterJoinOperator, parentOperator : Operator) {
 * 	find parentOperator(leftOuterJoinOperator, parentOperator);
 * 	LeftOuterJoinOperator.leftInput(leftOuterJoinOperator, leftInputOperator);
 * 	LeftOuterJoinOperator.rightInput(leftOuterJoinOperator, dualOperator);
 * }
 * </pre></code>
 * 
 * @see UnnecessaryLeftOuterJoinMatch
 * @see UnnecessaryLeftOuterJoinProcessor
 * @see UnnecessaryLeftOuterJoinQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class UnnecessaryLeftOuterJoinMatcher extends BaseMatcher<UnnecessaryLeftOuterJoinMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static UnnecessaryLeftOuterJoinMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    UnnecessaryLeftOuterJoinMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (UnnecessaryLeftOuterJoinMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static UnnecessaryLeftOuterJoinMatcher create() throws ViatraQueryException {
    return new UnnecessaryLeftOuterJoinMatcher();
  }
  
  private final static int POSITION_LEFTINPUTOPERATOR = 0;
  
  private final static int POSITION_DUALOPERATOR = 1;
  
  private final static int POSITION_LEFTOUTERJOINOPERATOR = 2;
  
  private final static int POSITION_PARENTOPERATOR = 3;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(UnnecessaryLeftOuterJoinMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private UnnecessaryLeftOuterJoinMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a UnnecessaryLeftOuterJoinMatch object.
   * 
   */
  public Collection<UnnecessaryLeftOuterJoinMatch> getAllMatches(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a UnnecessaryLeftOuterJoinMatch object, or null if no match is found.
   * 
   */
  public UnnecessaryLeftOuterJoinMatch getOneArbitraryMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator, final IMatchProcessor<? super UnnecessaryLeftOuterJoinMatch> processor) {
    rawForEachMatch(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator, final IMatchProcessor<? super UnnecessaryLeftOuterJoinMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public UnnecessaryLeftOuterJoinMatch newMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return UnnecessaryLeftOuterJoinMatch.newMatch(pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfleftInputOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_LEFTINPUTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInputOperator() {
    return rawAccumulateAllValuesOfleftInputOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInputOperator(final UnnecessaryLeftOuterJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfleftInputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInputOperator(final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfleftInputOperator(new Object[]{
    null, 
    pDualOperator, 
    pLeftOuterJoinOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for dualOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<DualObjectSourceOperator> rawAccumulateAllValuesOfdualOperator(final Object[] parameters) {
    Set<DualObjectSourceOperator> results = new HashSet<DualObjectSourceOperator>();
    rawAccumulateAllValues(POSITION_DUALOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for dualOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<DualObjectSourceOperator> getAllValuesOfdualOperator() {
    return rawAccumulateAllValuesOfdualOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for dualOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<DualObjectSourceOperator> getAllValuesOfdualOperator(final UnnecessaryLeftOuterJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfdualOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for dualOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<DualObjectSourceOperator> getAllValuesOfdualOperator(final Operator pLeftInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfdualOperator(new Object[]{
    pLeftInputOperator, 
    null, 
    pLeftOuterJoinOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOuterJoinOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<LeftOuterJoinOperator> rawAccumulateAllValuesOfleftOuterJoinOperator(final Object[] parameters) {
    Set<LeftOuterJoinOperator> results = new HashSet<LeftOuterJoinOperator>();
    rawAccumulateAllValues(POSITION_LEFTOUTERJOINOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOuterJoinOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOfleftOuterJoinOperator() {
    return rawAccumulateAllValuesOfleftOuterJoinOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOuterJoinOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOfleftOuterJoinOperator(final UnnecessaryLeftOuterJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfleftOuterJoinOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOuterJoinOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOfleftOuterJoinOperator(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfleftOuterJoinOperator(new Object[]{
    pLeftInputOperator, 
    pDualOperator, 
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
  public Set<Operator> getAllValuesOfparentOperator(final UnnecessaryLeftOuterJoinMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pLeftInputOperator, 
    pDualOperator, 
    pLeftOuterJoinOperator, 
    null
    });
  }
  
  @Override
  protected UnnecessaryLeftOuterJoinMatch tupleToMatch(final Tuple t) {
    try {
    	return UnnecessaryLeftOuterJoinMatch.newMatch((Operator) t.get(POSITION_LEFTINPUTOPERATOR), (DualObjectSourceOperator) t.get(POSITION_DUALOPERATOR), (LeftOuterJoinOperator) t.get(POSITION_LEFTOUTERJOINOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected UnnecessaryLeftOuterJoinMatch arrayToMatch(final Object[] match) {
    try {
    	return UnnecessaryLeftOuterJoinMatch.newMatch((Operator) match[POSITION_LEFTINPUTOPERATOR], (DualObjectSourceOperator) match[POSITION_DUALOPERATOR], (LeftOuterJoinOperator) match[POSITION_LEFTOUTERJOINOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected UnnecessaryLeftOuterJoinMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return UnnecessaryLeftOuterJoinMatch.newMutableMatch((Operator) match[POSITION_LEFTINPUTOPERATOR], (DualObjectSourceOperator) match[POSITION_DUALOPERATOR], (LeftOuterJoinOperator) match[POSITION_LEFTOUTERJOINOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<UnnecessaryLeftOuterJoinMatcher> querySpecification() throws ViatraQueryException {
    return UnnecessaryLeftOuterJoinQuerySpecification.instance();
  }
}
