/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.CascadableSelectionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.LogicalExpression;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.CascadableSelection pattern,
 * to be used in conjunction with {@link CascadableSelectionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see CascadableSelectionMatcher
 * @see CascadableSelectionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class CascadableSelectionMatch extends BasePatternMatch {
  private Operator fParentOperator;
  
  private SelectionOperator fSelectionOperator;
  
  private LogicalExpression fLeftOperand;
  
  private LogicalExpression fRightOperand;
  
  private static List<String> parameterNames = makeImmutableList("parentOperator", "selectionOperator", "leftOperand", "rightOperand");
  
  private CascadableSelectionMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    this.fParentOperator = pParentOperator;
    this.fSelectionOperator = pSelectionOperator;
    this.fLeftOperand = pLeftOperand;
    this.fRightOperand = pRightOperand;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("selectionOperator".equals(parameterName)) return this.fSelectionOperator;
    if ("leftOperand".equals(parameterName)) return this.fLeftOperand;
    if ("rightOperand".equals(parameterName)) return this.fRightOperand;
    return null;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public SelectionOperator getSelectionOperator() {
    return this.fSelectionOperator;
  }
  
  public LogicalExpression getLeftOperand() {
    return this.fLeftOperand;
  }
  
  public LogicalExpression getRightOperand() {
    return this.fRightOperand;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    if ("selectionOperator".equals(parameterName) ) {
    	this.fSelectionOperator = (SelectionOperator) newValue;
    	return true;
    }
    if ("leftOperand".equals(parameterName) ) {
    	this.fLeftOperand = (LogicalExpression) newValue;
    	return true;
    }
    if ("rightOperand".equals(parameterName) ) {
    	this.fRightOperand = (LogicalExpression) newValue;
    	return true;
    }
    return false;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setSelectionOperator(final SelectionOperator pSelectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelectionOperator = pSelectionOperator;
  }
  
  public void setLeftOperand(final LogicalExpression pLeftOperand) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftOperand = pLeftOperand;
  }
  
  public void setRightOperand(final LogicalExpression pRightOperand) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRightOperand = pRightOperand;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.CascadableSelection";
  }
  
  @Override
  public List<String> parameterNames() {
    return CascadableSelectionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParentOperator, fSelectionOperator, fLeftOperand, fRightOperand};
  }
  
  @Override
  public CascadableSelectionMatch toImmutable() {
    return isMutable() ? newMatch(fParentOperator, fSelectionOperator, fLeftOperand, fRightOperand) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"selectionOperator\"=" + prettyPrintValue(fSelectionOperator) + ", ");
    
    result.append("\"leftOperand\"=" + prettyPrintValue(fLeftOperand) + ", ");
    
    result.append("\"rightOperand\"=" + prettyPrintValue(fRightOperand)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fSelectionOperator == null) ? 0 : fSelectionOperator.hashCode());
    result = prime * result + ((fLeftOperand == null) ? 0 : fLeftOperand.hashCode());
    result = prime * result + ((fRightOperand == null) ? 0 : fRightOperand.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof CascadableSelectionMatch)) { // this should be infrequent
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
    CascadableSelectionMatch other = (CascadableSelectionMatch) obj;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fSelectionOperator == null) {if (other.fSelectionOperator != null) return false;}
    else if (!fSelectionOperator.equals(other.fSelectionOperator)) return false;
    if (fLeftOperand == null) {if (other.fLeftOperand != null) return false;}
    else if (!fLeftOperand.equals(other.fLeftOperand)) return false;
    if (fRightOperand == null) {if (other.fRightOperand != null) return false;}
    else if (!fRightOperand.equals(other.fRightOperand)) return false;
    return true;
  }
  
  @Override
  public CascadableSelectionQuerySpecification specification() {
    try {
    	return CascadableSelectionQuerySpecification.instance();
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
  public static CascadableSelectionMatch newEmptyMatch() {
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static CascadableSelectionMatch newMutableMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return new Mutable(pParentOperator, pSelectionOperator, pLeftOperand, pRightOperand);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOperand the fixed value of pattern parameter leftOperand, or null if not bound.
   * @param pRightOperand the fixed value of pattern parameter rightOperand, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static CascadableSelectionMatch newMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
    return new Immutable(pParentOperator, pSelectionOperator, pLeftOperand, pRightOperand);
  }
  
  private static final class Mutable extends CascadableSelectionMatch {
    Mutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
      super(pParentOperator, pSelectionOperator, pLeftOperand, pRightOperand);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends CascadableSelectionMatch {
    Immutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LogicalExpression pLeftOperand, final LogicalExpression pRightOperand) {
      super(pParentOperator, pSelectionOperator, pLeftOperand, pRightOperand);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
