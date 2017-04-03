package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import java.util.List
import relalg.Variable
import java.util.ArrayList

class SchemaConverter {
 
	extension VariableNameConverter variableNameConverter = new VariableNameConverter

	boolean schemaIndices

	new(boolean schemaIndices) {
		this.schemaIndices = schemaIndices
	}

	def convertSchema(List<Variable> schema) {
		val list = new ArrayList(schema.size)
		schema.forEach[ element, index |
			list.add('''«IF schemaIndices»{}_{[«index»]} «ENDIF»\var{«element.convertVariable»}''')
		]
		'''\langle «list.join(', ')» \rangle'''
	} 
	
}
