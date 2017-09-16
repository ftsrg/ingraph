package ingraph.compiler.cypher2qplan.builders

import javax.imageio.metadata.IIOMetadataFormat

import ingraph.model.qplan
import ingraph.model.qplan.QNode
import org.slizaa.neo4j.opencypher.openCypher.{Create, Delete, VariableRef}

import scala.collection.JavaConverters._

/**
  * This is the builder for Create/Update/Delete clauses of the openCypher to relational algebra compiler.
  */
object CudBuilder {

  /**
    * Build and return a create operator from the CREATE clause and attach p_input to its input.
    */
//  def buildCreateOperator(u0: Create, child: qplan.QNode) {
//      val u1 = modelFactory.createCreateOperator => [
//        input = p_input
//      ]
//      for (_u2: u0.pattern.patterns) {
//        val u2 = _u2 as PatternElement
//        if (u2 === null) {
//          ce.l.unrecoverableError('''PatternElement expected at create, but received «_u2.class.name»''')
//        }
//        val t0 = ce.vb.vertexVariableFactoryElements.containsKey(u2.nodepattern.variable?.name)
//        val u4 = buildCreateNodePattern(u2.nodepattern, ce)
//        if (!t0) {
//          u1.elements.add(u4)
//        }
//        var lastVertexVariable = (u4.expression as VariableExpression).variable as VertexVariable
//        for (element: u2.chain) {
//          val t1 = ce.vb.vertexVariableFactoryElements.containsKey(element.nodePattern.variable?.name)
//          val u5 = buildCreateNodePattern(element.nodePattern, ce)
//          if (!t1) {
//            u1.elements.add(u5)
//          }
//          val t2 = ce.vb.edgeVariableFactoryElements.containsKey(element.relationshipPattern.detail?.variable?.name)
//          val u6 = buildCreateRelationshipPattern(element.relationshipPattern, lastVertexVariable, (u5.expression as VariableExpression).variable as VertexVariable, ce)
//          if (!t2) {
//            u1.elements.add(u6)
//          }
//          lastVertexVariable = (u5.expression as VariableExpression).variable as VertexVariable
//        }
//      }
//      u1
//  }
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
  def buildDeleteOperator(element: Delete, child: qplan.QNode): QNode = {
    val attributes = element.getExpressions.asScala.flatMap {
      case v: VariableRef => Some(AttributeBuilder.buildAttribute(v))
      case _ => None
    }
    qplan.Delete(attributes, element.isDetach, child)
  }

}
