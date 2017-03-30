/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.UnnecessaryLeftOuterJoinQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.unnecessaryLeftOuterJoin pattern,
 * to be used in conjunction with {@link UnnecessaryLeftOuterJoinMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see UnnecessaryLeftOuterJoinMatcher
 * @see UnnecessaryLeftOuterJoinProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class UnnecessaryLeftOuterJoinMatch extends BasePatternMatch {
  private Operator fInputOperator;
  
  private LeftOuterJoinOperator fLeftOuterJoinOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("inputOperator", "leftOuterJoinOperator", "parentOperator");
  
  private UnnecessaryLeftOuterJoinMatch(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    this.fInputOperator = pInputOperator;
    this.fLeftOuterJoinOperator = pLeftOuterJoinOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("inputOperator".equals(parameterName)) return this.fInputOperator;
    if ("leftOuterJoinOperator".equals(parameterName)) return this.fLeftOuterJoinOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public Operator getInputOperator() {
    return this.fInputOperator;
  }
  
  public LeftOuterJoinOperator getLeftOuterJoinOperator() {
    return this.fLeftOuterJoinOperator;
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
    if ("leftOuterJoinOperator".equals(parameterName) ) {
    	this.fLeftOuterJoinOperator = (LeftOuterJoinOperator) newValue;
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
  
  public void setLeftOuterJoinOperator(final LeftOuterJoinOperator pLeftOuterJoinOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftOuterJoinOperator = pLeftOuterJoinOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.unnecessaryLeftOuterJoin";
  }
  
  @Override
  public List<String> parameterNames() {
    return UnnecessaryLeftOuterJoinMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fInputOperator, fLeftOuterJoinOperator, fParentOperator};
  }
  
  @Override
  public UnnecessaryLeftOuterJoinMatch toImmutable() {
    return isMutable() ? newMatch(fInputOperator, fLeftOuterJoinOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"inputOperator\"=" + prettyPrintValue(fInputOperator) + ", ");
    
    result.append("\"leftOuterJoinOperator\"=" + prettyPrintValue(fLeftOuterJoinOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fInputOperator == null) ? 0 : fInputOperator.hashCode());
    result = prime * result + ((fLeftOuterJoinOperator == null) ? 0 : fLeftOuterJoinOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof UnnecessaryLeftOuterJoinMatch)) { // this should be infrequent
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
    UnnecessaryLeftOuterJoinMatch other = (UnnecessaryLeftOuterJoinMatch) obj;
    if (fInputOperator == null) {if (other.fInputOperator != null) return false;}
    else if (!fInputOperator.equals(other.fInputOperator)) return false;
    if (fLeftOuterJoinOperator == null) {if (other.fLeftOuterJoinOperator != null) return false;}
    else if (!fLeftOuterJoinOperator.equals(other.fLeftOuterJoinOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public UnnecessaryLeftOuterJoinQuerySpecification specification() {
    try {
    	return UnnecessaryLeftOuterJoinQuerySpecification.instance();
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
  public static UnnecessaryLeftOuterJoinMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static UnnecessaryLeftOuterJoinMatch newMutableMatch(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return new Mutable(pInputOperator, pLeftOuterJoinOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static UnnecessaryLeftOuterJoinMatch newMatch(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return new Immutable(pInputOperator, pLeftOuterJoinOperator, pParentOperator);
  }
  
  private static final class Mutable extends UnnecessaryLeftOuterJoinMatch {
    Mutable(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
      super(pInputOperator, pLeftOuterJoinOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends UnnecessaryLeftOuterJoinMatch {
    Immutable(final Operator pInputOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
      super(pInputOperator, pLeftOuterJoinOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
