package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeListVariableFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.ExpressionVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.EUtil
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.logger.IngraphLogger
import ingraph.relalg.util.ElementVariableExtractor
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Accessors
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.Order
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.Where
import org.slizaa.neo4j.opencypher.openCypher.With
import relalg.AbstractEdgeVariable
import relalg.AttributeVariable
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.Expression
import relalg.ExpressionVariable
import relalg.LabelSetStatus
import relalg.MaxHopsType
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.Variable
import relalg.VertexLabel
import relalg.VertexVariable

/**
 * This is the variable builder component of
 * the openCypher to relational algebra compiler.
 */
class VariableBuilder {

	/** The model factory for the relational graph algebra representation */
	val modelFactory = RelalgFactory.eINSTANCE
	val IngraphLogger logger // = new IngraphLogger(VariableBuilder.name)
	extension ElementVariableExtractor elementVariableExtractor = new ElementVariableExtractor

	@Accessors(PUBLIC_GETTER)
	val RelalgContainer topLevelContainer

	extension ElementVariableUtil elementVariableUtil

	val VertexVariableFactory vertexVariableFactory
	val EdgeVariableFactory edgeVariableFactory
	val EdgeListVariableFactory edgeListVariableFactory
	/**
	 * Chain means that these are chained from the previous return-items.
	 */
	val Map<String, ExpressionVariable> expressionVariableChain
	/**
	 * Extended below practically means they are created from the aliased return items
	 * or unwind item.
	 */
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
		this.edgeListVariableFactory = new EdgeListVariableFactory(this.topLevelContainer)
		this.expressionVariableChain = new HashMap
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
		this.edgeListVariableFactory = new EdgeListVariableFactory(this.topLevelContainer)
		this.expressionVariableChain = new HashMap
		expressionVariableChain.forEach[
			val f_it = it
			/**
			 * FIXME: deactivated if to add simple variable-wrapping ExpressionVariables regardless of having inferred name
			 *
			 * MATCH (segment:Segment)
			 * WHERE segment.length <= 0
			 * WITH segment
			 * RETURN DISTINCT segment, segment.length AS length
			 */
			//if (!f_it.hasInferredName) { // only those with actual name or alias can be re-used in the next context
				this.expressionVariableChain.put(f_it.name, f_it)
			//}
		]
		this.expressionVariableFactoryExtended = new ExpressionVariableFactory(this.topLevelContainer)
		this.vertexLabelFactory = vertexLabelFactory
		this.edgeLabelFactory = edgeLabelFactory

