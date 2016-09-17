package ingraph.optimization.transformations

import java.util.List
import java.util.stream.Collectors
import java.util.stream.Stream
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.Container
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.Variable

class SchemaInferencer {

	new() {
	}

	def addSchemaInformation(Container container) {
		val rootExpression = container.rootExpression
		rootExpression.inferSchema
	}

	/**
	 * schema
	 */
	def dispatch List<Variable> inferSchema(GetVerticesOperator op) {
		op.setSchema(#[op.vertexVariable])
	}

	def dispatch List<Variable> inferSchema(GetEdgesOperator op) {
		println(op.schema.map[it.name].join(", "))
		op.setSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])		
		op.schema
	}

	def dispatch List<Variable> inferSchema(ProjectionOperator op) {
		op.input.inferSchema
		op.setSchema(op.variables)
	}

	def dispatch List<Variable> inferSchema(AlphaOperator op) {
		op.setSchema(op.input.inferSchema)
	}

	def dispatch List<Variable> inferSchema(BetaOperator op) {
		op.setSchema(Stream.concat(op.leftInput.inferSchema.stream, op.rightInput.inferSchema.stream).collect(Collectors.toList()))
	}

	def setSchema(Operator op, List<Variable> schema) {
		op.schema.addAll(schema)
		schema		
	}

}
