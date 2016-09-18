package ingraph.optimization.transformations

import ingraph.optimization.patterns.ExpandOperatorMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
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
import relalg.Container
import relalg.RelalgFactory

class Relalg2ReteTransformation {

	extension RelalgFactory relalgFactory = RelalgFactory.eINSTANCE
	extension BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory

	new() {
		// ViatraQueryLoggingUtil.setupConsoleAppenderForDefaultLogger()
		ViatraQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF)
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl());
	}

	def transform(Container container) {
		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.createResource(URI.createURI("queryplan.relalg"))
		resource.contents.add(container)
		val AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(
			new EMFScope(resourceSet))

		val transformation = BatchTransformation.forEngine(engine).build
		val statements = transformation.transformationStatements

		val expandVertexRule = expandVertexRule
		val expandOperatorRule = expandOperatorRule

		statements.fireWhilePossible(expandVertexRule)
		statements.fireWhilePossible(expandOperatorRule)

		return container
	}

	/**
	 * Replace the GetVertexOperator + ExpandOperator pairs with a single GetEdgesOperator
	 */
	def expandVertexRule() {
		createRule() //
		.precondition(ExpandVertexMatcher.querySpecification) //
		.action [ //
			val getVerticesOperator = getVerticesOperator
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = getVerticesOperator.vertexVariable
				targetVertexVariable = expandOperator.targetVertexVariable
				edgeVariable = expandOperator.edgeVariable
			]

			// change containing edge:
			// * (parent)-[:input]->(geo) or
			// * (parent)-[:leftInput]->(geo) or
			// * (parent)-[:rightInput]->(geo)
			val inputOpposite = expandOperator.eContainingFeature
			expandOperator.eContainer.eSet(inputOpposite, getEdgesOperator)
		].build
	}

	/**
	 * Replace a single expand operator with a GetEdgesOperator and a JoinOperator
	 */
	def getExpandOperatorRule() {
		createRule() //
		.precondition(ExpandOperatorMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.sourceVertexVariable
				targetVertexVariable = expandOperator.targetVertexVariable
				edgeVariable = expandOperator.edgeVariable
			]
			val joinOperator = createJoinOperator => [
				leftInput = expandOperator.input
				rightInput = getEdgesOperator
			]
			val inputOpposite = expandOperator.eContainingFeature
			expandOperator.eContainer.eSet(inputOpposite, joinOperator)
		].build
	}

}
