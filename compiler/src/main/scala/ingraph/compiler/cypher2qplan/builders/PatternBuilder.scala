package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.BuilderUtil
import ingraph.model.qplan
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object PatternBuilder {
  def buildPattern(pattern: oc.PatternPart): qplan.QNode = {
    pattern match {
      case p: oc.PatternElement => buildPatternHelper(p.getNodepattern, p.getChain.asScala)
      case p: oc.PatternElementChain => qplan.QStub()
      // general PatternPart instance
      case p => qplan.QStub()
    }
  }
  def buildPattern(pattern: oc.RelationshipsPattern): qplan.QNode = {
    buildPatternHelper(pattern.getNodePattern, pattern.getChain.asScala)
  }


  //  def dispatch Operator buildRelalg(PatternPart p, CompilerEnvironment ce) {
//    // TODO: handle variable assignment
//    if (p.part instanceof ShortestPath) {
//    }
//    if (p.part instanceof AllShortestPaths) {
//    }
//    if (p.^var !== null) {
//      ce.l.unsupported('Variable assignment not supported for PatternPart (in MATCH clause)')
//    }
//
//    // pass through variable assignment body to buildRelalg(PatternElement e)
//    buildRelalg(p.part, ce)
//  }
//
//  def static dispatch Operator buildRelalg(PatternElementChain ec, CompilerEnvironment ce) {
//    val patternElementChain_VertexVariable = ce.vb.buildVertexVariable(ec.nodePattern) => [
//    // parse map-like constraints if given
//    BuilderUtil.attachProperties(ec.nodePattern.properties, it, ce)
//    ]
//    val patternElementChain_EdgeVariable = ce.vb.buildEdgeVariable(ec.relationshipPattern.detail) => [
//    // parse map-like constraints if given
//    BuilderUtil.attachProperties(ec.relationshipPattern.detail?.properties, it, ce)
//    ]
//
//    modelFactory.createExpandOperator() => [
//    edgeVariable = patternElementChain_EdgeVariable;
//    direction = BuilderUtil.convertToDirection(ec.relationshipPattern)
//    targetVertexVariable = patternElementChain_VertexVariable;
//    ]
//
//  }

  /*
   * This will create the relational algebraic representation of a patternElement.
   *
   * This was factored out to handle PatternElement and RelationshipsPattern in the same code
   */
  def buildPatternHelper(n: oc.NodePattern, chain: Seq[oc.PatternElementChain]): qplan.QNode = {

    var lastAttr = AttributeBuilder.buildAttribute(n)
    var lastNode: qplan.QNode = qplan.GetVertices(lastAttr)

    for(el <- chain) {
      val v = AttributeBuilder.buildAttribute(el.getNodePattern)
      val e = AttributeBuilder.buildAttribute(el.getRelationshipPattern)
      lastNode = qplan.Expand(src = lastAttr,
        trg = v,
        edge = e,
        dir = BuilderUtil.convertToDirection(el.getRelationshipPattern),
        child = lastNode)
      lastAttr = v
    }
    lastNode
  }
}
