package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.ExpressionVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.cypher2relalg.util.IngraphLogger
import java.util.ArrayList
import java.util.Collection
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.xtend.lib.annotations.Accessors
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.AttributeVariable
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.Expression
import relalg.ExpressionVariable
import relalg.LabelSetStatus
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.Variable
import relalg.VariableExpression
import relalg.VertexLabel
import relalg.VertexVariable

/**
 * This is the variable builder component of
 * the openCypher to relational algebra compiler.
 */
class VariableBuilder {
	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension IngraphLogger logger = new IngraphLogger(VariableBuilder.name)

	val RelalgContainer topLevelContainer

	extension ElementVariableUtil elementVariableUtil

	@Accessors(PUBLIC_GETTER)
	val VertexVariableFactory vertexVariableFactory
	@Accessors(PUBLIC_GETTER)
	val EdgeVariableFactory edgeVariableFactory
	/**
	 * Chain means that these are chained from the previous return-items.
	 */
	@Accessors(PUBLIC_GETTER)
	val ExpressionVariableFactory expressionVariableFactoryChain
	/**
	 * Extended below practically means they are created from the aliased return items.
	 */
	@Accessors(PUBLIC_GETTER)
	val ExpressionVariableFactory expressionVariableFactoryExtended

	val VertexLabelFactory vertexLabelFactory
	val EdgeLabelFactory edgeLabelFactory

	/**
	 * Constructor that allows propagating the topLevelContainer,
	 * but creates new factories for variables and labels.
	 *
	 * Use this to create separate builder for each SingleQuery.
	 */
	new(RelalgContainer topLevelContainer, IngraphLogger logger) {
		this.logger=logger

		this.topLevelContainer = topLevelContainer

		this.vertexVariableFactory = new VertexVariableFactory(this.topLevelContainer)
		this.edgeVariableFactory = new EdgeVariableFactory(this.topLevelContainer)
		this.expressionVariableFactoryChain = new ExpressionVariableFactory(this.topLevelContainer)
		this.expressionVariableFactoryExtended = new ExpressionVariableFactory(this.topLevelContainer)
		this.vertexLabelFactory = new VertexLabelFactory(this.topLevelContainer)
		this.edgeLabelFactory = new EdgeLabelFactory(this.topLevelContainer)

		this.elementVariableUtil = new ElementVariableUtil(this.topLevelContainer)
	}

	/**
	 * Constructor that allows propagating the topLevelContainer
	 * and the label factories, but creates new factories for variables.
	 *
	 * This is used to create separate builder for each SingleQuery.
	 * Outside of this class, you should to use the public helper
	 * {@link #cloneBuilderWithNewVariableFactories cloneBuilderWithNewVariableFactories}
	 * call instead.
	 */
	protected new(RelalgContainer topLevelContainer, VertexLabelFactory vertexLabelFactory, EdgeLabelFactory edgeLabelFactory, Collection<ExpressionVariable> expressionVariableChain, IngraphLogger logger) {
		this.logger=logger

		this.topLevelContainer = topLevelContainer

		this.vertexVariableFactory = new VertexVariableFactory(this.topLevelContainer)
		this.edgeVariableFactory = new EdgeVariableFactory(this.topLevelContainer)
		this.expressionVariableFactoryChain = new ExpressionVariableFactory(this.topLevelContainer)
		// Actual edgeVariables and vertexVariables are extracted from the ExpressionVariable
		//        and put right in the corresponding factories' element list
		expressionVariableChain.forEach[
			val f_it = it
			if (!f_it.hasInferredName) { // only those with actual name or alias can be re-used in the next context
				switch f_ite: f_it.expression {
					VariableExpression: switch v: f_ite.variable {
						VertexVariable: vertexVariableFactory.elements.put(f_it.name, v)
						EdgeVariable: edgeVariableFactory.elements.put(f_it.name, v)
						default: expressionVariableFactoryChain.elements.put(f_it.name, f_it)
					}
					default: expressionVariableFactoryChain.elements.put(f_it.name, f_it)
				}
			}
		]
		this.expressionVariableFactoryExtended = new ExpressionVariableFactory(this.topLevelContainer)
		this.vertexLabelFactory = vertexLabelFactory
		this.edgeLabelFactory = edgeLabelFactory

		this.elementVariableUtil = new ElementVariableUtil(this.topLevelContainer)
	}

	/**
	 * Creates a new VariableBuilder instance that has new variable factory instances
	 * but retains label factories and the topLevelContainer.
	 */
	def cloneBuilderWithNewVariableFactories() {
		new VariableBuilder(topLevelContainer, vertexLabelFactory, edgeLabelFactory, expressionVariableFactoryExtended.elements.values,  logger)
	}

	protected def AttributeVariable buildPropertyLookupHelper(Variable ev, ExpressionNodeLabelsAndPropertyLookup e) {
		switch ev {
			VertexVariable: {
				if (e.propertyLookups.length == 1) {
					ev.createAttribute(e.propertyLookups.get(0).propertyKeyName)
				} else {
					unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
					null
				}
			}
			EdgeVariable: {
				if (e.propertyLookups.length == 1) {
					ev.createAttribute(e.propertyLookups.get(0).propertyKeyName)
				} else {
					unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
					null
				}
			}
			default: {
				unrecoverableError('''Unsupported type received: «ev.class.name»''')
				null
			}
		}
	}

	def dispatch Variable buildRelalgVariable(ExpressionNodeLabelsAndPropertyLookup e) {
		val ev = buildRelalgVariable(e.left)
		buildPropertyLookupHelper(ev, e)
	}

