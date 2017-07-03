package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail;
import org.slizaa.neo4j.opencypher.openCypher.Variable;
import relalg.EdgeLabelSet;
import relalg.EdgeListVariable;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class EdgeListVariableFactory extends VariableFactory<EdgeListVariable> {
  public EdgeListVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public EdgeListVariable createSpecificNamedElement() {
    EdgeListVariable _createEdgeListVariable = this.factory.createEdgeListVariable();
    final Procedure1<EdgeListVariable> _function = (EdgeListVariable it) -> {
      EdgeLabelSet _createEdgeLabelSet = this.factory.createEdgeLabelSet();
      it.setEdgeLabelSet(_createEdgeLabelSet);
    };
    return ObjectExtensions.<EdgeListVariable>operator_doubleArrow(_createEdgeListVariable, _function);
  }
  
  public EdgeListVariable createElement(final RelationshipDetail rd) {
    EdgeListVariable _xifexpression = null;
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
