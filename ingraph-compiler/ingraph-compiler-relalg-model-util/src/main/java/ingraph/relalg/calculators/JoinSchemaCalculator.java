package ingraph.relalg.calculators;

import ingraph.relalg.collectors.CollectionHelper;
import java.util.Arrays;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractJoinOperator;
import relalg.AntiJoinOperator;
import relalg.EdgeVariable;
import relalg.EquiJoinLikeOperator;
import relalg.TransitiveClosureJoinOperator;
import relalg.Variable;

@SuppressWarnings("all")
public class JoinSchemaCalculator {
  @Extension
  private CollectionHelper collectionHelper = new CollectionHelper();
  
  protected List<Variable> _calculateJoinSchema(final EquiJoinLikeOperator op, final List<Variable> leftSchema, final List<Variable> rightSchema) {
    return this.calculateEquiJoinSchema(leftSchema, rightSchema);
  }
  
  protected List<Variable> _calculateJoinSchema(final AntiJoinOperator op, final List<Variable> leftSchema, final List<Variable> rightSchema) {
    return leftSchema;
  }
  
  protected List<Variable> _calculateJoinSchema(final TransitiveClosureJoinOperator op, final List<Variable> leftSchema, final List<Variable> rightSchema) {
    List<Variable> _xblockexpression = null;
    {
      final List<Variable> joinSchema = this.calculateEquiJoinSchema(leftSchema, rightSchema);
      final Function1<Variable, Variable> _function = new Function1<Variable, Variable>() {
        public Variable apply(final Variable it) {
          Variable _xifexpression = null;
          if (((it instanceof EdgeVariable) && it.getName().equals(op.getEdgeListVariable().getName()))) {
            _xifexpression = op.getEdgeListVariable();
          } else {
            _xifexpression = it;
          }
          return _xifexpression;
        }
      };
      final List<Variable> joinSchemaWithEdgeList = ListExtensions.<Variable, Variable>map(joinSchema, _function);
      _xblockexpression = joinSchemaWithEdgeList;
    }
    return _xblockexpression;
  }
  
  public List<Variable> calculateEquiJoinSchema(final List<Variable> leftSchema, final List<Variable> rightSchema) {
    List<Variable> _xblockexpression = null;
    {
      final Function1<Variable, String> _function = new Function1<Variable, String>() {
        public String apply(final Variable it) {
          return it.fullName();
        }
      };
      final List<String> leftSchemaNames = ListExtensions.<Variable, String>map(leftSchema, _function);
      final Function1<Variable, Boolean> _function_1 = new Function1<Variable, Boolean>() {
        public Boolean apply(final Variable variable) {
          String _fullName = variable.fullName();
          boolean _contains = leftSchemaNames.contains(_fullName);
          return Boolean.valueOf((!_contains));
        }
      };
      Iterable<Variable> _filter = IterableExtensions.<Variable>filter(rightSchema, _function_1);
      final List<Variable> joinSchema = this.collectionHelper.<Variable>union(leftSchema, _filter);
      _xblockexpression = IterableExtensions.<Variable>toList(joinSchema);
    }
    return _xblockexpression;
  }
  
  public List<Variable> calculateJoinSchema(final AbstractJoinOperator op, final List<Variable> leftSchema, final List<Variable> rightSchema) {
    if (op instanceof TransitiveClosureJoinOperator) {
      return _calculateJoinSchema((TransitiveClosureJoinOperator)op, leftSchema, rightSchema);
    } else if (op instanceof AntiJoinOperator) {
      return _calculateJoinSchema((AntiJoinOperator)op, leftSchema, rightSchema);
    } else if (op instanceof EquiJoinLikeOperator) {
      return _calculateJoinSchema((EquiJoinLikeOperator)op, leftSchema, rightSchema);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op, leftSchema, rightSchema).toString());
    }
  }
}
