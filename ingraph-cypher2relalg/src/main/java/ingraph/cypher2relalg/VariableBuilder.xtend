package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.cypher2relalg.util.IngraphLogger
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.EdgeVariable
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.Variable
import relalg.VertexVariable
import relalg.LabelSetStatus
import ingraph.cypher2relalg.factories.EdgeLabelFactory
import java.util.ArrayList
import java.util.Iterator
import java.util.List
import org.eclipse.emf.common.util.EList
import relalg.BinaryLogicalOperatorType
import relalg.BinaryOperator
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.LabelSetStatus
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnionOperator
import relalg.VertexLabel
import relalg.VertexVariable


/**
 * This is the variable builder component of
 * the openCypher to relational algebra compiler.
 */
class VariableBuilder {
  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension IngraphLogger logger = new IngraphLogger(VariableBuilder.name)
  extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(logger)

  val RelalgContainer topLevelContainer

  extension ElementVariableUtil elementVariableUtil

  val VertexVariableFactory vertexVariableFactory
  val EdgeVariableFactory edgeVariableFactory

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
}
