package ingraph.cypher2relalg.util

import ingraph.logger.IngraphLogger
import java.util.ArrayList
import java.util.HashSet
import java.util.Iterator
import java.util.LinkedList
import java.util.List
import java.util.Set
import relalg.AbstractEdgeVariable
import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticOperationExpression
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperatorType
import relalg.BinaryOperator
import relalg.DuplicateEliminationOperator
import relalg.EmptyListExpression
import relalg.ExpandOperator
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GraphObjectVariable
import relalg.GroupingOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.ListExpression
import relalg.Literal
import relalg.LogicalExpression
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.SelectionOperator
import relalg.SortOperator
import relalg.TopOperator
import relalg.UnaryArithmeticOperationExpression
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.VariableExpression

class Cypher2RelalgUtil {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension IngraphLogger logger

	new(IngraphLogger logger) {
		this.logger=logger
	}

	def Operator buildLeftDeepTree(Class<? extends BinaryOperator> binaryOperatorType,
		Iterator<Operator> i) {
		var Operator retVal = null

		// build a left deep tree of Joins from the match clauses
		if (i != null && i.hasNext) {
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

	def LogicalExpression buildLeftDeepTree(BinaryLogicalOperatorType binaryLogicalOperator,
		Iterator<? extends LogicalExpression> i, RelalgContainer outerContainer) {
		var LogicalExpression retVal = null

		// build a left deep tree of logical expressions with AND/OR
		if (i != null && i.hasNext) {
			for (retVal = i.next; i.hasNext;) {
				val nextAE = createBinaryLogicalExpression => [
					operator = binaryLogicalOperator
					expressionContainer = outerContainer
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
	 * head is put on the leftInput for the 1st element of the tail list,
	 * which in turn will be put on the leftInput on the 2nd element of the tail list
	 * and so on.
	 */
	def chainBinaryOperatorsLeft(Operator head, Iterable<? extends BinaryOperator> tail) {
		var lastAlgebraExpression = head

		for (BinaryOperator op : tail) {
			op.leftInput = lastAlgebraExpression
			lastAlgebraExpression = op
		}

		lastAlgebraExpression
	}

	/**
	 * Finds the variables in expression e that are outside of grouping functions
	 * and appends them to the groupingVariables set.
	 *
	 * This is not multithreaded-safe, so don't put it in map, forEach etc. call.
	 *
	 * @param e the expression to process
	 * @param groupingVariables set holding the grouping variables. This set will be extended in-place.
	 * @param seenAggregate indicates if previously we have seen any aggregate function
	 *
	 * @return boolean value indicating if there were an aggregate function. It can be expressed as: "seenAggregate || (e contains aggregate function call)"
	 */
	def accumulateGroupingVariables(Expression e, Set<Variable> groupingVariables, boolean seenAggregate) {
		var effectivelySeenAggregate = seenAggregate
		val fifo = new LinkedList<Expression>

		fifo.add(e)
		while (!fifo.empty) {
			val el = fifo.removeFirst
			switch (el) {
				VariableExpression: {
					switch (myVar: el.variable) {
						GraphObjectVariable: groupingVariables.add(myVar)
						// Do not check the content of myVar.expression, else it will introduce unnecessary aggregations.
						// See the following query for an example:
						// MATCH (tag)<--(message) WITH tag, count(message) AS countMessage RETURN tag, countMessage
//						ExpressionVariable: fifo.add(myVar.expression)
					}
				}
				ArithmeticOperationExpression: {
					fifo.add(el.leftOperand)
					fifo.add(el.rightOperand)
				}
				UnaryArithmeticOperationExpression: {
					fifo.add(el.operand)
				}
				Literal: {}
				FunctionExpression: {
					if (el.functor.isAggregation) {
						effectivelySeenAggregate = true
					} else {
						fifo.addAll(el.arguments)
					}
				}
				EmptyListExpression: {}
				ListExpression: {
					fifo.add(el.head)
					fifo.add(el.tail)
				}
				BinaryLogicalExpression: {
					fifo.add(el.leftOperand)
					fifo.add(el.rightOperand)
				}
				ArithmeticComparisonExpression: {
					fifo.add(el.leftOperand)
					fifo.add(el.rightOperand)
				}
				default: {
					unsupported('''Unexpected, yet unsupported expression type found while enumerating grouping variables, got «el.class.name»''')
				}
			}
		}

		effectivelySeenAggregate
	}

	/**
	 * Finds edge variables in the given relalg tree.
	 *
	 * @param op the operator tree to process
	 *
	 * @return set of edge variables found in the relalg tree
	 */
	def Set<AbstractEdgeVariable> extractEdgeVariables(Operator op) {
		val fifo = new LinkedList<Operator>
		val edgeVariables = new HashSet<AbstractEdgeVariable>

		fifo.add(op)
		while (!fifo.empty) {
			val el = fifo.removeFirst
			switch (el) {
				ExpandOperator: {
					edgeVariables.add(el.edgeVariable)
					fifo.add(el.input)
				}
				BinaryOperator: {
					fifo.add(el.leftInput)
					fifo.add(el.rightInput)
				}
				UnaryOperator: {
					fifo.add(el.input)
				}
				GetEdgesOperator: {
					edgeVariables.add(el.edgeVariable)
				}
				NullaryOperator: {
				}
				default: {
					unsupported('''Unexpected, yet unsupported expression type found while enumerating grouping variables, got «el.class.name»''')
				}
			}
		}

		edgeVariables
	}

	/**
	 * Inject the given Left Outer Join operator just above the content
	 * into operator tree. Content will become the rightInput of the left outer join operator.
	 * @param op A operator tree of the form
	 *        SelectionOperator? TopOperator? SortOperator? DuplicateEliminationOperator? ProjectionOperator GroupingOperator? content
	 * @param lojo A Left Outer Join operator instance to be injected.
	 */
	def validateAndInjectLOJO(Operator op, LeftOuterJoinOperator lojo) {
		// we find the parent node of content and also validate the sequence in a pass
		var UnaryOperator parent = null
		var currentOperator = op
		val unaryOperatorTreeSeen = new ArrayList<String>
		val pattern = #[
		  new UnaryOperator0or1Pattern(SelectionOperator, true)
		, new UnaryOperator0or1Pattern(TopOperator, true)
		, new UnaryOperator0or1Pattern(SortOperator, true)
		, new UnaryOperator0or1Pattern(DuplicateEliminationOperator, true)
		, new UnaryOperator0or1Pattern(ProjectionOperator, false)
		, new UnaryOperator0or1Pattern(GroupingOperator, true)
		]
		for (p: pattern) {
			if (p.opc.isInstance(currentOperator)) {
				if (currentOperator instanceof UnaryOperator) {
					unaryOperatorTreeSeen.add(currentOperator.class.name)
					parent = currentOperator
					currentOperator = currentOperator.input
				} else {
					unrecoverableError('''Static error in the compiler!'''
													 +''' We support a chain of «UnaryOperator.name» for pattern matching.'''
													 +''' So far we have seen a chain of «unaryOperatorTreeSeen.join(',')», and now we reached «currentOperator.class.name».'''
													 +''' This does not match what we expected: «pattern.join(' ')»'''
														)
				}
			} else if (p.mandatory) {
				unrecoverableError('''So far we have seen a chain of «unaryOperatorTreeSeen.join(',')», and now we reached «currentOperator.class.name».'''
												 +''' This does not match what we expected: «pattern.join(' ')»'''
													)
			}
		}
		if (parent === null) {
			unrecoverableError('''This should never happen: after validation, we found parent to be null, but reached this point...''')
		} else {
			lojo.rightInput = parent.input
			parent.input = lojo
		}
		op
	}
}
