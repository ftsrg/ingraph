package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import java.util.List
import relalg.Variable
import java.util.ArrayList
import java.util.Collection
import java.util.Collections

class SchemaConverter {
 
	extension VariableNameConverter variableNameConverter = new VariableNameConverter

	extension TupleConverter tupleConverter = new TupleConverter
	boolean schemaIndices

	new(boolean schemaIndices) {
		this.schemaIndices = schemaIndices
	}

	def convertSchema(List<Variable> schema) {
		val list = new ArrayList(schema.size)
		schema.forEach[ element, index |
			list.add('''\var{«element.convertVariable»}''')
		]
		'''\langle «list.join(', ')» \rangle'''
	}

	def convertSchemaWithIndices(List<Variable> schema) {
		convertSchemaWithIndices(schema, null)
	}

	def convertSchemaWithIndices(List<Variable> schema, List<Integer> tupleIndices) {
		val list = new ArrayList(schema.size)
		schema.forEach[ element, index |
			list.add('''
				«IF schemaIndices»{}_{«index»}^{«tupleIndices?.get(index)?.convertTupleIndex»}«ENDIF»
				\var{«element.convertVariable»}
			''')
		]
		'''\langle «list.join(', ')» \rangle'''
	} 
}
