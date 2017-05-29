/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import ingraph.optimization.patterns.util.DefaultExpandOperatorQuerySpecification;
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
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.defaultExpandOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link DefaultExpandOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * parentOperator
 *           |
 *           | input
 *           V
 *     expandOperator
 * 
 * // [2] transformation for eliminating the remaining default expand operators
 * pattern defaultExpandOperator(expandOperator : ExpandOperator, parentOperator : Operator) {
 * 	find parentOperator(expandOperator, parentOperator);
 * 	find expandOperatorWithDefaultEdgeVariable(expandOperator);
 * }
 * </pre></code>
 * 
 * @see DefaultExpandOperatorMatch
 * @see DefaultExpandOperatorProcessor
 * @see DefaultExpandOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class DefaultExpandOperatorMatcher extends BaseMatcher<DefaultExpandOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static DefaultExpandOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    DefaultExpandOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (DefaultExpandOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static DefaultExpandOperatorMatcher create() throws ViatraQueryException {
    return new DefaultExpandOperatorMatcher();
  }
  
  private final static int POSITION_EXPANDOPERATOR = 0;
  
  private final static int POSITION_PARENTOPERATOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(DefaultExpandOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private DefaultExpandOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a DefaultExpandOperatorMatch object.
   * 
   */
  public Collection<DefaultExpandOperatorMatch> getAllMatches(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pExpandOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a DefaultExpandOperatorMatch object, or null if no match is found.
   * 
   */
  public DefaultExpandOperatorMatch getOneArbitraryMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pExpandOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pExpandOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pExpandOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator, final IMatchProcessor<? super DefaultExpandOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pExpandOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator, final IMatchProcessor<? super DefaultExpandOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pExpandOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public DefaultExpandOperatorMatch newMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return DefaultExpandOperatorMatch.newMatch(pExpandOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ExpandOperator> rawAccumulateAllValuesOfexpandOperator(final Object[] parameters) {
    Set<ExpandOperator> results = new HashSet<ExpandOperator>();
    rawAccumulateAllValues(POSITION_EXPANDOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator() {
    return rawAccumulateAllValuesOfexpandOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final DefaultExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfexpandOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final Operator pParentOperator) {
    return rawAccumulateAllValuesOfexpandOperator(new Object[]{
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
  public Set<Operator> getAllValuesOfparentOperator(final DefaultExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final ExpandOperator pExpandOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pExpandOperator, 
    null
    });
  }
  
  @Override
  protected DefaultExpandOperatorMatch tupleToMatch(final Tuple t) {
    try {
        return DefaultExpandOperatorMatch.newMatch((ExpandOperator) t.get(POSITION_EXPANDOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected DefaultExpandOperatorMatch arrayToMatch(final Object[] match) {
    try {
        return DefaultExpandOperatorMatch.newMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected DefaultExpandOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
        return DefaultExpandOperatorMatch.newMutableMatch((ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<DefaultExpandOperatorMatcher> querySpecification() throws ViatraQueryException {
    return DefaultExpandOperatorQuerySpecification.instance();
  }
}
