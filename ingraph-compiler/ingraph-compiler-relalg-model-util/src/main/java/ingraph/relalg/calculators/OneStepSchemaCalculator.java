package ingraph.relalg.calculators;

import ingraph.relalg.calculators.ExternalSchemaCalculator;
import ingraph.relalg.calculators.ExtraVariablesCalculator;
import ingraph.relalg.calculators.InternalSchemaCalculator;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.RelalgContainer;

/**
 * Executes the ExternalSchemaCalculator, the ExtraVariablesCalculator and the InternalSchemaCalculator
 * on a relational algebra expression.
 */
@SuppressWarnings("all")
public class OneStepSchemaCalculator {
  @Extension
  private ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator();
  
  @Extension
  private ExtraVariablesCalculator extraVariablesCalculator = new ExtraVariablesCalculator();
  
  @Extension
  private InternalSchemaCalculator internalSchemaCalculator = new InternalSchemaCalculator();
  
  public RelalgContainer calculateSchema(final RelalgContainer container) {
    RelalgContainer _xblockexpression = null;
    {
      this.externalSchemaCalculator.calculateExternalSchema(container);
      this.extraVariablesCalculator.calculateExtraVariables(container);
      _xblockexpression = this.internalSchemaCalculator.calculateInternalSchema(container);
    }
    return _xblockexpression;
  }
}
