/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.SelQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.LeftOuterJoinOperator;
import relalg.SelectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.sel pattern,
 * to be used in conjunction with {@link SelMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SelMatcher
 * @see SelProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SelMatch extends BasePatternMatch {
  private LeftOuterJoinOperator fLojOperator;
  
  private SelectionOperator fSelectionOperator;
  
  private static List<String> parameterNames = makeImmutableList("lojOperator", "selectionOperator");
  
  private SelMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    this.fLojOperator = pLojOperator;
    this.fSelectionOperator = pSelectionOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("lojOperator".equals(parameterName)) return this.fLojOperator;
    if ("selectionOperator".equals(parameterName)) return this.fSelectionOperator;
    return null;
  }
  
  public LeftOuterJoinOperator getLojOperator() {
    return this.fLojOperator;
  }
  
  public SelectionOperator getSelectionOperator() {
    return this.fSelectionOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("lojOperator".equals(parameterName) ) {
        this.fLojOperator = (LeftOuterJoinOperator) newValue;
        return true;
    }
    if ("selectionOperator".equals(parameterName) ) {
        this.fSelectionOperator = (SelectionOperator) newValue;
        return true;
    }
    return false;
  }
  
  public void setLojOperator(final LeftOuterJoinOperator pLojOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fLojOperator = pLojOperator;
  }
  
  public void setSelectionOperator(final SelectionOperator pSelectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelectionOperator = pSelectionOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.sel";
  }
  
  @Override
  public List<String> parameterNames() {
    return SelMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fLojOperator, fSelectionOperator};
  }
  
  @Override
  public SelMatch toImmutable() {
    return isMutable() ? newMatch(fLojOperator, fSelectionOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"lojOperator\"=" + prettyPrintValue(fLojOperator) + ", ");
    
    result.append("\"selectionOperator\"=" + prettyPrintValue(fSelectionOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fLojOperator == null) ? 0 : fLojOperator.hashCode());
    result = prime * result + ((fSelectionOperator == null) ? 0 : fSelectionOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof SelMatch)) { // this should be infrequent
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
    SelMatch other = (SelMatch) obj;
    if (fLojOperator == null) {if (other.fLojOperator != null) return false;}
    else if (!fLojOperator.equals(other.fLojOperator)) return false;
    if (fSelectionOperator == null) {if (other.fSelectionOperator != null) return false;}
    else if (!fSelectionOperator.equals(other.fSelectionOperator)) return false;
    return true;
  }
  
  @Override
  public SelQuerySpecification specification() {
    try {
        return SelQuerySpecification.instance();
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
  public static SelMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SelMatch newMutableMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return new Mutable(pLojOperator, pSelectionOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pLojOperator the fixed value of pattern parameter lojOperator, or null if not bound.
   * @param pSelectionOperator the fixed value of pattern parameter selectionOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SelMatch newMatch(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
    return new Immutable(pLojOperator, pSelectionOperator);
  }
  
  private static final class Mutable extends SelMatch {
    Mutable(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
      super(pLojOperator, pSelectionOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SelMatch {
    Immutable(final LeftOuterJoinOperator pLojOperator, final SelectionOperator pSelectionOperator) {
      super(pLojOperator, pSelectionOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
