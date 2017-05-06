package ingraph.cypher2relalg.util

import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode
import ingraph.logger.IngraphLogger
import java.util.HashSet
import java.util.Iterator
import java.util.LinkedList
import java.util.List
import java.util.Set
import relalg.AbstractEdgeVariable
import relalg.ArithmeticComparisonExpression
import relalg.BinaryArithmeticOperationExpression
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperatorType
import relalg.BinaryOperator
import relalg.EmptyListExpression
import relalg.ExpandOperator
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GraphObjectVariable
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.ListExpression
import relalg.Literal
import relalg.LogicalExpression
import relalg.NullaryOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
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
		chainEncapsulatedBinaryOperatorsLeft(head, tail, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_TREE_ROOT)
	}

	/**
	 * Chain operators to build a left deep tree.
	 *
	 * Building a left deep tree requires binary operators, so tail should contain relalg trees that
	 * under arbitrary number of UnaryOperator's, lead to a BinaryOperator, i.e. encapsulates a BinaryOperator.
	 * These binary operators connect relalg trees together.
	 * 
	 * @param mode determines the matching mode. For possibilities, see EncapsulatedBinaryOperatorChainMode 
	 */
	def chainEncapsulatedBinaryOperatorsLeft(Operator head, Iterable<? extends Operator> tail, EncapsulatedBinaryOperatorChainMode mode) {
		var lastAlgebraExpression = head

		for (Operator op : tail) {
			val binaryOp = switch (mode) {
				case CHAIN_AT_TREE_ROOT: {
				  if (op instanceof BinaryOperator) {
				    op
				  } else {
				    unrecoverableError('''Chain mode «mode» requested on a tree having a root other than «typeof(BinaryOperator)».''')
				    null
				  }
				}
				case CHAIN_AT_FIRST_BINARY_OPERATOR: findBinaryOperator(op, false)
				case CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC: findBinaryOperator(op, true)
			}
			if (binaryOp.leftInput !== null) {
			  unrecoverableError('''During chaining, found «binaryOp.class» instance having its leftInput !== null.''')
			}
			binaryOp.leftInput = lastAlgebraExpression
			lastAlgebraExpression = op
		}

		lastAlgebraExpression
	}

	/**
	 * Finds and returns a BinaryOperator instance that can be reached from op.
	 * Parameter travelToLeft determines which BinaryOperator to return:
	 *  - if false, the first BinaryOperator found will be returned,
	 *    i.e. the one that was found after traveling through zero or more UnaryOpretor instances.
	 *    Note: If op itself is a BinaryOperator instance, it will be returned.
	 *  - in true, we follow leftInput for BinaryOperator instances having a populated leftInput (i.e. !== null ),
	 *    and return the first BinaryOperator instance w/ un-populated leftInput (i.e. === null )
	 *
	 *
	 * In case we don't find the requested BinaryOperator instance,
	 * an unrecoverable error will be raised.
	 * 
	 * @param travelToLeft specify if we want to travel through BinaryOperator instances w/ populated leftInput  
	 */
	protected def BinaryOperator findBinaryOperator(Operator op, boolean travelToLeft) {
		switch (op) {
			BinaryOperator: {
			  if (travelToLeft && op.leftInput !== null) {
			    findBinaryOperator(op.leftInput, travelToLeft)
			  } else {
			    op
			  }
			}
			UnaryOperator: findBinaryOperator(op.input, travelToLeft)
			default: {
				unrecoverableError('''Found «op.class», expected a tree leading to a BinaryOperator through zero or more UnaryOperator's and in case of travelToLeft==true populated BinaryOperator.leftInput references.''')
				null
			}
		}
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
						GraphObjectVariable:
							if (!groupingVariables.map[toString].toList.contains(myVar.toString)) {
								groupingVariables.add(myVar)
							}
						ExpressionVariable: {
							groupingVariables.add(myVar)
						}
					}
				}
				UnaryArithmeticOperationExpression: {
					fifo.add(el.operand)
				}
				BinaryArithmeticOperationExpression: {
					fifo.add(el.leftOperand)
					fifo.add(el.rightOperand)
				}
				Literal: {}
				FunctionExpression: {
					if (el.functor.isAggregation) {
						effectivelySeenAggregate = true
					} else {
						fifo.addAll(el.arguments)
//							groupingVariables.add(createExpressionVariable =>
//								[
//									expression = el
//									hasInferredName = true
//									namedElementContainer = el.expressionContainer
//								]
//							)
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
}
