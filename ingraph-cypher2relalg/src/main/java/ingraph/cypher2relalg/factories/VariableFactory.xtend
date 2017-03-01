package ingraph.cypher2relalg.factories

import relalg.Variable
import relalg.RelalgContainer
import java.util.HashMap
import org.eclipse.emf.ecore.EObject

abstract class VariableFactory<TVariable extends Variable> extends NamedElementFactory<TVariable> {

	/*
	 * this will be used to lookup names generated for dontCare variables.
	 *
	 * It is useful whenever a single node of the parse graph need to be processed consistently twice.
	 * EMF guarantees that .equals() holds iff == holds on two EObject instances.
	 */
	val dontCareElementNames = new HashMap<EObject, String>

	new(RelalgContainer container) {
		super(container)
	}

	override createElement(String elementName) {
		val e = super.createElement(elementName)
		if (elementName == null) {
			e.dontCare = true
		}
		return e
	}

	def createDontCareElement(EObject eo) {
		if (dontCareElementNames.get(eo) == null) {
			dontCareElementNames.put(eo, generateName)
		}
		createElement(dontCareElementNames.get(eo)) => [
			dontCare = true
		]
	}
}
