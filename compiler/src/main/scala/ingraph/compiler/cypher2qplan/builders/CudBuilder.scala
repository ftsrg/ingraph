package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.BuilderUtil
import ingraph.model.expr._
import ingraph.model.expr.types.VertexLabel
import ingraph.model.qplan
import ingraph.model.qplan.QNode
import org.slizaa.neo4j.opencypher.openCypher.{RemoveItem, SetItem}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * This is the builder for Create/Update/Delete clauses of the openCypher to relational algebra compiler.
  */
object CudBuilder {

  /**
    * Build and return a create operator from the CREATE clause and attach p_input to its input.
    */
  def buildCreateOperator(u0: oc.Create, child: qplan.QNode): QNode = {
    val attributes = u0.getPattern.getPatterns.asScala.flatMap {
      // Iterate through the comma-separated patterns of the create clause
      case pe: oc.PatternElement => Some(pe)
      case _ => None
    }.flatMap( pe => {
      val edgesAndVertices: ArrayBuffer[ElementAttribute] = ArrayBuffer.empty

      var chainVertex: VertexAttribute = AttributeBuilder.buildAttribute(pe.getNodepattern)
      edgesAndVertices.append(chainVertex)

      pe.getChain.asScala.toSeq.foreach(pec => {
        val nextVertex: VertexAttribute = AttributeBuilder.buildAttribute(pec.getNodePattern)
        val edgeAttribute: EdgeAttribute = AttributeBuilder.buildAttribute(pec.getRelationshipPattern) match {
          case e: EdgeAttribute => e
          case _ => throw new RuntimeException(s"Single edge pattern required when CREATE'ing relationships")
        }
        val direction: Direction = BuilderUtil.convertToDirection(pec.getRelationshipPattern)
        // put vertex before the edge itself
        edgesAndVertices.append(nextVertex, RichEdgeAttribute(chainVertex, nextVertex, edgeAttribute, direction))
        chainVertex = nextVertex
      })

      edgesAndVertices
    })

    return qplan.Create(attributes, child)


//      val t0 = ce.vb.vertexVariableFactoryElements.containsKey(u2.nodepattern.variable?.name)
//      val u4 = buildCreateNodePattern(u2.nodepattern, ce)
//      if (!t0) {
//        u1.elements.add(u4)
//      }
//      var lastVertexVariable = (u4.expression as VariableExpression).variable as VertexVariable
//
//      for (element: u2.chain) {
//        val t1 = ce.vb.vertexVariableFactoryElements.containsKey(element.nodePattern.variable?.name)
//        val u5 = buildCreateNodePattern(element.nodePattern, ce)
//        if (!t1) {
//          u1.elements.add(u5)
//        }
//        val t2 = ce.vb.edgeVariableFactoryElements.containsKey(element.relationshipPattern.detail?.variable?.name)
//        val u6 = buildCreateRelationshipPattern(element.relationshipPattern, lastVertexVariable, (u5.expression as VariableExpression).variable as VertexVariable, ce)
//        if (!t2) {
//          u1.elements.add(u6)
//        }
//        lastVertexVariable = (u5.expression as VariableExpression).variable as VertexVariable
//      }
  }

  /**
    * Provide the vertices for CREATE operator.
    */
  // TODO properties
  def buildCreateNodePattern(nodepattern: oc.NodePattern) {
//    val u0 = modelFactory.createVariableExpression => [
//      val vertexVariable = ce.vb.buildVertexVariable(nodepattern)
//      BuilderUtil.attachProperties(nodepattern.properties, vertexVariable, ce)
//      variable = vertexVariable
//      expressionContainer = ce.tlc
//    ]
    AttributeBuilder.buildAttribute(nodepattern.getVariable)
  }

//  protected def static buildCreateOperator(Create u0, Operator p_input, CompilerEnvironment ce) {
//    val u1 = modelFactory.createCreateOperator => [
//    input = p_input
//    ]
//    for (_u2: u0.pattern.patterns) {
//      val u2 = _u2 as PatternElement
//      if (u2 === null) {
//        ce.l.unrecoverableError('''PatternElement expected at create, but received «_u2.class.name»''')
//      }
//      val t0 = ce.vb.vertexVariableFactoryElements.containsKey(u2.nodepattern.variable?.name)
//      val u4 = buildCreateNodePattern(u2.nodepattern, ce)
//      if (!t0) {
//        u1.elements.add(u4)
//      }
//      var lastVertexVariable = (u4.expression as VariableExpression).variable as VertexVariable
//      for (element: u2.chain) {
//        val t1 = ce.vb.vertexVariableFactoryElements.containsKey(element.nodePattern.variable?.name)
//        val u5 = buildCreateNodePattern(element.nodePattern, ce)
//        if (!t1) {
//          u1.elements.add(u5)
//        }
//        val t2 = ce.vb.edgeVariableFactoryElements.containsKey(element.relationshipPattern.detail?.variable?.name)
//        val u6 = buildCreateRelationshipPattern(element.relationshipPattern, lastVertexVariable, (u5.expression as VariableExpression).variable as VertexVariable, ce)
//        if (!t2) {
//          u1.elements.add(u6)
//        }
//        lastVertexVariable = (u5.expression as VariableExpression).variable as VertexVariable
//      }
//    }
//    u1
//  }

