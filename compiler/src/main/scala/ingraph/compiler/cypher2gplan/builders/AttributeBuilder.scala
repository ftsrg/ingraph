package ingraph.compiler.cypher2gplan.builders

import java.util.concurrent.atomic.AtomicLong

import ingraph.compiler.cypher2gplan.util.{BuilderUtil, StringUtil}
import ingraph.compiler.exceptions._
import ingraph.model.expr.{EdgeLabelSet, ElementAttribute}
import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.mutable.ListBuffer

object AttributeBuilder {
  def buildAttribute(n: oc.NodePattern): expr.VertexAttribute = {
    val nls = BuilderUtil.parseToVertexLabelSet(n.getNodeLabels)
    val props = LiteralBuilder.buildProperties(n.getProperties)

    Option(n.getVariable) match {
      case Some(nv) => expr.VertexAttribute(nv.getName, nls, props, isAnonymous = false)
      case None => expr.VertexAttribute(generateUniqueName, nls, props, isAnonymous = true)
    }
  }

  def buildAttribute(el: oc.RelationshipPattern): expr.AbstractEdgeAttribute = {
    Option(el.getDetail) match {
      case Some(elDetail) => {
        val (name, isAnon) = Option(elDetail.getVariable) match {
          case Some(ev) => (ev.getName, false)
          case None => (generateUniqueName, true)
        }
        val els = BuilderUtil.parseToEdgeLabelSet(elDetail.getTypes)
        val props = LiteralBuilder.buildProperties(elDetail.getProperties)

        Option(elDetail.getRange) match {
          case Some(r) => {
            val minHops = StringUtil.toOptionInt(r.getLower)
            val maxHops = StringUtil.toOptionInt(r.getUpper) match {
              case None => minHops
              case x => x
            }
            expr.EdgeListAttribute(name, els, props, isAnonymous = isAnon, minHops, maxHops)
          }
          case None => expr.EdgeAttribute(name, els, props, isAnonymous = isAnon)
        }
      }
      case None => expr.EdgeAttribute(generateUniqueName, isAnonymous = true)
    }
  }

  def buildAttribute(e: oc.ExpressionPropertyLookup): UnresolvedAttribute = {
    UnresolvedAttribute(Seq(e.getLeft.asInstanceOf[oc.VariableRef].getVariableRef.getName, e.getPropertyLookups.get(0).getPropertyKeyName))
  }

  def buildAttribute(v: oc.VariableDeclaration): UnresolvedAttribute = {
    UnresolvedAttribute(v.getName)
  }

  def buildAttribute(vr: oc.VariableRef): UnresolvedAttribute = {
    buildAttribute(vr.getVariableRef)
  }

  // always use .getAndIncrement on this object
  private val generatedNameCounterNext = new AtomicLong

  def generateUniqueName: String = {
    s"_e${generatedNameCounterNext.getAndIncrement}"
  }

  /**
    * Extract vertex and edge attributes from the pattern.
    *
    * Check if filter is a chain of Expand operators on top of a single GetVertices, and attributes are properly chained
    * and if not, ExpandChainException is thrown.
    *
    * @param pattern
    * @throws ExpandChainException
    * @return
    */
  def extractAttributesFromExpandChain(pattern: gplan.GNode): ListBuffer[Expression] = {
    val relationshipVariableExpressions = ListBuffer.empty[Expression]
    var currOp = pattern
    var chainElem: ElementAttribute = null
    while (currOp != null) {
      currOp match {
        case gplan.GetVertices(v) if chainElem == null || v == chainElem => relationshipVariableExpressions.append(v); currOp = null; chainElem = null
        case gplan.Expand(src, trg, edge, _, child) if chainElem == null || trg == chainElem => relationshipVariableExpressions.append(trg, edge); currOp = child; chainElem = src
        case gplan.GetVertices(_) | gplan.Expand(_, _, _, _, _) => throw new ExpandChainException("We should never see this condition: Expand and Getvertices not properly chained")
        case e => throw new UnexpectedTypeException(e, "expand chain")
      }
    }

    relationshipVariableExpressions
  }


