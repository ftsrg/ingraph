package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import java.util.Arrays
import org.slizaa.neo4j.opencypher.openCypher.CaseExpression
import org.slizaa.neo4j.opencypher.openCypher.Count
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionList
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.IndexExpression
import org.slizaa.neo4j.opencypher.openCypher.NullConstant
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.Parameter
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.Expression
import relalg.IndexAccessExpression
import relalg.RelalgFactory
import relalg.function.Function

package class ExpressionBuilder {

	/** The model factory for the relational graph algebra representation */
	val static modelFactory = RelalgFactory.eINSTANCE

	def static dispatch Expression buildExpression(VariableRef e, CompilerEnvironment ce) {
		ce.vb.buildVariableExpression(e, false)
	}

	def static dispatch Expression buildExpression(StringConstant e, CompilerEnvironment ce) {
		LiteralBuilder.buildStringLiteral(e, ce)
	}

	def static dispatch Expression buildExpression(CaseExpression e, CompilerEnvironment ce) {
		if (!(e.expression instanceof CaseExpression)) {
			ce.l.unrecoverableError("Outer CaseExpressions should contain a CaseExpression")
		}
		val caseExpr = e.expression as CaseExpression

		// do we process a simple case expression,
		// i.e. when there is a single value we search for
		var boolean isSimple = false

		val retVal = if (caseExpr.caseExpression === null) {
			modelFactory.createGenericCaseExpression => [
				expressionContainer = ce.tlc
			]
		} else {
		  isSimple = true
			modelFactory.createSimpleCaseExpression => [
				expressionContainer = ce.tlc
				test = ComparableElementBuilder.buildComparableElement(caseExpr.caseExpression, ce)
			]
		}

		// WHEN when THEN then
		for (ca: caseExpr.caseAlternatives) {
		  val case_ = modelFactory.createCase => [
		    then = buildExpression(ca.then, ce)
		  ]
		  if (isSimple) {
		    case_.when = ComparableElementBuilder.buildComparableElement(ca.when, ce)
		  } else {
		    case_.when = LogicalExpressionBuilder.buildLogicalExpressionNoJoinAllowed(ca.when, ce)
		  }
		  retVal.cases.add(case_)
		}

		// ELSE elseExpression
		if (caseExpr.elseExpression !== null) {
			retVal.fallback = buildExpression(caseExpr.elseExpression, ce)
		}

		retVal
	}

	def static dispatch Expression buildExpression(FunctionInvocation fi, CompilerEnvironment ce) {
		val fe = modelFactory.createFunctionExpression => [
			expressionContainer = ce.tlc
		]

		BuilderUtil.populateFunctionExpression(fe, fi, ce)

		fe
	}

	def static dispatch Expression buildExpression(Count fi, CompilerEnvironment ce) {
		modelFactory.createFunctionExpression => [
			functor = Function.COUNT_ALL
			expressionContainer = ce.tlc
		]
	}

	def static dispatch Expression buildExpression(ExpressionNodeLabelsAndPropertyLookup e, CompilerEnvironment ce) {
		ce.vb.buildVariableExpression(e, false)
	}

	def static dispatch Expression buildExpression(IndexExpression ie, CompilerEnvironment ce) {
		var IndexAccessExpression retVal
		if (ie.expression === null) {
			ce.l.unrecoverableError('''Index lookup expression found having null as subscript.''')
		}
		if (ie.expression instanceof NumberConstant) {
			if (ie.upper === null) {
				retVal = modelFactory.createIndexSimpleAccessExpression => [
					idx = LiteralBuilder.buildNumber(ie.expression as NumberConstant, ce)
				]
			} else {
				if (ie.upper instanceof NumberConstant) {
					retVal = modelFactory.createIndexRangeAccessExpression => [
						lower = LiteralBuilder.buildNumber(ie.expression as NumberConstant, ce)
						upper = LiteralBuilder.buildNumber(ie.upper as NumberConstant, ce)
					]
				} else {
					ce.l.unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.upper.class.name».''')
				}
			}
		} else {
			ce.l.unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.expression.class.name».''')
		}
		retVal => [
			list = buildExpression(ie.left, ce)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch Expression buildExpression(ExpressionList el, CompilerEnvironment ce) {
		val emptyList = modelFactory.createEmptyListExpression => [
			head = null
			tail = null
			expressionContainer = ce.tlc
		]
		// the tail of the first expression will be the list that was built
		val first = modelFactory.createListExpression => [
			tail = emptyList
		]
		var recent = first

		for (e : el.expressions) {
			recent.tail = modelFactory.createListExpression => [
				head = buildExpression(e, ce)
				tail = emptyList
				expressionContainer = ce.tlc
			]
			recent = recent.tail
		}

		first.tail
	}

	def static dispatch Expression buildExpression(ExpressionComparison e, CompilerEnvironment ce) {
	  LogicalExpressionBuilder.buildLogicalExpressionNoJoinAllowed(e, ce)
	}

	/**
	 * Catch-all to pass on calls to more-specific methods
	 */
	def static dispatch Expression buildExpression(org.slizaa.neo4j.opencypher.openCypher.Expression e, CompilerEnvironment ce) {
		switch (e) {
			NumberConstant,
			ExpressionPlusMinus,
			ExpressionMulDiv,
			ExpressionPower:
				ArithmeticExpressionBuilder.buildArithmeticExpression(e, ce)
			NullConstant:
				modelFactory.createNullLiteral => [
					expressionContainer = ce.tlc
				]
			default:
				throw new IllegalArgumentException('''Unhandled parameter types: «Arrays.<Object>asList(e)»''')
		}
	}

	protected def static expressionToSkipLimitConstant(org.slizaa.neo4j.opencypher.openCypher.Expression expression, CompilerEnvironment ce) {
		switch expression {
			case expression === null: null //this is in-line with a null-safe call on expression as it was used before
			NumberConstant: LiteralBuilder.buildNumberLiteral(expression, ce)
			Parameter: buildRelalgParameter(expression, ce)
			default: ce.l.unsupported('''Only NumberConstants are supported as SKIP/LIMIT values, got «expression»''')
		}
	}

	protected def static buildRelalgParameter(Parameter expression, CompilerEnvironment ce) {
		modelFactory.createParameter => [
			name = expression.parameter
			expressionContainer = ce.tlc
		]
	}
}