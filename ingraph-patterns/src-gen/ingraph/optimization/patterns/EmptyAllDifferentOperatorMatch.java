/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.EmptyAllDifferentOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.AllDifferentOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.emptyAllDifferentOperator pattern,
 * to be used in conjunction with {@link EmptyAllDifferentOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EmptyAllDifferentOperatorMatcher
 * @see EmptyAllDifferentOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EmptyAllDifferentOperatorMatch extends BasePatternMatch {
  private Operator fInputOperator;
  
  private AllDifferentOperator fAllDifferentOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("inputOperator", "allDifferentOperator", "parentOperator");
  
  private EmptyAllDifferentOperatorMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    this.fInputOperator = pInputOperator;
    this.fAllDifferentOperator = pAllDifferentOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("inputOperator".equals(parameterName)) return this.fInputOperator;
    if ("allDifferentOperator".equals(parameterName)) return this.fAllDifferentOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public Operator getInputOperator() {
    return this.fInputOperator;
  }
  
  public AllDifferentOperator getAllDifferentOperator() {
    return this.fAllDifferentOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("inputOperator".equals(parameterName) ) {
    	this.fInputOperator = (Operator) newValue;
    	return true;
    }
    if ("allDifferentOperator".equals(parameterName) ) {
    	this.fAllDifferentOperator = (AllDifferentOperator) newValue;
    	return true;
    }
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setInputOperator(final Operator pInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fInputOperator = pInputOperator;
  }
  
  public void setAllDifferentOperator(final AllDifferentOperator pAllDifferentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fAllDifferentOperator = pAllDifferentOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.emptyAllDifferentOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return EmptyAllDifferentOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fInputOperator, fAllDifferentOperator, fParentOperator};
  }
  
  @Override
  public EmptyAllDifferentOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fInputOperator, fAllDifferentOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"inputOperator\"=" + prettyPrintValue(fInputOperator) + ", ");
    
    result.append("\"allDifferentOperator\"=" + prettyPrintValue(fAllDifferentOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fInputOperator == null) ? 0 : fInputOperator.hashCode());
    result = prime * result + ((fAllDifferentOperator == null) ? 0 : fAllDifferentOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EmptyAllDifferentOperatorMatch)) { // this should be infrequent
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
    EmptyAllDifferentOperatorMatch other = (EmptyAllDifferentOperatorMatch) obj;
    if (fInputOperator == null) {if (other.fInputOperator != null) return false;}
    else if (!fInputOperator.equals(other.fInputOperator)) return false;
    if (fAllDifferentOperator == null) {if (other.fAllDifferentOperator != null) return false;}
    else if (!fAllDifferentOperator.equals(other.fAllDifferentOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public EmptyAllDifferentOperatorQuerySpecification specification() {
    try {
    	return EmptyAllDifferentOperatorQuerySpecification.instance();
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
  public static EmptyAllDifferentOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EmptyAllDifferentOperatorMatch newMutableMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return new Mutable(pInputOperator, pAllDifferentOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EmptyAllDifferentOperatorMatch newMatch(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
    return new Immutable(pInputOperator, pAllDifferentOperator, pParentOperator);
  }
  
  private static final class Mutable extends EmptyAllDifferentOperatorMatch {
    Mutable(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
      super(pInputOperator, pAllDifferentOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EmptyAllDifferentOperatorMatch {
    Immutable(final Operator pInputOperator, final AllDifferentOperator pAllDifferentOperator, final Operator pParentOperator) {
      super(pInputOperator, pAllDifferentOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
