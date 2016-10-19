package ingraph.trainbenchmark

import ingraph.relalg.util.SchemaInferencer
import relalg.RelalgFactory

/**
 * Query plan factory for generating query plans used in our Periodica Polytechnica submission.
 */
class QueryPlanFactory {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE
	protected extension SchemaInferencer schemaInferencer = new SchemaInferencer
	
	protected val container = createRelalgContainer

}
