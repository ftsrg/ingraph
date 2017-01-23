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
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.xtend.lib.annotations.Accessors
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.Expression
import relalg.ExpressionVariable
import relalg.LabelSetStatus
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
  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension IngraphLogger logger = new IngraphLogger(VariableBuilder.name)

  val RelalgContainer topLevelContainer

  extension ElementVariableUtil elementVariableUtil

  @Accessors(PUBLIC_GETTER)
  val VertexVariableFactory vertexVariableFactory
  @Accessors(PUBLIC_GETTER)
  val EdgeVariableFactory edgeVariableFactory
  @Accessors(PUBLIC_GETTER)
  val ExpressionVariableFactory expressionVariableFactory

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
    this.expressionVariableFactory = new ExpressionVariableFactory(this.topLevelContainer)
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
  protected new(RelalgContainer topLevelContainer, VertexLabelFactory vertexLabelFactory, EdgeLabelFactory edgeLabelFactory, IngraphLogger logger) {
    this.logger=logger

    this.topLevelContainer = topLevelContainer

    this.vertexVariableFactory = new VertexVariableFactory(this.topLevelContainer)
    this.edgeVariableFactory = new EdgeVariableFactory(this.topLevelContainer)
    this.expressionVariableFactory = new ExpressionVariableFactory(this.topLevelContainer)
    this.vertexLabelFactory = vertexLabelFactory
    this.edgeLabelFactory = edgeLabelFactory

    this.elementVariableUtil = new ElementVariableUtil(this.topLevelContainer)
  }

  /**
   * Creates a new VariableBuilder instance that has new variable factory instances
   * but retains label factories and the topLevelContainer.
   */
  def cloneBuilderWithNewVariableFactories() {
    new VariableBuilder(topLevelContainer, vertexLabelFactory, edgeLabelFactory, logger)
  }

  def dispatch Variable buildRelalgVariable(ExpressionNodeLabelsAndPropertyLookup e) {
    val ev = buildRelalgVariable(e.left)
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

  def dispatch Variable buildRelalgVariable(VariableRef varRef) {
    switch varRef {
      // TODO: expression variables from return has the highest priority in name resolution for order by,
      // but they don't play a role when building later return items
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
    expressionVariableFactory.createElement(name, expression) => [
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
    if (vertexVariable.vertexLabelSet == null) {
      vertexVariable.vertexLabelSet = createVertexLabelSet
    }
    if (!vertexVariable.vertexLabelSet.vertexLabels.contains(label)) {
      vertexVariable.vertexLabelSet => [
        vertexLabels.add(label)
        status = LabelSetStatus.NON_EMPTY
      ]
    }
  }

  def protected ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
    if (edgeVariable.edgeLabelSet == null) {
      edgeVariable.edgeLabelSet = createEdgeLabelSet
    }
    if (!edgeVariable.edgeLabelSet.edgeLabels.contains(label)) {
      edgeVariable.edgeLabelSet => [
        edgeLabels.add(label) // TODO this is not correct, see #10
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

    if (edgeVariable.edgeLabelSet == null) {
      // no previous labelset was in effect
      edgeVariable.edgeLabelSet = createEdgeLabelSet
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
      // we had a previous labelset
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
  def dispatch buildVariableExpression(VariableRef v) {
    createVariableExpression => [
      variable = buildRelalgVariable(v)
      container = topLevelContainer
    ]
  }

  /**
   * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
   *
   * This builder method ensures that the new eVariableEpression instance
   * is registered to the container registered with this builder.
   */
  def dispatch buildVariableExpression(ExpressionNodeLabelsAndPropertyLookup v) {
    createVariableExpression => [
      variable = buildRelalgVariable(v)
      container = topLevelContainer
    ]
  }
}
