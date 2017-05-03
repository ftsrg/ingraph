/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.TransitiveExpandOperatorMatch;
import ingraph.optimization.patterns.util.TransitiveExpandOperatorQuerySpecification;
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
import relalg.EdgeListVariable;
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.transitiveExpandOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TransitiveExpandOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // [3] transform for eliminating the rest of expand operators
 * pattern transitiveExpandOperator(inputOperator : Operator, expandOperator : ExpandOperator, parentOperator : Operator, edgeListVariable : EdgeListVariable) {
 *   find parentOperator(expandOperator, parentOperator);
 *   ExpandOperator.input(expandOperator, inputOperator);
 *   ExpandOperator.edgeVariable(expandOperator, edgeListVariable);
 *   EdgeListVariable(edgeListVariable);
 * }
 * </pre></code>
 * 
 * @see TransitiveExpandOperatorMatch
 * @see TransitiveExpandOperatorProcessor
 * @see TransitiveExpandOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TransitiveExpandOperatorMatcher extends BaseMatcher<TransitiveExpandOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TransitiveExpandOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    TransitiveExpandOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (TransitiveExpandOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static TransitiveExpandOperatorMatcher create() throws ViatraQueryException {
    return new TransitiveExpandOperatorMatcher();
  }
  
  private final static int POSITION_INPUTOPERATOR = 0;
  
  private final static int POSITION_EXPANDOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static int POSITION_EDGELISTVARIABLE = 3;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(TransitiveExpandOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private TransitiveExpandOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return matches represented as a TransitiveExpandOperatorMatch object.
   * 
   */
  public Collection<TransitiveExpandOperatorMatch> getAllMatches(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawGetAllMatches(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return a match represented as a TransitiveExpandOperatorMatch object, or null if no match is found.
   * 
   */
  public TransitiveExpandOperatorMatch getOneArbitraryMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawGetOneArbitraryMatch(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawHasMatch(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawCountMatches(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable, final IMatchProcessor<? super TransitiveExpandOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable, final IMatchProcessor<? super TransitiveExpandOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TransitiveExpandOperatorMatch newMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return TransitiveExpandOperatorMatch.newMatch(pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable);
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
  public Set<Operator> getAllValuesOfinputOperator(final TransitiveExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfinputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for inputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfinputOperator(final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawAccumulateAllValuesOfinputOperator(new Object[]{
    null, 
    pExpandOperator, 
    pParentOperator, 
    pEdgeListVariable
    });
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
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final TransitiveExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfexpandOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for expandOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ExpandOperator> getAllValuesOfexpandOperator(final Operator pInputOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return rawAccumulateAllValuesOfexpandOperator(new Object[]{
    pInputOperator, 
    null, 
    pParentOperator, 
    pEdgeListVariable
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
  public Set<Operator> getAllValuesOfparentOperator(final TransitiveExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final Operator pInputOperator, final ExpandOperator pExpandOperator, final EdgeListVariable pEdgeListVariable) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pInputOperator, 
    pExpandOperator, 
    null, 
    pEdgeListVariable
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeListVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<EdgeListVariable> rawAccumulateAllValuesOfedgeListVariable(final Object[] parameters) {
    Set<EdgeListVariable> results = new HashSet<EdgeListVariable>();
    rawAccumulateAllValues(POSITION_EDGELISTVARIABLE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeListVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeListVariable> getAllValuesOfedgeListVariable() {
    return rawAccumulateAllValuesOfedgeListVariable(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeListVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeListVariable> getAllValuesOfedgeListVariable(final TransitiveExpandOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfedgeListVariable(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for edgeListVariable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EdgeListVariable> getAllValuesOfedgeListVariable(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfedgeListVariable(new Object[]{
    pInputOperator, 
    pExpandOperator, 
    pParentOperator, 
    null
    });
  }
  
  @Override
  protected TransitiveExpandOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return TransitiveExpandOperatorMatch.newMatch((Operator) t.get(POSITION_INPUTOPERATOR), (ExpandOperator) t.get(POSITION_EXPANDOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR), (EdgeListVariable) t.get(POSITION_EDGELISTVARIABLE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TransitiveExpandOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return TransitiveExpandOperatorMatch.newMatch((Operator) match[POSITION_INPUTOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR], (EdgeListVariable) match[POSITION_EDGELISTVARIABLE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TransitiveExpandOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TransitiveExpandOperatorMatch.newMutableMatch((Operator) match[POSITION_INPUTOPERATOR], (ExpandOperator) match[POSITION_EXPANDOPERATOR], (Operator) match[POSITION_PARENTOPERATOR], (EdgeListVariable) match[POSITION_EDGELISTVARIABLE]);
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
  public static IQuerySpecification<TransitiveExpandOperatorMatcher> querySpecification() throws ViatraQueryException {
    return TransitiveExpandOperatorQuerySpecification.instance();
  }
}
