/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.CommutativeOperatorMatch;
import ingraph.optimization.patterns.util.CommutativeOperatorQuerySpecification;
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
import relalg.CommutativeBinaryOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.CommutativeOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link CommutativeOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // joins are commutative: a op b = b op a
 * pattern
 * CommutativeOperator(op : CommutativeBinaryOperator, leftInput : Operator, rightInput : Operator) {
 * 	CommutativeBinaryOperator.leftInput(op, leftInput);
 * 	CommutativeBinaryOperator.rightInput(op, rightInput);
 * }
 * </pre></code>
 * 
 * @see CommutativeOperatorMatch
 * @see CommutativeOperatorProcessor
 * @see CommutativeOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class CommutativeOperatorMatcher extends BaseMatcher<CommutativeOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static CommutativeOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    CommutativeOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (CommutativeOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static CommutativeOperatorMatcher create() throws ViatraQueryException {
    return new CommutativeOperatorMatcher();
  }
  
  private final static int POSITION_OP = 0;
  
  private final static int POSITION_LEFTINPUT = 1;
  
  private final static int POSITION_RIGHTINPUT = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(CommutativeOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private CommutativeOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return matches represented as a CommutativeOperatorMatch object.
   * 
   */
  public Collection<CommutativeOperatorMatch> getAllMatches(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return rawGetAllMatches(new Object[]{pOp, pLeftInput, pRightInput});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return a match represented as a CommutativeOperatorMatch object, or null if no match is found.
   * 
   */
  public CommutativeOperatorMatch getOneArbitraryMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return rawGetOneArbitraryMatch(new Object[]{pOp, pLeftInput, pRightInput});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return rawHasMatch(new Object[]{pOp, pLeftInput, pRightInput});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return rawCountMatches(new Object[]{pOp, pLeftInput, pRightInput});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput, final IMatchProcessor<? super CommutativeOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pOp, pLeftInput, pRightInput}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput, final IMatchProcessor<? super CommutativeOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pOp, pLeftInput, pRightInput}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public CommutativeOperatorMatch newMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return CommutativeOperatorMatch.newMatch(pOp, pLeftInput, pRightInput);
  }
  
  /**
   * Retrieve the set of values that occur in matches for op.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<CommutativeBinaryOperator> rawAccumulateAllValuesOfop(final Object[] parameters) {
    Set<CommutativeBinaryOperator> results = new HashSet<CommutativeBinaryOperator>();
    rawAccumulateAllValues(POSITION_OP, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for op.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CommutativeBinaryOperator> getAllValuesOfop() {
    return rawAccumulateAllValuesOfop(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CommutativeBinaryOperator> getAllValuesOfop(final CommutativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfop(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CommutativeBinaryOperator> getAllValuesOfop(final Operator pLeftInput, final Operator pRightInput) {
    return rawAccumulateAllValuesOfop(new Object[]{
    null, 
    pLeftInput, 
    pRightInput
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfleftInput(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_LEFTINPUT, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInput() {
    return rawAccumulateAllValuesOfleftInput(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInput(final CommutativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfleftInput(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInput(final CommutativeBinaryOperator pOp, final Operator pRightInput) {
    return rawAccumulateAllValuesOfleftInput(new Object[]{
    pOp, 
    null, 
    pRightInput
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfrightInput(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_RIGHTINPUT, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInput() {
    return rawAccumulateAllValuesOfrightInput(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInput(final CommutativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfrightInput(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInput.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInput(final CommutativeBinaryOperator pOp, final Operator pLeftInput) {
    return rawAccumulateAllValuesOfrightInput(new Object[]{
    pOp, 
    pLeftInput, 
    null
    });
  }
  
  @Override
  protected CommutativeOperatorMatch tupleToMatch(final Tuple t) {
    try {
        return CommutativeOperatorMatch.newMatch((CommutativeBinaryOperator) t.get(POSITION_OP), (Operator) t.get(POSITION_LEFTINPUT), (Operator) t.get(POSITION_RIGHTINPUT));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected CommutativeOperatorMatch arrayToMatch(final Object[] match) {
    try {
        return CommutativeOperatorMatch.newMatch((CommutativeBinaryOperator) match[POSITION_OP], (Operator) match[POSITION_LEFTINPUT], (Operator) match[POSITION_RIGHTINPUT]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected CommutativeOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
        return CommutativeOperatorMatch.newMutableMatch((CommutativeBinaryOperator) match[POSITION_OP], (Operator) match[POSITION_LEFTINPUT], (Operator) match[POSITION_RIGHTINPUT]);
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
  public static IQuerySpecification<CommutativeOperatorMatcher> querySpecification() throws ViatraQueryException {
    return CommutativeOperatorQuerySpecification.instance();
  }
}
