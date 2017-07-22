package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.Parameter
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.AttributeVariable
import relalg.ComparableExpression
import relalg.RelalgFactory

package class ComparableElementBuilder {

	/** The model factory for the relational graph algebra representation */
	val static modelFactory = RelalgFactory.eINSTANCE

	def static dispatch ComparableExpression buildComparableElement(Parameter e, CompilerEnvironment ce) {
		modelFactory.createParameterComparableExpression => [
			parameter = modelFactory.createParameter => [
				name = e.parameter
				expressionContainer = ce.tlc
			]
			expressionContainer = ce.tlc
		]
	}

	def static dispatch ComparableExpression buildComparableElement(NumberConstant e, CompilerEnvironment ce) {
		LiteralBuilder.buildNumberLiteral(e, ce)
	}

	def static dispatch ComparableExpression buildComparableElement(StringConstant e, CompilerEnvironment ce) {
		LiteralBuilder.buildStringLiteral(e, ce)
	}

	def static dispatch ComparableExpression buildComparableElement(VariableRef e, CompilerEnvironment ce) {
		modelFactory.createVariableComparableExpression => [
			variable = ce.vb.buildRelalgVariable(e)
			expressionContainer = ce.tlc
		]
	}

	def static dispatch ComparableExpression buildComparableElement(ExpressionPlusMinus e, CompilerEnvironment ce) {
		ArithmeticExpressionBuilder.buildArithmeticExpression(e, ce)
	}

	def static dispatch ComparableExpression buildComparableElement(ExpressionMulDiv e, CompilerEnvironment ce) {
		ArithmeticExpressionBuilder.buildArithmeticExpression(e, ce)
	}

	def static dispatch ComparableExpression buildComparableElement(ExpressionPower e, CompilerEnvironment ce) {
		ArithmeticExpressionBuilder.buildArithmeticExpression(e, ce)
	}

	def static dispatch ComparableExpression buildComparableElement(ExpressionNodeLabelsAndPropertyLookup e, CompilerEnvironment ce) {
		val x = ce.vb.buildRelalgVariable(e)
		// as AttributeVariable
		if (x instanceof AttributeVariable) {
			modelFactory.createVariableComparableExpression => [
				variable = x
				expressionContainer = ce.tlc
			]
		} else {
			ce.l.unsupported('''Unsupported type received: «x.class.name»''')
			null
		}
	}

	def static dispatch ComparableExpression buildComparableElement(FunctionInvocation fi, CompilerEnvironment ce) {
		val fe = modelFactory.createFunctionComparableExpression => [
			expressionContainer = ce.tlc
		]

		BuilderUtil.populateFunctionExpression(fe, fi, ce)

		fe
	}

}