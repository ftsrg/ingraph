package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail;
import org.slizaa.neo4j.opencypher.openCypher.Variable;
import relalg.EdgeLabelSet;
import relalg.EdgeVariable;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class EdgeVariableFactory extends VariableFactory<EdgeVariable> {
  public EdgeVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public EdgeVariable createSpecificNamedElement() {
    EdgeVariable _createEdgeVariable = this.factory.createEdgeVariable();
    final Procedure1<EdgeVariable> _function = (EdgeVariable it) -> {
      EdgeLabelSet _createEdgeLabelSet = this.factory.createEdgeLabelSet();
      it.setEdgeLabelSet(_createEdgeLabelSet);
    };
    return ObjectExtensions.<EdgeVariable>operator_doubleArrow(_createEdgeVariable, _function);
  }
  
  public EdgeVariable createElement(final RelationshipDetail rd) {
    EdgeVariable _xifexpression = null;
    Variable _variable = null;
    if (rd!=null) {
      _variable=rd.getVariable();
    }
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
