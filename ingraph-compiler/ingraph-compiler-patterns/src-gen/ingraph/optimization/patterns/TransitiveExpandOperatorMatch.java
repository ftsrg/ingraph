/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.TransitiveExpandOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.EdgeListVariable;
import relalg.ExpandOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.transitiveExpandOperator pattern,
 * to be used in conjunction with {@link TransitiveExpandOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TransitiveExpandOperatorMatcher
 * @see TransitiveExpandOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TransitiveExpandOperatorMatch extends BasePatternMatch {
  private Operator fInputOperator;
  
  private ExpandOperator fExpandOperator;
  
  private Operator fParentOperator;
  
  private EdgeListVariable fEdgeListVariable;
  
  private static List<String> parameterNames = makeImmutableList("inputOperator", "expandOperator", "parentOperator", "edgeListVariable");
  
  private TransitiveExpandOperatorMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    this.fInputOperator = pInputOperator;
    this.fExpandOperator = pExpandOperator;
    this.fParentOperator = pParentOperator;
    this.fEdgeListVariable = pEdgeListVariable;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("inputOperator".equals(parameterName)) return this.fInputOperator;
    if ("expandOperator".equals(parameterName)) return this.fExpandOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("edgeListVariable".equals(parameterName)) return this.fEdgeListVariable;
    return null;
  }
  
  public Operator getInputOperator() {
    return this.fInputOperator;
  }
  
  public ExpandOperator getExpandOperator() {
    return this.fExpandOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public EdgeListVariable getEdgeListVariable() {
    return this.fEdgeListVariable;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("inputOperator".equals(parameterName) ) {
        this.fInputOperator = (Operator) newValue;
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
    if ("edgeListVariable".equals(parameterName) ) {
        this.fEdgeListVariable = (EdgeListVariable) newValue;
        return true;
    }
    return false;
  }
  
  public void setInputOperator(final Operator pInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fInputOperator = pInputOperator;
  }
  
  public void setExpandOperator(final ExpandOperator pExpandOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fExpandOperator = pExpandOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setEdgeListVariable(final EdgeListVariable pEdgeListVariable) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fEdgeListVariable = pEdgeListVariable;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.transitiveExpandOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return TransitiveExpandOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fInputOperator, fExpandOperator, fParentOperator, fEdgeListVariable};
  }
  
  @Override
  public TransitiveExpandOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fInputOperator, fExpandOperator, fParentOperator, fEdgeListVariable) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"inputOperator\"=" + prettyPrintValue(fInputOperator) + ", ");
    
    result.append("\"expandOperator\"=" + prettyPrintValue(fExpandOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"edgeListVariable\"=" + prettyPrintValue(fEdgeListVariable)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fInputOperator == null) ? 0 : fInputOperator.hashCode());
    result = prime * result + ((fExpandOperator == null) ? 0 : fExpandOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fEdgeListVariable == null) ? 0 : fEdgeListVariable.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof TransitiveExpandOperatorMatch)) { // this should be infrequent
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
    TransitiveExpandOperatorMatch other = (TransitiveExpandOperatorMatch) obj;
    if (fInputOperator == null) {if (other.fInputOperator != null) return false;}
    else if (!fInputOperator.equals(other.fInputOperator)) return false;
    if (fExpandOperator == null) {if (other.fExpandOperator != null) return false;}
    else if (!fExpandOperator.equals(other.fExpandOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fEdgeListVariable == null) {if (other.fEdgeListVariable != null) return false;}
    else if (!fEdgeListVariable.equals(other.fEdgeListVariable)) return false;
    return true;
  }
  
  @Override
  public TransitiveExpandOperatorQuerySpecification specification() {
    try {
        return TransitiveExpandOperatorQuerySpecification.instance();
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
  public static TransitiveExpandOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TransitiveExpandOperatorMatch newMutableMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return new Mutable(pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pInputOperator the fixed value of pattern parameter inputOperator, or null if not bound.
   * @param pExpandOperator the fixed value of pattern parameter expandOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pEdgeListVariable the fixed value of pattern parameter edgeListVariable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TransitiveExpandOperatorMatch newMatch(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
    return new Immutable(pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable);
  }
  
  private static final class Mutable extends TransitiveExpandOperatorMatch {
    Mutable(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
      super(pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TransitiveExpandOperatorMatch {
    Immutable(final Operator pInputOperator, final ExpandOperator pExpandOperator, final Operator pParentOperator, final EdgeListVariable pEdgeListVariable) {
      super(pInputOperator, pExpandOperator, pParentOperator, pEdgeListVariable);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
