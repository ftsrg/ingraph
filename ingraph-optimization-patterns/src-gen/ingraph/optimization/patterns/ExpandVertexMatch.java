/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
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
  private GetVerticesOperator fV;
  
  private ExpandOperator fE;
  
  private static List<String> parameterNames = makeImmutableList("v", "e");
  
  private ExpandVertexMatch(final GetVerticesOperator pV, final ExpandOperator pE) {
    this.fV = pV;
    this.fE = pE;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("v".equals(parameterName)) return this.fV;
    if ("e".equals(parameterName)) return this.fE;
    return null;
  }
  
  public GetVerticesOperator getV() {
    return this.fV;
  }
  
  public ExpandOperator getE() {
    return this.fE;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("v".equals(parameterName) ) {
    	this.fV = (GetVerticesOperator) newValue;
    	return true;
    }
    if ("e".equals(parameterName) ) {
    	this.fE = (ExpandOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setV(final GetVerticesOperator pV) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fV = pV;
  }
  
  public void setE(final ExpandOperator pE) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fE = pE;
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
    return new Object[]{fV, fE};
  }
  
  @Override
  public ExpandVertexMatch toImmutable() {
    return isMutable() ? newMatch(fV, fE) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"v\"=" + prettyPrintValue(fV) + ", ");
    
    result.append("\"e\"=" + prettyPrintValue(fE)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fV == null) ? 0 : fV.hashCode());
    result = prime * result + ((fE == null) ? 0 : fE.hashCode());
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
    if (fV == null) {if (other.fV != null) return false;}
    else if (!fV.equals(other.fV)) return false;
    if (fE == null) {if (other.fE != null) return false;}
    else if (!fE.equals(other.fE)) return false;
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
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExpandVertexMatch newMutableMatch(final GetVerticesOperator pV, final ExpandOperator pE) {
    return new Mutable(pV, pE);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pV the fixed value of pattern parameter v, or null if not bound.
   * @param pE the fixed value of pattern parameter e, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExpandVertexMatch newMatch(final GetVerticesOperator pV, final ExpandOperator pE) {
    return new Immutable(pV, pE);
  }
  
  private static final class Mutable extends ExpandVertexMatch {
    Mutable(final GetVerticesOperator pV, final ExpandOperator pE) {
      super(pV, pE);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExpandVertexMatch {
    Immutable(final GetVerticesOperator pV, final ExpandOperator pE) {
      super(pV, pE);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
