/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.JoinOnDualQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.joinOnDual pattern,
 * to be used in conjunction with {@link JoinOnDualMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see JoinOnDualMatcher
 * @see JoinOnDualProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class JoinOnDualMatch extends BasePatternMatch {
  private Operator fOtherInputOperator;
  
  private EquiJoinLikeOperator fEquiJoinLikeOperator;
  
  private static List<String> parameterNames = makeImmutableList("otherInputOperator", "equiJoinLikeOperator");
  
  private JoinOnDualMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    this.fOtherInputOperator = pOtherInputOperator;
    this.fEquiJoinLikeOperator = pEquiJoinLikeOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("otherInputOperator".equals(parameterName)) return this.fOtherInputOperator;
    if ("equiJoinLikeOperator".equals(parameterName)) return this.fEquiJoinLikeOperator;
    return null;
  }
  
  public Operator getOtherInputOperator() {
    return this.fOtherInputOperator;
  }
  
  public EquiJoinLikeOperator getEquiJoinLikeOperator() {
    return this.fEquiJoinLikeOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("otherInputOperator".equals(parameterName) ) {
        this.fOtherInputOperator = (Operator) newValue;
        return true;
    }
    if ("equiJoinLikeOperator".equals(parameterName) ) {
        this.fEquiJoinLikeOperator = (EquiJoinLikeOperator) newValue;
        return true;
    }
    return false;
  }
  
  public void setOtherInputOperator(final Operator pOtherInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fOtherInputOperator = pOtherInputOperator;
  }
  
  public void setEquiJoinLikeOperator(final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fEquiJoinLikeOperator = pEquiJoinLikeOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.joinOnDual";
  }
  
  @Override
  public List<String> parameterNames() {
    return JoinOnDualMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fOtherInputOperator, fEquiJoinLikeOperator};
  }
  
  @Override
  public JoinOnDualMatch toImmutable() {
    return isMutable() ? newMatch(fOtherInputOperator, fEquiJoinLikeOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"otherInputOperator\"=" + prettyPrintValue(fOtherInputOperator) + ", ");
    
    result.append("\"equiJoinLikeOperator\"=" + prettyPrintValue(fEquiJoinLikeOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fOtherInputOperator == null) ? 0 : fOtherInputOperator.hashCode());
    result = prime * result + ((fEquiJoinLikeOperator == null) ? 0 : fEquiJoinLikeOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof JoinOnDualMatch)) { // this should be infrequent
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
    JoinOnDualMatch other = (JoinOnDualMatch) obj;
    if (fOtherInputOperator == null) {if (other.fOtherInputOperator != null) return false;}
    else if (!fOtherInputOperator.equals(other.fOtherInputOperator)) return false;
    if (fEquiJoinLikeOperator == null) {if (other.fEquiJoinLikeOperator != null) return false;}
    else if (!fEquiJoinLikeOperator.equals(other.fEquiJoinLikeOperator)) return false;
    return true;
  }
  
  @Override
  public JoinOnDualQuerySpecification specification() {
    try {
        return JoinOnDualQuerySpecification.instance();
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
  public static JoinOnDualMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static JoinOnDualMatch newMutableMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return new Mutable(pOtherInputOperator, pEquiJoinLikeOperator);
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
  public static JoinOnDualMatch newMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
    return new Immutable(pOtherInputOperator, pEquiJoinLikeOperator);
  }
  
  private static final class Mutable extends JoinOnDualMatch {
    Mutable(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
      super(pOtherInputOperator, pEquiJoinLikeOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends JoinOnDualMatch {
    Immutable(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator) {
      super(pOtherInputOperator, pEquiJoinLikeOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
