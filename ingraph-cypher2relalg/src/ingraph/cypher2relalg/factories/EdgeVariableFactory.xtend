package ingraph.cypher2relalg.factories

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.EdgeVariable

class EdgeVariableFactory extends ElementFactory {

	var n = 1;
	@Accessors val edgeVariables = new HashMap<String, EdgeVariable>

	def createEdgeVariable(String edgeVariableName) {
		val variableName = edgeVariableName ?: generateEdgeName

		if (edgeVariables.get(variableName) == null) {
			val variable = createEdgeVariable => [name = variableName]
			edgeVariables.put(variableName, variable)
		}

		edgeVariables.get(variableName)
	}

	def generateEdgeName() {
		'_e' + n++;
	}

}
