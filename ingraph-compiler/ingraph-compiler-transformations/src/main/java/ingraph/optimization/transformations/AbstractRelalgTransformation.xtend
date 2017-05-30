package ingraph.optimization.transformations

import java.io.Closeable
import java.io.IOException
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import org.eclipse.xtext.EcoreUtil2
import relalg.BinaryOperator
import relalg.Direction
import relalg.ExpandOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryOperator

abstract class AbstractRelalgTransformation implements Closeable {

	protected val extension RelalgFactory relalgFactory = RelalgFactory.eINSTANCE
	protected val extension BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory

	protected val RelalgContainer container
	protected val AdvancedViatraQueryEngine engine
	protected val BatchTransformationStatements statements

	new(RelalgContainer container) {
		val logger = Logger.getRootLogger
		logger.setLevel(Level.ERROR)
		ViatraQueryLoggingUtil.setExternalLogger(logger)
		
		this.container = container
		val scope = new EMFScope(container)
		engine = AdvancedViatraQueryEngine.createUnmanagedEngine(scope)
		statements = BatchTransformation.forEngine(engine).build.transformationStatements
	}

	protected def void changeChildOperator(Operator parentOperator, Operator currentOperator, Operator newOperator) {
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
		
		if (currentOperator instanceof ExpandOperator) {
			EcoreUtil2.delete(currentOperator)
		}
	}

	protected def source(ExpandOperator expandOperator) {
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

	protected def target(ExpandOperator expandOperator) {
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

	override close() throws IOException {
		engine?.dispose
	}

	protected def normalizeDirection(Direction direction) {
		switch (direction) {
			case BOTH: {
				return Direction.BOTH
			}
			default: {
				return Direction.OUT
			}
		}
	}	
}
