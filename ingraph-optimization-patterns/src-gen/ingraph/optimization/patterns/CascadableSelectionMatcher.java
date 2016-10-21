/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.CascadableSelectionMatch;
import ingraph.optimization.patterns.util.CascadableSelectionQuerySpecification;
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
import relalg.LogicalExpression;
import relalg.SelectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.CascadableSelection pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link CascadableSelectionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // AND expressions are cascadable
 * pattern CascadableSelection(selection : SelectionOperator, leftOperand : LogicalExpression, rightOperand : LogicalExpression) {
 * 	SelectionOperator.condition(selection, condition);
 * 	BinaryLogicalExpression.operator(condition, ::AND);
 * 
 * 	BinaryLogicalExpression.leftOperand(condition, leftOperand);
 * 	BinaryLogicalExpression.rightOperand(condition, rightOperand);
 * }
 * </pre></code>
 * 
 * @see CascadableSelectionMatch
 * @see CascadableSelectionProcessor
 * @see CascadableSelectionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class CascadableSelectionMatcher extends BaseMatcher<CascadableSelectionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static CascadableSelectionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    CascadableSelectionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (CascadableSelectionMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static CascadableSelectionMatcher create() throws ViatraQueryException {
    return new CascadableSelectionMatcher();
  }
  
  private final static int POSITION_SELECTION = 0;
  
  private final static int POSITION_LEFTOPERAND = 1;
  
  private final static int POSITION_RIGHTOPERAND = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(CascadableSelectionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private CascadableSelectionMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return matches represented as a CascadableSelectionMatch object.
   * 
   */
  public Collection<CascadableSelectionMatch> getAllMatches(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return rawGetAllMatches(new Object[]{pSelection, pLeftOperand, pRightOperand});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return a match represented as a CascadableSelectionMatch object, or null if no match is found.
   * 
   */
  public CascadableSelectionMatch getOneArbitraryMatch(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return rawGetOneArbitraryMatch(new Object[]{pSelection, pLeftOperand, pRightOperand});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return rawHasMatch(new Object[]{pSelection, pLeftOperand, pRightOperand});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return rawCountMatches(new Object[]{pSelection, pLeftOperand, pRightOperand});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand, final IMatchProcessor<? super CascadableSelectionMatch> processor) {
    rawForEachMatch(new Object[]{pSelection, pLeftOperand, pRightOperand}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand, final IMatchProcessor<? super CascadableSelectionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSelection, pLeftOperand, pRightOperand}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSelection the fixed value of pattern parameter selection, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public CascadableSelectionMatch newMatch(final SelectionOperator pSelection, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return CascadableSelectionMatch.newMatch(pSelection, pLeftOperand, pRightOperand);
  }
  
  /**
   * Retrieve the set of values that occur in matches for selection.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SelectionOperator> rawAccumulateAllValuesOfselection(final Object[] parameters) {
    Set<SelectionOperator> results = new HashSet<SelectionOperator>();
    rawAccumulateAllValues(POSITION_SELECTION, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for selection.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselection() {
    return rawAccumulateAllValuesOfselection(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selection.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselection(final CascadableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfselection(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selection.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselection(final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return rawAccumulateAllValuesOfselection(new Object[]{
    null, 
    pLeftOperand, 
    pRightOperand
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<LogicalExpression> rawAccumulateAllValuesOfleftOperand(final Object[] parameters) {
    Set<LogicalExpression> results = new HashSet<LogicalExpression>();
    rawAccumulateAllValues(POSITION_LEFTOPERAND, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfleftOperand() {
    return rawAccumulateAllValuesOfleftOperand(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfleftOperand(final CascadableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfleftOperand(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for leftOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfleftOperand(final SelectionOperator pSelection, final LogicalExpression pRightOperand) {
    return rawAccumulateAllValuesOfleftOperand(new Object[]{
    pSelection, 
    null, 
    pRightOperand
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<LogicalExpression> rawAccumulateAllValuesOfrightOperand(final Object[] parameters) {
    Set<LogicalExpression> results = new HashSet<LogicalExpression>();
    rawAccumulateAllValues(POSITION_RIGHTOPERAND, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfrightOperand() {
    return rawAccumulateAllValuesOfrightOperand(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfrightOperand(final CascadableSelectionMatch partialMatch) {
    return rawAccumulateAllValuesOfrightOperand(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rightOperand.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<LogicalExpression> getAllValuesOfrightOperand(final SelectionOperator pSelection, final LogicalExpression pLeftOperand) {
    return rawAccumulateAllValuesOfrightOperand(new Object[]{
    pSelection, 
    pLeftOperand, 
    null
    });
  }
  
  @Override
  protected CascadableSelectionMatch tupleToMatch(final Tuple t) {
    try {
    	return CascadableSelectionMatch.newMatch((SelectionOperator) t.get(POSITION_SELECTION), (LogicalExpression) t.get(POSITION_LEFTOPERAND), (LogicalExpression) t.get(POSITION_RIGHTOPERAND));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected CascadableSelectionMatch arrayToMatch(final Object[] match) {
    try {
    	return CascadableSelectionMatch.newMatch((SelectionOperator) match[POSITION_SELECTION], (LogicalExpression) match[POSITION_LEFTOPERAND], (LogicalExpression) match[POSITION_RIGHTOPERAND]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected CascadableSelectionMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return CascadableSelectionMatch.newMutableMatch((SelectionOperator) match[POSITION_SELECTION], (LogicalExpression) match[POSITION_LEFTOPERAND], (LogicalExpression) match[POSITION_RIGHTOPERAND]);
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
  public static IQuerySpecification<CascadableSelectionMatcher> querySpecification() throws ViatraQueryException {
    return CascadableSelectionQuerySpecification.instance();
  }
}
