/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.AllDifferentOperatorEdgeVariablesQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.AllDifferentOperator;
import relalg.EdgeVariable;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.allDifferentOperatorEdgeVariables pattern,
 * to be used in conjunction with {@link AllDifferentOperatorEdgeVariablesMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see AllDifferentOperatorEdgeVariablesMatcher
 * @see AllDifferentOperatorEdgeVariablesProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class AllDifferentOperatorEdgeVariablesMatch extends BasePatternMatch {
  private AllDifferentOperator fAllDifferentOperator;
  
  private EdgeVariable fEdgeVariable;
  
  private static List<String> parameterNames = makeImmutableList("allDifferentOperator", "edgeVariable");
  
  private AllDifferentOperatorEdgeVariablesMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    this.fAllDifferentOperator = pAllDifferentOperator;
    this.fEdgeVariable = pEdgeVariable;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("allDifferentOperator".equals(parameterName)) return this.fAllDifferentOperator;
    if ("edgeVariable".equals(parameterName)) return this.fEdgeVariable;
    return null;
  }
  
  public AllDifferentOperator getAllDifferentOperator() {
    return this.fAllDifferentOperator;
  }
  
  public EdgeVariable getEdgeVariable() {
    return this.fEdgeVariable;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("allDifferentOperator".equals(parameterName) ) {
    	this.fAllDifferentOperator = (AllDifferentOperator) newValue;
    	return true;
    }
    if ("edgeVariable".equals(parameterName) ) {
    	this.fEdgeVariable = (EdgeVariable) newValue;
    	return true;
    }
    return false;
  }
  
  public void setAllDifferentOperator(final AllDifferentOperator pAllDifferentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fAllDifferentOperator = pAllDifferentOperator;
  }
  
  public void setEdgeVariable(final EdgeVariable pEdgeVariable) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fEdgeVariable = pEdgeVariable;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.allDifferentOperatorEdgeVariables";
  }
  
  @Override
  public List<String> parameterNames() {
    return AllDifferentOperatorEdgeVariablesMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fAllDifferentOperator, fEdgeVariable};
  }
  
  @Override
  public AllDifferentOperatorEdgeVariablesMatch toImmutable() {
    return isMutable() ? newMatch(fAllDifferentOperator, fEdgeVariable) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"allDifferentOperator\"=" + prettyPrintValue(fAllDifferentOperator) + ", ");
    
    result.append("\"edgeVariable\"=" + prettyPrintValue(fEdgeVariable)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fAllDifferentOperator == null) ? 0 : fAllDifferentOperator.hashCode());
    result = prime * result + ((fEdgeVariable == null) ? 0 : fEdgeVariable.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof AllDifferentOperatorEdgeVariablesMatch)) { // this should be infrequent
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
    AllDifferentOperatorEdgeVariablesMatch other = (AllDifferentOperatorEdgeVariablesMatch) obj;
    if (fAllDifferentOperator == null) {if (other.fAllDifferentOperator != null) return false;}
    else if (!fAllDifferentOperator.equals(other.fAllDifferentOperator)) return false;
    if (fEdgeVariable == null) {if (other.fEdgeVariable != null) return false;}
    else if (!fEdgeVariable.equals(other.fEdgeVariable)) return false;
    return true;
  }
  
  @Override
  public AllDifferentOperatorEdgeVariablesQuerySpecification specification() {
    try {
    	return AllDifferentOperatorEdgeVariablesQuerySpecification.instance();
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
  public static AllDifferentOperatorEdgeVariablesMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static AllDifferentOperatorEdgeVariablesMatch newMutableMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return new Mutable(pAllDifferentOperator, pEdgeVariable);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pAllDifferentOperator the fixed value of pattern parameter allDifferentOperator, or null if not bound.
   * @param pEdgeVariable the fixed value of pattern parameter edgeVariable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static AllDifferentOperatorEdgeVariablesMatch newMatch(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
    return new Immutable(pAllDifferentOperator, pEdgeVariable);
  }
  
  private static final class Mutable extends AllDifferentOperatorEdgeVariablesMatch {
    Mutable(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
      super(pAllDifferentOperator, pEdgeVariable);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends AllDifferentOperatorEdgeVariablesMatch {
    Immutable(final AllDifferentOperator pAllDifferentOperator, final EdgeVariable pEdgeVariable) {
      super(pAllDifferentOperator, pEdgeVariable);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
