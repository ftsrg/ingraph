/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.LeftDeepTreeNodesMatch;
import ingraph.optimization.patterns.util.LeftDeepTreeNodesQuerySpecification;
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
import relalg.BinaryLogicalExpression;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.leftDeepTreeNodes pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link LeftDeepTreeNodesMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern leftDeepTreeNodes(parent : BinaryLogicalExpression, child : BinaryLogicalExpression) {
 *   BinaryLogicalExpression.leftOperand(parent, child);
 *   BinaryLogicalExpression(child);
 * }
 * </pre></code>
 * 
 * @see LeftDeepTreeNodesMatch
 * @see LeftDeepTreeNodesProcessor
 * @see LeftDeepTreeNodesQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class LeftDeepTreeNodesMatcher extends BaseMatcher<LeftDeepTreeNodesMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static LeftDeepTreeNodesMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    LeftDeepTreeNodesMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (LeftDeepTreeNodesMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static LeftDeepTreeNodesMatcher create() throws ViatraQueryException {
    return new LeftDeepTreeNodesMatcher();
  }
  
  private final static int POSITION_PARENT = 0;
  
  private final static int POSITION_CHILD = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(LeftDeepTreeNodesMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private LeftDeepTreeNodesMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return matches represented as a LeftDeepTreeNodesMatch object.
   * 
   */
  public Collection<LeftDeepTreeNodesMatch> getAllMatches(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return rawGetAllMatches(new Object[]{pParent, pChild});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return a match represented as a LeftDeepTreeNodesMatch object, or null if no match is found.
   * 
   */
  public LeftDeepTreeNodesMatch getOneArbitraryMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return rawGetOneArbitraryMatch(new Object[]{pParent, pChild});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return rawHasMatch(new Object[]{pParent, pChild});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return rawCountMatches(new Object[]{pParent, pChild});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild, final IMatchProcessor<? super LeftDeepTreeNodesMatch> processor) {
    rawForEachMatch(new Object[]{pParent, pChild}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild, final IMatchProcessor<? super LeftDeepTreeNodesMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pParent, pChild}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public LeftDeepTreeNodesMatch newMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return LeftDeepTreeNodesMatch.newMatch(pParent, pChild);
  }
  
  /**
   * Retrieve the set of values that occur in matches for parent.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<BinaryLogicalExpression> rawAccumulateAllValuesOfparent(final Object[] parameters) {
    Set<BinaryLogicalExpression> results = new HashSet<BinaryLogicalExpression>();
    rawAccumulateAllValues(POSITION_PARENT, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for parent.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfparent() {
    return rawAccumulateAllValuesOfparent(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parent.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfparent(final LeftDeepTreeNodesMatch partialMatch) {
    return rawAccumulateAllValuesOfparent(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for parent.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfparent(final BinaryLogicalExpression pChild) {
    return rawAccumulateAllValuesOfparent(new Object[]{
    null, 
    pChild
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for child.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<BinaryLogicalExpression> rawAccumulateAllValuesOfchild(final Object[] parameters) {
    Set<BinaryLogicalExpression> results = new HashSet<BinaryLogicalExpression>();
    rawAccumulateAllValues(POSITION_CHILD, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for child.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfchild() {
    return rawAccumulateAllValuesOfchild(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for child.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfchild(final LeftDeepTreeNodesMatch partialMatch) {
    return rawAccumulateAllValuesOfchild(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for child.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<BinaryLogicalExpression> getAllValuesOfchild(final BinaryLogicalExpression pParent) {
    return rawAccumulateAllValuesOfchild(new Object[]{
    pParent, 
    null
    });
  }
  
  @Override
  protected LeftDeepTreeNodesMatch tupleToMatch(final Tuple t) {
    try {
    	return LeftDeepTreeNodesMatch.newMatch((BinaryLogicalExpression) t.get(POSITION_PARENT), (BinaryLogicalExpression) t.get(POSITION_CHILD));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected LeftDeepTreeNodesMatch arrayToMatch(final Object[] match) {
    try {
    	return LeftDeepTreeNodesMatch.newMatch((BinaryLogicalExpression) match[POSITION_PARENT], (BinaryLogicalExpression) match[POSITION_CHILD]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected LeftDeepTreeNodesMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return LeftDeepTreeNodesMatch.newMutableMatch((BinaryLogicalExpression) match[POSITION_PARENT], (BinaryLogicalExpression) match[POSITION_CHILD]);
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
  public static IQuerySpecification<LeftDeepTreeNodesMatcher> querySpecification() throws ViatraQueryException {
    return LeftDeepTreeNodesQuerySpecification.instance();
  }
}
