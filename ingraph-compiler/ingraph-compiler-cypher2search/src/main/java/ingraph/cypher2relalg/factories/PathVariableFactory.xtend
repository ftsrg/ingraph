package ingraph.cypher2relalg.factories

import relalg.PathVariable
import relalg.RelalgContainer

class PathVariableFactory extends VariableFactory<PathVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		modelFactory.createPathVariable => [
		]
	}
}
