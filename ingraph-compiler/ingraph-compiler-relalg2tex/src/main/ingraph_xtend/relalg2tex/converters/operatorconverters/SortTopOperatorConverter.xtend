package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import ingraph.relalg2tex.converters.elementconverters.MiscConverters
import relalg.SortOperator
import relalg.TopOperator

class SortTopOperatorConverter {

	extension MiscConverters miscConverters = new MiscConverters
	extension ExpressionConverter expressionConverter = new ExpressionConverter

	def String topOperatorToTex(TopOperator op) {
		'''\topp{«if (op.limit !== null) op.limit.convertExpression else ""»}{«if (op.skip !== null) op.skip.convertExpression else ""»}'''
	}

	def String sortOperatorToTex(SortOperator op) {
		'''\sort{«op.entries.map[sortEntryToTex].join(", ")»}'''
	}

}
