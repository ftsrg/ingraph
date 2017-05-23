/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.FoldableConstantExpressionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Expression;
import relalg.Literal;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.FoldableConstantExpression pattern,
 * to be used in conjunction with {@link FoldableConstantExpressionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see FoldableConstantExpressionMatcher
 * @see FoldableConstantExpressionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class FoldableConstantExpressionMatch extends BasePatternMatch {
  private Expression fE;
  
  private Literal fV1;
  
  private Literal fV2;
  
  private static List<String> parameterNames = makeImmutableList("e", "v1", "v2");
  
  private FoldableConstantExpressionMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    this.fE = pE;
    this.fV1 = pV1;
    this.fV2 = pV2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("e".equals(parameterName)) return this.fE;
    if ("v1".equals(parameterName)) return this.fV1;
    if ("v2".equals(parameterName)) return this.fV2;
    return null;
  }
  
  public Expression getE() {
    return this.fE;
  }
  
  public Literal getV1() {
    return this.fV1;
  }
  
  public Literal getV2() {
    return this.fV2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("e".equals(parameterName) ) {
        this.fE = (Expression) newValue;
        return true;
    }
    if ("v1".equals(parameterName) ) {
        this.fV1 = (Literal) newValue;
        return true;
    }
    if ("v2".equals(parameterName) ) {
        this.fV2 = (Literal) newValue;
        return true;
    }
    return false;
  }
  
  public void setE(final Expression pE) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fE = pE;
  }
  
  public void setV1(final Literal pV1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fV1 = pV1;
  }
  
  public void setV2(final Literal pV2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fV2 = pV2;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.FoldableConstantExpression";
  }
  
  @Override
  public List<String> parameterNames() {
    return FoldableConstantExpressionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fE, fV1, fV2};
  }
  
  @Override
  public FoldableConstantExpressionMatch toImmutable() {
    return isMutable() ? newMatch(fE, fV1, fV2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"e\"=" + prettyPrintValue(fE) + ", ");
    
    result.append("\"v1\"=" + prettyPrintValue(fV1) + ", ");
    
    result.append("\"v2\"=" + prettyPrintValue(fV2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fE == null) ? 0 : fE.hashCode());
    result = prime * result + ((fV1 == null) ? 0 : fV1.hashCode());
    result = prime * result + ((fV2 == null) ? 0 : fV2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof FoldableConstantExpressionMatch)) { // this should be infrequent
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
    FoldableConstantExpressionMatch other = (FoldableConstantExpressionMatch) obj;
    if (fE == null) {if (other.fE != null) return false;}
    else if (!fE.equals(other.fE)) return false;
    if (fV1 == null) {if (other.fV1 != null) return false;}
    else if (!fV1.equals(other.fV1)) return false;
    if (fV2 == null) {if (other.fV2 != null) return false;}
    else if (!fV2.equals(other.fV2)) return false;
    return true;
  }
  
  @Override
  public FoldableConstantExpressionQuerySpecification specification() {
    try {
        return FoldableConstantExpressionQuerySpecification.instance();
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
  public static FoldableConstantExpressionMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @param pV1 the fixed value of pattern parameter v1, or null if not bound.
   * @param pV2 the fixed value of pattern parameter v2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static FoldableConstantExpressionMatch newMutableMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    return new Mutable(pE, pV1, pV2);
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
  public static FoldableConstantExpressionMatch newMatch(final Expression pE, final Literal pV1, final Literal pV2) {
    return new Immutable(pE, pV1, pV2);
  }
  
  private static final class Mutable extends FoldableConstantExpressionMatch {
    Mutable(final Expression pE, final Literal pV1, final Literal pV2) {
      super(pE, pV1, pV2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends FoldableConstantExpressionMatch {
    Immutable(final Expression pE, final Literal pV1, final Literal pV2) {
      super(pE, pV1, pV2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
