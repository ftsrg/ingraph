package ingraph.cypher2relalg.factories

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.NamedElement
import relalg.RelalgFactory
import relalg.RelalgContainer

abstract class NamedElementFactory<TNamedElement extends NamedElement> {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE
	protected final RelalgContainer container
	
	new(RelalgContainer container) {
		this.container = container	
	} 

	var n = 1;
	@Accessors val elements = new HashMap<String, TNamedElement>

	def createElement(String elementName) {
		val variableName = elementName ?: generateName

		if (elements.get(variableName) == null) {
			val variable = createSpecificNamedElement => [
				name = variableName
				it.container = this.container
			]
			elements.put(variableName, variable)
		}

		val element = elements.get(variableName)
		//container.elements.add(element)

		return element
	}

	def hasElement(String elementName) {
		return (elements.get(elementName) != null)
	}

	def generateName() {
		'_e' + n++;
	}

	def TNamedElement createSpecificNamedElement()
}
