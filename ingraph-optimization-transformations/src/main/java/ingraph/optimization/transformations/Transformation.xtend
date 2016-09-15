package ingraph.optimization.transformations

import ingraph.optimization.patterns.ExpandVertexMatcher
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.apache.log4j.Level
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation

class Transformation {

	def static void main(String[] args) {
		//ViatraQueryLoggingUtil.setupConsoleAppenderForDefaultLogger()
		ViatraQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF)
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl());

		val queryPlan = TrainBenchmarkUtil.routeSensor

		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.createResource(URI.createURI("queryplan.relalg"))
		resource.contents.add(queryPlan)
		val AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(
			new EMFScope(resourceSet))

		val transformation = BatchTransformation.forEngine(engine).build
		val statements = transformation.transformationStatements

		val f = new BatchTransformationRuleFactory
		val rule = f.createRule() //
		.precondition(ExpandVertexMatcher.querySpecification) //
		.action [ //
			println(it)
		].build

		statements.fireAllCurrent(rule)
	}

}
