/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.VariablesInLogicalExpressionMatch;
import ingraph.optimization.patterns.util.VariablesInLogicalExpressionQuerySpecification;
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
import relalg.Expression;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.variablesInLogicalExpression pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link VariablesInLogicalExpressionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern variablesInLogicalExpression(expression : Expression) {
 *   BinaryLogicalExpression.operator(expression, ::AND);
 *   
 *   find leftDeepTreeNodes+(expression, expression2);
 *   
 *   BinaryLogicalExpression.rightOperand(expression2, notNull);
 *   UnaryGraphObjectLogicalExpression.operator(notNull, ::IS_NOT_NULL);
 * }
 * </pre></code>
 * 
 * @see VariablesInLogicalExpressionMatch
 * @see VariablesInLogicalExpressionProcessor
 * @see VariablesInLogicalExpressionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class VariablesInLogicalExpressionMatcher extends BaseMatcher<VariablesInLogicalExpressionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static VariablesInLogicalExpressionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    VariablesInLogicalExpressionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (VariablesInLogicalExpressionMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static VariablesInLogicalExpressionMatcher create() throws ViatraQueryException {
    return new VariablesInLogicalExpressionMatcher();
  }
  
  private final static int POSITION_EXPRESSION = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(VariablesInLogicalExpressionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private VariablesInLogicalExpressionMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return matches represented as a VariablesInLogicalExpressionMatch object.
   * 
   */
  public Collection<VariablesInLogicalExpressionMatch> getAllMatches(final Expression pExpression) {
    return rawGetAllMatches(new Object[]{pExpression});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return a match represented as a VariablesInLogicalExpressionMatch object, or null if no match is found.
   * 
   */
  public VariablesInLogicalExpressionMatch getOneArbitraryMatch(final Expression pExpression) {
    return rawGetOneArbitraryMatch(new Object[]{pExpression});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Expression pExpression) {
    return rawHasMatch(new Object[]{pExpression});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Expression pExpression) {
    return rawCountMatches(new Object[]{pExpression});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Expression pExpression, final IMatchProcessor<? super VariablesInLogicalExpressionMatch> processor) {
    rawForEachMatch(new Object[]{pExpression}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Expression pExpression, final IMatchProcessor<? super VariablesInLogicalExpressionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pExpression}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public VariablesInLogicalExpressionMatch newMatch(final Expression pExpression) {
    return VariablesInLogicalExpressionMatch.newMatch(pExpression);
  }
  
  /**
   * Retrieve the set of values that occur in matches for expression.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Expression> rawAccumulateAllValuesOfexpression(final Object[] parameters) {
    Set<Expression> results = new HashSet<Expression>();
    rawAccumulateAllValues(POSITION_EXPRESSION, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for expression.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Expression> getAllValuesOfexpression() {
    return rawAccumulateAllValuesOfexpression(emptyArray());
  }
  
  @Override
  protected VariablesInLogicalExpressionMatch tupleToMatch(final Tuple t) {
    try {
        return VariablesInLogicalExpressionMatch.newMatch((Expression) t.get(POSITION_EXPRESSION));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected VariablesInLogicalExpressionMatch arrayToMatch(final Object[] match) {
    try {
        return VariablesInLogicalExpressionMatch.newMatch((Expression) match[POSITION_EXPRESSION]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected VariablesInLogicalExpressionMatch arrayToMatchMutable(final Object[] match) {
    try {
        return VariablesInLogicalExpressionMatch.newMutableMatch((Expression) match[POSITION_EXPRESSION]);
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
  public static IQuerySpecification<VariablesInLogicalExpressionMatcher> querySpecification() throws ViatraQueryException {
    return VariablesInLogicalExpressionQuerySpecification.instance();
  }
}
