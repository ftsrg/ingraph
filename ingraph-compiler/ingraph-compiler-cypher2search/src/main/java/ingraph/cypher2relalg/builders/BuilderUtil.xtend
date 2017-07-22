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

package class BuilderUtil {

	/** The model factory for the relational graph algebra representation */
	val static modelFactory = RelalgFactory.eINSTANCE

	def static void populateFunctionExpression(FunctionExpression fe, FunctionInvocation fi, CompilerEnvironment ce) {
		fe.functor = Function.valueOf(fi.functionName.name.toUpperCase)
		// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
		fe.arguments.addAll(fi.parameter.map[ExpressionBuilder.buildExpression(it, ce)])
	}

	/**
	 * Given a RelationshipPattern instance, it's direction information
	 * is mapped to the relalg model's Direction type.
	 *
	 * @param pattern the relationship pattern
	 * @return the appropriate direction descriptor
	 */
	def static convertToDirection(RelationshipPattern pattern) {
		val isLeftArrow = pattern.incoming
		val isRightArrow = pattern.outgoing

		if (isLeftArrow && isRightArrow || !(isLeftArrow || isRightArrow))
			Direction.BOTH
		else if(isLeftArrow) Direction.IN else Direction.OUT
	}

	/**
	 * Parse map-like constraints if given
	 * and attach to the ElementVariable in certain cases.
	 *
	 * FIXME: attach to the VertexVariable only if in a MATCH or CREATE context
	 * otherwise, selection operators should be created, see #67
	 */
	def static dispatch void attachProperties(MapLiteral properties, ElementVariable ev, CompilerEnvironment ce) {
		if (properties !== null) {
			val pList = modelFactory.createPropertyList => [
				expressionContainer = ce.tlc
			]

			for (e: properties.entries) {
				val key = e.key
				val value = ExpressionBuilder.buildExpression(e.value, ce)
				pList.entries.put(key, value)
			}

			ev.properties = pList
		}
	}

	def static dispatch void attachProperties(Properties properties, ElementVariable ev, CompilerEnvironment ce) {
		ce.l.unsupported('''Parsing Properties type is unsupported.''')
	}

	def static dispatch void attachProperties(Void p, ElementVariable ev, CompilerEnvironment ce) {}
}