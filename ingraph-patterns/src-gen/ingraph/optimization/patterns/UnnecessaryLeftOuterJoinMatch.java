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
import relalg.DualObjectSourceOperator;
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
  private Operator fLeftInputOperator;
  
  private DualObjectSourceOperator fDualOperator;
  
  private LeftOuterJoinOperator fLeftOuterJoinOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("leftInputOperator", "dualOperator", "leftOuterJoinOperator", "parentOperator");
  
  private UnnecessaryLeftOuterJoinMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    this.fLeftInputOperator = pLeftInputOperator;
    this.fDualOperator = pDualOperator;
    this.fLeftOuterJoinOperator = pLeftOuterJoinOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("leftInputOperator".equals(parameterName)) return this.fLeftInputOperator;
    if ("dualOperator".equals(parameterName)) return this.fDualOperator;
    if ("leftOuterJoinOperator".equals(parameterName)) return this.fLeftOuterJoinOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public Operator getLeftInputOperator() {
    return this.fLeftInputOperator;
  }
  
  public DualObjectSourceOperator getDualOperator() {
    return this.fDualOperator;
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
    if ("leftInputOperator".equals(parameterName) ) {
    	this.fLeftInputOperator = (Operator) newValue;
    	return true;
    }
    if ("dualOperator".equals(parameterName) ) {
    	this.fDualOperator = (DualObjectSourceOperator) newValue;
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
  
  public void setLeftInputOperator(final Operator pLeftInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftInputOperator = pLeftInputOperator;
  }
  
  public void setDualOperator(final DualObjectSourceOperator pDualOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fDualOperator = pDualOperator;
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
    return new Object[]{fLeftInputOperator, fDualOperator, fLeftOuterJoinOperator, fParentOperator};
  }
  
  @Override
  public UnnecessaryLeftOuterJoinMatch toImmutable() {
    return isMutable() ? newMatch(fLeftInputOperator, fDualOperator, fLeftOuterJoinOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"leftInputOperator\"=" + prettyPrintValue(fLeftInputOperator) + ", ");
    
    result.append("\"dualOperator\"=" + prettyPrintValue(fDualOperator) + ", ");
    
    result.append("\"leftOuterJoinOperator\"=" + prettyPrintValue(fLeftOuterJoinOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fLeftInputOperator == null) ? 0 : fLeftInputOperator.hashCode());
    result = prime * result + ((fDualOperator == null) ? 0 : fDualOperator.hashCode());
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
    if (fLeftInputOperator == null) {if (other.fLeftInputOperator != null) return false;}
    else if (!fLeftInputOperator.equals(other.fLeftInputOperator)) return false;
    if (fDualOperator == null) {if (other.fDualOperator != null) return false;}
    else if (!fDualOperator.equals(other.fDualOperator)) return false;
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
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static UnnecessaryLeftOuterJoinMatch newMutableMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return new Mutable(pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pDualOperator the fixed value of pattern parameter dualOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static UnnecessaryLeftOuterJoinMatch newMatch(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
    return new Immutable(pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator);
  }
  
  private static final class Mutable extends UnnecessaryLeftOuterJoinMatch {
    Mutable(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
      super(pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends UnnecessaryLeftOuterJoinMatch {
    Immutable(final Operator pLeftInputOperator, final DualObjectSourceOperator pDualOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pParentOperator) {
      super(pLeftInputOperator, pDualOperator, pLeftOuterJoinOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
