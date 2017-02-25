/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.ExpandVertexQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.ExpandOperator;
import relalg.GetVerticesOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.expandVertex pattern,
 * to be used in conjunction with {@link ExpandVertexMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExpandVertexMatcher
 * @see ExpandVertexProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExpandVertexMatch extends BasePatternMatch {
  private GetVerticesOperator fGetVerticesOperator;
  
  private ExpandOperator fExpandOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("getVerticesOperator", "expandOperator", "parentOperator");
  
  private ExpandVertexMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    this.fGetVerticesOperator = pGetVerticesOperator;
    this.fExpandOperator = pExpandOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("getVerticesOperator".equals(parameterName)) return this.fGetVerticesOperator;
    if ("expandOperator".equals(parameterName)) return this.fExpandOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public GetVerticesOperator getGetVerticesOperator() {
    return this.fGetVerticesOperator;
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
    if ("getVerticesOperator".equals(parameterName) ) {
    	this.fGetVerticesOperator = (GetVerticesOperator) newValue;
    	return true;
    }
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
  
  public void setGetVerticesOperator(final GetVerticesOperator pGetVerticesOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fGetVerticesOperator = pGetVerticesOperator;
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
    return "ingraph.optimization.patterns.expandVertex";
  }
  
  @Override
  public List<String> parameterNames() {
    return ExpandVertexMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fGetVerticesOperator, fExpandOperator, fParentOperator};
  }
  
  @Override
  public ExpandVertexMatch toImmutable() {
    return isMutable() ? newMatch(fGetVerticesOperator, fExpandOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"getVerticesOperator\"=" + prettyPrintValue(fGetVerticesOperator) + ", ");
    
    result.append("\"expandOperator\"=" + prettyPrintValue(fExpandOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fGetVerticesOperator == null) ? 0 : fGetVerticesOperator.hashCode());
    result = prime * result + ((fExpandOperator == null) ? 0 : fExpandOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExpandVertexMatch)) { // this should be infrequent
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
    ExpandVertexMatch other = (ExpandVertexMatch) obj;
    if (fGetVerticesOperator == null) {if (other.fGetVerticesOperator != null) return false;}
    else if (!fGetVerticesOperator.equals(other.fGetVerticesOperator)) return false;
    if (fExpandOperator == null) {if (other.fExpandOperator != null) return false;}
    else if (!fExpandOperator.equals(other.fExpandOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public ExpandVertexQuerySpecification specification() {
    try {
    	return ExpandVertexQuerySpecification.instance();
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
  public static ExpandVertexMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExpandVertexMatch newMutableMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return new Mutable(pGetVerticesOperator, pExpandOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pGetVerticesOperator the fixed value of pattern parameter getVerticesOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExpandVertexMatch newMatch(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
    return new Immutable(pGetVerticesOperator, pExpandOperator, pParentOperator);
  }
  
  private static final class Mutable extends ExpandVertexMatch {
    Mutable(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
      super(pGetVerticesOperator, pExpandOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExpandVertexMatch {
    Immutable(final GetVerticesOperator pGetVerticesOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator) {
      super(pGetVerticesOperator, pExpandOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
