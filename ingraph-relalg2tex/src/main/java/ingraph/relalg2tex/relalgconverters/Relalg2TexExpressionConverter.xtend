package ingraph.relalg2tex.relalgconverters

import relalg.BinaryOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.TernaryOperator
import relalg.UnaryOperator
import ingraph.relalg2tex.config.RelalgConverterConfig

class Relalg2TexExpressionConverter extends AbstractRelalg2TexConverter {

	new() {
		super()
	}

	new(RelalgConverterConfig config) {
		super(config)
	}

	override convertBody(Operator expression) {
		'''
			«IF config.standaloneDocument»$$«ENDIF»
			«children(expression)»
			«IF config.standaloneDocument»$$«ENDIF»
		'''
	}

	/**
	 * children
	 * 
	 * we add newlines intentionally to allow the autobreak package
	 * to break the expressions to multiple lines
	 */
	def dispatch CharSequence children(NullaryOperator op) {
		'''«op.operator»
		'''
	}

	def dispatch CharSequence children(UnaryOperator op) {
		'''«op.operator»
		\Big(«op.input.children»\Big)
		'''
	}

	def dispatch CharSequence children(BinaryOperator op) {
		'''«op.leftInput.children»
		«op.operator»
		«op.rightInput.children»
		'''
	}
	
	def dispatch CharSequence children(TernaryOperator op) {
		'''«op.leftInput.children»
		«op.operator»_{«op.middleInput.children»}
		«op.rightInput.children»
		'''
	}

}
