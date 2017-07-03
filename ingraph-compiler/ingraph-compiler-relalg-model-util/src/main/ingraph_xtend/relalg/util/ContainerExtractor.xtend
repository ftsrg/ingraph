package ingraph.relalg.util

import relalg.Operator
import relalg.RelalgContainer

class ContainerExtractor {
	
	def dispatch RelalgContainer extractContainer(RelalgContainer container) {
		return container
	}

	def dispatch RelalgContainer extractContainer(Operator op) {
		return extractContainer(op.eContainer)
	}

}