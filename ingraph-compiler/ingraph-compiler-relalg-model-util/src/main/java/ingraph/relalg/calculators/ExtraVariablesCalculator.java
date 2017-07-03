package ingraph.relalg.calculators;

import ingraph.relalg.calculators.ExtraVariablesPropagator;
import ingraph.relalg.calculators.VariableExtractor;
import ingraph.relalg.collectors.CollectionHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.AbstractJoinOperator;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.RelalgContainer;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.Variable;

/**
 * Calculates extra variables.
 * 
 * For example, a projection may need extra variables for projecting attributes
 * or a selection may need extra attributes for evaluating conditions.
 */
@SuppressWarnings("all")
public class ExtraVariablesCalculator {
  @Extension
  private VariableExtractor variableExtractor = new VariableExtractor();
  
  @Extension
  private CollectionHelper listUnionCalculator = new CollectionHelper();
  
  @Extension
  private ExtraVariablesPropagator extraVariablesPropagator = new ExtraVariablesPropagator();
  
  public RelalgContainer calculateExtraVariables(final RelalgContainer container) {
    RelalgContainer _xblockexpression = null;
    {
      boolean _isExternalSchemaInferred = container.isExternalSchemaInferred();
      boolean _not = (!_isExternalSchemaInferred);
      if (_not) {
        throw new IllegalStateException("ExternalSchemaCalculator must be executed before ExtraVariableCalculator");
      } else {
        boolean _isExtraVariablesInferred = container.isExtraVariablesInferred();
        if (_isExtraVariablesInferred) {
          throw new IllegalStateException("ExtraVariablesCalculator on relalg container was already executed");
        } else {
          container.setExtraVariablesInferred(true);
        }
      }
      Operator _rootExpression = container.getRootExpression();
      this.fillExtraVariables(_rootExpression, Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList()));
      _xblockexpression = container;
    }
    return _xblockexpression;
  }
  
  /**
   * nullary operators
   */
  private void _fillExtraVariables(final NullaryOperator op, final List<Variable> extraVariables) {
    EList<Variable> _extraVariables = op.getExtraVariables();
    _extraVariables.addAll(extraVariables);
  }
  
  /**
   * unary operators
   * 
   * some unary operators, such as selection, projection and grouping, often require extra variables
   */
  private void _fillExtraVariables(final UnaryOperator op, final List<Variable> extraVariables) {
    EList<Variable> _extraVariables = op.getExtraVariables();
    _extraVariables.addAll(extraVariables);
    final List<? extends Variable> newExtraVariables = this.variableExtractor.extractUnaryOperatorExtraVariables(op);
    List<Variable> _uniqueUnion = this.listUnionCalculator.<Variable>uniqueUnion(extraVariables, newExtraVariables);
    List<? extends Variable> _calculatedVariables = this.variableExtractor.getCalculatedVariables(op);
    List<Variable> inputExtraVariables = this.listUnionCalculator.<Variable>minus(_uniqueUnion, _calculatedVariables);
    Operator _input = op.getInput();
    final List<Variable> propagatedInputExtraVariables = this.extraVariablesPropagator.propagateTo(inputExtraVariables, _input);
    Operator _input_1 = op.getInput();
    this.fillExtraVariables(_input_1, propagatedInputExtraVariables);
  }
  
  /**
   * binary operators
   */
  private void _fillExtraVariables(final UnionOperator op, final List<Variable> extraVariables) {
    EList<Variable> _extraVariables = op.getExtraVariables();
    _extraVariables.addAll(extraVariables);
    Operator _leftInput = op.getLeftInput();
    this.fillExtraVariables(_leftInput, extraVariables);
    Operator _rightInput = op.getRightInput();
    this.fillExtraVariables(_rightInput, extraVariables);
  }
  
  private void _fillExtraVariables(final AbstractJoinOperator op, final List<Variable> extraVariables) {
    EList<Variable> _extraVariables = op.getExtraVariables();
    _extraVariables.addAll(extraVariables);
    Operator _leftInput = op.getLeftInput();
    final List<Variable> leftExtraVariables = this.extraVariablesPropagator.propagateTo(extraVariables, _leftInput);
    Operator _rightInput = op.getRightInput();
    final List<Variable> rightExtraVariables = this.extraVariablesPropagator.propagateTo(extraVariables, _rightInput);
    rightExtraVariables.removeAll(leftExtraVariables);
    Operator _leftInput_1 = op.getLeftInput();
    this.fillExtraVariables(_leftInput_1, leftExtraVariables);
    Operator _rightInput_1 = op.getRightInput();
    this.fillExtraVariables(_rightInput_1, rightExtraVariables);
  }
  
  private void fillExtraVariables(final Operator op, final List<Variable> extraVariables) {
    if (op instanceof UnionOperator) {
      _fillExtraVariables((UnionOperator)op, extraVariables);
      return;
    } else if (op instanceof AbstractJoinOperator) {
      _fillExtraVariables((AbstractJoinOperator)op, extraVariables);
      return;
    } else if (op instanceof NullaryOperator) {
      _fillExtraVariables((NullaryOperator)op, extraVariables);
      return;
    } else if (op instanceof UnaryOperator) {
      _fillExtraVariables((UnaryOperator)op, extraVariables);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op, extraVariables).toString());
    }
  }
}
