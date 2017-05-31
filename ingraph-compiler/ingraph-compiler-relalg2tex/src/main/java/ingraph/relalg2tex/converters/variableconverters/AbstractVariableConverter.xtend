package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg.expressions.ExpressionUnwrapper
import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import ingraph.relalg2tex.converters.elementconverters.MiscConverters
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.AttributeVariable
import relalg.EdgeListVariable
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.ListVariable
import relalg.PropertyList
import relalg.PropertyListEntry

abstract class AbstractVariableConverter {

	protected extension StringEscaper stringEscaper = new StringEscaper
	extension MiscConverters miscConverters = new MiscConverters
	extension ExpressionConverter expressionConverter = new ExpressionConverter
	
	def dispatch convertVariable(ElementVariable variable) {
		'''\var{«variable.escapedName»} «IF variable.properties !== null»«variable.properties.convertProperties»«ENDIF»'''
	}
	
	def dispatch convertVariable(EdgeListVariable variable) {
		'''\var{[«variable.escapedName»]}_{«variable.minHops»}^{«variable.maxHops.hopsToString»}'''
	}

	def dispatch convertVariable(AttributeVariable variable) {
		'''\var{«variable.baseVariable.escapedName».«variable.escapedName»}'''
	}

	def dispatch convertVariable(ListVariable variable) {
		'''\var{«variable.escapedName»}'''
	}

	// convert properties and property list entries
	def convertProperties(PropertyList propertyList) {
		if (propertyList.entries.isEmpty) {
			""
		} else {
			'''_{\{ «propertyList.entries.map[convertPropertyListEntry].join(", ")» \}}'''
		}
	}

	def convertPropertyListEntry(PropertyListEntry entry) {
		'''\atom{«entry.key»}: «entry.value.convertExpression»'''
	}

	def unwrap(ExpressionVariable ev) {
		ExpressionUnwrapper.extractExpressionVariable(ev)
	}

}
