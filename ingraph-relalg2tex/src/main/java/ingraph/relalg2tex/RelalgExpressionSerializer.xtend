package ingraph.relalg2tex

import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.UnaryOperator
import relalg.BinaryOperator

class RelalgExpressionSerializer extends AbstractRelalgSerializer {

	new() {
		super()
	}

	new(RelalgSerializerConfig config) {
		super(config)
	}

	override serializeBody(Operator expression) {
		'''
			«IF config.standaloneDocument»$$«ENDIF»
			«children(expression)»
			«IF config.standaloneDocument»$$«ENDIF»
		'''
	}

	/**
	 * children
	 */
	def dispatch CharSequence children(GetVerticesOperator op) {
		'''«op.operator»'''
	}

	def dispatch CharSequence children(GetEdgesOperator op) {
		'''«op.operator»'''
	}

	def dispatch CharSequence children(UnaryOperator op) {
		'''«op.operator» \Big(«op.input.children»\Big)'''
	}

	def dispatch CharSequence children(BinaryOperator op) {
		'''«op.leftInput.children» «op.operator» «op.rightInput.children»'''
	}

}
