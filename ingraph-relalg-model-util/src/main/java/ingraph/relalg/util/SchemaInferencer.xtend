package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.RelalgContainer
import relalg.UnaryOperator

class SchemaInferencer {

	val boolean includeEdges

	new() {
		this.includeEdges = true
	}

	new(boolean includeEdges) {
		this.includeEdges = includeEdges
	}

	def addSchemaInformation(RelalgContainer container) {
		val rootExpression = container.getRootExpression
		rootExpression.inferSchema
		container
	}

	/**
	 * inferSchema
	 */
	// nullary operators
	def dispatch List<Variable> inferSchema(GetVerticesOperator op) {
		op.defineSchema(#[op.vertexVariable])
	}

	def dispatch List<Variable> inferSchema(GetEdgesOperator op) {
		if (includeEdges) {
			op.defineSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])
		} else {
			op.defineSchema(#[op.sourceVertexVariable, op.targetVertexVariable])
		}
	}

	// unary operators
	def dispatch List<Variable> inferSchema(ProjectionOperator op) {
		val schema = op.getInput.inferSchema

		// check if all projected variables are in the schema
		op.variables.filter(typeof(AttributeVariable)).forEach [
			if (!schema.contains(it.element))
				throw new IllegalStateException("Attribute " + it.name +
					" cannot be projected as its vertex/edge variable does not exists.")
		]
		op.variables.filter(typeof(ElementVariable)).forEach [
			if (!schema.contains(it))
				throw new IllegalStateException("Variable " + it.name +
					" is not part of the schema in projection operator.")
		]
//		TODO fix this
		op.defineSchema(op.variables as List)
	}

	def dispatch List<Variable> inferSchema(ExpandOperator op) {
		val schema = Lists.newArrayList(op.getInput.inferSchema)
		if (includeEdges) {
			schema.add(op.edgeVariable)
		}
		schema.add(op.targetVertexVariable)
		op.defineSchema(schema)
	}

	// rest of the unary operators
	def dispatch List<Variable> inferSchema(UnaryOperator op) {
		op.defineSchema(op.getInput.inferSchema)
	}

	// binary operators
	def dispatch List<Variable> inferSchema(AbstractJoinOperator op) {
		val leftInputSchema = Lists.newArrayList(op.getLeftInput.inferSchema)
		val rightInputSchema = Lists.newArrayList(op.getRightInput.inferSchema)
		op.defineSchema(Lists.newArrayList(Iterables.concat(leftInputSchema, rightInputSchema)))

		// calculate the mutual variables
		leftInputSchema.retainAll(rightInputSchema)
		op.mutualVariables.addAll(leftInputSchema)

		op.schema
	}

	def dispatch List<Variable> inferSchema(UnionOperator op) {
		// The openCypher acceptance test and the Neo4j code contradict each other:
		// OpenCypher
		// https://github.com/opencypher/openCypher/blob/master/tck/features/UnionAcceptance.feature
		// Scenario: Should be able to create text output from union queries
		// Given an empty graph
		// And having executed:
		// """
		// CREATE (:A), (:B)
		// """
		// When executing query:
		// """
		// MATCH (a:A)
		// RETURN a AS a
		// UNION
		// MATCH (b:B)
		// RETURN b AS a
		// """
		// Then the result should be:
		// | a    |
		// | (:A) |
		// | (:B) |
		// And no side effects
		// Neo4j
		// https://github.com/neo4j/neo4j/blob/3.1/community/cypher/spec-suite-tools/src/test/scala/cypher/feature/steps/SpecSuiteErrorHandler.scala#L166
		// All sub queries in an UNION must have the same column names
		op.getLeftInput.inferSchema
		op.getRightInput.inferSchema

		// we only keep the left schema
		op.defineSchema(op.getLeftInput.schema)
	}

	/**
	 * defineSchema
	 */
	def defineSchema(Operator op, List<Variable> schema) {
		op.schema.addAll(schema)
		schema
	}

}
