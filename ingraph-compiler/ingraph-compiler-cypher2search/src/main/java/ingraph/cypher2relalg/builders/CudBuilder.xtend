package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Delete
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.RelationshipPattern
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.ExpressionVariable
import relalg.Operator
import relalg.RelalgFactory
import relalg.VariableExpression
import relalg.VertexVariable

/**
 * This is the builder for Create/Update/Delete clauses of the openCypher to relational algebra compiler.
 */
package class CudBuilder {

	/** The model factory for the relational graph algebra representation */
	val protected static modelFactory = RelalgFactory.eINSTANCE

	/**
	 * Build and return a create operator from the CREATE clause and attach p_input to its input.
	 */
	protected def static buildCreateOperator(Create u0, Operator p_input, CompilerEnvironment ce) {
		val u1 = modelFactory.createCreateOperator => [
			input = p_input
		]
		for (_u2: u0.pattern.patterns) {
			val u2 = _u2 as PatternElement
			if (u2 === null) {
				ce.l.unrecoverableError('''PatternElement expected at create, but received «_u2.class.name»''')
			}
			val t0 = ce.vb.vertexVariableFactoryElements.containsKey(u2.nodepattern.variable?.name)
			val u4 = buildCreateNodePattern(u2.nodepattern, ce)
			if (!t0) {
				u1.elements.add(u4)
			}
			var lastVertexVariable = (u4.expression as VariableExpression).variable as VertexVariable
			for (element: u2.chain) {
				val t1 = ce.vb.vertexVariableFactoryElements.containsKey(element.nodePattern.variable?.name)
				val u5 = buildCreateNodePattern(element.nodePattern, ce)
				if (!t1) {
					u1.elements.add(u5)
				}
				val t2 = ce.vb.edgeVariableFactoryElements.containsKey(element.relationshipPattern.detail?.variable?.name)
				val u6 = buildCreateRelationshipPattern(element.relationshipPattern, lastVertexVariable, (u5.expression as VariableExpression).variable as VertexVariable, ce)
				if (!t2) {
					u1.elements.add(u6)
				}
				lastVertexVariable = (u5.expression as VariableExpression).variable as VertexVariable
			}
		}
		u1
	}

	/**
	 * Provide the edges for CREATE operator.
	 */
	protected def static ExpressionVariable buildCreateRelationshipPattern(RelationshipPattern relationshippattern, VertexVariable source, VertexVariable target, CompilerEnvironment ce) {
		val u0 = modelFactory.createNavigationDescriptor => [
			edgeVariable = ce.vb.buildEdgeVariable(relationshippattern.detail)
			BuilderUtil.attachProperties(relationshippattern.detail.properties, edgeVariable, ce)
			sourceVertexVariable = source
			targetVertexVariable = target
			direction = BuilderUtil.convertToDirection(relationshippattern)
			expressionContainer = ce.tlc
		]
		val u1 = ce.vb.buildExpressionVariable(u0.edgeVariable.name, u0)
		u1
	}

	/**
	 * Provide the vertices for CREATE operator.
	 */
	protected def static ExpressionVariable buildCreateNodePattern(NodePattern nodepattern, CompilerEnvironment ce) {
		val u0 = modelFactory.createVariableExpression => [
			val vertexVariable = ce.vb.buildVertexVariable(nodepattern)
			BuilderUtil.attachProperties(nodepattern.properties, vertexVariable, ce)
			variable = vertexVariable
			expressionContainer = ce.tlc
		]
		val u1 = ce.vb.buildExpressionVariable(u0.variable.name, u0)
		u1
	}

	/**
	 * Build and return a delete operator from the DELETE clause and attach p_input to its input.
	 */
	protected def static buildDeleteOperator(Delete element, Operator p_input, CompilerEnvironment ce) {
		val u1 = modelFactory.createDeleteOperator => [
			detach = element.detach
		]
		u1.input = p_input
		for (element2: element.expressions) {
		  val u2 = element2 as VariableRef
		  val u4 = buildDeleteVariableRef(u2, ce)
		  u1.elements.add(u4)
		}
		u1
	}

	/**
	 * Provide the vertices for DELETE operator.
	 */
	protected def static ExpressionVariable buildDeleteVariableRef(VariableRef variableref, CompilerEnvironment ce) {
		val u0 = modelFactory.createVariableExpression => [
			variable = ce.vb.buildRelalgVariable(variableref)
			expressionContainer = ce.tlc
		]
		val u1 = ce.vb.buildExpressionVariable(u0.variable.name, u0)
		u1
	}
}