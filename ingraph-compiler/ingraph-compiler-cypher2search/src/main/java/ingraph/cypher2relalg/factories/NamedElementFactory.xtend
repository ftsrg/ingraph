package ingraph.cypher2relalg.factories

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.NamedElement
import relalg.RelalgContainer
import relalg.RelalgFactory

abstract class NamedElementFactory<TNamedElement extends NamedElement> {

	protected final RelalgFactory modelFactory = RelalgFactory.eINSTANCE
	protected final RelalgContainer container

	new(RelalgContainer container) {
		this.container = container
	}

	// ensure that we generate unique names in the compiler
	private static var n = 1;
	@Accessors(PUBLIC_GETTER)
	val elements = new HashMap<String, TNamedElement>

	def createElement(String elementName) {
		val variableName = elementName ?: generateName

		if (elements.get(variableName) === null) {
			val variable = createSpecificNamedElement => [
				name = variableName
				it.namedElementContainer = this.container
			]
			elements.put(variableName, variable)
		}

		elements.get(variableName)
	}

	/**
	 * Indicates if this factory is aware of the element by its name,
	 * i.e. the element has ever been created by this factory instance.
	 */
	def hasElement(String elementName) {
		return (elements.get(elementName) !== null)
	}

	/**
	 * Returns the element by name this factory is aware of it,
	 * i.e. the element has ever been created by this factory instance.
	 *
	 * Otherwise returns null.
	 */
	def TNamedElement getElement(String elementName) {
		return elements.get(elementName)
	}

	def generateName() {
		'_e' + n++;
	}

	def TNamedElement createSpecificNamedElement()
}
