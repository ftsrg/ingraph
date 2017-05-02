/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.EmptyAllDifferentOperatorMatch;
import ingraph.optimization.patterns.util.EmptyAllDifferentOperatorQuerySpecification;
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
import relalg.AllDifferentOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.emptyAllDifferentOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EmptyAllDifferentOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // [b]
 * pattern emptyAllDifferentOperator(inputOperator : Operator, allDifferentOperator : AllDifferentOperator, parentOperator : Operator) {
 * 	find parentOperator(allDifferentOperator, parentOperator);
 * 	0 == count find allDifferentOperatorEdgeVariables(allDifferentOperator, _);
 * 	AllDifferentOperator.input(allDifferentOperator, inputOperator);
 * }
 * </pre></code>
 * 
 * @see EmptyAllDifferentOperatorMatch
 * @see EmptyAllDifferentOperatorProcessor
 * @see EmptyAllDifferentOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class EmptyAllDifferentOperatorMatcher extends BaseMatcher<EmptyAllDifferentOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EmptyAllDifferentOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    EmptyAllDifferentOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (EmptyAllDifferentOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static EmptyAllDifferentOperatorMatcher create() throws ViatraQueryException {
    return new EmptyAllDifferentOperatorMatcher();
  }
  
  private final static int POSITION_INPUTOPERATOR = 0;
  
  private final static int POSITION_ALLDIFFERENTOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EmptyAllDifferentOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private EmptyAllDifferentOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a EmptyAllDifferentOperatorMatch object.
   * 
   */
  public Collection<EmptyAllDifferentOperatorMatch> getAllMatches(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a EmptyAllDifferentOperatorMatch object, or null if no match is found.
   * 
   */
  public EmptyAllDifferentOperatorMatch getOneArbitraryMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator, final IMatchProcessor<? super EmptyAllDifferentOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator, final IMatchProcessor<? super EmptyAllDifferentOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pInputOperator, pAllDifferentOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EmptyAllDifferentOperatorMatch newMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return EmptyAllDifferentOperatorMatch.newMatch(pInputOperator, pAllDifferentOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for inputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfinputOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_INPUTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for inputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfinputOperator() {
    return rawAccumulateAllValuesOfinputOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for inputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfinputOperator(final EmptyAllDifferentOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfinputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for inputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfinputOperator(final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfinputOperator(new Object[]{
    null, 
    pAllDifferentOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<AllDifferentOperator> rawAccumulateAllValuesOfallDifferentOperator(final Object[] parameters) {
    Set<AllDifferentOperator> results = new HashSet<AllDifferentOperator>();
    rawAccumulateAllValues(POSITION_ALLDIFFERENTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator() {
    return rawAccumulateAllValuesOfallDifferentOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator(final EmptyAllDifferentOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfallDifferentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for allDifferentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<AllDifferentOperator> getAllValuesOfallDifferentOperator(final Operator pInputOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfallDifferentOperator(new Object[]{
    pInputOperator, 
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
  public Set<Operator> getAllValuesOfparentOperator(final EmptyAllDifferentOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pInputOperator, 
    pAllDifferentOperator, 
    null
    });
  }
  
  @Override
  protected EmptyAllDifferentOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return EmptyAllDifferentOperatorMatch.newMatch((Operator) t.get(POSITION_INPUTOPERATOR), (AllDifferentOperator) t.get(POSITION_ALLDIFFERENTOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EmptyAllDifferentOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return EmptyAllDifferentOperatorMatch.newMatch((Operator) match[POSITION_INPUTOPERATOR], (AllDifferentOperator) match[POSITION_ALLDIFFERENTOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EmptyAllDifferentOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return EmptyAllDifferentOperatorMatch.newMutableMatch((Operator) match[POSITION_INPUTOPERATOR], (AllDifferentOperator) match[POSITION_ALLDIFFERENTOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<EmptyAllDifferentOperatorMatcher> querySpecification() throws ViatraQueryException {
    return EmptyAllDifferentOperatorQuerySpecification.instance();
  }
}
