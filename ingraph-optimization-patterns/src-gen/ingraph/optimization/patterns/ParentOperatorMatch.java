/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.parentOperator pattern,
 * to be used in conjunction with {@link ParentOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ParentOperatorMatcher
 * @see ParentOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ParentOperatorMatch extends BasePatternMatch {
  private Operator fParentOperator;
  
  private Operator fOperator;
  
  private static List<String> parameterNames = makeImmutableList("parentOperator", "operator");
  
  private ParentOperatorMatch(final Operator pParentOperator, final Operator pOperator) {
    this.fParentOperator = pParentOperator;
    this.fOperator = pOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("operator".equals(parameterName)) return this.fOperator;
    return null;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public Operator getOperator() {
    return this.fOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    if ("operator".equals(parameterName) ) {
    	this.fOperator = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setOperator(final Operator pOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fOperator = pOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.parentOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return ParentOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParentOperator, fOperator};
  }
  
  @Override
  public ParentOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fParentOperator, fOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"operator\"=" + prettyPrintValue(fOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fOperator == null) ? 0 : fOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ParentOperatorMatch)) { // this should be infrequent
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
    ParentOperatorMatch other = (ParentOperatorMatch) obj;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fOperator == null) {if (other.fOperator != null) return false;}
    else if (!fOperator.equals(other.fOperator)) return false;
    return true;
  }
  
  @Override
  public ParentOperatorQuerySpecification specification() {
    try {
    	return ParentOperatorQuerySpecification.instance();
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
  public static ParentOperatorMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ParentOperatorMatch newMutableMatch(final Operator pParentOperator, final Operator pOperator) {
    return new Mutable(pParentOperator, pOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pOperator the fixed value of pattern parameter operator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ParentOperatorMatch newMatch(final Operator pParentOperator, final Operator pOperator) {
    return new Immutable(pParentOperator, pOperator);
  }
  
  private static final class Mutable extends ParentOperatorMatch {
    Mutable(final Operator pParentOperator, final Operator pOperator) {
      super(pParentOperator, pOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ParentOperatorMatch {
    Immutable(final Operator pParentOperator, final Operator pOperator) {
      super(pParentOperator, pOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