		this.elementVariableUtil = new ElementVariableUtil(this.topLevelContainer)
	}

	/**
	 * Creates a new VariableBuilder instance that has new variable factory instances
	 * but retains label factories and the topLevelContainer.
	 * 
	 * This also chains forward expression variables, i.e. those from a WITH or RETURN clause
	 */
	def cloneBuilderWithNewVariableFactories() {
		new VariableBuilder(topLevelContainer, vertexLabelFactory, edgeLabelFactory, expressionVariableFactoryExtended.elements.values,  logger)
	}

	protected def AttributeVariable buildPropertyLookupHelper(Variable ev, ExpressionNodeLabelsAndPropertyLookup e) {
		if (e.propertyLookups.length == 1) {
			val attributeName = e.propertyLookups.get(0).propertyKeyName

			if (ev instanceof ExpressionVariable) {
				val innerVariable = extractElementVariable(ev)
				if (innerVariable === null) {
					logger.unrecoverableError('''Can't find neither VertexVariable nor EdgeVariable wrapped into the ExpressionVariable, so can't build attribute variable.''')
				}
			}

			switch ev {
				VertexVariable
			,	EdgeVariable
			,	ExpressionVariable
				: {
					ev.createAttribute(attributeName)
				}
				default: {
					logger.unrecoverableError('''Unsupported type received: «ev.class.name»''')
					null
				}
			}

		} else {
			logger.unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
			null
		}
	}

	def dispatch Variable buildRelalgVariable(ExpressionNodeLabelsAndPropertyLookup e) {
		val v = buildRelalgVariable(e.left)
		buildPropertyLookupHelper(v, e)
	}

	def dispatch Variable buildRelalgVariable(VariableRef varRef) {
		val useExpressionVariables = findOutExpressionVariableUsageFromContext(varRef)
		buildRelalgVariableInternal(varRef, useExpressionVariables)
	}

	/**
	 * Variable lookup scope depends on the clause in which the variable is being resolved.
	 *
	 * UNWIND, ORDER BY and WITH...WHERE clauses use expression variables, others don't use them for name lookup
	 */
	def findOutExpressionVariableUsageFromContext(EObject eo) {
	  EUtil.hasInContainerHierarchy(eo, #[typeof(Unwind)], typeof(Cypher))
	  || EUtil.hasInContainerHierarchy(eo, #[typeof(Order)], typeof(Cypher))
	  || EUtil.hasInContainerHierarchy(eo, #[typeof(Where), typeof(With)], typeof(Cypher))
	}

	/**
	 * Resolves a variable by its name.
	 *
	 * Expression variables from return has the highest priority in name resolution for order by
	 * and UNWIND, but they don't play a role when building later return items.
	 *
	 * @param useExpressionVariables indicate whether we should take expressionvariables into account for name resolution.
	 */
	protected def Variable buildRelalgVariableInternal(VariableRef varRef, boolean useExpressionVariables) {
		val v = findVariable(varRef.variableRef.name, useExpressionVariables)
		if (v === null) {
			logger.unrecoverableError('''Variable name not found: «varRef.variableRef.name»''')
			null
		} else {
			v
		}
	}

	def buildEdgeVariable(RelationshipDetail r) {
		val edgeVariable = if (r?.range === null) {
			edgeVariableFactory.createElement(r)
		} else {
			edgeListVariableFactory.createElement(r) => [
				minHops = if (r?.range.lower === null) {
					1
				} else {
					Integer.valueOf(r?.range.lower)
				}
				maxHops = if (r?.range.upper === null) {
					modelFactory.createMaxHops() => [
						maxHopsType = MaxHopsType.UNLIMITED
					]
				} else {
					modelFactory.createMaxHops() => [
						maxHopsType = MaxHopsType.LIMITED
						hops = Integer.valueOf(r?.range.upper)
					]
				}
			]
		}

		// add labels to the variable
		edgeVariable.combineLabelSet(r?.types?.relTypeName, edgeLabelFactory)

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

	/**
	 * Wraps an expression into an ExpressionVariable with its name given or inferred.
	 *
	 * @param name the name, or null to have it inferred.
	 * @param expression the expression to wrap
	 *
	 * @return the ExpressionVariable itself.
	 */
	def ExpressionVariable buildExpressionVariable(String name, Expression expression) {
		val iName = if (name === null) {
			ExpressionNameInferencer.inferName(expression, logger)
		} else {
			name
		}

		expressionVariableFactoryExtended.createElement(iName, expression) => [
			hasInferredName = (name === null)
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

	def void combineLabelSet(AbstractEdgeVariable edgeVariable, EList<String> labels, EdgeLabelFactory edgeLabelFactory) {
		/*
		 * if we receive an empty labelset, this does not change the labelset constraint
		 * if the edge variable
		 */
		if (labels === null || labels.empty) {
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
					logger.warning('''Contradicting labelset constraints found for edge variable «edgeVariable.name»''')
					LabelSetStatus.CONTRADICTING
				} else {
					LabelSetStatus.NON_EMPTY
				}

		}
	}

	/**
	 * Packs the appropriate variable into a VariableExpression.
	 *
	 * This builder method ensures that the new VariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def buildVariableExpression(Variable v) {
		modelFactory.createVariableExpression => [
			variable = v
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
	 *
	 * This builder method ensures that the new VariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def dispatch buildVariableExpression(VariableRef v, boolean useExpressionVariables) {
		modelFactory.createVariableExpression => [
			variable = if (useExpressionVariables) { buildRelalgVariableInternal(v, true) } else { buildRelalgVariable(v) }
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
	 *
	 * This builder method ensures that the new VariableEpression instance
	 * is registered to the container registered with this builder.
	 */
	def dispatch buildVariableExpression(ExpressionNodeLabelsAndPropertyLookup e, boolean useExpressionVariables) {
		modelFactory.createVariableExpression => [
			variable = if (useExpressionVariables) {
				val e_left = e.left
				if (e_left instanceof VariableRef) {
					val v = buildRelalgVariableInternal(e_left, true)
					buildPropertyLookupHelper(v, e)
				} else {
					logger.unrecoverableError('''Unexpected type found as base type for property lookup. Expected: variable reference. Found: «e_left.class.name»''')
					null
				}
			} else { buildRelalgVariable(e) }
			expressionContainer = topLevelContainer
		]
	}

	def getVertexVariableFactoryElements() {
		vertexVariableFactory.elements
	}
	def getEdgeVariableFactoryElements() {
		edgeVariableFactory.elements
	}

	/**
	 * Finds and returns a variable by name in the variable registers,
	 * i.e. in the factories or in the chained variables.
	 *
	 * @param useExpressionVariables specifies whether to look into the expressionVariableFactory,
	 *        i.e. if we are interested in variables from the WITH/RETURN/UNWIND clauses of the current subquery. 
	 *        Note, that chained variables are always looked up
	 *
	 * If not found or null was passed for name, null is returned.
	 */
	def findVariable(String name, boolean useExpressionVariables) {
		if (name === null) {
			null
		} else if (useExpressionVariables && expressionVariableFactoryExtended.hasElement(name)) {
			expressionVariableFactoryExtended.getElement(name)
		} else if (expressionVariableChain.get(name) !== null) {
			expressionVariableChain.get(name)
		} else if (vertexVariableFactory.hasElement(name)) {
			vertexVariableFactory.getElement(name)
		} else if (edgeVariableFactory.hasElement(name)) {
			edgeVariableFactory.getElement(name)
		} else if (edgeListVariableFactory.hasElement(name)) {
		  edgeListVariableFactory.getElement(name)
		} else {
			null
		}
	}
}
