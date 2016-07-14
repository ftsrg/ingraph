/**
 * Generated from platform:/resource/hello-patterns/src/relalg/my.vql
 */
package relalg;

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
import relalg.JoinNode;
import relalg.ReteNode;
import relalg.TrimmerNode;
import relalg.TrimmerUpMatch;
import relalg.util.TrimmerUpQuerySpecification;

/**
 * Generated pattern matcher API of the relalg.trimmerUp pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TrimmerUpMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern trimmerUp(join, trimmer, rn1, rn2) {
 * 	JoinNode.leftParent(join, rn1);
 * 	JoinNode.rightParent(join, rn2);
 * 	TrimmerNode.parent(trimmer, join);
 * }
 * </pre></code>
 * 
 * @see TrimmerUpMatch
 * @see TrimmerUpProcessor
 * @see TrimmerUpQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TrimmerUpMatcher extends BaseMatcher<TrimmerUpMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TrimmerUpMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    TrimmerUpMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TrimmerUpMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_JOIN = 0;
  
  private final static int POSITION_TRIMMER = 1;
  
  private final static int POSITION_RN1 = 2;
  
  private final static int POSITION_RN2 = 3;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(TrimmerUpMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private TrimmerUpMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return matches represented as a TrimmerUpMatch object.
   * 
   */
  public Collection<TrimmerUpMatch> getAllMatches(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return rawGetAllMatches(new Object[]{pJoin, pTrimmer, pRn1, pRn2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return a match represented as a TrimmerUpMatch object, or null if no match is found.
   * 
   */
  public TrimmerUpMatch getOneArbitraryMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return rawGetOneArbitraryMatch(new Object[]{pJoin, pTrimmer, pRn1, pRn2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return rawHasMatch(new Object[]{pJoin, pTrimmer, pRn1, pRn2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return rawCountMatches(new Object[]{pJoin, pTrimmer, pRn1, pRn2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2, final IMatchProcessor<? super TrimmerUpMatch> processor) {
    rawForEachMatch(new Object[]{pJoin, pTrimmer, pRn1, pRn2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2, final IMatchProcessor<? super TrimmerUpMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pJoin, pTrimmer, pRn1, pRn2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TrimmerUpMatch newMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return TrimmerUpMatch.newMatch(pJoin, pTrimmer, pRn1, pRn2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for join.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<JoinNode> rawAccumulateAllValuesOfjoin(final Object[] parameters) {
    Set<JoinNode> results = new HashSet<JoinNode>();
    rawAccumulateAllValues(POSITION_JOIN, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for join.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinNode> getAllValuesOfjoin() {
    return rawAccumulateAllValuesOfjoin(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for join.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinNode> getAllValuesOfjoin(final TrimmerUpMatch partialMatch) {
    return rawAccumulateAllValuesOfjoin(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for join.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<JoinNode> getAllValuesOfjoin(final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return rawAccumulateAllValuesOfjoin(new Object[]{
    null, 
    pTrimmer, 
    pRn1, 
    pRn2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for trimmer.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<TrimmerNode> rawAccumulateAllValuesOftrimmer(final Object[] parameters) {
    Set<TrimmerNode> results = new HashSet<TrimmerNode>();
    rawAccumulateAllValues(POSITION_TRIMMER, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for trimmer.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrimmerNode> getAllValuesOftrimmer() {
    return rawAccumulateAllValuesOftrimmer(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for trimmer.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrimmerNode> getAllValuesOftrimmer(final TrimmerUpMatch partialMatch) {
    return rawAccumulateAllValuesOftrimmer(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for trimmer.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrimmerNode> getAllValuesOftrimmer(final JoinNode pJoin, final ReteNode pRn1, final ReteNode pRn2) {
    return rawAccumulateAllValuesOftrimmer(new Object[]{
    pJoin, 
    null, 
    pRn1, 
    pRn2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ReteNode> rawAccumulateAllValuesOfrn1(final Object[] parameters) {
    Set<ReteNode> results = new HashSet<ReteNode>();
    rawAccumulateAllValues(POSITION_RN1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn1() {
    return rawAccumulateAllValuesOfrn1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn1(final TrimmerUpMatch partialMatch) {
    return rawAccumulateAllValuesOfrn1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn1(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn2) {
    return rawAccumulateAllValuesOfrn1(new Object[]{
    pJoin, 
    pTrimmer, 
    null, 
    pRn2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ReteNode> rawAccumulateAllValuesOfrn2(final Object[] parameters) {
    Set<ReteNode> results = new HashSet<ReteNode>();
    rawAccumulateAllValues(POSITION_RN2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn2() {
    return rawAccumulateAllValuesOfrn2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn2(final TrimmerUpMatch partialMatch) {
    return rawAccumulateAllValuesOfrn2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for rn2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ReteNode> getAllValuesOfrn2(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1) {
    return rawAccumulateAllValuesOfrn2(new Object[]{
    pJoin, 
    pTrimmer, 
    pRn1, 
    null
    });
  }
  
  @Override
  protected TrimmerUpMatch tupleToMatch(final Tuple t) {
    try {
    	return TrimmerUpMatch.newMatch((JoinNode) t.get(POSITION_JOIN), (TrimmerNode) t.get(POSITION_TRIMMER), (ReteNode) t.get(POSITION_RN1), (ReteNode) t.get(POSITION_RN2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TrimmerUpMatch arrayToMatch(final Object[] match) {
    try {
    	return TrimmerUpMatch.newMatch((JoinNode) match[POSITION_JOIN], (TrimmerNode) match[POSITION_TRIMMER], (ReteNode) match[POSITION_RN1], (ReteNode) match[POSITION_RN2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TrimmerUpMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TrimmerUpMatch.newMutableMatch((JoinNode) match[POSITION_JOIN], (TrimmerNode) match[POSITION_TRIMMER], (ReteNode) match[POSITION_RN1], (ReteNode) match[POSITION_RN2]);
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
  public static IQuerySpecification<TrimmerUpMatcher> querySpecification() throws ViatraQueryException {
    return TrimmerUpQuerySpecification.instance();
  }
}
