/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.SwappableSelectionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.SwappableSelection pattern,
 * to be used in conjunction with {@link SwappableSelectionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SwappableSelectionMatcher
 * @see SwappableSelectionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SwappableSelectionMatch extends BasePatternMatch {
  private Operator fParentOperator;
  
  private SelectionOperator fSelectionOperator1;
  
  private SelectionOperator fSelectionOperator2;
  
  private static List<String> parameterNames = makeImmutableList("parentOperator", "selectionOperator1", "selectionOperator2");
  
  private SwappableSelectionMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    this.fParentOperator = pParentOperator;
    this.fSelectionOperator1 = pSelectionOperator1;
    this.fSelectionOperator2 = pSelectionOperator2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("selectionOperator1".equals(parameterName)) return this.fSelectionOperator1;
    if ("selectionOperator2".equals(parameterName)) return this.fSelectionOperator2;
    return null;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public SelectionOperator getSelectionOperator1() {
    return this.fSelectionOperator1;
  }
  
  public SelectionOperator getSelectionOperator2() {
    return this.fSelectionOperator2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    if ("selectionOperator1".equals(parameterName) ) {
    	this.fSelectionOperator1 = (SelectionOperator) newValue;
    	return true;
    }
    if ("selectionOperator2".equals(parameterName) ) {
    	this.fSelectionOperator2 = (SelectionOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setSelectionOperator1(final SelectionOperator pSelectionOperator1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelectionOperator1 = pSelectionOperator1;
  }
  
  public void setSelectionOperator2(final SelectionOperator pSelectionOperator2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelectionOperator2 = pSelectionOperator2;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.SwappableSelection";
  }
  
  @Override
  public List<String> parameterNames() {
    return SwappableSelectionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParentOperator, fSelectionOperator1, fSelectionOperator2};
  }
  
  @Override
  public SwappableSelectionMatch toImmutable() {
    return isMutable() ? newMatch(fParentOperator, fSelectionOperator1, fSelectionOperator2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"selectionOperator1\"=" + prettyPrintValue(fSelectionOperator1) + ", ");
    
    result.append("\"selectionOperator2\"=" + prettyPrintValue(fSelectionOperator2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fSelectionOperator1 == null) ? 0 : fSelectionOperator1.hashCode());
    result = prime * result + ((fSelectionOperator2 == null) ? 0 : fSelectionOperator2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SwappableSelectionMatch)) { // this should be infrequent
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
    SwappableSelectionMatch other = (SwappableSelectionMatch) obj;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fSelectionOperator1 == null) {if (other.fSelectionOperator1 != null) return false;}
    else if (!fSelectionOperator1.equals(other.fSelectionOperator1)) return false;
    if (fSelectionOperator2 == null) {if (other.fSelectionOperator2 != null) return false;}
    else if (!fSelectionOperator2.equals(other.fSelectionOperator2)) return false;
    return true;
  }
  
  @Override
  public SwappableSelectionQuerySpecification specification() {
    try {
    	return SwappableSelectionQuerySpecification.instance();
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
  public static SwappableSelectionMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SwappableSelectionMatch newMutableMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return new Mutable(pParentOperator, pSelectionOperator1, pSelectionOperator2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator1 the fixed value of pattern parameter selectionOperator1, or null if not bound.
   * @param pSelectionOperator2 the fixed value of pattern parameter selectionOperator2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SwappableSelectionMatch newMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
    return new Immutable(pParentOperator, pSelectionOperator1, pSelectionOperator2);
  }
  
  private static final class Mutable extends SwappableSelectionMatch {
    Mutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
      super(pParentOperator, pSelectionOperator1, pSelectionOperator2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SwappableSelectionMatch {
    Immutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator1, final SelectionOperator pSelectionOperator2) {
      super(pParentOperator, pSelectionOperator1, pSelectionOperator2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
