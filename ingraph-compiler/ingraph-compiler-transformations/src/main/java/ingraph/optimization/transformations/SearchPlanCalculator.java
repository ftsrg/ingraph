package ingraph.optimization.transformations;

import ingraph.optimization.transformations.SimplifyingTransformation;
import ingraph.relalg.calculators.ExternalSchemaCalculator;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class SearchPlanCalculator {
  private final ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator();
  
  public RelalgContainer apply(final RelalgContainer searchPlan) {
    final SimplifyingTransformation simplifyingTransformation = new SimplifyingTransformation(searchPlan);
    final RelalgContainer simplifiedPlan = simplifyingTransformation.simplify();
    final RelalgContainer simplifiedPlanWithSchema = this.externalSchemaCalculator.calculateExternalSchema(simplifiedPlan);
    return simplifiedPlanWithSchema;
  }
}
