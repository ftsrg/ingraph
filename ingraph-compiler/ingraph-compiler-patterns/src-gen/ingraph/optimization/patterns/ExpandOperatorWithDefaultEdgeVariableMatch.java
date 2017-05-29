/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.ExpandOperatorWithDefaultEdgeVariableQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.ExpandOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.expandOperatorWithDefaultEdgeVariable pattern,
 * to be used in conjunction with {@link ExpandOperatorWithDefaultEdgeVariableMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExpandOperatorWithDefaultEdgeVariableMatcher
 * @see ExpandOperatorWithDefaultEdgeVariableProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandOperatorWithDefaultEdgeVariableMatch extends BasePatternMatch {
  private ExpandOperator fDefaultExpandOperator;
  
  private static List<String> parameterNames = makeImmutableList("defaultExpandOperator");
  
  private ExpandOperatorWithDefaultEdgeVariableMatch(final ExpandOperator pDefaultExpandOperator) {
    this.fDefaultExpandOperator = pDefaultExpandOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("defaultExpandOperator".equals(parameterName)) return this.fDefaultExpandOperator;
    return null;
  }
  
  public ExpandOperator getDefaultExpandOperator() {
    return this.fDefaultExpandOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("defaultExpandOperator".equals(parameterName) ) {
        this.fDefaultExpandOperator = (ExpandOperator) newValue;
        return true;
    }
    return false;
  }
  
  public void setDefaultExpandOperator(final ExpandOperator pDefaultExpandOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fDefaultExpandOperator = pDefaultExpandOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.expandOperatorWithDefaultEdgeVariable";
  }
  
  @Override
  public List<String> parameterNames() {
    return ExpandOperatorWithDefaultEdgeVariableMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fDefaultExpandOperator};
  }
  
  @Override
  public ExpandOperatorWithDefaultEdgeVariableMatch toImmutable() {
    return isMutable() ? newMatch(fDefaultExpandOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"defaultExpandOperator\"=" + prettyPrintValue(fDefaultExpandOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fDefaultExpandOperator == null) ? 0 : fDefaultExpandOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof ExpandOperatorWithDefaultEdgeVariableMatch)) { // this should be infrequent
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
    ExpandOperatorWithDefaultEdgeVariableMatch other = (ExpandOperatorWithDefaultEdgeVariableMatch) obj;
    if (fDefaultExpandOperator == null) {if (other.fDefaultExpandOperator != null) return false;}
    else if (!fDefaultExpandOperator.equals(other.fDefaultExpandOperator)) return false;
    return true;
  }
  
  @Override
  public ExpandOperatorWithDefaultEdgeVariableQuerySpecification specification() {
    try {
        return ExpandOperatorWithDefaultEdgeVariableQuerySpecification.instance();
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
  public static ExpandOperatorWithDefaultEdgeVariableMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExpandOperatorWithDefaultEdgeVariableMatch newMutableMatch(final ExpandOperator pDefaultExpandOperator) {
    return new Mutable(pDefaultExpandOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pDefaultExpandOperator the fixed value of pattern parameter defaultExpandOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExpandOperatorWithDefaultEdgeVariableMatch newMatch(final ExpandOperator pDefaultExpandOperator) {
    return new Immutable(pDefaultExpandOperator);
  }
  
  private static final class Mutable extends ExpandOperatorWithDefaultEdgeVariableMatch {
    Mutable(final ExpandOperator pDefaultExpandOperator) {
      super(pDefaultExpandOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExpandOperatorWithDefaultEdgeVariableMatch {
    Immutable(final ExpandOperator pDefaultExpandOperator) {
      super(pDefaultExpandOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
