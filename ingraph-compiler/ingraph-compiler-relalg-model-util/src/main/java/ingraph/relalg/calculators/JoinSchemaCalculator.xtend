package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import java.util.List
import relalg.AntiJoinOperator
import relalg.EdgeVariable
import relalg.EquiJoinLikeOperator
import relalg.TransitiveClosureJoinOperator
import relalg.Variable

class JoinSchemaCalculator {

	extension CollectionHelper collectionHelper = new CollectionHelper

	// the join for transitive closure changes the schema as it introduces an EdgeListVariable 
	def dispatch calculateJoinSchema(EquiJoinLikeOperator op, List<Variable> leftSchema, List<Variable> rightSchema) {
		calculateEquiJoinSchema(leftSchema, rightSchema)
	}

	def dispatch calculateJoinSchema(AntiJoinOperator op, List<Variable> leftSchema, List<Variable> rightSchema) {
		leftSchema
	}

	def dispatch calculateJoinSchema(TransitiveClosureJoinOperator op, List<Variable> leftSchema, List<Variable> rightSchema) {
		val joinSchema = calculateEquiJoinSchema(leftSchema, rightSchema)
		// replace the EdgeVariable with and EdgeListVariable (e.g. replace "e" with "[e]")
		val joinSchemaWithEdgeList = joinSchema.map[
			if (it instanceof EdgeVariable && it.name.equals(op.edgeListVariable.name)) {
				op.edgeListVariable
			} else {
				it
			}
		]
		joinSchemaWithEdgeList
	}

	//
	def calculateEquiJoinSchema(List<Variable> leftSchema, List<Variable> rightSchema) {
		val leftSchemaNames = leftSchema.map[toString]
		val joinSchema = union( // 
			leftSchema,
			rightSchema.filter[
				variable | !leftSchemaNames.contains(variable.toString) // only keep variables that are NOT in the left schema
			]
		)
		joinSchema.toList
	}

}