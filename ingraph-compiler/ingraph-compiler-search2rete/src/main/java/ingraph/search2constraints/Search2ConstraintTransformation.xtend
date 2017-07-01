 package ingraph.search2constraints

import com.google.common.collect.Sets
import ingraph.logger.IngraphLogger
import ingraph.optimization.transformations.AbstractRelalgTransformation
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import ingraph.search2constraints.constraints.Constraint
import relalg.RelalgContainer

class Search2ConstraintTransformation extends AbstractRelalgTransformation {

	extension IngraphLogger logger = new IngraphLogger(Search2ConstraintTransformation.name)
	extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor

	new(RelalgContainer container) {
		super(container) 
	}
	
	def transformToConstraints() {
		info("Transforming relational algebra expression to constraints")		

		// Currently no transformation is done on the inpot relalg. tree
		//statements.fireWhilePossible(getVerticesAndExpandOperatorRule)
		
		val constraints = Sets.<Constraint>newHashSet
		container.rootExpression.traverse[
			constraints.addAll(Relalg2ConstraintCompiler.compile(it))
		]

		return constraints
	}
	
}
