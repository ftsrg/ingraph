package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slizaa.neo4j.opencypher.openCypher.NodePattern;
import org.slizaa.neo4j.opencypher.openCypher.Variable;
import relalg.RelalgContainer;
import relalg.VertexLabelSet;
import relalg.VertexVariable;

@SuppressWarnings("all")
public class VertexVariableFactory extends VariableFactory<VertexVariable> {
  public VertexVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public VertexVariable createSpecificNamedElement() {
    VertexVariable _createVertexVariable = this.factory.createVertexVariable();
    final Procedure1<VertexVariable> _function = (VertexVariable it) -> {
      VertexLabelSet _createVertexLabelSet = this.factory.createVertexLabelSet();
      it.setVertexLabelSet(_createVertexLabelSet);
    };
    return ObjectExtensions.<VertexVariable>operator_doubleArrow(_createVertexVariable, _function);
  }
  
  public VertexVariable createElement(final NodePattern rd) {
    VertexVariable _xifexpression = null;
    Variable _variable = rd.getVariable();
    boolean _tripleNotEquals = (_variable != null);
    if (_tripleNotEquals) {
      Variable _variable_1 = rd.getVariable();
      String _name = _variable_1.getName();
      _xifexpression = this.createElement(_name);
    } else {
      _xifexpression = this.createDontCareElement(rd);
    }
    return _xifexpression;
  }
}
