package ingraph.relalg.util

import relalg.Operator
import relalg.RelalgContainer

class GetContainer {
	
	def dispatch RelalgContainer getContainer(RelalgContainer container) {
		return container
	}

	def dispatch RelalgContainer getContainer(Operator op) {
		return getContainer(op.eContainer)
	}

}