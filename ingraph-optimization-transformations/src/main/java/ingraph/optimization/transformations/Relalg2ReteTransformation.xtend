package ingraph.optimization.transformations

import ingraph.optimization.patterns.ExpandOperatorMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
import ingraph.relalg.util.SchemaInferencer
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
import relalg.ExpandOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryOperator
import relalg.BinaryOperator

class Relalg2ReteTransformation {

	val extension RelalgFactory relalgFactory = RelalgFactory.eINSTANCE
	val extension BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory

	new() {
		// ViatraQueryLoggingUtil.setupConsoleAppenderForDefaultLogger()
		ViatraQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF)
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl())
	}
	
	def void changeOperator(Operator parentOperator, Operator currentOperator, Operator newOperator) {
		switch parentOperator {
			UnaryOperator: {
				parentOperator.input = newOperator
			}
			BinaryOperator: {
				if (parentOperator.getLeftInput.equals(currentOperator)) {
					parentOperator.leftInput = newOperator	
				}
				if (parentOperator.getRightInput.equals(currentOperator)) {
					parentOperator.rightInput = newOperator	
				}
			}
		}
	}

	def transformToRete(RelalgContainer container) {
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
			//println("expand vertex rule fired")
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]
			
			changeOperator(parentOperator, expandOperator, getEdgesOperator)
		].build
	}

	/**
	 * Replace a single expand operator with a GetEdgesOperator and a JoinOperator
	 */
	def expandOperatorRule() {
		createRule() //
		.precondition(ExpandOperatorMatcher.querySpecification) //
		.action [ //
			//println("expandoperator rule fired")
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]
			val joinOperator = createJoinOperator => [
				leftInput = expandOperator.getInput
				rightInput = getEdgesOperator
			]
			
			changeOperator(parentOperator, expandOperator, joinOperator)
		].build
	}

	def source(ExpandOperator expandOperator) {
		switch (expandOperator.direction) {
			case BOTH,
			case IN: {
				return expandOperator.targetVertexVariable
			}
			case OUT: {
				return expandOperator.sourceVertexVariable
			}
		}
	}

	def target(ExpandOperator expandOperator) {
		switch (expandOperator.direction) {
			case BOTH,
			case IN: {
				return expandOperator.sourceVertexVariable
			}
			case OUT: {
				return expandOperator.targetVertexVariable
			}
		}
	}

}
