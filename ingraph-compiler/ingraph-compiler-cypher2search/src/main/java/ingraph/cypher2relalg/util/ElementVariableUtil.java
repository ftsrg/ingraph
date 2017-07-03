package ingraph.cypher2relalg.util;

import com.google.common.base.Objects;
import ingraph.cypher2relalg.factories.AttributeVariableFactory;
import java.util.HashMap;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.AttributeVariable;
import relalg.ElementVariable;
import relalg.ExpressionVariable;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.Variable;

@SuppressWarnings("all")
public class ElementVariableUtil {
  @Extension
  protected RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  protected final RelalgContainer container;
  
  private final HashMap<Variable, AttributeVariableFactory> factories = new HashMap<Variable, AttributeVariableFactory>();
  
  public ElementVariableUtil(final RelalgContainer container) {
    this.container = container;
  }
  
  public AttributeVariable createAttribute(final Variable element, final String attributeName) {
    Object _xifexpression = null;
    if (((element instanceof ElementVariable) || (element instanceof ExpressionVariable))) {
      AttributeVariableFactory _get = this.factories.get(element);
      boolean _equals = Objects.equal(_get, null);
      if (_equals) {
        AttributeVariableFactory _attributeVariableFactory = new AttributeVariableFactory(this.container);
        this.factories.put(element, _attributeVariableFactory);
      }
      AttributeVariableFactory _get_1 = this.factories.get(element);
      final AttributeVariable attribute = _get_1.createElement(attributeName);
      if ((element instanceof ElementVariable)) {
        attribute.setElement(((ElementVariable)element));
      } else {
        if ((element instanceof ExpressionVariable)) {
          attribute.setExpVar(((ExpressionVariable)element));
        } else {
          throw new RuntimeException("FIXME: this should never be reached.");
        }
      }
      return attribute;
    } else {
      _xifexpression = null;
    }
    return ((AttributeVariable)_xifexpression);
  }
}
