/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.JoinOnDualMatch;
import ingraph.optimization.patterns.util.JoinOnDualQuerySpecification;
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
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.joinOnDual pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link JoinOnDualMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern joinOnDual(otherInputOperator : Operator, equiJoinLikeOperator : EquiJoinLikeOperator) {
 * 	DualObjectSourceOperator(dualOperator);
 * 	JoinOperator.leftInput(equiJoinLikeOperator, dualOperator);
 * 	JoinOperator.rightInput(equiJoinLikeOperator, otherInputOperator);
 * } or {
 *   DualObjectSourceOperator(dualOperator);
 *   JoinOperator.leftInput(equiJoinLikeOperator, otherInputOperator);
 *   JoinOperator.rightInput(equiJoinLikeOperator, dualOperator);
 * } or {
 *   DualObjectSourceOperator(dualOperator);
 *   LeftOuterJoinOperator.leftInput(equiJoinLikeOperator, otherInputOperator);
 *   LeftOuterJoinOperator.rightInput(equiJoinLikeOperator, dualOperator);
 * }
 * </pre></code>
 * 
 * @see JoinOnDualMatch
 * @see JoinOnDualProcessor
 * @see JoinOnDualQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class JoinOnDualMatcher extends BaseMatcher<JoinOnDualMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static JoinOnDualMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    JoinOnDualMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (JoinOnDualMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static JoinOnDualMatcher create() throws ViatraQueryException {
    return new JoinOnDualMatcher();
  }
  
  private final static int POSITION_OTHERINPUTOPERATOR = 0;
  
  private final static int POSITION_EQUIJOINLIKEOPERATOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(JoinOnDualMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private JoinOnDualMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return matches represented as a JoinOnDualMatch object.
   * 
   */
  public Collection<JoinOnDualMatch> getAllMatches(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawGetAllMatches(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return a match represented as a JoinOnDualMatch object, or null if no match is found.
   * 
   */
  public JoinOnDualMatch getOneArbitraryMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawHasMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawCountMatches(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final IMatchProcessor<? super JoinOnDualMatch> processor) {
    rawForEachMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final IMatchProcessor<? super JoinOnDualMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pOtherInputOperator, pEquiJoinLikeOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public JoinOnDualMatch newMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return JoinOnDualMatch.newMatch(pOtherInputOperator, pEquiJoinLikeOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Operator> rawAccumulateAllValuesOfotherInputOperator(final Object[] parameters) {
    Set<Operator> results = new HashSet<Operator>();
    rawAccumulateAllValues(POSITION_OTHERINPUTOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator() {
    return rawAccumulateAllValuesOfotherInputOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator(final JoinOnDualMatch partialMatch) {
    return rawAccumulateAllValuesOfotherInputOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for otherInputOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfotherInputOperator(final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return rawAccumulateAllValuesOfotherInputOperator(new Object[]{
    null, 
    pEquiJoinLikeOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<EquiJoinLikeOperator> rawAccumulateAllValuesOfequiJoinLikeOperator(final Object[] parameters) {
    Set<EquiJoinLikeOperator> results = new HashSet<EquiJoinLikeOperator>();
    rawAccumulateAllValues(POSITION_EQUIJOINLIKEOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator() {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator(final JoinOnDualMatch partialMatch) {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for equiJoinLikeOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<EquiJoinLikeOperator> getAllValuesOfequiJoinLikeOperator(final Operator pOtherInputOperator) {
    return rawAccumulateAllValuesOfequiJoinLikeOperator(new Object[]{
    pOtherInputOperator, 
    null
    });
  }
  
  @Override
  protected JoinOnDualMatch tupleToMatch(final Tuple t) {
    try {
    	return JoinOnDualMatch.newMatch((Operator) t.get(POSITION_OTHERINPUTOPERATOR), (EquiJoinLikeOperator) t.get(POSITION_EQUIJOINLIKEOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected JoinOnDualMatch arrayToMatch(final Object[] match) {
    try {
    	return JoinOnDualMatch.newMatch((Operator) match[POSITION_OTHERINPUTOPERATOR], (EquiJoinLikeOperator) match[POSITION_EQUIJOINLIKEOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected JoinOnDualMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return JoinOnDualMatch.newMutableMatch((Operator) match[POSITION_OTHERINPUTOPERATOR], (EquiJoinLikeOperator) match[POSITION_EQUIJOINLIKEOPERATOR]);
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
  public static IQuerySpecification<JoinOnDualMatcher> querySpecification() throws ViatraQueryException {
    return JoinOnDualQuerySpecification.instance();
  }
}
