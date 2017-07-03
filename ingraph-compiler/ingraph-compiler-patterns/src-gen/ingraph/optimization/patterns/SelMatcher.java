/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.SelMatch;
import ingraph.optimization.patterns.util.SelQuerySpecification;
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
import relalg.SelectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.sel pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SelMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern sel(lojOperator: LeftOuterJoinOperator, selectionOperator: SelectionOperator) {
 *   SelectionOperator.condition(selectionOperator, expression);
 *   SelectionOperator.input(selectionOperator, lojOperator);
 * 
 *   BinaryLogicalExpression.operator(expression, ::AND);
 * 
 *   find leftDeepTreeNodes+(expression, expression2);
 * 
 *   BinaryLogicalExpression.rightOperand(expression2, notNull);
 *   UnaryGraphObjectLogicalExpression.operator(notNull, ::IS_NOT_NULL); // (expression2 != NULL)
 * }
 * </pre></code>
 * 
 * @see SelMatch
 * @see SelProcessor
 * @see SelQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SelMatcher extends BaseMatcher<SelMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SelMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    SelMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (SelMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static SelMatcher create() throws ViatraQueryException {
    return new SelMatcher();
  }
  
  private final static int POSITION_LOJOPERATOR = 0;
  
  private final static int POSITION_SELECTIONOPERATOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SelMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private SelMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return matches represented as a SelMatch object.
   * 
   */
  public Collection<SelMatch> getAllMatches(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return rawGetAllMatches(new Object[]{pLojOperator, pSelectionOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return a match represented as a SelMatch object, or null if no match is found.
   * 
   */
  public SelMatch getOneArbitraryMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pLojOperator, pSelectionOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return rawHasMatch(new Object[]{pLojOperator, pSelectionOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return rawCountMatches(new Object[]{pLojOperator, pSelectionOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator, final IMatchProcessor<? super SelMatch> processor) {
    rawForEachMatch(new Object[]{pLojOperator, pSelectionOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator, final IMatchProcessor<? super SelMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pLojOperator, pSelectionOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SelMatch newMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return SelMatch.newMatch(pLojOperator, pSelectionOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for lojOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<LeftOuterJoinOperator> rawAccumulateAllValuesOflojOperator(final Object[] parameters) {
    Set<LeftOuterJoinOperator> results = new HashSet<LeftOuterJoinOperator>();
    rawAccumulateAllValues(POSITION_LOJOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for lojOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOflojOperator() {
    return rawAccumulateAllValuesOflojOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for lojOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOflojOperator(final SelMatch partialMatch) {
    return rawAccumulateAllValuesOflojOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for lojOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LeftOuterJoinOperator> getAllValuesOflojOperator(final SelectionOperator pSelectionOperator) {
    return rawAccumulateAllValuesOflojOperator(new Object[]{
    null, 
    pSelectionOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<SelectionOperator> rawAccumulateAllValuesOfselectionOperator(final Object[] parameters) {
    Set<SelectionOperator> results = new HashSet<SelectionOperator>();
    rawAccumulateAllValues(POSITION_SELECTIONOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator() {
    return rawAccumulateAllValuesOfselectionOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator(final SelMatch partialMatch) {
    return rawAccumulateAllValuesOfselectionOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for selectionOperator.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SelectionOperator> getAllValuesOfselectionOperator(final LeftOuterJoinOperator pLojOperator) {
    return rawAccumulateAllValuesOfselectionOperator(new Object[]{
    pLojOperator, 
    null
    });
  }
  
  @Override
  protected SelMatch tupleToMatch(final Tuple t) {
    try {
        return SelMatch.newMatch((LeftOuterJoinOperator) t.get(POSITION_LOJOPERATOR), (SelectionOperator) t.get(POSITION_SELECTIONOPERATOR));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SelMatch arrayToMatch(final Object[] match) {
    try {
        return SelMatch.newMatch((LeftOuterJoinOperator) match[POSITION_LOJOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SelMatch arrayToMatchMutable(final Object[] match) {
    try {
        return SelMatch.newMutableMatch((LeftOuterJoinOperator) match[POSITION_LOJOPERATOR], (SelectionOperator) match[POSITION_SELECTIONOPERATOR]);
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
  public static IQuerySpecification<SelMatcher> querySpecification() throws ViatraQueryException {
    return SelQuerySpecification.instance();
  }
}
