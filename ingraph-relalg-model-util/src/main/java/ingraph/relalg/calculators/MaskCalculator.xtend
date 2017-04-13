package ingraph.relalg.calculators

import ingraph.relalg.util.SchemaToMap
import relalg.AbstractJoinOperator
import relalg.NullaryOperator
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator

class MaskCalculator {
	
	extension SchemaToMap schemaToMap = new SchemaToMap  
	
	/**
	 * calculateTuples
	 */
	// nullary operators
	def dispatch void calculateTuples(NullaryOperator op) {
		// do nothing
	}
	
	// unary operators
	def dispatch void calculateTuples(UnaryOperator op) {
		// do nothing    
	}
	
	// binary operators
	def dispatch void calculateTuples(AbstractJoinOperator op) {
		val leftIndices = op.leftInput.schemaToMapNames
		val rightIndices = op.rightInput.schemaToMapNames
		
		op.commonVariables.forEach[ variable |
			op.leftMask.add(leftIndices.get(variable.name))
			op.rightMask.add(rightIndices.get(variable.name))
		]
	}
	
	def dispatch void calculateTuples(UnionOperator op) {
		// do nothing
	}

}