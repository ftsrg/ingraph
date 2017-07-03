package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class ExpressionVariableFactory extends VariableFactory<ExpressionVariable> {
  public ExpressionVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public ExpressionVariable createSpecificNamedElement() {
    return this.factory.createExpressionVariable();
  }
  
  public ExpressionVariable createElement(final String name, final Expression expression) {
    ExpressionVariable _xifexpression = null;
    if ((name != null)) {
      ExpressionVariable _createElement = this.createElement(name);
      final Procedure1<ExpressionVariable> _function = (ExpressionVariable it) -> {
        it.setExpression(expression);
      };
      _xifexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createElement, _function);
    } else {
      ExpressionVariable _createDontCareElement = this.createDontCareElement(expression);
      final Procedure1<ExpressionVariable> _function_1 = (ExpressionVariable it) -> {
        it.setExpression(expression);
      };
      _xifexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createDontCareElement, _function_1);
    }
    return _xifexpression;
  }
}
