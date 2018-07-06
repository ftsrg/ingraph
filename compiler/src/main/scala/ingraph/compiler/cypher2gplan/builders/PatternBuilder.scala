package ingraph.compiler.cypher2gplan.builders

import ingraph.compiler.cypher2gplan.util.BuilderUtil
import ingraph.model.gplan
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object PatternBuilder {
  def buildPattern(pattern: oc.PatternPart): gplan.GNode = {
    pattern match {
      case p: oc.PatternElement => buildPatternHelper(p.getNodepattern, p.getChain.asScala)
      case p: oc.PatternElementChain => gplan.GStub()
      // general PatternPart instance
      case p => gplan.GStub()
    }
  }
  def buildPattern(pattern: oc.RelationshipsPattern): gplan.GNode = {
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
  def buildPatternHelper(n: oc.NodePattern, chain: Seq[oc.PatternElementChain]): gplan.GNode = {
    val firstAttr = AttributeBuilder.buildAttribute(n)
    val firstNode: gplan.GNode = gplan.GetVertices(firstAttr)

    chain.foldLeft[gplan.GNode](firstNode)((lastNode, el) => {
      val v = AttributeBuilder.buildAttribute(el.getNodePattern)
      val e = AttributeBuilder.buildAttribute(el.getRelationshipPattern)
      val srcAttr = lastNode match {
        case e: gplan.Expand => e.trg
        case gv: gplan.GetVertices => gv.v
      }
      gplan.Expand(src = srcAttr,
        trg = v,
        edge = e,
        dir = BuilderUtil.convertToDirection(el.getRelationshipPattern),
        child = lastNode)
    })
  }
}
