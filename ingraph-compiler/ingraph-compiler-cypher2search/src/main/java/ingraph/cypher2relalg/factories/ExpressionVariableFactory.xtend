package ingraph.cypher2relalg.factories

import relalg.Expression
import relalg.ExpressionVariable
import relalg.RelalgContainer

class ExpressionVariableFactory extends VariableFactory<ExpressionVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		modelFactory.createExpressionVariable
	}

	def createElement(String name, Expression expression) {
		if (name !== null) {
			createElement(name) => [
				it.expression = expression
			]
		} else {
			createDontCareElement(expression) => [
				it.expression = expression
			]
		}
	}
}
