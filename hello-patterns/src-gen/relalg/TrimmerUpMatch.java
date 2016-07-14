/**
 * Generated from platform:/resource/hello-patterns/src/relalg/my.vql
 */
package relalg;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.JoinNode;
import relalg.ReteNode;
import relalg.TrimmerNode;
import relalg.util.TrimmerUpQuerySpecification;

/**
 * Pattern-specific match representation of the relalg.trimmerUp pattern,
 * to be used in conjunction with {@link TrimmerUpMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TrimmerUpMatcher
 * @see TrimmerUpProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TrimmerUpMatch extends BasePatternMatch {
  private JoinNode fJoin;
  
  private TrimmerNode fTrimmer;
  
  private ReteNode fRn1;
  
  private ReteNode fRn2;
  
  private static List<String> parameterNames = makeImmutableList("join", "trimmer", "rn1", "rn2");
  
  private TrimmerUpMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    this.fJoin = pJoin;
    this.fTrimmer = pTrimmer;
    this.fRn1 = pRn1;
    this.fRn2 = pRn2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("join".equals(parameterName)) return this.fJoin;
    if ("trimmer".equals(parameterName)) return this.fTrimmer;
    if ("rn1".equals(parameterName)) return this.fRn1;
    if ("rn2".equals(parameterName)) return this.fRn2;
    return null;
  }
  
  public JoinNode getJoin() {
    return this.fJoin;
  }
  
  public TrimmerNode getTrimmer() {
    return this.fTrimmer;
  }
  
  public ReteNode getRn1() {
    return this.fRn1;
  }
  
  public ReteNode getRn2() {
    return this.fRn2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("join".equals(parameterName) ) {
    	this.fJoin = (JoinNode) newValue;
    	return true;
    }
    if ("trimmer".equals(parameterName) ) {
    	this.fTrimmer = (TrimmerNode) newValue;
    	return true;
    }
    if ("rn1".equals(parameterName) ) {
    	this.fRn1 = (ReteNode) newValue;
    	return true;
    }
    if ("rn2".equals(parameterName) ) {
    	this.fRn2 = (ReteNode) newValue;
    	return true;
    }
    return false;
  }
  
  public void setJoin(final JoinNode pJoin) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fJoin = pJoin;
  }
  
  public void setTrimmer(final TrimmerNode pTrimmer) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTrimmer = pTrimmer;
  }
  
  public void setRn1(final ReteNode pRn1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRn1 = pRn1;
  }
  
  public void setRn2(final ReteNode pRn2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRn2 = pRn2;
  }
  
  @Override
  public String patternName() {
    return "relalg.trimmerUp";
  }
  
  @Override
  public List<String> parameterNames() {
    return TrimmerUpMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fJoin, fTrimmer, fRn1, fRn2};
  }
  
  @Override
  public TrimmerUpMatch toImmutable() {
    return isMutable() ? newMatch(fJoin, fTrimmer, fRn1, fRn2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"join\"=" + prettyPrintValue(fJoin) + ", ");
    
    result.append("\"trimmer\"=" + prettyPrintValue(fTrimmer) + ", ");
    
    result.append("\"rn1\"=" + prettyPrintValue(fRn1) + ", ");
    
    result.append("\"rn2\"=" + prettyPrintValue(fRn2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fJoin == null) ? 0 : fJoin.hashCode());
    result = prime * result + ((fTrimmer == null) ? 0 : fTrimmer.hashCode());
    result = prime * result + ((fRn1 == null) ? 0 : fRn1.hashCode());
    result = prime * result + ((fRn2 == null) ? 0 : fRn2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof TrimmerUpMatch)) { // this should be infrequent
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
    TrimmerUpMatch other = (TrimmerUpMatch) obj;
    if (fJoin == null) {if (other.fJoin != null) return false;}
    else if (!fJoin.equals(other.fJoin)) return false;
    if (fTrimmer == null) {if (other.fTrimmer != null) return false;}
    else if (!fTrimmer.equals(other.fTrimmer)) return false;
    if (fRn1 == null) {if (other.fRn1 != null) return false;}
    else if (!fRn1.equals(other.fRn1)) return false;
    if (fRn2 == null) {if (other.fRn2 != null) return false;}
    else if (!fRn2.equals(other.fRn2)) return false;
    return true;
  }
  
  @Override
  public TrimmerUpQuerySpecification specification() {
    try {
    	return TrimmerUpQuerySpecification.instance();
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
  public static TrimmerUpMatch newEmptyMatch() {
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TrimmerUpMatch newMutableMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return new Mutable(pJoin, pTrimmer, pRn1, pRn2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pJoin the fixed value of pattern parameter join, or null if not bound.
   * @param pTrimmer the fixed value of pattern parameter trimmer, or null if not bound.
   * @param pRn1 the fixed value of pattern parameter rn1, or null if not bound.
   * @param pRn2 the fixed value of pattern parameter rn2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TrimmerUpMatch newMatch(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
    return new Immutable(pJoin, pTrimmer, pRn1, pRn2);
  }
  
  private static final class Mutable extends TrimmerUpMatch {
    Mutable(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
      super(pJoin, pTrimmer, pRn1, pRn2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TrimmerUpMatch {
    Immutable(final JoinNode pJoin, final TrimmerNode pTrimmer, final ReteNode pRn1, final ReteNode pRn2) {
      super(pJoin, pTrimmer, pRn1, pRn2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
