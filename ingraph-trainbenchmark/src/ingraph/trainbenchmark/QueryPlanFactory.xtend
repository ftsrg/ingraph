package ingraph.trainbenchmark

import relalg.RelalgFactory

/**
 * Query plan factory for generating query plans used in our Periodica Polytechnica submission.
 */
class QueryPlanFactory {

	protected val extension RelalgFactory factory = RelalgFactory.eINSTANCE

	protected val container = createRelationalAlgebraContainer

}
