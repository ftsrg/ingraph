package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import relalg.AbstractJoinOperator
import relalg.AntiJoinOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.TransitiveClosureJoinOperator
import relalg.UnionOperator

class BinaryOperatorConverter {

	val RelalgConverterConfig config

	extension StringEscaper stringEscaper = new StringEscaper
	extension VariableNameConverter variableNameConverter = new VariableNameConverter

	new(RelalgConverterConfig config) {
		this.config = config
	}

	def dispatch convertBinaryOperator(AbstractJoinOperator op) {
		'''«op.joinOperator»''' +
		'''«IF config.includeCommonVariables»\{«op.commonVariables.map['''\var{«escapedName»}'''].join(", ")»\}«ENDIF»'''
	}

	def dispatch convertBinaryOperator(UnionOperator op) {
		'''union'''
	}

	def dispatch joinOperator(JoinOperator op) {
		'''join'''
	}

	def dispatch joinOperator(TransitiveClosureJoinOperator op) {
		'''transitivejoin_{«op.edgeListVariable.convertVariable»}'''
	}

	def dispatch joinOperator(AntiJoinOperator op) {
		'''antijoin'''
	}

	def dispatch joinOperator(LeftOuterJoinOperator op) {
		'''leftouterjoin'''
	}

}
