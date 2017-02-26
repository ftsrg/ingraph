/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.DefaultExpandOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.ExpandOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.defaultExpandOperator pattern,
 * to be used in conjunction with {@link DefaultExpandOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see DefaultExpandOperatorMatcher
 * @see DefaultExpandOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class DefaultExpandOperatorMatch extends BasePatternMatch {
  private ExpandOperator fExpandOperator;
  
  private static List<String> parameterNames = makeImmutableList("expandOperator");
  
  private DefaultExpandOperatorMatch(final ExpandOperator pExpandOperator) {
    this.fExpandOperator = pExpandOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("expandOperator".equals(parameterName)) return this.fExpandOperator;
    return null;
  }
  
  public ExpandOperator getExpandOperator() {
    return this.fExpandOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("expandOperator".equals(parameterName) ) {
    	this.fExpandOperator = (ExpandOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setExpandOperator(final ExpandOperator pExpandOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fExpandOperator = pExpandOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.defaultExpandOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return DefaultExpandOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fExpandOperator};
  }
  
  @Override
  public DefaultExpandOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fExpandOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"expandOperator\"=" + prettyPrintValue(fExpandOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fExpandOperator == null) ? 0 : fExpandOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof DefaultExpandOperatorMatch)) { // this should be infrequent
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
    DefaultExpandOperatorMatch other = (DefaultExpandOperatorMatch) obj;
    if (fExpandOperator == null) {if (other.fExpandOperator != null) return false;}
    else if (!fExpandOperator.equals(other.fExpandOperator)) return false;
    return true;
  }
  
  @Override
  public DefaultExpandOperatorQuerySpecification specification() {
    try {
    	return DefaultExpandOperatorQuerySpecification.instance();
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
  public static DefaultExpandOperatorMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static DefaultExpandOperatorMatch newMutableMatch(final ExpandOperator pExpandOperator) {
    return new Mutable(pExpandOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static DefaultExpandOperatorMatch newMatch(final ExpandOperator pExpandOperator) {
    return new Immutable(pExpandOperator);
  }
  
  private static final class Mutable extends DefaultExpandOperatorMatch {
    Mutable(final ExpandOperator pExpandOperator) {
      super(pExpandOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends DefaultExpandOperatorMatch {
    Immutable(final ExpandOperator pExpandOperator) {
      super(pExpandOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
