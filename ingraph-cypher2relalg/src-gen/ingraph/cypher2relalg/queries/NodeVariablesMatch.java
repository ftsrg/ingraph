/**
 * Generated from platform:/resource/ingraph-cypher2relalg/src/main/java/ingraph/cypher2relalg/queries/PatternQueries.vql
 */
package ingraph.cypher2relalg.queries;

import ingraph.cypher2relalg.queries.util.NodeVariablesQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.slizaa.neo4j.opencypher.openCypher.Variable;

/**
 * Pattern-specific match representation of the ingraph.cypher2relalg.queries.nodeVariables pattern,
 * to be used in conjunction with {@link NodeVariablesMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see NodeVariablesMatcher
 * @see NodeVariablesProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class NodeVariablesMatch extends BasePatternMatch {
  private Variable fVariable;
  
  private static List<String> parameterNames = makeImmutableList("variable");
  
  private NodeVariablesMatch(final Variable pVariable) {
    this.fVariable = pVariable;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("variable".equals(parameterName)) return this.fVariable;
    return null;
  }
  
  public Variable getVariable() {
    return this.fVariable;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("variable".equals(parameterName) ) {
    	this.fVariable = (Variable) newValue;
    	return true;
    }
    return false;
  }
  
  public void setVariable(final Variable pVariable) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fVariable = pVariable;
  }
  
  @Override
  public String patternName() {
    return "ingraph.cypher2relalg.queries.nodeVariables";
  }
  
  @Override
  public List<String> parameterNames() {
    return NodeVariablesMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fVariable};
  }
  
  @Override
  public NodeVariablesMatch toImmutable() {
    return isMutable() ? newMatch(fVariable) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"variable\"=" + prettyPrintValue(fVariable)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fVariable == null) ? 0 : fVariable.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof NodeVariablesMatch)) { // this should be infrequent
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
    NodeVariablesMatch other = (NodeVariablesMatch) obj;
    if (fVariable == null) {if (other.fVariable != null) return false;}
    else if (!fVariable.equals(other.fVariable)) return false;
    return true;
  }
  
  @Override
  public NodeVariablesQuerySpecification specification() {
    try {
    	return NodeVariablesQuerySpecification.instance();
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
  public static NodeVariablesMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static NodeVariablesMatch newMutableMatch(final Variable pVariable) {
    return new Mutable(pVariable);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pVariable the fixed value of pattern parameter variable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static NodeVariablesMatch newMatch(final Variable pVariable) {
    return new Immutable(pVariable);
  }
  
  private static final class Mutable extends NodeVariablesMatch {
    Mutable(final Variable pVariable) {
      super(pVariable);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends NodeVariablesMatch {
    Immutable(final Variable pVariable) {
      super(pVariable);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