  /**
    * Provide the edges for CREATE operator.
    */
//  protected def static ExpressionVariable buildCreateRelationshipPattern(RelationshipPattern relationshippattern, VertexVariable source, VertexVariable target, CompilerEnvironment ce) {
//    val u0 = modelFactory.createNavigationDescriptor => [
//    edgeVariable = ce.vb.buildEdgeVariable(relationshippattern.detail)
//    BuilderUtil.attachProperties(relationshippattern.detail.properties, edgeVariable, ce)
//    sourceVertexVariable = source
//    targetVertexVariable = target
//    direction = BuilderUtil.convertToDirection(relationshippattern)
//    expressionContainer = ce.tlc
//    ]
//    val u1 = ce.vb.buildExpressionVariable(u0.edgeVariable.name, u0)
//    u1
//  }

  /**
    * Provide the vertices for CREATE operator.
    */
//  protected def static ExpressionVariable buildCreateNodePattern(NodePattern nodepattern, CompilerEnvironment ce) {
//    val u0 = modelFactory.createVariableExpression => [
//    val vertexVariable = ce.vb.buildVertexVariable(nodepattern)
//    BuilderUtil.attachProperties(nodepattern.properties, vertexVariable, ce)
//    variable = vertexVariable
//    expressionContainer = ce.tlc
//    ]
//    val u1 = ce.vb.buildExpressionVariable(u0.variable.name, u0)
//    u1
//  }

  /**
    * Build and return a delete operator from the DELETE clause and attach child to its input.
    */
  def buildDeleteOperator(element: oc.Delete, child: qplan.QNode): QNode = {
    val attributes = element.getExpressions.asScala.flatMap {
      case v: oc.VariableRef => Some(AttributeBuilder.buildAttribute(v))
      case _ => None
    }
    qplan.Delete(attributes, element.isDetach, child)
  }

  /**
    * Build and return a merge operator from the MERGE clause and attach child to its input.
    */
  def buildMergeOperator(element: oc.Merge, child: qplan.QNode): QNode = {
    qplan.Merge(null, child)
  }

  /**
    * Build and return a remove operator from the SET clause and attach child to its input.
    */
  def buildSetOperator(element: oc.Set, child: qplan.QNode): QNode = {
    val vertexLabelUpdates = element.getSetItems.asScala.map(
      r => r match {
        //case pe: oc.PropertyExpression => ???
        case r: SetItem => {
          val vertex = AttributeBuilder.buildAttribute(r.getVariable).asInstanceOf[VertexAttribute] // here come the
          // ClassCastException
          val labels: Set[VertexLabel] = r.getNodeLabels.getNodeLabels.asScala.map(_.getLabelName).toSet
          VertexLabelUpdate(vertex, labels)
        }
      }
    ).toSet
    qplan.SetNode(vertexLabelUpdates, child)
  }

  /**
    * Build and return a remove operator from the REMOVE clause and attach child to its input.
    */
  def buildRemoveOperator(element: oc.Remove, child: qplan.QNode): QNode = {
    val vertexLabelUpdates = element.getRemoveItems.asScala.map(
      r => r match {
        //case pe: oc.PropertyExpression => ???
        case r: RemoveItem => {
          val vertex = AttributeBuilder.buildAttribute(r.getVariable).asInstanceOf[VertexAttribute] // here come the
          // ClassCastException
          val labels: Set[VertexLabel] = r.getNodeLabels.getNodeLabels.asScala.map(_.getLabelName).toSet
          VertexLabelUpdate(vertex, labels)
        }
      }
    ).toSet
    qplan.Remove(vertexLabelUpdates, child)
  }

}
