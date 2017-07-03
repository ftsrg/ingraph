package ingraph.cypher2relalg.util;

import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import relalg.UnaryOperator;

/**
 * Represents an element in an UnaryOperator pattern, where that
 * particular operator can occur 0 or 1 times, i.e. this is the ? from regular expressions.
 */
@SuppressWarnings("all")
public class UnaryOperator0or1Pattern {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final Class<? extends UnaryOperator> opc;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final boolean optional;
  
  public UnaryOperator0or1Pattern(final Class<? extends UnaryOperator> opc, final boolean optional) {
    this.opc = opc;
    this.optional = optional;
  }
  
  /**
   * Indicate if this pattern element is mandatory, i.e. NOT optional.
   */
  public boolean isMandatory() {
    return (!this.optional);
  }
  
  @Override
  public String toString() {
    String _name = this.opc.getName();
    String _xifexpression = null;
    if (this.optional) {
      _xifexpression = "?";
    } else {
      _xifexpression = "";
    }
    return (_name + _xifexpression);
  }
  
  @Pure
  public Class<? extends UnaryOperator> getOpc() {
    return this.opc;
  }
  
  @Pure
  public boolean isOptional() {
    return this.optional;
  }
}
