package ingraph.relalg2tex

import relalg.BinaryOperator
import relalg.Cardinality
import relalg.GetEdgesOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.UnaryOperator

class RelalgTreeSerializer extends AbstractRelalgSerializer {

    new() {
        super()
    }

    new(RelalgSerializerConfig config) {
        super(config)
    }

    override serializeBody(Operator expression) {
        '''
            \begin{forest} for tree={align=center}
            «toNode(expression)»
            ;
            \end{forest}
        '''
    }

    /**
     * toNode
     */
    def CharSequence toNode(
        Operator op) {
        '''
		«««		Optimization: an AllDifferent operator with a single edge variable is not useful at all.
«««		«IF (expression instanceof AllDifferentOperator) && (expression as AllDifferentOperator).edgeVariables.length <= 1»
«««			«toNode((expression as AllDifferentOperator).getInput)»
«««		«ELSE»
			[
			««« $ first line \\ second line $	
			{«op.operator»$
			\\
			\footnotesize
			$\color{gray} \langle \var{«op.schema.map[ name.escape ].join(', ')»} \rangle$
			«IF config.includeCardinality && op.cardinality != null» \\ \footnotesize \# «op.cardinality.formatCardinality»«ENDIF»}''' +
            '''«op?.children»''' + // invoke children
            '''
            «IF op instanceof NullaryOperator»,tier=input,for tree={blue,densely dashed}«ENDIF»
            ]
            «««		«ENDIF»
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

    def formatCardinality(Cardinality cardinality) {
        return String.format("%.02f", cardinality?.value)
    }

    /**
     * operatorToTeX
     */
    override dispatch operatorToTex(
        GetEdgesOperator op) {
        #[
            '''\getedgesi«op.sourceVertexVariable.toTexParameterWithLabels»«op.targetVertexVariable.toTexParameterWithLabels»''',
            '''\getedgesii«op.edgeVariable.toTexParameterWithLabels»'''
        ]
    }

    /**
     * operator
     */
    override operator(Operator op) {
        '''$«op?.operatorToTex.join('''$\\$''')»'''
    }

}
