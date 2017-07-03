package ingraph.relalg.calculators;

import ingraph.relalg.util.SchemaToMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.AbstractJoinOperator;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.Variable;

@SuppressWarnings("all")
public class MaskCalculator {
  @Extension
  private SchemaToMap schemaToMap = new SchemaToMap();
  
  /**
   * calculateTuples
   */
  protected void _calculateTuples(final NullaryOperator op) {
  }
  
  protected void _calculateTuples(final UnaryOperator op) {
  }
  
  protected void _calculateTuples(final AbstractJoinOperator op) {
    Operator _leftInput = op.getLeftInput();
    final Map<String, Integer> leftIndices = this.schemaToMap.schemaToMapNames(_leftInput);
    Operator _rightInput = op.getRightInput();
    final Map<String, Integer> rightIndices = this.schemaToMap.schemaToMapNames(_rightInput);
    EList<Variable> _commonVariables = op.getCommonVariables();
    final Consumer<Variable> _function = new Consumer<Variable>() {
      public void accept(final Variable variable) {
        EList<Integer> _leftMask = op.getLeftMask();
        String _name = variable.getName();
        Integer _get = leftIndices.get(_name);
        _leftMask.add(_get);
        EList<Integer> _rightMask = op.getRightMask();
        String _name_1 = variable.getName();
        Integer _get_1 = rightIndices.get(_name_1);
        _rightMask.add(_get_1);
      }
    };
    _commonVariables.forEach(_function);
  }
  
  protected void _calculateTuples(final UnionOperator op) {
  }
  
  public void calculateTuples(final Operator op) {
    if (op instanceof UnionOperator) {
      _calculateTuples((UnionOperator)op);
      return;
    } else if (op instanceof AbstractJoinOperator) {
      _calculateTuples((AbstractJoinOperator)op);
      return;
    } else if (op instanceof NullaryOperator) {
      _calculateTuples((NullaryOperator)op);
      return;
    } else if (op instanceof UnaryOperator) {
      _calculateTuples((UnaryOperator)op);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
