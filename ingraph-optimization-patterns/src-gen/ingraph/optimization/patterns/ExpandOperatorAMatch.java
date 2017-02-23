/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.ExpandOperatorAQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.expandOperatorA pattern,
 * to be used in conjunction with {@link ExpandOperatorAMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExpandOperatorAMatcher
 * @see ExpandOperatorAProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandOperatorAMatch extends BasePatternMatch {
  private ExpandOperator fExpandOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("expandOperator", "parentOperator");
  
  private ExpandOperatorAMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    this.fExpandOperator = pExpandOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("expandOperator".equals(parameterName)) return this.fExpandOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public ExpandOperator getExpandOperator() {
    return this.fExpandOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("expandOperator".equals(parameterName) ) {
    	this.fExpandOperator = (ExpandOperator) newValue;
    	return true;
    }
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setExpandOperator(final ExpandOperator pExpandOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fExpandOperator = pExpandOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.expandOperatorA";
  }
  
  @Override
  public List<String> parameterNames() {
    return ExpandOperatorAMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fExpandOperator, fParentOperator};
  }
  
  @Override
  public ExpandOperatorAMatch toImmutable() {
    return isMutable() ? newMatch(fExpandOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"expandOperator\"=" + prettyPrintValue(fExpandOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fExpandOperator == null) ? 0 : fExpandOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExpandOperatorAMatch)) { // this should be infrequent
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
    ExpandOperatorAMatch other = (ExpandOperatorAMatch) obj;
    if (fExpandOperator == null) {if (other.fExpandOperator != null) return false;}
    else if (!fExpandOperator.equals(other.fExpandOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public ExpandOperatorAQuerySpecification specification() {
    try {
    	return ExpandOperatorAQuerySpecification.instance();
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
  public static ExpandOperatorAMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExpandOperatorAMatch newMutableMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return new Mutable(pExpandOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExpandOperatorAMatch newMatch(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return new Immutable(pExpandOperator, pParentOperator);
  }
  
  private static final class Mutable extends ExpandOperatorAMatch {
    Mutable(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
      super(pExpandOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExpandOperatorAMatch {
    Immutable(final ExpandOperator pExpandOperator, final Operator pParentOperator) {
      super(pExpandOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
