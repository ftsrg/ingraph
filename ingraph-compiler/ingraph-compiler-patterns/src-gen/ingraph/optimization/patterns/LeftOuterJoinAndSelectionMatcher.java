/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import ingraph.optimization.patterns.util.LeftOuterJoinAndSelectionQuerySpecification;
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
import relalg.LeftOuterJoinOperator;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.leftOuterJoinAndSelection pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link LeftOuterJoinAndSelectionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * parentOperator
 *             |
 *             | input
 *             V
 *        selectionOperator.condition = NOT(...)
 *             |
 *             | input
 *             V
 *      leftOuterJoinOperator
 *         |         |
 *        /           \
 *       /             \
 *      | leftInput     | rightInput
 *      V               V
 *    leftInputOp...  operator
 * 
 * // [5] (run after [2])
 * // transformation for combining adjacent selection and leftOuterJoin operators to a single antijoin operator
 * pattern leftOuterJoinAndSelection(
 *   parentOperator : Operator,
 *   selectionOperator : SelectionOperator,
 *   leftOuterJoinOperator: LeftOuterJoinOperator,
 *   leftInputOperator: Operator,
 *   rightInputOperator: Operator
 * ) {
 *   find parentOperator(selectionOperator, parentOperator);
 *   SelectionOperator.input(selectionOperator, leftOuterJoinOperator);
 *   SelectionOperator.condition(selectionOperator, condition);
 *   UnaryLogicalExpression.operator(condition, ::NOT);
 *   UnaryLogicalExpression.operand(condition, _conditionInternalExpression);
 *   
 *   LeftOuterJoinOperator.leftInput(leftOuterJoinOperator, leftInputOperator);
 *   LeftOuterJoinOperator.rightInput(leftOuterJoinOperator, rightInputOperator);
 * }
 * </pre></code>
 * 
 * @see LeftOuterJoinAndSelectionMatch
 * @see LeftOuterJoinAndSelectionProcessor
 * @see LeftOuterJoinAndSelectionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class LeftOuterJoinAndSelectionMatcher extends BaseMatcher<LeftOuterJoinAndSelectionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static LeftOuterJoinAndSelectionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    LeftOuterJoinAndSelectionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (LeftOuterJoinAndSelectionMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static LeftOuterJoinAndSelectionMatcher create() throws ViatraQueryException {
    return new LeftOuterJoinAndSelectionMatcher();
  }
  
  private final static int POSITION_PARENTOPERATOR = 0;
  
  private final static int POSITION_SELECTIONOPERATOR = 1;
  
  private final static int POSITION_LEFTOUTERJOINOPERATOR = 2;
  
  private final static int POSITION_LEFTINPUTOPERATOR = 3;
  
  private final static int POSITION_RIGHTINPUTOPERATOR = 4;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(LeftOuterJoinAndSelectionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private LeftOuterJoinAndSelectionMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return matches represented as a LeftOuterJoinAndSelectionMatch object.
   * 
   */
  public Collection<LeftOuterJoinAndSelectionMatch> getAllMatches(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawGetAllMatches(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return a match represented as a LeftOuterJoinAndSelectionMatch object, or null if no match is found.
   * 
   */
  public LeftOuterJoinAndSelectionMatch getOneArbitraryMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawHasMatch(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawCountMatches(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator, final IMatchProcessor<? super LeftOuterJoinAndSelectionMatch> processor) {
    rawForEachMatch(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator, final IMatchProcessor<? super LeftOuterJoinAndSelectionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public LeftOuterJoinAndSelectionMatch newMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return LeftOuterJoinAndSelectionMatch.newMatch(pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator);
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
  public Set<Operator> getAllValuesOfparentOperator(final LeftOuterJoinAndSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    null, 
    pSelectionOperator, 
    pLeftOuterJoinOperator, 
    pLeftInputOperator, 
    pRightInputOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SelectionOperator> rawAccumulateAllValuesOfselectionOperator(final Object[] parameters) {
    Set<SelectionOperator> results = new HashSet<SelectionOperator>();
    rawAccumulateAllValues(POSITION_SELECTIONOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator() {
    return rawAccumulateAllValuesOfselectionOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator(final LeftOuterJoinAndSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfselectionOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator(final Operator pParentOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawAccumulateAllValuesOfselectionOperator(new Object[]{
    pParentOperator, 
    null, 
    pLeftOuterJoinOperator, 
    pLeftInputOperator, 
    pRightInputOperator
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
  public Set<LeftOuterJoinOperator> getAllValuesOfleftOuterJoinOperator(final LeftOuterJoinAndSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfleftOuterJoinOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOuterJoinOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOfleftOuterJoinOperator(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return rawAccumulateAllValuesOfleftOuterJoinOperator(new Object[]{
    pParentOperator, 
    pSelectionOperator, 
    null, 
    pLeftInputOperator, 
    pRightInputOperator
    });
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
  public Set<Operator> getAllValuesOfleftInputOperator(final LeftOuterJoinAndSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfleftInputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfleftInputOperator(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pRightInputOperator) {
    return rawAccumulateAllValuesOfleftInputOperator(new Object[]{
    pParentOperator, 
    pSelectionOperator, 
    pLeftOuterJoinOperator, 
    null, 
    pRightInputOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfrightInputOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_RIGHTINPUTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInputOperator() {
    return rawAccumulateAllValuesOfrightInputOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInputOperator(final LeftOuterJoinAndSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfrightInputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfrightInputOperator(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator) {
    return rawAccumulateAllValuesOfrightInputOperator(new Object[]{
    pParentOperator, 
    pSelectionOperator, 
    pLeftOuterJoinOperator, 
    pLeftInputOperator, 
    null
    });
  }
  
  @Override
  protected LeftOuterJoinAndSelectionMatch tupleToMatch(final Tuple t) {
    try {
        return LeftOuterJoinAndSelectionMatch.newMatch((Operator) t.get(POSITION_PARENTOPERATOR), (SelectionOperator) t.get(POSITION_SELECTIONOPERATOR), (LeftOuterJoinOperator) t.get(POSITION_LEFTOUTERJOINOPERATOR), (Operator) t.get(POSITION_LEFTINPUTOPERATOR), (Operator) t.get(POSITION_RIGHTINPUTOPERATOR));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LeftOuterJoinAndSelectionMatch arrayToMatch(final Object[] match) {
    try {
        return LeftOuterJoinAndSelectionMatch.newMatch((Operator) match[POSITION_PARENTOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR], (LeftOuterJoinOperator) match[POSITION_LEFTOUTERJOINOPERATOR], (Operator) match[POSITION_LEFTINPUTOPERATOR], (Operator) match[POSITION_RIGHTINPUTOPERATOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LeftOuterJoinAndSelectionMatch arrayToMatchMutable(final Object[] match) {
    try {
        return LeftOuterJoinAndSelectionMatch.newMutableMatch((Operator) match[POSITION_PARENTOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR], (LeftOuterJoinOperator) match[POSITION_LEFTOUTERJOINOPERATOR], (Operator) match[POSITION_LEFTINPUTOPERATOR], (Operator) match[POSITION_RIGHTINPUTOPERATOR]);
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
  public static IQuerySpecification<LeftOuterJoinAndSelectionMatcher> querySpecification() throws ViatraQueryException {
    return LeftOuterJoinAndSelectionQuerySpecification.instance();
  }
}
