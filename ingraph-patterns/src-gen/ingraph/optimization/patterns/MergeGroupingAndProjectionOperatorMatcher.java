/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.MergeGroupingAndProjectionOperatorMatch;
import ingraph.optimization.patterns.util.MergeGroupingAndProjectionOperatorQuerySpecification;
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
import relalg.GroupingOperator;
import relalg.Operator;
import relalg.ProjectionOperator;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.mergeGroupingAndProjectionOperator pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link MergeGroupingAndProjectionOperatorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * parentOperator
 *           |
 *           | input
 *           V
 *   projectionOperator
 *           |
 *           | input
 *           V
 *    groupingOperator
 * 
 * // [4] transformation for combining adjacent grouping and projection operators to a single groupingAndProjection operator
 * pattern mergeGroupingAndProjectionOperator(projectionOperator : ProjectionOperator, groupingOperator : GroupingOperator, parentOperator : Operator) {
 *   find parentOperator(projectionOperator, parentOperator);
 *   ProjectionOperator.input(projectionOperator, groupingOperator);
 *   // do not merge operators that are already GroupingAndProjectionOperators
 *   neg find groupingAndProjectionOperator(projectionOperator);
 *   neg find groupingAndProjectionOperator(groupingOperator);
 * }
 * </pre></code>
 * 
 * @see MergeGroupingAndProjectionOperatorMatch
 * @see MergeGroupingAndProjectionOperatorProcessor
 * @see MergeGroupingAndProjectionOperatorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class MergeGroupingAndProjectionOperatorMatcher extends BaseMatcher<MergeGroupingAndProjectionOperatorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static MergeGroupingAndProjectionOperatorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    MergeGroupingAndProjectionOperatorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (MergeGroupingAndProjectionOperatorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static MergeGroupingAndProjectionOperatorMatcher create() throws ViatraQueryException {
    return new MergeGroupingAndProjectionOperatorMatcher();
  }
  
  private final static int POSITION_PROJECTIONOPERATOR = 0;
  
  private final static int POSITION_GROUPINGOPERATOR = 1;
  
  private final static int POSITION_PARENTOPERATOR = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(MergeGroupingAndProjectionOperatorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private MergeGroupingAndProjectionOperatorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return matches represented as a MergeGroupingAndProjectionOperatorMatch object.
   * 
   */
  public Collection<MergeGroupingAndProjectionOperatorMatch> getAllMatches(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return rawGetAllMatches(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return a match represented as a MergeGroupingAndProjectionOperatorMatch object, or null if no match is found.
   * 
   */
  public MergeGroupingAndProjectionOperatorMatch getOneArbitraryMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return rawGetOneArbitraryMatch(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return rawHasMatch(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return rawCountMatches(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator, final IMatchProcessor<? super MergeGroupingAndProjectionOperatorMatch> processor) {
    rawForEachMatch(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator, final IMatchProcessor<? super MergeGroupingAndProjectionOperatorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pProjectionOperator, pGroupingOperator, pParentOperator}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public MergeGroupingAndProjectionOperatorMatch newMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return MergeGroupingAndProjectionOperatorMatch.newMatch(pProjectionOperator, pGroupingOperator, pParentOperator);
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<ProjectionOperator> rawAccumulateAllValuesOfprojectionOperator(final Object[] parameters) {
    Set<ProjectionOperator> results = new HashSet<ProjectionOperator>();
    rawAccumulateAllValues(POSITION_PROJECTIONOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator() {
    return rawAccumulateAllValuesOfprojectionOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator(final MergeGroupingAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfprojectionOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for projectionOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<ProjectionOperator> getAllValuesOfprojectionOperator(final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfprojectionOperator(new Object[]{
    null, 
    pGroupingOperator, 
    pParentOperator
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<GroupingOperator> rawAccumulateAllValuesOfgroupingOperator(final Object[] parameters) {
    Set<GroupingOperator> results = new HashSet<GroupingOperator>();
    rawAccumulateAllValues(POSITION_GROUPINGOPERATOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GroupingOperator> getAllValuesOfgroupingOperator() {
    return rawAccumulateAllValuesOfgroupingOperator(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GroupingOperator> getAllValuesOfgroupingOperator(final MergeGroupingAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfgroupingOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for groupingOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<GroupingOperator> getAllValuesOfgroupingOperator(final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return rawAccumulateAllValuesOfgroupingOperator(new Object[]{
    pProjectionOperator, 
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
  public Set<Operator> getAllValuesOfparentOperator(final MergeGroupingAndProjectionOperatorMatch partialMatch) {
    return rawAccumulateAllValuesOfparentOperator(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parentOperator.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Operator> getAllValuesOfparentOperator(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator) {
    return rawAccumulateAllValuesOfparentOperator(new Object[]{
    pProjectionOperator, 
    pGroupingOperator, 
    null
    });
  }
  
  @Override
  protected MergeGroupingAndProjectionOperatorMatch tupleToMatch(final Tuple t) {
    try {
    	return MergeGroupingAndProjectionOperatorMatch.newMatch((ProjectionOperator) t.get(POSITION_PROJECTIONOPERATOR), (GroupingOperator) t.get(POSITION_GROUPINGOPERATOR), (Operator) t.get(POSITION_PARENTOPERATOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MergeGroupingAndProjectionOperatorMatch arrayToMatch(final Object[] match) {
    try {
    	return MergeGroupingAndProjectionOperatorMatch.newMatch((ProjectionOperator) match[POSITION_PROJECTIONOPERATOR], (GroupingOperator) match[POSITION_GROUPINGOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MergeGroupingAndProjectionOperatorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return MergeGroupingAndProjectionOperatorMatch.newMutableMatch((ProjectionOperator) match[POSITION_PROJECTIONOPERATOR], (GroupingOperator) match[POSITION_GROUPINGOPERATOR], (Operator) match[POSITION_PARENTOPERATOR]);
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
  public static IQuerySpecification<MergeGroupingAndProjectionOperatorMatcher> querySpecification() throws ViatraQueryException {
    return MergeGroupingAndProjectionOperatorQuerySpecification.instance();
  }
}
