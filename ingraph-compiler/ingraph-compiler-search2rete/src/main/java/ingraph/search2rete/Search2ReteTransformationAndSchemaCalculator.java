package ingraph.search2rete;

import ingraph.optimization.transformations.SimplifyingTransformation;
import ingraph.relalg.calculators.OneStepSchemaCalculator;
import ingraph.search2rete.Search2ReteTransformation;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class Search2ReteTransformationAndSchemaCalculator {
  private final OneStepSchemaCalculator oneStepSchemaCalculator = new OneStepSchemaCalculator();
  
  public RelalgContainer apply(final RelalgContainer searchPlan) {
    final SimplifyingTransformation simplifyingTransformation = new SimplifyingTransformation(searchPlan);
    final RelalgContainer simplifiedPlan = simplifyingTransformation.simplify();
    final Search2ReteTransformation search2rete = new Search2ReteTransformation(simplifiedPlan);
    final RelalgContainer retePlan = search2rete.transformToRete();
    final RelalgContainer retePlanWithSchema = this.oneStepSchemaCalculator.calculateSchema(retePlan);
    return retePlanWithSchema;
  }
}
