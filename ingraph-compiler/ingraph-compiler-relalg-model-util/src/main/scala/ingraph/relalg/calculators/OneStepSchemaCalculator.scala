package ingraph.relalg.calculators

import ingraph.relalg.calculators.{ExternalSchemaCalculator, ExtraVariablesCalculator, InternalSchemaCalculator}
import relalg.RelalgContainer

/**
  * Executes the ExternalSchemaCalculator, the ExtraVariablesCalculator and the InternalSchemaCalculator
  * on a relational algebra expression.
  */
class OneStepSchemaCalculator {

  val externalSchemaCalculator = new ExternalSchemaCalculator
  val extraVariablesCalculator = new ExtraVariablesCalculator
  val internalSchemaCalculator = new InternalSchemaCalculator

  def calculateSchema(container: RelalgContainer): RelalgContainer = {
    externalSchemaCalculator.calculateExternalSchema(container)
    extraVariablesCalculator.calculateExtraVariables(container)
    internalSchemaCalculator.calculateInternalSchema(container)
    container
  }

}
