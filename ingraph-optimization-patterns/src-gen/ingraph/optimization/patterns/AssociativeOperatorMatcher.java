/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.AssociativeOperatorMatch;
import ingraph.optimization.patterns.util.AssociativeOperatorQuerySpecification;
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
import relalg.JoinOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.AssociativeOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link AssociativeOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // (a op1 b) op2 c = a op1 (b op2 c)
 * pattern
 * AssociativeOperator(op1 : JoinOperator, op2 : JoinOperator, a : Operator, b : Operator, c : Operator) {
 * 	JoinOperator.leftInput(op2, op1);
 * 	JoinOperator.rightInput(op2, c);
 * 	
 * 	JoinOperator.leftInput(op1, a);
 * 	JoinOperator.rightInput(op1, b);
 * }
 * </pre></code>
 * 
 * @see AssociativeOperatorMatch
 * @see AssociativeOperatorProcessor
 * @see AssociativeOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class AssociativeOperatorMatcher extends BaseMatcher<AssociativeOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AssociativeOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    AssociativeOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (AssociativeOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AssociativeOperatorMatcher create() throws ViatraQueryException {
    return new AssociativeOperatorMatcher();
  }
  
  private final static int POSITION_OP1 = 0;
  
  private final static int POSITION_OP2 = 1;
  
  private final static int POSITION_A = 2;
  
  private final static int POSITION_B = 3;
  
  private final static int POSITION_C = 4;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AssociativeOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private AssociativeOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return matches represented as a AssociativeOperatorMatch object.
   * 
   */
  public Collection<AssociativeOperatorMatch> getAllMatches(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return rawGetAllMatches(new Object[]{pOp1, pOp2, pA, pB, pC});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return a match represented as a AssociativeOperatorMatch object, or null if no match is found.
   * 
   */
  public AssociativeOperatorMatch getOneArbitraryMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return rawGetOneArbitraryMatch(new Object[]{pOp1, pOp2, pA, pB, pC});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return rawHasMatch(new Object[]{pOp1, pOp2, pA, pB, pC});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return rawCountMatches(new Object[]{pOp1, pOp2, pA, pB, pC});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC, final IMatchProcessor<? super AssociativeOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pOp1, pOp2, pA, pB, pC}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC, final IMatchProcessor<? super AssociativeOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pOp1, pOp2, pA, pB, pC}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public AssociativeOperatorMatch newMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return AssociativeOperatorMatch.newMatch(pOp1, pOp2, pA, pB, pC);
  }
  
  /**
   * Retrieve the set of values that occur in matches for op1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<JoinOperator> rawAccumulateAllValuesOfop1(final Object[] parameters) {
    Set<JoinOperator> results = new HashSet<JoinOperator>();
    rawAccumulateAllValues(POSITION_OP1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for op1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop1() {
    return rawAccumulateAllValuesOfop1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop1(final AssociativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfop1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop1(final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return rawAccumulateAllValuesOfop1(new Object[]{
    null, 
    pOp2, 
    pA, 
    pB, 
    pC
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for op2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<JoinOperator> rawAccumulateAllValuesOfop2(final Object[] parameters) {
    Set<JoinOperator> results = new HashSet<JoinOperator>();
    rawAccumulateAllValues(POSITION_OP2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for op2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop2() {
    return rawAccumulateAllValuesOfop2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop2(final AssociativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfop2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for op2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinOperator> getAllValuesOfop2(final JoinOperator pOp1, final Operator pA, final Operator pB, final Operator pC) {
    return rawAccumulateAllValuesOfop2(new Object[]{
    pOp1, 
    null, 
    pA, 
    pB, 
    pC
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for a.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfa(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_A, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for a.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfa() {
    return rawAccumulateAllValuesOfa(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for a.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfa(final AssociativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfa(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for a.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfa(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pB, final Operator pC) {
    return rawAccumulateAllValuesOfa(new Object[]{
    pOp1, 
    pOp2, 
    null, 
    pB, 
    pC
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for b.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfb(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_B, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for b.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfb() {
    return rawAccumulateAllValuesOfb(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for b.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfb(final AssociativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfb(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for b.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfb(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pC) {
    return rawAccumulateAllValuesOfb(new Object[]{
    pOp1, 
    pOp2, 
    pA, 
    null, 
    pC
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for c.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfc(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_C, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for c.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfc() {
    return rawAccumulateAllValuesOfc(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for c.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfc(final AssociativeOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfc(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for c.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfc(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB) {
    return rawAccumulateAllValuesOfc(new Object[]{
    pOp1, 
    pOp2, 
    pA, 
    pB, 
    null
    });
  }
  
  @Override
  protected AssociativeOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return AssociativeOperatorMatch.newMatch((JoinOperator) t.get(POSITION_OP1), (JoinOperator) t.get(POSITION_OP2), (Operator) t.get(POSITION_A), (Operator) t.get(POSITION_B), (Operator) t.get(POSITION_C));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected AssociativeOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return AssociativeOperatorMatch.newMatch((JoinOperator) match[POSITION_OP1], (JoinOperator) match[POSITION_OP2], (Operator) match[POSITION_A], (Operator) match[POSITION_B], (Operator) match[POSITION_C]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected AssociativeOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return AssociativeOperatorMatch.newMutableMatch((JoinOperator) match[POSITION_OP1], (JoinOperator) match[POSITION_OP2], (Operator) match[POSITION_A], (Operator) match[POSITION_B], (Operator) match[POSITION_C]);
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
  public static IQuerySpecification<AssociativeOperatorMatcher> querySpecification() throws ViatraQueryException {
    return AssociativeOperatorQuerySpecification.instance();
  }
}
