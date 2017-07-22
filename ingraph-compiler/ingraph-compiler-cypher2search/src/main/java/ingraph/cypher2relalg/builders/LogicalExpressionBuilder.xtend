package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.slizaa.neo4j.opencypher.openCypher.ContainsExpression
import org.slizaa.neo4j.opencypher.openCypher.EndsWithExpression
import org.slizaa.neo4j.opencypher.openCypher.Expression
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.InCollectionExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNotNullExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNullExpression
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression
import org.slizaa.neo4j.opencypher.openCypher.RegExpMatchingExpression
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.StartsWithExpression
import relalg.ArithmeticComparisonOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.LogicalExpression
import relalg.Operator
import relalg.RelalgFactory
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalOperatorType
import relalg.function.Function

package class LogicalExpressionBuilder {

	/** The model factory for the relational graph algebra representation */
	val static modelFactory = RelalgFactory.eINSTANCE

	/**
	 * This is a wrapper around the buildRelalgLogicalExpression to be used
	 * in contexts where no join clauses should be generated.
	 *
	 * We use this outside of WHERE clauses.
	 */
	def static buildLogicalExpressionNoJoinAllowed(Expression e, CompilerEnvironment ce) {
		// there should be no join clauses added when we build
		// a logical expression outside the WHERE clause
		val dummyJoins = new BasicEList<Operator>
		val logicalExp = buildLogicalExpression(e, dummyJoins, ce)
		if (dummyJoins.size > 0) {
			ce.l.unrecoverableError('''Joins found when building a logical expression in generic expression position.''')
		}

		logicalExp
	}

	def static dispatch LogicalExpression buildLogicalExpression(ExpressionAnd e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.AND
			leftOperand = buildLogicalExpression(e.left, joins, ce)
			rightOperand = buildLogicalExpression(e.right, joins, ce)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch LogicalExpression buildLogicalExpression(ExpressionOr e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.OR
			leftOperand = buildLogicalExpression(e.left, joins, ce)
			rightOperand = buildLogicalExpression(e.right, joins, ce)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch LogicalExpression buildLogicalExpression(ExpressionXor e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.XOR
			leftOperand = buildLogicalExpression(e.left, joins, ce)
			rightOperand = buildLogicalExpression(e.right, joins, ce)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch LogicalExpression buildLogicalExpression(IsNotNullExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
			operand = ce.vb.buildRelalgVariable(e.left)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch LogicalExpression buildLogicalExpression(IsNullExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NULL
			operand = ce.vb.buildRelalgVariable(e.left)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch LogicalExpression buildLogicalExpression(
		RegExpMatchingExpression e,
		EList<Operator> joins,
		CompilerEnvironment ce
	) {
		val fe = modelFactory.createFunctionLogicalExpression => [
			expressionContainer = ce.tlc
		]

		fe.functor = Function.REGEX_LIKE

		fe.arguments.add(ExpressionBuilder.buildExpression(e.left, ce))
		fe.arguments.add(ExpressionBuilder.buildExpression(e.right, ce))

		fe
	}

	def static dispatch LogicalExpression buildLogicalExpression(
		Expression e,
		EList<Operator> joins,
		CompilerEnvironment ce
	) {
		switch e.operator.toLowerCase {
			case "not":
				modelFactory.createUnaryLogicalExpression => [
					operator = UnaryLogicalOperatorType.NOT
					operand = buildLogicalExpression(e.left, joins, ce)
					expressionContainer = ce.tlc
				]
			default: {
				ce.l.unsupported("TODO: " + e.operator)
				null
			}
		}
	}

	def static dispatch LogicalExpression buildLogicalExpression(ParenthesizedExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		buildLogicalExpression(e.expression, joins, ce)
	}

	def static dispatch LogicalExpression buildLogicalExpression(RelationshipsPattern e, EList<Operator> joins, CompilerEnvironment ce) {
		// We add all the variables in the pattern as a NOT NULL expression
		val EList<LogicalExpression> relationshipVariableExpressions = new BasicEList<LogicalExpression>()

		relationshipVariableExpressions.add(modelFactory.createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
			operand = ce.vb.buildVertexVariable(e.nodePattern)
			expressionContainer = ce.tlc
		])

		relationshipVariableExpressions.addAll(
			// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
			e.chain.map [
				val mapIt = it
				modelFactory.createUnaryGraphObjectLogicalExpression => [
					operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
					operand = ce.vb.buildEdgeVariable(mapIt.relationshipPattern.detail)
					expressionContainer = ce.tlc
				]
			]
		)
		relationshipVariableExpressions.addAll(
			// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
			e.chain.map [
				val mapIt = it
				modelFactory.createUnaryGraphObjectLogicalExpression => [
					operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
					operand = ce.vb.buildVertexVariable(mapIt.nodePattern)
					expressionContainer = ce.tlc
				]
			]
		)

		joins.add(RelalgBuilder.buildRelalg(e, ce))

		Cypher2RelalgUtil.buildLeftDeepTree(BinaryLogicalOperatorType.AND, relationshipVariableExpressions.iterator, ce)
	}

	def static dispatch LogicalExpression buildLogicalExpression(ExpressionComparison e, EList<Operator> joins, CompilerEnvironment ce) {
		// FIXME: add type check to ensure that the operands are comparable
		modelFactory.createArithmeticComparisonExpression => [
			operator = switch e.operator {
				case "=": ArithmeticComparisonOperatorType.EQUAL_TO
				case "<>": ArithmeticComparisonOperatorType.NOT_EQUAL_TO
				case "<": ArithmeticComparisonOperatorType.LESS_THAN
				case "<=": ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL
				case ">": ArithmeticComparisonOperatorType.GREATER_THAN
				case ">=": ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL
			}
			leftOperand = ComparableElementBuilder.buildComparableElement(e.left, ce)
			rightOperand = ComparableElementBuilder.buildComparableElement(e.right, ce)
			expressionContainer = ce.tlc
		]
	}

	/**
	 * Processes STARTS WITH create a function invocation: STARTS_WITH(string, prefixString)
	 */
	def static dispatch LogicalExpression buildLogicalExpression(StartsWithExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createFunctionLogicalExpression => [
			functor = Function.STARTS_WITH
			arguments.add(ExpressionBuilder.buildExpression(e.left, ce))
			arguments.add(ExpressionBuilder.buildExpression(e.right, ce))
			expressionContainer = ce.tlc
		]
	}

	/**
	 * Processes ENDS WITH create a function invocation: ENDS_WITH(string, postfixString)
	 */
	def static dispatch LogicalExpression buildLogicalExpression(EndsWithExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createFunctionLogicalExpression => [
			functor = Function.ENDS_WITH
			arguments.add(ExpressionBuilder.buildExpression(e.left, ce))
			arguments.add(ExpressionBuilder.buildExpression(e.right, ce))
			expressionContainer = ce.tlc
		]
	}

	/**
	 * Processes CONTAINS by creating a function invocation: CONTAINS(string, middleString)
	 */
	def static dispatch LogicalExpression buildLogicalExpression(ContainsExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createFunctionLogicalExpression => [
			functor = Function.CONTAINS
			arguments.add(ExpressionBuilder.buildExpression(e.left, ce))
			arguments.add(ExpressionBuilder.buildExpression(e.right, ce))
			expressionContainer = ce.tlc
		]
	}

	/**
	 * Processes IN by creating a function invocation: IN_COLLECTION(ANY, LIST expression)
	 */
	def static dispatch LogicalExpression buildLogicalExpression(InCollectionExpression e, EList<Operator> joins, CompilerEnvironment ce) {
		modelFactory.createFunctionLogicalExpression => [
			functor = Function.IN_COLLECTION
			arguments.add(ExpressionBuilder.buildExpression(e.left, ce))
			arguments.add(ExpressionBuilder.buildExpression(e.right, ce))
			expressionContainer = ce.tlc
		]
	}	
}