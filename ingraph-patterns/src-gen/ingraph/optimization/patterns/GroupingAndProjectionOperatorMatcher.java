/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.GroupingAndProjectionOperatorMatch;
import ingraph.optimization.patterns.util.GroupingAndProjectionOperatorQuerySpecification;
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
import relalg.GroupingAndProjectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.groupingAndProjectionOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link GroupingAndProjectionOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern groupingAndProjectionOperator(groupingAndProjectionOperator) {
 * 	GroupingAndProjectionOperator(groupingAndProjectionOperator);
 * }
 * </pre></code>
 * 
 * @see GroupingAndProjectionOperatorMatch
 * @see GroupingAndProjectionOperatorProcessor
 * @see GroupingAndProjectionOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class GroupingAndProjectionOperatorMatcher extends BaseMatcher<GroupingAndProjectionOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static GroupingAndProjectionOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    GroupingAndProjectionOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (GroupingAndProjectionOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static GroupingAndProjectionOperatorMatcher create() throws ViatraQueryException {
    return new GroupingAndProjectionOperatorMatcher();
  }
  
  private final static int POSITION_GROUPINGANDPROJECTIONOPERATOR = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(GroupingAndProjectionOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private GroupingAndProjectionOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return matches represented as a GroupingAndProjectionOperatorMatch object.
   * 
   */
  public Collection<GroupingAndProjectionOperatorMatch> getAllMatches(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return rawGetAllMatches(new Object[]{pGroupingAndProjectionOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return a match represented as a GroupingAndProjectionOperatorMatch object, or null if no match is found.
   * 
   */
  public GroupingAndProjectionOperatorMatch getOneArbitraryMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pGroupingAndProjectionOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return rawHasMatch(new Object[]{pGroupingAndProjectionOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return rawCountMatches(new Object[]{pGroupingAndProjectionOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator, final IMatchProcessor<? super GroupingAndProjectionOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pGroupingAndProjectionOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator, final IMatchProcessor<? super GroupingAndProjectionOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pGroupingAndProjectionOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public GroupingAndProjectionOperatorMatch newMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return GroupingAndProjectionOperatorMatch.newMatch(pGroupingAndProjectionOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingAndProjectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<GroupingAndProjectionOperator> rawAccumulateAllValuesOfgroupingAndProjectionOperator(final Object[] parameters) {
    Set<GroupingAndProjectionOperator> results = new HashSet<GroupingAndProjectionOperator>();
    rawAccumulateAllValues(POSITION_GROUPINGANDPROJECTIONOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingAndProjectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GroupingAndProjectionOperator> getAllValuesOfgroupingAndProjectionOperator() {
    return rawAccumulateAllValuesOfgroupingAndProjectionOperator(emptyArray());
  }
  
  @Override
  protected GroupingAndProjectionOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return GroupingAndProjectionOperatorMatch.newMatch((GroupingAndProjectionOperator) t.get(POSITION_GROUPINGANDPROJECTIONOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected GroupingAndProjectionOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return GroupingAndProjectionOperatorMatch.newMatch((GroupingAndProjectionOperator) match[POSITION_GROUPINGANDPROJECTIONOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected GroupingAndProjectionOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return GroupingAndProjectionOperatorMatch.newMutableMatch((GroupingAndProjectionOperator) match[POSITION_GROUPINGANDPROJECTIONOPERATOR]);
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
  public static IQuerySpecification<GroupingAndProjectionOperatorMatcher> querySpecification() throws ViatraQueryException {
    return GroupingAndProjectionOperatorQuerySpecification.instance();
  }
}
