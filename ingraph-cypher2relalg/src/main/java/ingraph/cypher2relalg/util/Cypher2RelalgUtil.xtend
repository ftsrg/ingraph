package ingraph.cypher2relalg.util

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import java.util.ArrayList
import java.util.Iterator
import java.util.List
import org.eclipse.emf.common.util.EList
import relalg.BinaryLogicalOperator
import relalg.BinaryOperator
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.LabelSetStatus
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnionOperator
import relalg.VertexLabel
import relalg.VertexVariable

class Cypher2RelalgUtil {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension IngraphLogger logger

	new(IngraphLogger logger) {
		this.logger=logger
	}

	def ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		if (vertexVariable.vertexLabelSet == null) {
			vertexVariable.vertexLabelSet = createVertexLabelSet
		}
		if (!vertexVariable.vertexLabelSet.vertexLabels.contains(label)) {
			vertexVariable.vertexLabelSet => [
				vertexLabels.add(label)
				status = LabelSetStatus.NON_EMPTY
			]
		}
	}

	def ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
		if (edgeVariable.edgeLabelSet == null) {
			edgeVariable.edgeLabelSet = createEdgeLabelSet
		}
		if (!edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
			edgeVariable.edgeLabelSet => [
				edgeLabels.add(label) // TODO this is not correct, see #10
				status = LabelSetStatus.NON_EMPTY
			]
		}
	}

	def void combineLabelSet(EdgeVariable edgeVariable, EList<String> labels, EdgeLabelFactory edgeLabelFactory) {
		/*
		 *  if we receive an empty labelset, this does not change the labelset constraint
		 * if the edge variable
		 */
		if (labels == null || labels.empty) {
			return
		}

		if (edgeVariable.edgeLabelSet == null) {
			// no previous labelset was in effect
			edgeVariable.edgeLabelSet = createEdgeLabelSet
			labels.forEach [
				val label = edgeLabelFactory.createElement(it)
				if (!edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
					edgeVariable.edgeLabelSet.edgeLabels.add(label)
				}
			]
			edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
					LabelSetStatus.EMPTY
				} else {
					LabelSetStatus.NON_EMPTY
				}
		} else {
			// we had a previous labelset
			// we combine (intersect) the labelset received with the previous one
			val List<EdgeLabel> intersection = new ArrayList<EdgeLabel>

			labels.forEach [
				val label = edgeLabelFactory.createElement(it)
				if (!intersection.contains(label) && edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
					intersection.add(label)
				}
			]

			/*
			 * a tiny optimization: if a set has the same number of element
			 * before and after intersecting with an other set, it is the same.
			 *
			 * So we need to replace labelset only if their size changed
			 */

			if (edgeVariable.edgeLabelSet.edgeLabels.size != intersection.size) {
				edgeVariable.edgeLabelSet.edgeLabels.clear
				edgeVariable.edgeLabelSet.edgeLabels.addAll(intersection)
			}

			edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
					warning('''Contradicting labelset constraints found for edge variable «edgeVariable.name»''')
					LabelSetStatus.CONTRADICTING
				} else {
					LabelSetStatus.NON_EMPTY
				}

		}
	}


	def Operator buildLeftDeepTree(Class<? extends BinaryOperator> binaryOperatorType,
		Iterator<Operator> i) {
		var Operator retVal = null

		// build a left deep tree of Joins from the match clauses
		if (i?.hasNext) {
			for (retVal = i.next; i.hasNext;) {
				val nextAE = switch (binaryOperatorType) {
					case typeof(JoinOperator): createJoinOperator
					case typeof(UnionOperator): createUnionOperator
					case typeof(LeftOuterJoinOperator): createLeftOuterJoinOperator
					default: {
						unsupported('''Got unexpected BinaryOperator type «binaryOperatorType.name» to build left-deep-tree''')
						null
					}
				}
				nextAE.rightInput = i.next
				nextAE.leftInput = retVal
				retVal = nextAE
			}
		}

		return retVal
	}

	def LogicalExpression buildLeftDeepTree(BinaryLogicalOperator binaryLogicalOperator,
		Iterator<? extends LogicalExpression> i, RelalgContainer outerContainer) {
		var LogicalExpression retVal = null

		// build a left deep tree of logical expressions with AND/OR
		if (i?.hasNext) {
			for (retVal = i.next; i.hasNext;) {
				val nextAE = createBinaryLogicalExpression => [
					operator = binaryLogicalOperator
					container = outerContainer
				]
				nextAE.rightOperand = i.next
				nextAE.leftOperand = retVal
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
		var Operator lastAlgebraExpression = gvo

		for (ExpandOperator op : expandList) {
			op.sourceVertexVariable = lastVertexVariable
			op.input = lastAlgebraExpression

			lastVertexVariable = op.targetVertexVariable
			lastAlgebraExpression = op
		}

		lastAlgebraExpression
	}

	/**
	 * Chain binary operators together to build a left deep tree.
	 *
	 * head is put on the leftInput for the 1st element of the tail list, which in turn will be put on the leftInput on the 2nd element of the tail list and so on.
	 */
	def chainBinaryOperatorsLeft(Operator head, List<? extends BinaryOperator> tail) {
		var lastAlgebraExpression = head

		for (BinaryOperator op : tail) {
			op.leftInput = lastAlgebraExpression
			lastAlgebraExpression = op
		}

		lastAlgebraExpression
	}
}
