package ingraph.search2constraints

import ingraph.search2constraints.constraints.AllDifferent
import ingraph.search2constraints.constraints.Constraint
import ingraph.search2constraints.constraints.DirectedEdge
import ingraph.search2constraints.constraints.DuplicateElimination
import ingraph.search2constraints.constraints.UndirectedEdge
import ingraph.search2constraints.constraints.Vertex
import java.util.List
import relalg.AbstractEdgeVariable
import relalg.AllDifferentOperator
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.VertexVariable

/**
 * Utility class for compiling relational algebra operators into search Constraints.
 * For operators with no compilation logic (missing dispatch method) an <code>IllegalArgumentException</code> is thrown.
 */
class Relalg2ConstraintCompiler {

	static def dispatch List<Constraint> compile(GetVerticesOperator getVerticesOp) {
		val variable = getVerticesOp.vertexVariable
		#[new Vertex(variable)]
	}

	def static List<Constraint> compileEdgeConstraint(VertexVariable src, EdgeVariable e, VertexVariable trg, Direction dir){
		if (dir == Direction.OUT) {
			#[new DirectedEdge(src, e, trg)]
		} else if (dir == Direction.IN) {
			#[new DirectedEdge(trg, e, src)]
		} else if (dir == Direction.BOTH) {
			
			
			#[new UndirectedEdge(#{src, trg} , e)]
		} else {
			throw new IllegalArgumentException("Compiling constraint for direction " + dir + " is not supported")
		}
		
	}

	static def dispatch List<Constraint> compile(GetEdgesOperator getEdgesOp) {
		val source = getEdgesOp.sourceVertexVariable
		val edge = getEdgesOp.edgeVariable
		val target = getEdgesOp.targetVertexVariable
		val direction = getEdgesOp.direction
		
		return compileEdgeConstraint(source, convertToConcreteEdgeVariable(edge), target, direction)
	}
	
	protected def static EdgeVariable convertToConcreteEdgeVariable(AbstractEdgeVariable edge) {
		if(! (edge instanceof EdgeVariable)){
			throw new IllegalArgumentException("Currently only EdgeVariables are supported among AbstractEdgeVariables.")
		}
		edge as EdgeVariable
	}

	// unary operators 
	static def dispatch List<Constraint> compile(AllDifferentOperator allDiffOp) {
		#[new AllDifferent]
	}

	static def dispatch List<Constraint> compile(DuplicateEliminationOperator dupElimOp) {
		#[new DuplicateElimination]
	}
	
	static def dispatch List<Constraint> compile(ExpandOperator expandOp) {
		val source = expandOp.sourceVertexVariable
		val edge = expandOp.edgeVariable
		val target = expandOp.targetVertexVariable
		val direction = expandOp.direction
		
		return compileEdgeConstraint(source, convertToConcreteEdgeVariable(edge), target, direction)
	}

}
