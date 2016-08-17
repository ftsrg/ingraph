package relalg

abstract class TexSerializer {
	
	/***
	 * Whether to generate a full TeX document
	 */
	protected boolean full
	
	new(boolean full) {
		this.full = full
	}
	
	
	def serialize(AlgebraExpression expression) {
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
	
	def abstract CharSequence serializeBody(AlgebraExpression expression)
	
}