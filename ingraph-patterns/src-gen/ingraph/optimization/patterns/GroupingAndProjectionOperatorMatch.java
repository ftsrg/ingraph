/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.GroupingAndProjectionOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.GroupingAndProjectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.groupingAndProjectionOperator pattern,
 * to be used in conjunction with {@link GroupingAndProjectionOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see GroupingAndProjectionOperatorMatcher
 * @see GroupingAndProjectionOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class GroupingAndProjectionOperatorMatch extends BasePatternMatch {
  private GroupingAndProjectionOperator fGroupingAndProjectionOperator;
  
  private static List<String> parameterNames = makeImmutableList("groupingAndProjectionOperator");
  
  private GroupingAndProjectionOperatorMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    this.fGroupingAndProjectionOperator = pGroupingAndProjectionOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("groupingAndProjectionOperator".equals(parameterName)) return this.fGroupingAndProjectionOperator;
    return null;
  }
  
  public GroupingAndProjectionOperator getGroupingAndProjectionOperator() {
    return this.fGroupingAndProjectionOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("groupingAndProjectionOperator".equals(parameterName) ) {
    	this.fGroupingAndProjectionOperator = (GroupingAndProjectionOperator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setGroupingAndProjectionOperator(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fGroupingAndProjectionOperator = pGroupingAndProjectionOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.groupingAndProjectionOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return GroupingAndProjectionOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fGroupingAndProjectionOperator};
  }
  
  @Override
  public GroupingAndProjectionOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fGroupingAndProjectionOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"groupingAndProjectionOperator\"=" + prettyPrintValue(fGroupingAndProjectionOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fGroupingAndProjectionOperator == null) ? 0 : fGroupingAndProjectionOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof GroupingAndProjectionOperatorMatch)) { // this should be infrequent
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
    GroupingAndProjectionOperatorMatch other = (GroupingAndProjectionOperatorMatch) obj;
    if (fGroupingAndProjectionOperator == null) {if (other.fGroupingAndProjectionOperator != null) return false;}
    else if (!fGroupingAndProjectionOperator.equals(other.fGroupingAndProjectionOperator)) return false;
    return true;
  }
  
  @Override
  public GroupingAndProjectionOperatorQuerySpecification specification() {
    try {
    	return GroupingAndProjectionOperatorQuerySpecification.instance();
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
  public static GroupingAndProjectionOperatorMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static GroupingAndProjectionOperatorMatch newMutableMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return new Mutable(pGroupingAndProjectionOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pGroupingAndProjectionOperator the fixed value of pattern parameter groupingAndProjectionOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static GroupingAndProjectionOperatorMatch newMatch(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
    return new Immutable(pGroupingAndProjectionOperator);
  }
  
  private static final class Mutable extends GroupingAndProjectionOperatorMatch {
    Mutable(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
      super(pGroupingAndProjectionOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends GroupingAndProjectionOperatorMatch {
    Immutable(final GroupingAndProjectionOperator pGroupingAndProjectionOperator) {
      super(pGroupingAndProjectionOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
