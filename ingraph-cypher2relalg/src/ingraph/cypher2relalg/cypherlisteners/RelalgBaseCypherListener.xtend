package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherBaseListener
import java.util.Iterator
import relalg.AlgebraExpression
import relalg.BetaOperator
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.JoinOperator
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable

class RelalgBaseCypherListener extends CypherBaseListener {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		vertexVariable.vertexLabel = label
	}

	def ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
		edgeVariable.edgeLabel = label
	}
	
	def AlgebraExpression buildLeftDeepTree(Class<? extends BetaOperator> betaOperatorType,
		Iterator<AlgebraExpression> i) {
		var AlgebraExpression retVal = null

		// build a left deep tree of Joins from the match clauses
		if (i?.hasNext) {
			for (retVal = i.next; i.hasNext;) {
				val nextAE = switch (betaOperatorType) {
					case typeof(JoinOperator): createJoinOperator
					default: throw new IllegalArgumentException(
						"Got unexpected BetaOperator type to build left-deep-tree")
				}
				nextAE.rightInput = i.next
				nextAE.leftInput = retVal
				retVal = nextAE
			}
		}

		return retVal
	}
}
