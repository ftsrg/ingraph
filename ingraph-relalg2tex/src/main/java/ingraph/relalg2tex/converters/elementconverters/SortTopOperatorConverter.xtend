package ingraph.relalg2tex.converters.elementconverters

import relalg.SortOperator
import relalg.TopOperator

class SortTopOperatorConverter {

	extension MiscConverters miscConverters = new MiscConverters
	extension ExpressionConverter expressionConverter = new ExpressionConverter

	def topOperatorToTex(TopOperator op) {
		'''\topp{«if (op.limit !== null) op.limit.convertExpression else ""»}{«if (op.skip !== null) op.skip.convertExpression else ""»}'''.toString
	}

	def sortOperatorToTex(SortOperator op) {
		'''\sort{«op.entries.map[sortEntryToTex].join(", ")»}'''.toString
	}

}