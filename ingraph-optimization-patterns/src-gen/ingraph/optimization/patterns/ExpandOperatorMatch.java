/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.ExpandOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.ExpandOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.expandOperator pattern,
 * to be used in conjunction with {@link ExpandOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExpandOperatorMatcher
 * @see ExpandOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandOperatorMatch extends BasePatternMatch {
  private ExpandOperator fE;
  
  private static List<String> parameterNames = makeImmutableList("e");
  
  private ExpandOperatorMatch(final ExpandOperator pE) {
    this.fE = pE;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("e".equals(parameterName)) return this.fE;
    return null;
  }
  
  public ExpandOperator getE() {
    return this.fE;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("e".equals(parameterName) ) {
    	this.fE = (ExpandOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setE(final ExpandOperator pE) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fE = pE;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.expandOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return ExpandOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fE};
  }
  
  @Override
  public ExpandOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fE) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"e\"=" + prettyPrintValue(fE)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fE == null) ? 0 : fE.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExpandOperatorMatch)) { // this should be infrequent
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
    ExpandOperatorMatch other = (ExpandOperatorMatch) obj;
    if (fE == null) {if (other.fE != null) return false;}
    else if (!fE.equals(other.fE)) return false;
    return true;
  }
  
  @Override
  public ExpandOperatorQuerySpecification specification() {
    try {
    	return ExpandOperatorQuerySpecification.instance();
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
  public static ExpandOperatorMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExpandOperatorMatch newMutableMatch(final ExpandOperator pE) {
    return new Mutable(pE);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExpandOperatorMatch newMatch(final ExpandOperator pE) {
    return new Immutable(pE);
  }
  
  private static final class Mutable extends ExpandOperatorMatch {
    Mutable(final ExpandOperator pE) {
      super(pE);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExpandOperatorMatch {
    Immutable(final ExpandOperator pE) {
      super(pE);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
