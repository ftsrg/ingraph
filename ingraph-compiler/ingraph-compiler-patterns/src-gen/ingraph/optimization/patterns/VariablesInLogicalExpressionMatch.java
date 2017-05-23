/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.VariablesInLogicalExpressionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Expression;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.variablesInLogicalExpression pattern,
 * to be used in conjunction with {@link VariablesInLogicalExpressionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see VariablesInLogicalExpressionMatcher
 * @see VariablesInLogicalExpressionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class VariablesInLogicalExpressionMatch extends BasePatternMatch {
  private Expression fExpression;
  
  private static List<String> parameterNames = makeImmutableList("expression");
  
  private VariablesInLogicalExpressionMatch(final Expression pExpression) {
    this.fExpression = pExpression;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("expression".equals(parameterName)) return this.fExpression;
    return null;
  }
  
  public Expression getExpression() {
    return this.fExpression;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("expression".equals(parameterName) ) {
        this.fExpression = (Expression) newValue;
        return true;
    }
    return false;
  }
  
  public void setExpression(final Expression pExpression) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fExpression = pExpression;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.variablesInLogicalExpression";
  }
  
  @Override
  public List<String> parameterNames() {
    return VariablesInLogicalExpressionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fExpression};
  }
  
  @Override
  public VariablesInLogicalExpressionMatch toImmutable() {
    return isMutable() ? newMatch(fExpression) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"expression\"=" + prettyPrintValue(fExpression)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fExpression == null) ? 0 : fExpression.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof VariablesInLogicalExpressionMatch)) { // this should be infrequent
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof IPatternMatch)) {
            return false;
        }
        IPatternMatch otherSig  = (IPatternMatch) obj;
        if (!specification().equals(otherSig.specification()))
            return false;
        return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    VariablesInLogicalExpressionMatch other = (VariablesInLogicalExpressionMatch) obj;
    if (fExpression == null) {if (other.fExpression != null) return false;}
    else if (!fExpression.equals(other.fExpression)) return false;
    return true;
  }
  
  @Override
  public VariablesInLogicalExpressionQuerySpecification specification() {
    try {
        return VariablesInLogicalExpressionQuerySpecification.instance();
    } catch (ViatraQueryException ex) {
         // This cannot happen, as the match object can only be instantiated if the query specification exists
         throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static VariablesInLogicalExpressionMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static VariablesInLogicalExpressionMatch newMutableMatch(final Expression pExpression) {
    return new Mutable(pExpression);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpression the fixed value of pattern parameter expression, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static VariablesInLogicalExpressionMatch newMatch(final Expression pExpression) {
    return new Immutable(pExpression);
  }
  
  private static final class Mutable extends VariablesInLogicalExpressionMatch {
    Mutable(final Expression pExpression) {
      super(pExpression);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends VariablesInLogicalExpressionMatch {
    Immutable(final Expression pExpression) {
      super(pExpression);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
