package ingraph.relalg2tex.converters.operatorconverters

import relalg.AbstractJoinOperator
import relalg.AntiJoinOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.UnionOperator
import ingraph.relalg2tex.config.RelalgConverterConfig

class BinaryOperatorConverter {

	RelalgConverterConfig config

	extension ingraph.relalg2tex.converters.elementconverters.StringEscaper stringEscaper = new ingraph.relalg2tex.converters.elementconverters.StringEscaper

	new(RelalgConverterConfig config) {
		this.config = config
	}

	def dispatch convertBinaryOperator(AbstractJoinOperator operator) {
		'''«operator.joinOperator»''' +
		'''«IF config.includeCommonVariables»\{«operator.commonVariables.map['''\var{«escapedName»}'''].join(", ")»\}«ENDIF»'''
	}

	def dispatch convertBinaryOperator(UnionOperator operator) {
		'''union'''
	}

	def dispatch joinOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch joinOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	def dispatch joinOperator(LeftOuterJoinOperator operator) {
		'''leftouterjoin'''
	}

}