	def dispatch Variable buildRelalgVariable(VariableRef varRef) {
		switch varRef {
			case vertexVariableFactory.hasElement(varRef.variableRef.name) &&
				edgeVariableFactory.hasElement(varRef.variableRef.name): {
				unrecoverableError('''Variable name ambigous: «varRef.variableRef.name»''')
				null
			}
			case vertexVariableFactory.hasElement(varRef.variableRef.name): {
				vertexVariableFactory.createElement(varRef.variableRef.name)
			}
			case edgeVariableFactory.hasElement(varRef.variableRef.name): {
				edgeVariableFactory.createElement(varRef.variableRef.name)
			}
			default: {
				unrecoverableError('''Variable name not found: «varRef.variableRef.name»''')
				null
			}
		}
	}

	def dispatch Variable buildRelalgVariableExtended(VariableRef varRef) {
		if (expressionVariableFactoryExtended.hasElement(varRef.variableRef.name)) {
			expressionVariableFactoryExtended.createElement(varRef.variableRef.name)
		} else {
			buildRelalgVariable(varRef)
		}
	}

	/**
	 * Expression variables from return has the highest priority in name resolution for order by,
	 * but they don't play a role when building later return items.
	 *
	 * These "*Extended" methods take Expression variables into account for name resolution.
	 */
	def dispatch Variable buildRelalgVariableExtended(ExpressionNodeLabelsAndPropertyLookup e) {
		val ev = buildRelalgVariableExtended(e.left)
		if (ev instanceof ExpressionVariable) {
			val evx = ev.expression
			if (evx instanceof VariableExpression) {
				buildPropertyLookupHelper(evx.variable, e)
			} else {
				unrecoverableError('''Property lookup attempt on an expression of type «ev.expression.class.name»''')
				null
			}
		} else {
			buildPropertyLookupHelper(ev, e)
		}
	}

	def buildEdgeVariable(RelationshipDetail r) {
		val edgeVariable = edgeVariableFactory.createElement(r)

		// add labels to the variable
		edgeVariable.combineLabelSet(r.types?.relTypeName, edgeLabelFactory)

		edgeVariable
	}

	def VertexVariable buildVertexVariable(NodePattern n) {
		val vertexVariable = vertexVariableFactory.createElement(n)

		// add labels to the variable
		n.nodeLabels?.nodeLabels?.forEach [
			vertexVariable.ensureLabel(vertexLabelFactory.createElement(it.labelName))
		]
		vertexVariable
	}

	def ExpressionVariable buildExpressionVariable(String name, Expression expression) {
		expressionVariableFactoryExtended.createElement(name, expression) => [
			hasInferredName = false
		]
	}

	def ExpressionVariable buildExpressionVariable(Expression expression) {
		val name = ExpressionNameInferencer.inferName(expression, logger)
		buildExpressionVariable(name, expression) => [
			hasInferredName = true
		]
	}

	def protected ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		if (!vertexVariable.vertexLabelSet.vertexLabels.contains(label)) {
			vertexVariable.vertexLabelSet => [
				vertexLabels.add(label)
				status = LabelSetStatus.NON_EMPTY
			]
		}
	}

	def void combineLabelSet(EdgeVariable edgeVariable, EList<String> labels, EdgeLabelFactory edgeLabelFactory) {
		/*
		 *  if we receive an empty labelset, this does not change the labelset constraint
		 * if the edge variable
		 */
		if (labels == null || labels.empty) {
			return
		}

		if (edgeVariable.edgeLabelSet.status == LabelSetStatus.EMPTY) {
			// no previous labelset constraint was in effect
			labels.forEach [
				val label = edgeLabelFactory.createElement(it)
				if (!edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
					edgeVariable.edgeLabelSet.edgeLabels.add(label)
				}
			]
			edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
					LabelSetStatus.EMPTY
				} else {
					LabelSetStatus.NON_EMPTY
				}
		} else {
			// we had a previous, non-empty labelset
			// we combine (intersect) the labelset received with the previous one
			val List<EdgeLabel> intersection = new ArrayList<EdgeLabel>

			labels.forEach [
				val label = edgeLabelFactory.createElement(it)
				if (!intersection.contains(label) && edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
					intersection.add(label)
				}
			]

			/*
			 * a tiny optimization: if a set has the same number of element
			 * before and after intersecting with an other set, it is the same.
			 *
			 * So we need to replace labelset only if their size changed
			 */

			if (edgeVariable.edgeLabelSet.edgeLabels.size != intersection.size) {
				edgeVariable.edgeLabelSet.edgeLabels.clear
				edgeVariable.edgeLabelSet.edgeLabels.addAll(intersection)
			}

			edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
					warning('''Contradicting labelset constraints found for edge variable «edgeVariable.name»''')
					LabelSetStatus.CONTRADICTING
				} else {
					LabelSetStatus.NON_EMPTY
				}

		}
	}

	/**
	 * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
	 *
	 * This builder method ensures that the new VariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def dispatch buildVariableExpression(Variable v) {
		createVariableExpression => [
			variable = v
			container = topLevelContainer
		]
	}

	/**
	 * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
	 *
	 * This builder method ensures that the new VariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def dispatch buildVariableExpression(VariableRef v, boolean useExpressionVariables) {
		createVariableExpression => [
			variable = if (useExpressionVariables) { buildRelalgVariableExtended(v) } else { buildRelalgVariable(v) }
			container = topLevelContainer
		]
	}

	/**
	 * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
	 *
	 * This builder method ensures that the new eVariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def dispatch buildVariableExpression(ExpressionNodeLabelsAndPropertyLookup v, boolean useExpressionVariables) {
		createVariableExpression => [
			variable = if (useExpressionVariables) { buildRelalgVariableExtended(v) } else { buildRelalgVariable(v) }
			container = topLevelContainer
		]
	}
}
