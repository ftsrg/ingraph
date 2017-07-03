package ingraph.relalg.calculators;

import com.google.common.collect.Iterables;
import ingraph.relalg.collectors.CollectionHelper;
import ingraph.relalg.util.ElementVariableExtractor;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AttributeVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.FunctionExpression;
import relalg.Operator;
import relalg.Variable;

@SuppressWarnings("all")
public class ExtraVariablesPropagator {
  @Extension
  private CollectionHelper listUnionCalculator = new CollectionHelper();
  
  @Extension
  private ElementVariableExtractor elementVariableExtractor = new ElementVariableExtractor();
  
  public List<Variable> propagateTo(final List<Variable> extraVariables, final Operator inputOp) {
    List<Variable> _xblockexpression = null;
    {
      EList<Variable> _externalSchema = inputOp.getExternalSchema();
      final Function1<Variable, String> _function = new Function1<Variable, String>() {
        public String apply(final Variable it) {
          return it.fullName();
        }
      };
      final List<String> inputSchemaNames = ListExtensions.<Variable, String>map(_externalSchema, _function);
      Iterable<AttributeVariable> _filter = Iterables.<AttributeVariable>filter(extraVariables, AttributeVariable.class);
      final Function1<AttributeVariable, Boolean> _function_1 = new Function1<AttributeVariable, Boolean>() {
        public Boolean apply(final AttributeVariable it) {
          return Boolean.valueOf(((!inputSchemaNames.contains(it.fullName())) && 
            inputSchemaNames.contains(ExtraVariablesPropagator.this.elementVariableExtractor.extractElementVariable(it.getBaseVariable()).fullName())));
        }
      };
      final Iterable<AttributeVariable> attributes = IterableExtensions.<AttributeVariable>filter(_filter, _function_1);
      Iterable<ExpressionVariable> _filter_1 = Iterables.<ExpressionVariable>filter(extraVariables, ExpressionVariable.class);
      final Function1<ExpressionVariable, Boolean> _function_2 = new Function1<ExpressionVariable, Boolean>() {
        public Boolean apply(final ExpressionVariable it) {
          Expression _expression = it.getExpression();
          return Boolean.valueOf((_expression instanceof FunctionExpression));
        }
      };
      Iterable<ExpressionVariable> _filter_2 = IterableExtensions.<ExpressionVariable>filter(_filter_1, _function_2);
      final Function1<ExpressionVariable, Boolean> _function_3 = new Function1<ExpressionVariable, Boolean>() {
        public Boolean apply(final ExpressionVariable it) {
          String _fullName = it.fullName();
          boolean _contains = inputSchemaNames.contains(_fullName);
          return Boolean.valueOf((!_contains));
        }
      };
      final Iterable<ExpressionVariable> functions = IterableExtensions.<ExpressionVariable>filter(_filter_2, _function_3);
      _xblockexpression = this.listUnionCalculator.<Variable>uniqueUnion(attributes, functions);
    }
    return _xblockexpression;
  }
}