  //  protected def AttributeVariable buildPropertyLookupHelper(Variable ev, ExpressionNodeLabelsAndPropertyLookup e) {
//    if (e.propertyLookups.length == 1) {
//      val attributeName = e.propertyLookups.get(0).propertyKeyName
//
//      if (ev instanceof ExpressionVariable) {
//        val innerVariable = extractElementVariable(ev)
//        if (innerVariable === null) {
//          logger.unrecoverableError('''Can't find neither VertexVariable nor EdgeVariable wrapped into the ExpressionVariable, so can't build attribute variable.''')
//        }
//      }
//
//      switch ev {
//        VertexVariable
//        ,	EdgeVariable
//        ,	ExpressionVariable
//          : {
//          ev.createAttribute(attributeName)
//        }
//        default: {
//          logger.unrecoverableError('''Unsupported type received: «ev.class.name»''')
//          null
//        }
//      }
//
//    } else {
//      logger.unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
//      null
//    }
//  }
//
//  def dispatch Variable buildRelalgVariable(ExpressionNodeLabelsAndPropertyLookup e) {
//    val v = buildRelalgVariable(e.left)
//    buildPropertyLookupHelper(v, e)
//  }
//
//  def dispatch Variable buildRelalgVariable(VariableRef varRef) {
//    val useExpressionVariables = findOutExpressionVariableUsageFromContext(varRef)
//    buildRelalgVariableInternal(varRef, useExpressionVariables)
//  }
//
//  /**
//    * Variable lookup scope depends on the clause in which the variable is being resolved.
//    *
//    * UNWIND, ORDER BY and WITH...WHERE clauses use expression variables, others don't use them for name lookup
//    */
//  def findOutExpressionVariableUsageFromContext(EObject eo) {
//    EUtil.hasInContainerHierarchy(eo, #[typeof(Unwind)], typeof(Cypher))
//    || EUtil.hasInContainerHierarchy(eo, #[typeof(Order)], typeof(Cypher))
//    || EUtil.hasInContainerHierarchy(eo, #[typeof(Where), typeof(With)], typeof(Cypher))
//  }
//
//  /**
//    * Resolves a variable by its name.
//    *
//    * Expression variables from return has the highest priority in name resolution for order by
//    * and UNWIND, but they don't play a role when building later return items.
//    *
//    * @param useExpressionVariables indicate whether we should take expressionvariables into account for name resolution.
//    */
//  protected def Variable buildRelalgVariableInternal(VariableRef varRef, boolean useExpressionVariables) {
//    val v = findVariable(varRef.variableRef.name, useExpressionVariables)
//    if (v === null) {
//      logger.unrecoverableError('''Variable name not found: «varRef.variableRef.name»''')
//      null
//    } else {
//      v
//    }
//  }
//
//  def buildEdgeVariable(RelationshipDetail r) {
//    val edgeVariable = if (r?.range === null) {
//      edgeVariableFactory.createElement(r)
//    } else {
//      edgeListVariableFactory.createElement(r) => [
//      minHops = if (r?.range.lower === null) {
//        1
//      } else {
//        Integer.valueOf(r?.range.lower)
//      }
//      maxHops = if (r?.range.upper === null) {
//        modelFactory.createMaxHops() => [
//        maxHopsType = MaxHopsType.UNLIMITED
//        ]
//      } else {
//        modelFactory.createMaxHops() => [
//        maxHopsType = MaxHopsType.LIMITED
//        hops = Integer.valueOf(r?.range.upper)
//        ]
//      }
//      ]
//    }
//
//    // add labels to the variable
//    edgeVariable.combineLabelSet(r?.types?.relTypeName, edgeLabelFactory)
//
//    edgeVariable
//  }
//
//  def VertexVariable buildVertexVariable(NodePattern n) {
//    val vertexVariable = vertexVariableFactory.createElement(n)
//
//    // add labels to the variable
//    n.nodeLabels?.nodeLabels?.forEach [
//      vertexVariable.ensureLabel(vertexLabelFactory.createElement(it.labelName))
//    ]
//    vertexVariable
//  }
//
//  /**
//    * Wraps an expression into an ExpressionVariable with its name given or inferred.
//    *
//    * @param name the name, or null to have it inferred.
//    * @param expression the expression to wrap
//    *
//    * @return the ExpressionVariable itself.
//    */
//  def ExpressionVariable buildExpressionVariable(String name, Expression expression) {
//    val iName = if (name === null) {
//      ExpressionNameInferencer.inferName(expression, logger)
//    } else {
//      name
//    }
//
//    expressionVariableFactoryExtended.createElement(iName, expression) => [
//    hasInferredName = (name === null)
//    ]
//  }
//
//  def protected ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
//    if (!vertexVariable.vertexLabelSet.vertexLabels.contains(label)) {
//      vertexVariable.vertexLabelSet => [
//      vertexLabels.add(label)
//      status = LabelSetStatus.NON_EMPTY
//      ]
//    }
//  }
//
//  def void combineLabelSet(AbstractEdgeVariable edgeVariable, EList<String> labels, EdgeLabelFactory edgeLabelFactory) {
//    /*
//     * if we receive an empty labelset, this does not change the labelset constraint
//     * if the edge variable
//     */
//    if (labels === null || labels.empty) {
//      return
//    }
//
//    if (edgeVariable.edgeLabelSet.status == LabelSetStatus.EMPTY) {
//      // no previous labelset constraint was in effect
//      labels.forEach [
//      val label = edgeLabelFactory.createElement(it)
//      if (!edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
//        edgeVariable.edgeLabelSet.edgeLabels.add(label)
//      }
//      ]
//      edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
//        LabelSetStatus.EMPTY
//      } else {
//        LabelSetStatus.NON_EMPTY
//      }
//    } else {
//      // we had a previous, non-empty labelset
//      // we combine (intersect) the labelset received with the previous one
//      val List<EdgeLabel> intersection = new ArrayList<EdgeLabel>
//
//      labels.forEach [
//      val label = edgeLabelFactory.createElement(it)
//      if (!intersection.contains(label) && edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
//        intersection.add(label)
//      }
//      ]
//
//      /*
//       * a tiny optimization: if a set has the same number of element
//       * before and after intersecting with an other set, it is the same.
//       *
//       * So we need to replace labelset only if their size changed
//       */
//
//      if (edgeVariable.edgeLabelSet.edgeLabels.size != intersection.size) {
//        edgeVariable.edgeLabelSet.edgeLabels.clear
//        edgeVariable.edgeLabelSet.edgeLabels.addAll(intersection)
//      }
//
//      edgeVariable.edgeLabelSet.status = if (edgeVariable.edgeLabelSet.edgeLabels.empty) {
//        logger.warning('''Contradicting labelset constraints found for edge variable «edgeVariable.name»''')
//        LabelSetStatus.CONTRADICTING
//      } else {
//        LabelSetStatus.NON_EMPTY
//      }
//
//    }
//  }
//
//  /**
//    * Packs the appropriate variable into a VariableExpression.
//    *
//    * This builder method ensures that the new VariableEpression instance
//    * is registered to the container registered with this builder.
//    */
//  def buildVariableExpression(Variable v) {
//    modelFactory.createVariableExpression => [
//    variable = v
//    expressionContainer = topLevelContainer
//    ]
//  }
//
//  /**
//    * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
//    *
//    * This builder method ensures that the new VariableEpression instance
//    * is registered to the container registered with this builder.
//    */
//  def dispatch buildVariableExpression(VariableRef v, boolean useExpressionVariables) {
//    modelFactory.createVariableExpression => [
//    variable = if (useExpressionVariables) { buildRelalgVariableInternal(v, true) } else { buildRelalgVariable(v) }
//    expressionContainer = topLevelContainer
//    ]
//  }
//
//  /**
//    * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
//    *
//    * This builder method ensures that the new VariableEpression instance
//    * is registered to the container registered with this builder.
//    */
//  def dispatch buildVariableExpression(ExpressionNodeLabelsAndPropertyLookup e, boolean useExpressionVariables) {
//    modelFactory.createVariableExpression => [
//    variable = if (useExpressionVariables) {
//      val e_left = e.left
//      if (e_left instanceof VariableRef) {
//        val v = buildRelalgVariableInternal(e_left, true)
//        buildPropertyLookupHelper(v, e)
//      } else {
//        logger.unrecoverableError('''Unexpected type found as base type for property lookup. Expected: variable reference. Found: «e_left.class.name»''')
//        null
//      }
//    } else { buildRelalgVariable(e) }
//    expressionContainer = topLevelContainer
//    ]
//  }
//
//  def getVertexVariableFactoryElements() {
//    vertexVariableFactory.elements
//  }
//  def getEdgeVariableFactoryElements() {
//    edgeVariableFactory.elements
//  }
//
//  /**
//    * Finds and returns a variable by name in the variable registers,
//    * i.e. in the factories or in the chained variables.
//    *
//    * @param useExpressionVariables specifies whether to look into the expressionVariableFactory,
//    *        i.e. if we are interested in variables from the WITH/RETURN/UNWIND clauses of the current subquery.
//    *        Note, that chained variables are always looked up
//    *
//    * If not found or null was passed for name, null is returned.
//    */
//  def findVariable(String name, boolean useExpressionVariables) {
//    if (name === null) {
//      null
//    } else if (useExpressionVariables && expressionVariableFactoryExtended.hasElement(name)) {
//      expressionVariableFactoryExtended.getElement(name)
//    } else if (expressionVariableChain.get(name) !== null) {
//      expressionVariableChain.get(name)
//    } else if (vertexVariableFactory.hasElement(name)) {
//      vertexVariableFactory.getElement(name)
//    } else if (edgeVariableFactory.hasElement(name)) {
//      edgeVariableFactory.getElement(name)
//    } else if (edgeListVariableFactory.hasElement(name)) {
//      edgeListVariableFactory.getElement(name)
//    } else {
//      null
//    }
//  }
}
