/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.TopAndProjectionOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.Operator;
import relalg.ProjectionOperator;
import relalg.TopOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.topAndProjectionOperator pattern,
 * to be used in conjunction with {@link TopAndProjectionOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TopAndProjectionOperatorMatcher
 * @see TopAndProjectionOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TopAndProjectionOperatorMatch extends BasePatternMatch {
  private TopOperator fTopOperator;
  
  private ProjectionOperator fProjectionOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("topOperator", "projectionOperator", "parentOperator");
  
  private TopAndProjectionOperatorMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    this.fTopOperator = pTopOperator;
    this.fProjectionOperator = pProjectionOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("topOperator".equals(parameterName)) return this.fTopOperator;
    if ("projectionOperator".equals(parameterName)) return this.fProjectionOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public TopOperator getTopOperator() {
    return this.fTopOperator;
  }
  
  public ProjectionOperator getProjectionOperator() {
    return this.fProjectionOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("topOperator".equals(parameterName) ) {
    	this.fTopOperator = (TopOperator) newValue;
    	return true;
    }
    if ("projectionOperator".equals(parameterName) ) {
    	this.fProjectionOperator = (ProjectionOperator) newValue;
    	return true;
    }
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setTopOperator(final TopOperator pTopOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTopOperator = pTopOperator;
  }
  
  public void setProjectionOperator(final ProjectionOperator pProjectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fProjectionOperator = pProjectionOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.topAndProjectionOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return TopAndProjectionOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTopOperator, fProjectionOperator, fParentOperator};
  }
  
  @Override
  public TopAndProjectionOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fTopOperator, fProjectionOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"topOperator\"=" + prettyPrintValue(fTopOperator) + ", ");
    
    result.append("\"projectionOperator\"=" + prettyPrintValue(fProjectionOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fTopOperator == null) ? 0 : fTopOperator.hashCode());
    result = prime * result + ((fProjectionOperator == null) ? 0 : fProjectionOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof TopAndProjectionOperatorMatch)) { // this should be infrequent
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
    TopAndProjectionOperatorMatch other = (TopAndProjectionOperatorMatch) obj;
    if (fTopOperator == null) {if (other.fTopOperator != null) return false;}
    else if (!fTopOperator.equals(other.fTopOperator)) return false;
    if (fProjectionOperator == null) {if (other.fProjectionOperator != null) return false;}
    else if (!fProjectionOperator.equals(other.fProjectionOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public TopAndProjectionOperatorQuerySpecification specification() {
    try {
    	return TopAndProjectionOperatorQuerySpecification.instance();
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
  public static TopAndProjectionOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TopAndProjectionOperatorMatch newMutableMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return new Mutable(pTopOperator, pProjectionOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTopOperator the fixed value of pattern parameter topOperator, or null if not bound.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TopAndProjectionOperatorMatch newMatch(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
    return new Immutable(pTopOperator, pProjectionOperator, pParentOperator);
  }
  
  private static final class Mutable extends TopAndProjectionOperatorMatch {
    Mutable(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
      super(pTopOperator, pProjectionOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TopAndProjectionOperatorMatch {
    Immutable(final TopOperator pTopOperator, final ProjectionOperator pProjectionOperator, final Operator pParentOperator) {
      super(pTopOperator, pProjectionOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
