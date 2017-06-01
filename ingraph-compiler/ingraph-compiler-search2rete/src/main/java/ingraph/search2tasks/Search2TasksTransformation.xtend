package ingraph.search2tasks

import ingraph.logger.IngraphLogger
import ingraph.optimization.transformations.AbstractRelalgTransformation
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import relalg.RelalgContainer

class Search2TasksTransformation extends AbstractRelalgTransformation {

	extension IngraphLogger logger = new IngraphLogger(Search2TasksTransformation.name)
	extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor

	new(RelalgContainer container) {
		super(container)
	}
	
	def transformToTasks() {
		info("Transforming relational algebra expression to tasks")		
		
		container.rootExpression.traverse[
			println(it)
		]
		
		return container
	}
	
}
