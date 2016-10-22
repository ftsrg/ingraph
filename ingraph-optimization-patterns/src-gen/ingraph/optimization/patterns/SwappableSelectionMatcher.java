/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.SwappableSelectionMatch;
import ingraph.optimization.patterns.util.SwappableSelectionQuerySpecification;
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
import relalg.SelectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.SwappableSelection pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SwappableSelectionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern SwappableSelection(parentOperator : Operator, selectionOperator1 : SelectionOperator, selectionOperator2: SelectionOperator) {
 * 	find parentOperator(parentOperator, selectionOperator1);
 * 	SelectionOperator.input(selectionOperator1, selectionOperator2);
 * }
 * </pre></code>
 * 
 * @see SwappableSelectionMatch
 * @see SwappableSelectionProcessor
 * @see SwappableSelectionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SwappableSelectionMatcher extends BaseMatcher<SwappableSelectionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SwappableSelectionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    SwappableSelectionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (SwappableSelectionMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static SwappableSelectionMatcher create() throws ViatraQueryException {
    return new SwappableSelectionMatcher();
  }
  
  private final static int POSITION_PARENTOPERATOR = 0;
  
  private final static int POSITION_SELECTIONOPERATOR1 = 1;
  
  private final static int POSITION_SELECTIONOPERATOR2 = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SwappableSelectionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private SwappableSelectionMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return matches represented as a SwappableSelectionMatch object.
   * 
   */
  public Collection<SwappableSelectionMatch> getAllMatches(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return rawGetAllMatches(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return a match represented as a SwappableSelectionMatch object, or null if no match is found.
   * 
   */
  public SwappableSelectionMatch getOneArbitraryMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return rawGetOneArbitraryMatch(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return rawHasMatch(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return rawCountMatches(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2, final IMatchProcessor<? super SwappableSelectionMatch> processor) {
    rawForEachMatch(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2, final IMatchProcessor<? super SwappableSelectionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pParentOperator, pSelectionOperator1, pSelectionOperator2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SwappableSelectionMatch newMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return SwappableSelectionMatch.newMatch(pParentOperator, pSelectionOperator1, pSelectionOperator2);
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
  public Set<Operator> getAllValuesOfparentOperator(final SwappableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    null, 
    pSelectionOperator1, 
    pSelectionOperator2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SelectionOperator> rawAccumulateAllValuesOfselectionOperator1(final Object[] parameters) {
    Set<SelectionOperator> results = new HashSet<SelectionOperator>();
    rawAccumulateAllValues(POSITION_SELECTIONOPERATOR1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator1() {
    return rawAccumulateAllValuesOfselectionOperator1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator1(final SwappableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfselectionOperator1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator1(final Operator pParentOperator, final SelectionOperator pSelectionOperator2) {
    return rawAccumulateAllValuesOfselectionOperator1(new Object[]{
    pParentOperator, 
    null, 
    pSelectionOperator2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SelectionOperator> rawAccumulateAllValuesOfselectionOperator2(final Object[] parameters) {
    Set<SelectionOperator> results = new HashSet<SelectionOperator>();
    rawAccumulateAllValues(POSITION_SELECTIONOPERATOR2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator2() {
    return rawAccumulateAllValuesOfselectionOperator2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator2(final SwappableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfselectionOperator2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator2(final Operator pParentOperator, final SelectionOperator pSelectionOperator1) {
    return rawAccumulateAllValuesOfselectionOperator2(new Object[]{
    pParentOperator, 
    pSelectionOperator1, 
    null
    });
  }
  
  @Override
  protected SwappableSelectionMatch tupleToMatch(final Tuple t) {
    try {
    	return SwappableSelectionMatch.newMatch((Operator) t.get(POSITION_PARENTOPERATOR), (SelectionOperator) t.get(POSITION_SELECTIONOPERATOR1), (SelectionOperator) t.get(POSITION_SELECTIONOPERATOR2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwappableSelectionMatch arrayToMatch(final Object[] match) {
    try {
    	return SwappableSelectionMatch.newMatch((Operator) match[POSITION_PARENTOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR1], (SelectionOperator) match[POSITION_SELECTIONOPERATOR2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwappableSelectionMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SwappableSelectionMatch.newMutableMatch((Operator) match[POSITION_PARENTOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR1], (SelectionOperator) match[POSITION_SELECTIONOPERATOR2]);
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
  public static IQuerySpecification<SwappableSelectionMatcher> querySpecification() throws ViatraQueryException {
    return SwappableSelectionQuerySpecification.instance();
  }
}
