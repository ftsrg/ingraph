/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import java.util.Arrays;
import java.util.List;

import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import ingraph.optimization.patterns.util.LeftDeepTreeNodesProcessor;
import ingraph.optimization.patterns.util.LeftDeepTreeNodesQuerySpecification;
import relalg.BinaryLogicalExpression;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.leftDeepTreeNodes pattern,
 * to be used in conjunction with {@link LeftDeepTreeNodesMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see LeftDeepTreeNodesMatcher
 * @see LeftDeepTreeNodesProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class LeftDeepTreeNodesMatch extends BasePatternMatch {
  private BinaryLogicalExpression fParent;
  
  private BinaryLogicalExpression fChild;
  
  private static List<String> parameterNames = makeImmutableList("parent", "child");
  
  private LeftDeepTreeNodesMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    this.fParent = pParent;
    this.fChild = pChild;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("parent".equals(parameterName)) return this.fParent;
    if ("child".equals(parameterName)) return this.fChild;
    return null;
  }
  
  public BinaryLogicalExpression getParent() {
    return this.fParent;
  }
  
  public BinaryLogicalExpression getChild() {
    return this.fChild;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("parent".equals(parameterName) ) {
    	this.fParent = (BinaryLogicalExpression) newValue;
    	return true;
    }
    if ("child".equals(parameterName) ) {
    	this.fChild = (BinaryLogicalExpression) newValue;
    	return true;
    }
    return false;
  }
  
  public void setParent(final BinaryLogicalExpression pParent) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParent = pParent;
  }
  
  public void setChild(final BinaryLogicalExpression pChild) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fChild = pChild;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.leftDeepTreeNodes";
  }
  
  @Override
  public List<String> parameterNames() {
    return LeftDeepTreeNodesMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fParent, fChild};
  }
  
  @Override
  public LeftDeepTreeNodesMatch toImmutable() {
    return isMutable() ? newMatch(fParent, fChild) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"parent\"=" + prettyPrintValue(fParent) + ", ");
    
    result.append("\"child\"=" + prettyPrintValue(fChild)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fParent == null) ? 0 : fParent.hashCode());
    result = prime * result + ((fChild == null) ? 0 : fChild.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof LeftDeepTreeNodesMatch)) { // this should be infrequent
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
    LeftDeepTreeNodesMatch other = (LeftDeepTreeNodesMatch) obj;
    if (fParent == null) {if (other.fParent != null) return false;}
    else if (!fParent.equals(other.fParent)) return false;
    if (fChild == null) {if (other.fChild != null) return false;}
    else if (!fChild.equals(other.fChild)) return false;
    return true;
  }
  
  @Override
  public LeftDeepTreeNodesQuerySpecification specification() {
    try {
    	return LeftDeepTreeNodesQuerySpecification.instance();
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
  public static LeftDeepTreeNodesMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static LeftDeepTreeNodesMatch newMutableMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return new Mutable(pParent, pChild);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pParent the fixed value of pattern parameter parent, or null if not bound.
   * @param pChild the fixed value of pattern parameter child, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static LeftDeepTreeNodesMatch newMatch(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
    return new Immutable(pParent, pChild);
  }
  
  private static final class Mutable extends LeftDeepTreeNodesMatch {
    Mutable(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
      super(pParent, pChild);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends LeftDeepTreeNodesMatch {
    Immutable(final BinaryLogicalExpression pParent, final BinaryLogicalExpression pChild) {
      super(pParent, pChild);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
