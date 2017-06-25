/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.LeftOuterJoinAndSelectionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;
import relalg.SelectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.leftOuterJoinAndSelection pattern,
 * to be used in conjunction with {@link LeftOuterJoinAndSelectionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see LeftOuterJoinAndSelectionMatcher
 * @see LeftOuterJoinAndSelectionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class LeftOuterJoinAndSelectionMatch extends BasePatternMatch {
  private Operator fParentOperator;
  
  private SelectionOperator fSelectionOperator;
  
  private LeftOuterJoinOperator fLeftOuterJoinOperator;
  
  private Operator fLeftInputOperator;
  
  private Operator fRightInputOperator;
  
  private static List<String> parameterNames = makeImmutableList("parentOperator", "selectionOperator", "leftOuterJoinOperator", "leftInputOperator", "rightInputOperator");
  
  private LeftOuterJoinAndSelectionMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    this.fParentOperator = pParentOperator;
    this.fSelectionOperator = pSelectionOperator;
    this.fLeftOuterJoinOperator = pLeftOuterJoinOperator;
    this.fLeftInputOperator = pLeftInputOperator;
    this.fRightInputOperator = pRightInputOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    if ("selectionOperator".equals(parameterName)) return this.fSelectionOperator;
    if ("leftOuterJoinOperator".equals(parameterName)) return this.fLeftOuterJoinOperator;
    if ("leftInputOperator".equals(parameterName)) return this.fLeftInputOperator;
    if ("rightInputOperator".equals(parameterName)) return this.fRightInputOperator;
    return null;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  public SelectionOperator getSelectionOperator() {
    return this.fSelectionOperator;
  }
  
  public LeftOuterJoinOperator getLeftOuterJoinOperator() {
    return this.fLeftOuterJoinOperator;
  }
  
  public Operator getLeftInputOperator() {
    return this.fLeftInputOperator;
  }
  
  public Operator getRightInputOperator() {
    return this.fRightInputOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parentOperator".equals(parameterName) ) {
        this.fParentOperator = (Operator) newValue;
        return true;
    }
    if ("selectionOperator".equals(parameterName) ) {
        this.fSelectionOperator = (SelectionOperator) newValue;
        return true;
    }
    if ("leftOuterJoinOperator".equals(parameterName) ) {
        this.fLeftOuterJoinOperator = (LeftOuterJoinOperator) newValue;
        return true;
    }
    if ("leftInputOperator".equals(parameterName) ) {
        this.fLeftInputOperator = (Operator) newValue;
        return true;
    }
    if ("rightInputOperator".equals(parameterName) ) {
        this.fRightInputOperator = (Operator) newValue;
        return true;
    }
    return false;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  public void setSelectionOperator(final SelectionOperator pSelectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelectionOperator = pSelectionOperator;
  }
  
  public void setLeftOuterJoinOperator(final LeftOuterJoinOperator pLeftOuterJoinOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftOuterJoinOperator = pLeftOuterJoinOperator;
  }
  
  public void setLeftInputOperator(final Operator pLeftInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLeftInputOperator = pLeftInputOperator;
  }
  
  public void setRightInputOperator(final Operator pRightInputOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRightInputOperator = pRightInputOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.leftOuterJoinAndSelection";
  }
  
  @Override
  public List<String> parameterNames() {
    return LeftOuterJoinAndSelectionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParentOperator, fSelectionOperator, fLeftOuterJoinOperator, fLeftInputOperator, fRightInputOperator};
  }
  
  @Override
  public LeftOuterJoinAndSelectionMatch toImmutable() {
    return isMutable() ? newMatch(fParentOperator, fSelectionOperator, fLeftOuterJoinOperator, fLeftInputOperator, fRightInputOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator) + ", ");
    
    result.append("\"selectionOperator\"=" + prettyPrintValue(fSelectionOperator) + ", ");
    
    result.append("\"leftOuterJoinOperator\"=" + prettyPrintValue(fLeftOuterJoinOperator) + ", ");
    
    result.append("\"leftInputOperator\"=" + prettyPrintValue(fLeftInputOperator) + ", ");
    
    result.append("\"rightInputOperator\"=" + prettyPrintValue(fRightInputOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    result = prime * result + ((fSelectionOperator == null) ? 0 : fSelectionOperator.hashCode());
    result = prime * result + ((fLeftOuterJoinOperator == null) ? 0 : fLeftOuterJoinOperator.hashCode());
    result = prime * result + ((fLeftInputOperator == null) ? 0 : fLeftInputOperator.hashCode());
    result = prime * result + ((fRightInputOperator == null) ? 0 : fRightInputOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof LeftOuterJoinAndSelectionMatch)) { // this should be infrequent
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
    LeftOuterJoinAndSelectionMatch other = (LeftOuterJoinAndSelectionMatch) obj;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    if (fSelectionOperator == null) {if (other.fSelectionOperator != null) return false;}
    else if (!fSelectionOperator.equals(other.fSelectionOperator)) return false;
    if (fLeftOuterJoinOperator == null) {if (other.fLeftOuterJoinOperator != null) return false;}
    else if (!fLeftOuterJoinOperator.equals(other.fLeftOuterJoinOperator)) return false;
    if (fLeftInputOperator == null) {if (other.fLeftInputOperator != null) return false;}
    else if (!fLeftInputOperator.equals(other.fLeftInputOperator)) return false;
    if (fRightInputOperator == null) {if (other.fRightInputOperator != null) return false;}
    else if (!fRightInputOperator.equals(other.fRightInputOperator)) return false;
    return true;
  }
  
  @Override
  public LeftOuterJoinAndSelectionQuerySpecification specification() {
    try {
        return LeftOuterJoinAndSelectionQuerySpecification.instance();
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
  public static LeftOuterJoinAndSelectionMatch newEmptyMatch() {
    return new Mutable(null, null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static LeftOuterJoinAndSelectionMatch newMutableMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return new Mutable(pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @param pLeftOuterJoinOperator the fixed value of pattern parameter leftOuterJoinOperator, or null if not bound.
   * @param pLeftInputOperator the fixed value of pattern parameter leftInputOperator, or null if not bound.
   * @param pRightInputOperator the fixed value of pattern parameter rightInputOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static LeftOuterJoinAndSelectionMatch newMatch(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
    return new Immutable(pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator);
  }
  
  private static final class Mutable extends LeftOuterJoinAndSelectionMatch {
    Mutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
      super(pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends LeftOuterJoinAndSelectionMatch {
    Immutable(final Operator pParentOperator, final SelectionOperator pSelectionOperator, final LeftOuterJoinOperator pLeftOuterJoinOperator, final Operator pLeftInputOperator, final Operator pRightInputOperator) {
      super(pParentOperator, pSelectionOperator, pLeftOuterJoinOperator, pLeftInputOperator, pRightInputOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
