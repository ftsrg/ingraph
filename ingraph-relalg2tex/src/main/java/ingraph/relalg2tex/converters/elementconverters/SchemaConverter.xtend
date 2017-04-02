package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import java.util.List
import relalg.Variable

class SchemaConverter {
 
	extension VariableNameConverter variableNameConverter = new VariableNameConverter
	extension StringEscaper stringEscaper = new StringEscaper

	def convertSchema(List<Variable> schema) {
		'''\langle «schema.map[ '''\var{«convertVariable.escape»}''' ].join(', ')» \rangle'''
	} 
	
}
