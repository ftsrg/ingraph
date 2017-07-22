package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.MapLiteral
import org.slizaa.neo4j.opencypher.openCypher.Properties
import org.slizaa.neo4j.opencypher.openCypher.RelationshipPattern
import relalg.Direction
import relalg.ElementVariable
import relalg.FunctionExpression
import relalg.RelalgFactory
import relalg.function.Function

package class BuilderUtil {

	/** The model factory for the relational graph algebra representation */
	val protected static modelFactory = RelalgFactory.eINSTANCE

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