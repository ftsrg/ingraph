package ingraph.cypher2relalg.factories

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.VertexVariable

class VertexVariableFactory extends ElementFactory {

	var n = 1;
	@Accessors val vertexVariables = new HashMap<String, VertexVariable>

	def createVertexVariable(String vertexVariableName) {
		val variableName = vertexVariableName ?: generateVertexName

		if (vertexVariables.get(variableName) == null) {
			val variable = createVertexVariable => [name = variableName]
			vertexVariables.put(variableName, variable)
		}

		vertexVariables.get(variableName)
	}

	def generateVertexName() {
		'_v' + n++;
	}

}
