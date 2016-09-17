package ingraph.optimization.transformations

import java.util.List
import java.util.stream.Collectors
import java.util.stream.Stream
import relalg.AbstractJoinOperator
import relalg.AlphaOperator
import relalg.AttributeVariable
import relalg.Container
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.UnionOperator
import relalg.Variable
import com.google.common.collect.Lists
import com.google.common.collect.Iterables

class SchemaInferencer {

	new() {
	}

	def addSchemaInformation(Container container) {
		val rootExpression = container.rootExpression
		rootExpression.inferSchema
		container
	}

	/**
	 * inferSchema
	 */
	def dispatch List<Variable> inferSchema(GetVerticesOperator op) {
		op.setSchema(#[op.vertexVariable])
	}

	def dispatch List<Variable> inferSchema(GetEdgesOperator op) {
		op.setSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])
	}

	def dispatch List<Variable> inferSchema(ProjectionOperator op) {
		val schema = op.input.inferSchema

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
		op.setSchema(op.variables)
	}

	def dispatch List<Variable> inferSchema(ExpandOperator op) {
		val schema = Lists.newArrayList(op.input.inferSchema)
		schema.add(op.edgeVariable)
		schema.add(op.targetVertexVariable)
		op.setSchema(schema)
	}

	def dispatch List<Variable> inferSchema(AlphaOperator op) {
		op.setSchema(op.input.inferSchema)
	}

	def dispatch List<Variable> inferSchema(AbstractJoinOperator op) {
		val leftInputSchema = Lists.newArrayList(op.leftInput.inferSchema)
		val rightInputSchema = Lists.newArrayList(op.rightInput.inferSchema)				
		op.setSchema(Lists.newArrayList(Iterables.concat(leftInputSchema, rightInputSchema)))
		
		// calculate the mutual variables
		leftInputSchema.retainAll(rightInputSchema)
		op.mutualVariables.addAll(leftInputSchema)		
		
		op.schema
	}

	def dispatch List<Variable> inferSchema(UnionOperator op) {
		throw new UnsupportedOperationException("TODO: implement union operator")
	}

	/**
	 * setSchema
	 */
	def setSchema(Operator op, List<Variable> schema) {
		op.schema.addAll(schema)
		schema
	}

}
