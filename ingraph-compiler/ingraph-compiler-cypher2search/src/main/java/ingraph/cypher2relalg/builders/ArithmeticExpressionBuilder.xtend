package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import org.slizaa.neo4j.opencypher.openCypher.Count
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.ArithmeticExpression
import relalg.BinaryArithmeticOperatorType
import relalg.ElementVariable
import relalg.RelalgFactory
import relalg.function.Function

package class ArithmeticExpressionBuilder {
	
	/** The model factory for the relational graph algebra representation */
	val static modelFactory = RelalgFactory.eINSTANCE

	def static dispatch ArithmeticExpression buildArithmeticExpression(ExpressionPlusMinus e, CompilerEnvironment ce) {
		modelFactory.createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "+": BinaryArithmeticOperatorType.PLUS
				case "-": BinaryArithmeticOperatorType.MINUS
			}
			leftOperand = buildArithmeticExpression(e.left, ce)
			rightOperand = buildArithmeticExpression(e.right, ce)
			expressionContainer = ce.tlc
		]

	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(ParenthesizedExpression e, CompilerEnvironment ce) {
		buildArithmeticExpression(e.expression, ce)
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(ExpressionMulDiv e, CompilerEnvironment ce) {
		modelFactory.createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "*": BinaryArithmeticOperatorType.MULTIPLICATION
				case "/": BinaryArithmeticOperatorType.DIVISION
				case "%": BinaryArithmeticOperatorType.MOD
			}
			leftOperand = buildArithmeticExpression(e.left, ce)
			rightOperand = buildArithmeticExpression(e.right, ce)
			expressionContainer = ce.tlc
		]

	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(ExpressionPower e, CompilerEnvironment ce) {
		modelFactory.createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "^": BinaryArithmeticOperatorType.POWER
			}
			leftOperand = buildArithmeticExpression(e.left, ce)
			rightOperand = buildArithmeticExpression(e.right, ce)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(NumberConstant e, CompilerEnvironment ce) {
		LiteralBuilder.buildNumberLiteral(e, ce)
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(ExpressionNodeLabelsAndPropertyLookup e, CompilerEnvironment ce) {
		modelFactory.createVariableArithmeticExpression => [
			variable = ce.vb.buildRelalgVariable(e)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(VariableRef e, CompilerEnvironment ce) {
		val ae = modelFactory.createVariableArithmeticExpression => [
			variable = ce.vb.buildRelalgVariable(e)
			expressionContainer = ce.tlc
		]

		if (ae.variable instanceof ElementVariable) {
			ce.l.unsupported('''Unsupported variable of type «ae.class.name» found in an arithmetic expression.''')
		}

		ae
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(FunctionInvocation fi, CompilerEnvironment ce) {
		val fe = modelFactory.createFunctionArithmeticExpression => [
			expressionContainer = ce.tlc
		]

		BuilderUtil.populateFunctionExpression(fe, fi, ce)
		if (!fe.functor.mightBeNumericValued) {
			ce.l.warning('''Expected numeric valued function, found «fe.functor» with output type «fe.functor.outputType»''')
		}

		fe
	}

	def static dispatch ArithmeticExpression buildArithmeticExpression(Count fi, CompilerEnvironment ce) {
		modelFactory.createFunctionArithmeticExpression => [
			functor = Function.COUNT_ALL
			expressionContainer = ce.tlc
		]
	}
}