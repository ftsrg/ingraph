package ingraph.search2tasks

import relalg.GetVerticesOperator
import ingraph.search2tasks.tasks.nullary.GetEdgesTask
import ingraph.search2tasks.tasks.nullary.GetVerticesTask
import ingraph.search2tasks.tasks.Task
import relalg.GetEdgesOperator
import relalg.Direction
import java.util.List
import relalg.AllDifferentOperator
import ingraph.search2tasks.tasks.unary.AllDifferentTask
import relalg.DuplicateEliminationOperator
import ingraph.search2tasks.tasks.unary.DuplicateEliminationTask
import ingraph.search2tasks.tasks.unary.ExpandBothTask
import relalg.ExpandOperator

/**
 * Utility class for compiling relational algebra operators into search tasks.
 * For operators with no compilation logic (missing dispatch method) an <code>IllegalArgumentException</code> is thrown.
 */
class TaskCompiler {

	// nullary operators
	static def dispatch List<Task> compile(GetVerticesOperator getVerticesOp) {
		val variable = getVerticesOp.vertexVariable
		#[new GetVerticesTask(variable)]
	}

	// XXX: debug; this is strange...
	static def dispatch List<Task> compile(GetEdgesOperator getEdgesOp) {
		if (getEdgesOp.direction == Direction.OUT) {
			val sourceVar = getEdgesOp.sourceVertexVariable
			val edgeVar = getEdgesOp.edgeVariable
			val targetVar = getEdgesOp.targetVertexVariable
			#[new GetEdgesTask(sourceVar, edgeVar, targetVar)]
		} else if (getEdgesOp.direction == Direction.IN) {
			val sourceVar = getEdgesOp.sourceVertexVariable
			val edgeVar = getEdgesOp.edgeVariable
			val targetVar = getEdgesOp.targetVertexVariable
			#[new GetEdgesTask(targetVar, edgeVar, sourceVar)]
		} else if (getEdgesOp.direction == Direction.BOTH) {
			val sourceVar = getEdgesOp.sourceVertexVariable
			val edgeVar = getEdgesOp.edgeVariable
			val targetVar = getEdgesOp.targetVertexVariable
			#[new GetEdgesTask(sourceVar, edgeVar, targetVar), new GetEdgesTask(targetVar, edgeVar, sourceVar)]
		} else {
			throw new IllegalArgumentException("Direction " + getEdgesOp.direction + " is not supported")
		}
	}

	// unary operators 
	static def dispatch List<Task> compile(AllDifferentOperator allDiffOp) {
		#[new AllDifferentTask]
	}

	static def dispatch List<Task> compile(DuplicateEliminationOperator dupElimOp) {
		#[new DuplicateEliminationTask]
	}
	
	static def dispatch List<Task> compile(ExpandOperator expandOp) {
		if (expandOp.direction == Direction.OUT) {
			val sourceVar = expandOp.sourceVertexVariable
			val edgeVar = expandOp.edgeVariable
			val targetVar = expandOp.targetVertexVariable
			#[new ExpandBothTask(sourceVar, edgeVar, targetVar)]
		} else if (expandOp.direction == Direction.IN) {
			val sourceVar = expandOp.sourceVertexVariable
			val edgeVar = expandOp.edgeVariable
			val targetVar = expandOp.targetVertexVariable
			#[new GetEdgesTask(targetVar, edgeVar, sourceVar)]
		} else if (expandOp.direction == Direction.BOTH) {
			val sourceVar = expandOp.sourceVertexVariable
			val edgeVar = expandOp.edgeVariable
			val targetVar = expandOp.targetVertexVariable
			#[new GetEdgesTask(sourceVar, edgeVar, targetVar), new GetEdgesTask(targetVar, edgeVar, sourceVar)]
		} else {
			throw new IllegalArgumentException("Direction " + expandOp.direction + " is not supported")
		}
	}

}
