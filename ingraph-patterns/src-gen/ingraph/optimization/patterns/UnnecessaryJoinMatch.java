/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.UnnecessaryJoinQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.EquiJoinLikeOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.unnecessaryJoin pattern,
 * to be used in conjunction with {@link UnnecessaryJoinMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see UnnecessaryJoinMatcher
 * @see UnnecessaryJoinProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class UnnecessaryJoinMatch extends BasePatternMatch {
  private Operator fOtherInputOperator;
  
  private EquiJoinLikeOperator fEquiJoinLikeOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("otherInputOperator", "equiJoinLikeOperator", "parentOperator");
  
  private UnnecessaryJoinMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    this.fOtherInputOperator = pOtherInputOperator;
    this.fEquiJoinLikeOperator = pEquiJoinLikeOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("otherInputOperator".equals(parameterName)) return this.fOtherInputOperator;
    if ("equiJoinLikeOperator".equals(parameterName)) return this.fEquiJoinLikeOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public Operator getOtherInputOperator() {
    return this.fOtherInputOperator;
  }
  
  public EquiJoinLikeOperator getEquiJoinLikeOperator() {
    return this.fEquiJoinLikeOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
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
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
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
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.unnecessaryJoin";
  }
  
  @Override
  public List<String> parameterNames() {
    return UnnecessaryJoinMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fOtherInputOperator, fEquiJoinLikeOperator, fParentOperator};
  }
  
  @Override
  public UnnecessaryJoinMatch toImmutable() {
    return isMutable() ? newMatch(fOtherInputOperator, fEquiJoinLikeOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"otherInputOperator\"=" + prettyPrintValue(fOtherInputOperator) + ", ");
    
    result.append("\"equiJoinLikeOperator\"=" + prettyPrintValue(fEquiJoinLikeOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fOtherInputOperator == null) ? 0 : fOtherInputOperator.hashCode());
    result = prime * result + ((fEquiJoinLikeOperator == null) ? 0 : fEquiJoinLikeOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof UnnecessaryJoinMatch)) { // this should be infrequent
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
    UnnecessaryJoinMatch other = (UnnecessaryJoinMatch) obj;
    if (fOtherInputOperator == null) {if (other.fOtherInputOperator != null) return false;}
    else if (!fOtherInputOperator.equals(other.fOtherInputOperator)) return false;
    if (fEquiJoinLikeOperator == null) {if (other.fEquiJoinLikeOperator != null) return false;}
    else if (!fEquiJoinLikeOperator.equals(other.fEquiJoinLikeOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public UnnecessaryJoinQuerySpecification specification() {
    try {
    	return UnnecessaryJoinQuerySpecification.instance();
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
  public static UnnecessaryJoinMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static UnnecessaryJoinMatch newMutableMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return new Mutable(pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOtherInputOperator the fixed value of pattern parameter otherInputOperator, or null if not bound.
   * @param pEquiJoinLikeOperator the fixed value of pattern parameter equiJoinLikeOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static UnnecessaryJoinMatch newMatch(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
    return new Immutable(pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator);
  }
  
  private static final class Mutable extends UnnecessaryJoinMatch {
    Mutable(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
      super(pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends UnnecessaryJoinMatch {
    Immutable(final Operator pOtherInputOperator, final EquiJoinLikeOperator pEquiJoinLikeOperator, final Operator pParentOperator) {
      super(pOtherInputOperator, pEquiJoinLikeOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
