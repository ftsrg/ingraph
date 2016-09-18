package ingraph.optimization.transformations

import ingraph.trainbenchmark.TrainBenchmarkUtil

class TransformationMain {

	def static void main(String[] args) {
		val rawQueryPlan = TrainBenchmarkUtil.routeSensor

		val t = new Transformation
		val transformedQueryPlan = t.transform(rawQueryPlan)
	
		val inferencer = new SchemaInferencer
		val queryPlanWithSchema = inferencer.addSchemaInformation(transformedQueryPlan)
	}

}
