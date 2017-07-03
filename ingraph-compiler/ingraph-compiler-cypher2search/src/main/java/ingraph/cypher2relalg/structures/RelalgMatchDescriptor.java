package ingraph.cypher2relalg.structures;

import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import relalg.LogicalExpression;
import relalg.Operator;

/**
 * This holds the compiled form of a MATCH cypher clause.
 * 
 * Condition should be placed in the left outer join
 * or above the possible join.
 */
@SuppressWarnings("all")
public class RelalgMatchDescriptor {
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PUBLIC_SETTER })
  private LogicalExpression condition;
  
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PUBLIC_SETTER })
  private Operator op;
  
  @Pure
  public LogicalExpression getCondition() {
    return this.condition;
  }
  
  public void setCondition(final LogicalExpression condition) {
    this.condition = condition;
  }
  
  @Pure
  public Operator getOp() {
    return this.op;
  }
  
  public void setOp(final Operator op) {
    this.op = op;
  }
}
