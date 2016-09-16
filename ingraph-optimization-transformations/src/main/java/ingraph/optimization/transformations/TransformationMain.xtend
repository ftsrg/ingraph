package ingraph.optimization.transformations

import ingraph.trainbenchmark.TrainBenchmarkUtil

class TransformationMain {

	def static void main(String[] args) {
		val t = new Transformation
		t.transform(TrainBenchmarkUtil.routeSensor)
	}

}
