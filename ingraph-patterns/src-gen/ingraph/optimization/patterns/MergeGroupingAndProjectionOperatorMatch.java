/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns;

import ingraph.optimization.patterns.util.MergeGroupingAndProjectionOperatorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import relalg.GroupingOperator;
import relalg.Operator;
import relalg.ProjectionOperator;

/**
 * Pattern-specific match representation of the ingraph.optimization.patterns.mergeGroupingAndProjectionOperator pattern,
 * to be used in conjunction with {@link MergeGroupingAndProjectionOperatorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see MergeGroupingAndProjectionOperatorMatcher
 * @see MergeGroupingAndProjectionOperatorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class MergeGroupingAndProjectionOperatorMatch extends BasePatternMatch {
  private ProjectionOperator fProjectionOperator;
  
  private GroupingOperator fGroupingOperator;
  
  private Operator fParentOperator;
  
  private static List<String> parameterNames = makeImmutableList("projectionOperator", "groupingOperator", "parentOperator");
  
  private MergeGroupingAndProjectionOperatorMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    this.fProjectionOperator = pProjectionOperator;
    this.fGroupingOperator = pGroupingOperator;
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("projectionOperator".equals(parameterName)) return this.fProjectionOperator;
    if ("groupingOperator".equals(parameterName)) return this.fGroupingOperator;
    if ("parentOperator".equals(parameterName)) return this.fParentOperator;
    return null;
  }
  
  public ProjectionOperator getProjectionOperator() {
    return this.fProjectionOperator;
  }
  
  public GroupingOperator getGroupingOperator() {
    return this.fGroupingOperator;
  }
  
  public Operator getParentOperator() {
    return this.fParentOperator;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("projectionOperator".equals(parameterName) ) {
    	this.fProjectionOperator = (ProjectionOperator) newValue;
    	return true;
    }
    if ("groupingOperator".equals(parameterName) ) {
    	this.fGroupingOperator = (GroupingOperator) newValue;
    	return true;
    }
    if ("parentOperator".equals(parameterName) ) {
    	this.fParentOperator = (Operator) newValue;
    	return true;
    }
    return false;
  }
  
  public void setProjectionOperator(final ProjectionOperator pProjectionOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fProjectionOperator = pProjectionOperator;
  }
  
  public void setGroupingOperator(final GroupingOperator pGroupingOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fGroupingOperator = pGroupingOperator;
  }
  
  public void setParentOperator(final Operator pParentOperator) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fParentOperator = pParentOperator;
  }
  
  @Override
  public String patternName() {
    return "ingraph.optimization.patterns.mergeGroupingAndProjectionOperator";
  }
  
  @Override
  public List<String> parameterNames() {
    return MergeGroupingAndProjectionOperatorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fProjectionOperator, fGroupingOperator, fParentOperator};
  }
  
  @Override
  public MergeGroupingAndProjectionOperatorMatch toImmutable() {
    return isMutable() ? newMatch(fProjectionOperator, fGroupingOperator, fParentOperator) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"projectionOperator\"=" + prettyPrintValue(fProjectionOperator) + ", ");
    
    result.append("\"groupingOperator\"=" + prettyPrintValue(fGroupingOperator) + ", ");
    
    result.append("\"parentOperator\"=" + prettyPrintValue(fParentOperator)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fProjectionOperator == null) ? 0 : fProjectionOperator.hashCode());
    result = prime * result + ((fGroupingOperator == null) ? 0 : fGroupingOperator.hashCode());
    result = prime * result + ((fParentOperator == null) ? 0 : fParentOperator.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof MergeGroupingAndProjectionOperatorMatch)) { // this should be infrequent
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
    MergeGroupingAndProjectionOperatorMatch other = (MergeGroupingAndProjectionOperatorMatch) obj;
    if (fProjectionOperator == null) {if (other.fProjectionOperator != null) return false;}
    else if (!fProjectionOperator.equals(other.fProjectionOperator)) return false;
    if (fGroupingOperator == null) {if (other.fGroupingOperator != null) return false;}
    else if (!fGroupingOperator.equals(other.fGroupingOperator)) return false;
    if (fParentOperator == null) {if (other.fParentOperator != null) return false;}
    else if (!fParentOperator.equals(other.fParentOperator)) return false;
    return true;
  }
  
  @Override
  public MergeGroupingAndProjectionOperatorQuerySpecification specification() {
    try {
    	return MergeGroupingAndProjectionOperatorQuerySpecification.instance();
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
  public static MergeGroupingAndProjectionOperatorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static MergeGroupingAndProjectionOperatorMatch newMutableMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return new Mutable(pProjectionOperator, pGroupingOperator, pParentOperator);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pProjectionOperator the fixed value of pattern parameter projectionOperator, or null if not bound.
   * @param pGroupingOperator the fixed value of pattern parameter groupingOperator, or null if not bound.
   * @param pParentOperator the fixed value of pattern parameter parentOperator, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static MergeGroupingAndProjectionOperatorMatch newMatch(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
    return new Immutable(pProjectionOperator, pGroupingOperator, pParentOperator);
  }
  
  private static final class Mutable extends MergeGroupingAndProjectionOperatorMatch {
    Mutable(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
      super(pProjectionOperator, pGroupingOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends MergeGroupingAndProjectionOperatorMatch {
    Immutable(final ProjectionOperator pProjectionOperator, final GroupingOperator pGroupingOperator, final Operator pParentOperator) {
      super(pProjectionOperator, pGroupingOperator, pParentOperator);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
