/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.AssociativeOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.JoinOperator;
import relalg.Operator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.AssociativeOperator pattern,
 * to be used in conjunction with {@link AssociativeOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see AssociativeOperatorMatcher
 * @see AssociativeOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class AssociativeOperatorMatch extends BasePatternMatch {
  private JoinOperator fOp1;
  
  private JoinOperator fOp2;
  
  private Operator fA;
  
  private Operator fB;
  
  private Operator fC;
  
  private static List<String> parameterNames = makeImmutableList("op1", "op2", "a", "b", "c");
  
  private AssociativeOperatorMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    this.fOp1 = pOp1;
    this.fOp2 = pOp2;
    this.fA = pA;
    this.fB = pB;
    this.fC = pC;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("op1".equals(parameterName)) return this.fOp1;
    if ("op2".equals(parameterName)) return this.fOp2;
    if ("a".equals(parameterName)) return this.fA;
    if ("b".equals(parameterName)) return this.fB;
    if ("c".equals(parameterName)) return this.fC;
    return null;
  }
  
  public JoinOperator getOp1() {
    return this.fOp1;
  }
  
  public JoinOperator getOp2() {
    return this.fOp2;
  }
  
  public Operator getA() {
    return this.fA;
  }
  
  public Operator getB() {
    return this.fB;
  }
  
  public Operator getC() {
    return this.fC;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("op1".equals(parameterName) ) {
        this.fOp1 = (JoinOperator) newValue;
        return true;
    }
    if ("op2".equals(parameterName) ) {
        this.fOp2 = (JoinOperator) newValue;
        return true;
    }
    if ("a".equals(parameterName) ) {
        this.fA = (Operator) newValue;
        return true;
    }
    if ("b".equals(parameterName) ) {
        this.fB = (Operator) newValue;
        return true;
    }
    if ("c".equals(parameterName) ) {
        this.fC = (Operator) newValue;
        return true;
    }
    return false;
  }
  
  public void setOp1(final JoinOperator pOp1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fOp1 = pOp1;
  }
  
  public void setOp2(final JoinOperator pOp2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fOp2 = pOp2;
  }
  
  public void setA(final Operator pA) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fA = pA;
  }
  
  public void setB(final Operator pB) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fB = pB;
  }
  
  public void setC(final Operator pC) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fC = pC;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.AssociativeOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return AssociativeOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fOp1, fOp2, fA, fB, fC};
  }
  
  @Override
  public AssociativeOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fOp1, fOp2, fA, fB, fC) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"op1\"=" + prettyPrintValue(fOp1) + ", ");
    
    result.append("\"op2\"=" + prettyPrintValue(fOp2) + ", ");
    
    result.append("\"a\"=" + prettyPrintValue(fA) + ", ");
    
    result.append("\"b\"=" + prettyPrintValue(fB) + ", ");
    
    result.append("\"c\"=" + prettyPrintValue(fC)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fOp1 == null) ? 0 : fOp1.hashCode());
    result = prime * result + ((fOp2 == null) ? 0 : fOp2.hashCode());
    result = prime * result + ((fA == null) ? 0 : fA.hashCode());
    result = prime * result + ((fB == null) ? 0 : fB.hashCode());
    result = prime * result + ((fC == null) ? 0 : fC.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof AssociativeOperatorMatch)) { // this should be infrequent
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
    AssociativeOperatorMatch other = (AssociativeOperatorMatch) obj;
    if (fOp1 == null) {if (other.fOp1 != null) return false;}
    else if (!fOp1.equals(other.fOp1)) return false;
    if (fOp2 == null) {if (other.fOp2 != null) return false;}
    else if (!fOp2.equals(other.fOp2)) return false;
    if (fA == null) {if (other.fA != null) return false;}
    else if (!fA.equals(other.fA)) return false;
    if (fB == null) {if (other.fB != null) return false;}
    else if (!fB.equals(other.fB)) return false;
    if (fC == null) {if (other.fC != null) return false;}
    else if (!fC.equals(other.fC)) return false;
    return true;
  }
  
  @Override
  public AssociativeOperatorQuerySpecification specification() {
    try {
        return AssociativeOperatorQuerySpecification.instance();
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
  public static AssociativeOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static AssociativeOperatorMatch newMutableMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return new Mutable(pOp1, pOp2, pA, pB, pC);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pOp1 the fixed value of pattern parameter op1, or null if not bound.
   * @param pOp2 the fixed value of pattern parameter op2, or null if not bound.
   * @param pA the fixed value of pattern parameter a, or null if not bound.
   * @param pB the fixed value of pattern parameter b, or null if not bound.
   * @param pC the fixed value of pattern parameter c, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static AssociativeOperatorMatch newMatch(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
    return new Immutable(pOp1, pOp2, pA, pB, pC);
  }
  
  private static final class Mutable extends AssociativeOperatorMatch {
    Mutable(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
      super(pOp1, pOp2, pA, pB, pC);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends AssociativeOperatorMatch {
    Immutable(final JoinOperator pOp1, final JoinOperator pOp2, final Operator pA, final Operator pB, final Operator pC) {
      super(pOp1, pOp2, pA, pB, pC);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
