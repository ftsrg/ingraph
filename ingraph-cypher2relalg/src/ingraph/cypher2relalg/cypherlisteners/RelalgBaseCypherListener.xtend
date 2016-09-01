package ingraph.cypher2relalg.cypherlisteners

import java.util.Iterator
import java.util.List
import relalg.AlgebraExpression
import relalg.BetaOperator
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable

class RelalgBaseCypherListener extends RelalgBaseUnsupportedCypherListener {

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

	/**
	 * Chain expand operators together and add sourceVertexVariables
	 */
	def chainExpandOperators(GetVerticesOperator gvo, List<ExpandOperator> expandList) {
		var lastVertexVariable = gvo.vertexVariable
		var AlgebraExpression lastAlgebraExpression = gvo

		for (ExpandOperator op : expandList) {
			op.sourceVertexVariable = lastVertexVariable
			op.input = lastAlgebraExpression

			lastVertexVariable = op.targetVertexVariable
			lastAlgebraExpression = op
		}

		lastAlgebraExpression
	}
}
