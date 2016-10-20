/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Optimization.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.ParentOperatorMatch;
import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
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

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.parentOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ParentOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern parentOperator(parentOperator : Operator, operator : Operator) {
 * 	AlphaOperator.input(parentOperator, operator);
 * } or {
 * 	BetaOperator.leftInput(parentOperator, operator);
 * } or {
 * 	BetaOperator.rightInput(parentOperator, operator);
 * }
 * </pre></code>
 * 
 * @see ParentOperatorMatch
 * @see ParentOperatorProcessor
 * @see ParentOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ParentOperatorMatcher extends BaseMatcher<ParentOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ParentOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ParentOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (ParentOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ParentOperatorMatcher create() throws ViatraQueryException {
    return new ParentOperatorMatcher();
  }
  
  private final static int POSITION_PARENTOPERATOR = 0;
  
  private final static int POSITION_OPERATOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ParentOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ParentOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return matches represented as a ParentOperatorMatch object.
   * 
   */
  public Collection<ParentOperatorMatch> getAllMatches(final Operator pParentOperator, final Operator pOperator) {
    return rawGetAllMatches(new Object[]{pParentOperator, pOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return a match represented as a ParentOperatorMatch object, or null if no match is found.
   * 
   */
  public ParentOperatorMatch getOneArbitraryMatch(final Operator pParentOperator, final Operator pOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pParentOperator, pOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pParentOperator, final Operator pOperator) {
    return rawHasMatch(new Object[]{pParentOperator, pOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pParentOperator, final Operator pOperator) {
    return rawCountMatches(new Object[]{pParentOperator, pOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pParentOperator, final Operator pOperator, final IMatchProcessor<? super ParentOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pParentOperator, pOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pParentOperator, final Operator pOperator, final IMatchProcessor<? super ParentOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pParentOperator, pOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ParentOperatorMatch newMatch(final Operator pParentOperator, final Operator pOperator) {
    return ParentOperatorMatch.newMatch(pParentOperator, pOperator);
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
  public Set<Operator> getAllValuesOfparentOperator(final ParentOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final Operator pOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    null, 
    pOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for operator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfoperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_OPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for operator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfoperator() {
    return rawAccumulateAllValuesOfoperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for operator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfoperator(final ParentOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfoperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for operator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfoperator(final Operator pParentOperator) {
    return rawAccumulateAllValuesOfoperator(new Object[]{
    pParentOperator, 
    null
    });
  }
  
  @Override
  protected ParentOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return ParentOperatorMatch.newMatch((Operator) t.get(POSITION_PARENTOPERATOR), (Operator) t.get(POSITION_OPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ParentOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return ParentOperatorMatch.newMatch((Operator) match[POSITION_PARENTOPERATOR], (Operator) match[POSITION_OPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ParentOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ParentOperatorMatch.newMutableMatch((Operator) match[POSITION_PARENTOPERATOR], (Operator) match[POSITION_OPERATOR]);
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
  public static IQuerySpecification<ParentOperatorMatcher> querySpecification() throws ViatraQueryException {
    return ParentOperatorQuerySpecification.instance();
  }
}
