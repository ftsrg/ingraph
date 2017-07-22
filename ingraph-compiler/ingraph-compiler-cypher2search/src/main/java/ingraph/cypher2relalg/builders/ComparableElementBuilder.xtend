package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.Cypher2RelalgConfig
import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode
import ingraph.cypher2relalg.structures.RelalgMatchDescriptor
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.cypher2relalg.util.GrammarUtil
import ingraph.cypher2relalg.util.StringUtil
import ingraph.cypher2relalg.util.Validator
import ingraph.emf.util.PrettyPrinter
import ingraph.logger.IngraphLogger
import java.math.BigInteger
import java.util.Arrays
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.AllShortestPaths
import org.slizaa.neo4j.opencypher.openCypher.CaseExpression
import org.slizaa.neo4j.opencypher.openCypher.Clause
import org.slizaa.neo4j.opencypher.openCypher.ContainsExpression
import org.slizaa.neo4j.opencypher.openCypher.Count
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Delete
import org.slizaa.neo4j.opencypher.openCypher.EndsWithExpression
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionList
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.InCollectionExpression
import org.slizaa.neo4j.opencypher.openCypher.IndexExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNotNullExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNullExpression
import org.slizaa.neo4j.opencypher.openCypher.MapLiteral
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.NullConstant
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.Parameter
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.Properties
import org.slizaa.neo4j.opencypher.openCypher.RegExpMatchingExpression
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery
import org.slizaa.neo4j.opencypher.openCypher.RelationshipPattern
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnBody
import org.slizaa.neo4j.opencypher.openCypher.ShortestPath
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.StartsWithExpression
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.With
import relalg.AbstractEdgeVariable
import relalg.ArithmeticComparisonOperatorType
import relalg.ArithmeticExpression
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.ComparableExpression
import relalg.Direction
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.IndexAccessExpression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.OrderDirection
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalOperatorType
import relalg.UnaryOperator
import relalg.Variable
import relalg.VariableExpression
import relalg.VertexVariable
import relalg.function.Function

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