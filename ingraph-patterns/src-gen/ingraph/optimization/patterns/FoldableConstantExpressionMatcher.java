/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.FoldableConstantExpressionMatch;
import ingraph.optimization.patterns.util.FoldableConstantExpressionQuerySpecification;
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
import relalg.Literal;

/**
 * Generated pattern matcher API of the ingraph.optimization.patterns.FoldableConstantExpression pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link FoldableConstantExpressionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern FoldableConstantExpression(e, v1: Literal, v2: Literal) {
 * 	ArithmeticOperationExpression.leftOperand(e, v1);
 * 	ArithmeticOperationExpression.rightOperand(e, v2);
 * } or {
 * 	ArithmeticComparisonExpression.leftOperand(e, v1);
 * 	ArithmeticComparisonExpression.rightOperand(e, v2);
 * } or {
 * 	BinaryLogicalExpression.leftOperand(e, v1);
 * 	BinaryLogicalExpression.rightOperand(e, v2);
 * }
 * </pre></code>
 * 
 * @see FoldableConstantExpressionMatch
 * @see FoldableConstantExpressionProcessor
 * @see FoldableConstantExpressionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class FoldableConstantExpressionMatcher extends BaseMatcher<FoldableConstantExpressionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static FoldableConstantExpressionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    FoldableConstantExpressionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = (FoldableConstantExpressionMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static FoldableConstantExpressionMatcher create() throws ViatraQueryException {
    return new FoldableConstantExpressionMatcher();
  }
  
  private final static int POSITION_E = 0;
  
  private final static int POSITION_V1 = 1;
  
  private final static int POSITION_V2 = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(FoldableConstantExpressionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private FoldableConstantExpressionMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return matches represented as a FoldableConstantExpressionMatch object.
   * 
   */
  public Collection<FoldableConstantExpressionMatch> getAllMatches(final Expression pE, final Literal pV1, final Literal pV2) {
    return rawGetAllMatches(new Object[]{pE, pV1, pV2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return a match represented as a FoldableConstantExpressionMatch object, or null if no match is found.
   * 
   */
  public FoldableConstantExpressionMatch getOneArbitraryMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    return rawGetOneArbitraryMatch(new Object[]{pE, pV1, pV2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    return rawHasMatch(new Object[]{pE, pV1, pV2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Expression pE, final Literal pV1, final Literal pV2) {
    return rawCountMatches(new Object[]{pE, pV1, pV2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Expression pE, final Literal pV1, final Literal pV2, final IMatchProcessor<? super FoldableConstantExpressionMatch> processor) {
    rawForEachMatch(new Object[]{pE, pV1, pV2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Expression pE, final Literal pV1, final Literal pV2, final IMatchProcessor<? super FoldableConstantExpressionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pE, pV1, pV2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public FoldableConstantExpressionMatch newMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    return FoldableConstantExpressionMatch.newMatch(pE, pV1, pV2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Expression> rawAccumulateAllValuesOfe(final Object[] parameters) {
    Set<Expression> results = new HashSet<Expression>();
    rawAccumulateAllValues(POSITION_E, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Expression> getAllValuesOfe() {
    return rawAccumulateAllValuesOfe(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Expression> getAllValuesOfe(final FoldableConstantExpressionMatch partialMatch) {
    return rawAccumulateAllValuesOfe(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for e.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Expression> getAllValuesOfe(final Literal pV1, final Literal pV2) {
    return rawAccumulateAllValuesOfe(new Object[]{
    null, 
    pV1, 
    pV2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for v1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Literal> rawAccumulateAllValuesOfv1(final Object[] parameters) {
    Set<Literal> results = new HashSet<Literal>();
    rawAccumulateAllValues(POSITION_V1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for v1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv1() {
    return rawAccumulateAllValuesOfv1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv1(final FoldableConstantExpressionMatch partialMatch) {
    return rawAccumulateAllValuesOfv1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv1(final Expression pE, final Literal pV2) {
    return rawAccumulateAllValuesOfv1(new Object[]{
    pE, 
    null, 
    pV2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for v2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Literal> rawAccumulateAllValuesOfv2(final Object[] parameters) {
    Set<Literal> results = new HashSet<Literal>();
    rawAccumulateAllValues(POSITION_V2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for v2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv2() {
    return rawAccumulateAllValuesOfv2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv2(final FoldableConstantExpressionMatch partialMatch) {
    return rawAccumulateAllValuesOfv2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for v2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Literal> getAllValuesOfv2(final Expression pE, final Literal pV1) {
    return rawAccumulateAllValuesOfv2(new Object[]{
    pE, 
    pV1, 
    null
    });
  }
  
  @Override
  protected FoldableConstantExpressionMatch tupleToMatch(final Tuple t) {
    try {
    	return FoldableConstantExpressionMatch.newMatch((Expression) t.get(POSITION_E), (Literal) t.get(POSITION_V1), (Literal) t.get(POSITION_V2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected FoldableConstantExpressionMatch arrayToMatch(final Object[] match) {
    try {
    	return FoldableConstantExpressionMatch.newMatch((Expression) match[POSITION_E], (Literal) match[POSITION_V1], (Literal) match[POSITION_V2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected FoldableConstantExpressionMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return FoldableConstantExpressionMatch.newMutableMatch((Expression) match[POSITION_E], (Literal) match[POSITION_V1], (Literal) match[POSITION_V2]);
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
  public static IQuerySpecification<FoldableConstantExpressionMatcher> querySpecification() throws ViatraQueryException {
    return FoldableConstantExpressionQuerySpecification.instance();
  }
}
