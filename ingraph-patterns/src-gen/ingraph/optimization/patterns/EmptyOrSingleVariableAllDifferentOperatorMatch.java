/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.EmptyOrSingleVariableAllDifferentOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.AllDifferentOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.emptyOrSingleVariableAllDifferentOperator pattern,
 * to be used in conjunction with {@link EmptyOrSingleVariableAllDifferentOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EmptyOrSingleVariableAllDifferentOperatorMatcher
 * @see EmptyOrSingleVariableAllDifferentOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EmptyOrSingleVariableAllDifferentOperatorMatch extends BasePatternMatch {
  private AllDifferentOperator fAllDifferentOperator;
  
  private static List<String> parameterNames = makeImmutableList("allDifferentOperator");
  
  private EmptyOrSingleVariableAllDifferentOperatorMatch(final AllDifferentOperator pAllDifferentOperator) {
    this.fAllDifferentOperator = pAllDifferentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("allDifferentOperator".equals(parameterName)) return this.fAllDifferentOperator;
    return null;
  }
  
  public AllDifferentOperator getAllDifferentOperator() {
    return this.fAllDifferentOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("allDifferentOperator".equals(parameterName) ) {
    	this.fAllDifferentOperator = (AllDifferentOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setAllDifferentOperator(final AllDifferentOperator pAllDifferentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fAllDifferentOperator = pAllDifferentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.emptyOrSingleVariableAllDifferentOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return EmptyOrSingleVariableAllDifferentOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fAllDifferentOperator};
  }
  
  @Override
  public EmptyOrSingleVariableAllDifferentOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fAllDifferentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"allDifferentOperator\"=" + prettyPrintValue(fAllDifferentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fAllDifferentOperator == null) ? 0 : fAllDifferentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EmptyOrSingleVariableAllDifferentOperatorMatch)) { // this should be infrequent
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
    EmptyOrSingleVariableAllDifferentOperatorMatch other = (EmptyOrSingleVariableAllDifferentOperatorMatch) obj;
    if (fAllDifferentOperator == null) {if (other.fAllDifferentOperator != null) return false;}
    else if (!fAllDifferentOperator.equals(other.fAllDifferentOperator)) return false;
    return true;
  }
  
  @Override
  public EmptyOrSingleVariableAllDifferentOperatorQuerySpecification specification() {
    try {
    	return EmptyOrSingleVariableAllDifferentOperatorQuerySpecification.instance();
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
  public static EmptyOrSingleVariableAllDifferentOperatorMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EmptyOrSingleVariableAllDifferentOperatorMatch newMutableMatch(final AllDifferentOperator pAllDifferentOperator) {
    return new Mutable(pAllDifferentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EmptyOrSingleVariableAllDifferentOperatorMatch newMatch(final AllDifferentOperator pAllDifferentOperator) {
    return new Immutable(pAllDifferentOperator);
  }
  
  private static final class Mutable extends EmptyOrSingleVariableAllDifferentOperatorMatch {
    Mutable(final AllDifferentOperator pAllDifferentOperator) {
      super(pAllDifferentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EmptyOrSingleVariableAllDifferentOperatorMatch {
    Immutable(final AllDifferentOperator pAllDifferentOperator) {
      super(pAllDifferentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
