/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.CommutativeOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.CommutativeBinaryOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.CommutativeOperator pattern,
 * to be used in conjunction with {@link CommutativeOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see CommutativeOperatorMatcher
 * @see CommutativeOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class CommutativeOperatorMatch extends BasePatternMatch {
  private CommutativeBinaryOperator fOp;
  
  private Operator fLeftInput;
  
  private Operator fRightInput;
  
  private static List<String> parameterNames = makeImmutableList("op", "leftInput", "rightInput");
  
  private CommutativeOperatorMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    this.fOp = pOp;
    this.fLeftInput = pLeftInput;
    this.fRightInput = pRightInput;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("op".equals(parameterName)) return this.fOp;
    if ("leftInput".equals(parameterName)) return this.fLeftInput;
    if ("rightInput".equals(parameterName)) return this.fRightInput;
    return null;
  }
  
  public CommutativeBinaryOperator getOp() {
    return this.fOp;
  }
  
  public Operator getLeftInput() {
    return this.fLeftInput;
  }
  
  public Operator getRightInput() {
    return this.fRightInput;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("op".equals(parameterName) ) {
    	this.fOp = (CommutativeBinaryOperator) newValue;
    	return true;
    }
    if ("leftInput".equals(parameterName) ) {
    	this.fLeftInput = (Operator) newValue;
    	return true;
    }
    if ("rightInput".equals(parameterName) ) {
    	this.fRightInput = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setOp(final CommutativeBinaryOperator pOp) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fOp = pOp;
  }
  
  public void setLeftInput(final Operator pLeftInput) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftInput = pLeftInput;
  }
  
  public void setRightInput(final Operator pRightInput) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRightInput = pRightInput;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.CommutativeOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return CommutativeOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fOp, fLeftInput, fRightInput};
  }
  
  @Override
  public CommutativeOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fOp, fLeftInput, fRightInput) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"op\"=" + prettyPrintValue(fOp) + ", ");
    
    result.append("\"leftInput\"=" + prettyPrintValue(fLeftInput) + ", ");
    
    result.append("\"rightInput\"=" + prettyPrintValue(fRightInput)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fOp == null) ? 0 : fOp.hashCode());
    result = prime * result + ((fLeftInput == null) ? 0 : fLeftInput.hashCode());
    result = prime * result + ((fRightInput == null) ? 0 : fRightInput.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof CommutativeOperatorMatch)) { // this should be infrequent
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
    CommutativeOperatorMatch other = (CommutativeOperatorMatch) obj;
    if (fOp == null) {if (other.fOp != null) return false;}
    else if (!fOp.equals(other.fOp)) return false;
    if (fLeftInput == null) {if (other.fLeftInput != null) return false;}
    else if (!fLeftInput.equals(other.fLeftInput)) return false;
    if (fRightInput == null) {if (other.fRightInput != null) return false;}
    else if (!fRightInput.equals(other.fRightInput)) return false;
    return true;
  }
  
  @Override
  public CommutativeOperatorQuerySpecification specification() {
    try {
    	return CommutativeOperatorQuerySpecification.instance();
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
  public static CommutativeOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static CommutativeOperatorMatch newMutableMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return new Mutable(pOp, pLeftInput, pRightInput);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOp the fixed value of pattern parameter op, or null if not bound.
   * @param pLeftInput the fixed value of pattern parameter leftInput, or null if not bound.
   * @param pRightInput the fixed value of pattern parameter rightInput, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static CommutativeOperatorMatch newMatch(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
    return new Immutable(pOp, pLeftInput, pRightInput);
  }
  
  private static final class Mutable extends CommutativeOperatorMatch {
    Mutable(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
      super(pOp, pLeftInput, pRightInput);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends CommutativeOperatorMatch {
    Immutable(final CommutativeBinaryOperator pOp, final Operator pLeftInput, final Operator pRightInput) {
      super(pOp, pLeftInput, pRightInput);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
