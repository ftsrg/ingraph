package ingraph.relalg2tex.converters.relalgconverters

import ingraph.relalg.util.ContainerExtractor
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.elementconverters.SchemaConverter
import relalg.AbstractJoinOperator
import relalg.BeamerOperator
import relalg.BinaryOperator
import relalg.Cardinality
import relalg.NullaryOperator
import relalg.Operator
import relalg.UnaryOperator

class Relalg2TexTreeConverter extends AbstractRelalg2TexConverter {

	extension SchemaConverter schemaConverter = new SchemaConverter(config.schemaIndices)
	extension ContainerExtractor containerExtractor = new ContainerExtractor

	new() {
		super()
	}

	new(RelalgConverterConfig config) {
		super(config)
	}

	override convertBody(Operator expression) {
		'''
			«IF config.textualOperators»\toggletrue{textualoperators}«ELSE»\togglefalse{textualoperators}«ENDIF»
			\begin{forest} for tree={align=center}
			«toNode(expression)»
			;
			\end{forest}
		'''
	}

	/**
	 * toNode
	 */
	def CharSequence toNode(Operator op) {
		val start =
				'''[
				{«op.operator»
				«IF !config.omitSchema»
					«IF op.extractContainer.isExternalSchemaInferred»
					\\ \footnotesize
					$\color{externalschemacolor} «op.externalSchema.convertSchema» $
					«ENDIF»
					«IF op.extractContainer.isExtraVariablesInferred»
					\\ \footnotesize
					$\color{extravariablescolor} «op.extraVariables.convertSchema» $
					«ENDIF»
					«IF op.extractContainer.isInternalSchemaInferred»
					\\ \footnotesize
						«IF op instanceof BeamerOperator»
						$\color{internalschemacolor} «op.internalSchema.convertSchemaWithIndices(op.tupleIndices)»$
						«ELSE»
						$\color{internalschemacolor} «op.internalSchema.convertSchemaWithIndices»$
						«ENDIF»
					«ENDIF»
				«ENDIF»
				«IF op instanceof AbstractJoinOperator && op.extractContainer.isInternalSchemaInferred && config.includeCommonVariables»
				\\ \footnotesize
				$\color{orange}
				\langle \var{«(op as AbstractJoinOperator).leftMask.join(", ")»} \rangle :
				\langle \var{«(op as AbstractJoinOperator).rightMask.join(", ")»} \rangle$
				«ENDIF»
				«IF config.includeCardinality && op.cardinality !== null»
				\\ \footnotesize \# «op.cardinality.formatCardinality»
				«ENDIF»
				}'''

		val end = '''«IF op instanceof NullaryOperator»,tier=input,for tree={nullarynodecolor,densely dashed}«ENDIF»]
		'''

		'''«IF op.includeOperator»«start»«ENDIF»«op?.children»«IF op.includeOperator»«end»«ENDIF»'''
	}

	/**
	 * children
	 */
	def dispatch children(NullaryOperator op) {
		''''''
	}

	def dispatch children(UnaryOperator op) {
		'''
			«op.input?.toNode»'''
	}

	def dispatch children(BinaryOperator op) {
		'''
			«op.leftInput.toNode»
			«op.rightInput.toNode»
		'''
	}

	def formatCardinality(Cardinality cardinality) {
		return String.format("%.02f", cardinality.value)
	}

	/**
	 * operator
	 */
	override operator(Operator op) {
		'''$«op?.convertOperator»$'''
	}

}
