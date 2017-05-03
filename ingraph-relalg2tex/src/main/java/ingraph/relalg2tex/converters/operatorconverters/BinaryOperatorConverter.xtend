package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.AbstractJoinOperator
import relalg.AntiJoinOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.TransitiveClosureJoinOperator
import relalg.UnionOperator

class BinaryOperatorConverter {

	val RelalgConverterConfig config

	extension StringEscaper stringEscaper = new StringEscaper

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

	def dispatch joinOperator(TransitiveClosureJoinOperator operator) {
		'''join\ast'''
	}

	def dispatch joinOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	def dispatch joinOperator(LeftOuterJoinOperator operator) {
		'''leftouterjoin'''
	}

}
