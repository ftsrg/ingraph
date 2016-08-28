package ingraph.cypher2relalg.factories

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.RelalgFactory
import relalg.NamedElement

abstract class ElementFactory<TNamedElement extends NamedElement> {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE

	var n = 1;
	@Accessors val elements = new HashMap<String, TNamedElement>

	def createElement(String elementName) {
		val variableName = elementName ?: generateName

		if (elements.get(variableName) == null) {
			val variable = createSpecificNamedElement => [name = variableName]
			elements.put(variableName, variable as TNamedElement)
		}

		elements.get(variableName)
	}

	def generateName() {
		'_e' + n++;
	}

	def TNamedElement createSpecificNamedElement()
}
