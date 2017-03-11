package ingraph.relalg2tex.converters.elementconverters

import java.util.List
import relalg.Variable

class SchemaConverter {
 
	extension VariableConverter variableConverter = new VariableConverter
	extension StringEscaper stringEscaper = new StringEscaper

	def convertSchema(List<Variable> schema) {
		'''\langle \var{«schema.map[ convertVariable.escape ].join(', ')»} \rangle'''
	} 
	
}
