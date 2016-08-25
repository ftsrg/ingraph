package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.ProductionOperator

abstract class TexSerializer {

	/***
	 * Whether to generate a full TeX document
	 */
	protected boolean full

	new(boolean full) {
		this.full = full
	}

	def serialize(AlgebraExpression expression) {
		if (expression instanceof ProductionOperator) {
			serialize((expression as ProductionOperator).parent)
		} else {
			'''
				«IF full»
					\documentclass{minimal}
					
					\input{relalg-packages}
					\input{relalg-commands}
					
					\begin{document}
				«ENDIF»
				«serializeBody(expression)»
				«IF full»
					\end{document}
				«ENDIF»
			'''
		}
	}

	def abstract CharSequence serializeBody(AlgebraExpression expression)

}
