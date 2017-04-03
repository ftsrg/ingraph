package ingraph.relalg2tex.converters.relalgconverters

import ingraph.relalg.util.ContainerExtractor
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.elementconverters.SchemaConverter
import ingraph.relalg2tex.converters.elementconverters.TupleConverter
import relalg.AbstractJoinOperator
import relalg.BeamerOperator
import relalg.BinaryOperator
import relalg.Cardinality
import relalg.NullaryOperator
import relalg.Operator
import relalg.TernaryOperator
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
		'''
		[
			{«op.operator»
			«IF op.extractContainer.basicSchemaInferred»
			\\ \footnotesize
			$\color{gray} «op.basicSchema.convertSchema» $
			«ENDIF»
			«IF op.extractContainer.isExtraVariablesInferred»
			\\ \footnotesize
			$\color{violet} «op.extraVariables.convertSchema» $
			«ENDIF»
			«IF op.extractContainer.fullSchemaInferred»
			\\ \footnotesize
			«IF op instanceof BeamerOperator»
			$\color{orange} «op.fullSchema.convertSchemaWithIndices(op.tupleIndices)»$
			«ELSE»
			$\color{orange} «op.fullSchema.convertSchemaWithIndices»$
			«ENDIF»
			«ENDIF»
			«IF op instanceof AbstractJoinOperator && op.extractContainer.fullSchemaInferred && config.includeCommonVariables»
			\\ \footnotesize
			$\color{orange}
			\langle \var{«(op as AbstractJoinOperator).leftMask.join(", ")»} \rangle :
			\langle \var{«(op as AbstractJoinOperator).rightMask.join(", ")»} \rangle$
			«ENDIF»
			«IF config.includeCardinality && op.cardinality !== null»
			\\ \footnotesize \# «op.cardinality.formatCardinality»
			«ENDIF»
			}«op?.children»«IF op instanceof NullaryOperator»,tier=input,for tree={blue,densely dashed}«ENDIF»
		]
		'''
	}

	/**
	 * children
	 */
	def dispatch children(NullaryOperator op) {
		''''''
	}

	def dispatch children(UnaryOperator op) {
		'''
		
			«op.input?.toNode»
		'''
	}

	def dispatch children(BinaryOperator op) {
		'''
		
			«op.leftInput.toNode»
			«op.rightInput.toNode»
		'''
	}
	
	def dispatch children(TernaryOperator op) {
		'''
		
			«op.leftInput.toNode»
			«op.middleInput.toNode»
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
		'''$«op?.convertOperator.join('''$\\$''')»$'''
	}

}
