/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.SortAndTopOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Operator;
import relalg.SortOperator;
import relalg.TopOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.sortAndTopOperator pattern,
 * to be used in conjunction with {@link SortAndTopOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SortAndTopOperatorMatcher
 * @see SortAndTopOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SortAndTopOperatorMatch extends BasePatternMatch {
  private Operator fParentOperator;
  
  private SortOperator fSortOperator;
  
  private TopOperator fTopOperator;
  
  private static List<String> parameterNames = makeImmutableList("parentOperator", "sortOperator", "topOperator");
  
  private SortAndTopOperatorMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    this.fParentOperator = pParentOperator;
    this.fSortOperator = pSortOperator;
    this.fTopOperator = pTopOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("sortOperator".equals(parameterName)) return this.fSortOperator;
    if ("topOperator".equals(parameterName)) return this.fTopOperator;
    return null;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public SortOperator getSortOperator() {
    return this.fSortOperator;
  }
  
  public TopOperator getTopOperator() {
    return this.fTopOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    if ("sortOperator".equals(parameterName) ) {
    	this.fSortOperator = (SortOperator) newValue;
    	return true;
    }
    if ("topOperator".equals(parameterName) ) {
    	this.fTopOperator = (TopOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setSortOperator(final SortOperator pSortOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSortOperator = pSortOperator;
  }
  
  public void setTopOperator(final TopOperator pTopOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTopOperator = pTopOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.sortAndTopOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return SortAndTopOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParentOperator, fSortOperator, fTopOperator};
  }
  
  @Override
  public SortAndTopOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fParentOperator, fSortOperator, fTopOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"sortOperator\"=" + prettyPrintValue(fSortOperator) + ", ");
    
    result.append("\"topOperator\"=" + prettyPrintValue(fTopOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fSortOperator == null) ? 0 : fSortOperator.hashCode());
    result = prime * result + ((fTopOperator == null) ? 0 : fTopOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SortAndTopOperatorMatch)) { // this should be infrequent
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
    SortAndTopOperatorMatch other = (SortAndTopOperatorMatch) obj;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fSortOperator == null) {if (other.fSortOperator != null) return false;}
    else if (!fSortOperator.equals(other.fSortOperator)) return false;
    if (fTopOperator == null) {if (other.fTopOperator != null) return false;}
    else if (!fTopOperator.equals(other.fTopOperator)) return false;
    return true;
  }
  
  @Override
  public SortAndTopOperatorQuerySpecification specification() {
    try {
    	return SortAndTopOperatorQuerySpecification.instance();
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
  public static SortAndTopOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SortAndTopOperatorMatch newMutableMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return new Mutable(pParentOperator, pSortOperator, pTopOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSortOperator the fixed value of pattern parameter sortOperator, or null if not bound.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SortAndTopOperatorMatch newMatch(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
    return new Immutable(pParentOperator, pSortOperator, pTopOperator);
  }
  
  private static final class Mutable extends SortAndTopOperatorMatch {
    Mutable(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
      super(pParentOperator, pSortOperator, pTopOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SortAndTopOperatorMatch {
    Immutable(final Operator pParentOperator, final SortOperator pSortOperator, final TopOperator pTopOperator) {
      super(pParentOperator, pSortOperator, pTopOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
