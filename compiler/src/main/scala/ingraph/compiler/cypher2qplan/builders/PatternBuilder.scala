package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.BuilderUtil
import ingraph.model.{expr, qplan}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object PatternBuilder {
  def buildPattern(pattern: oc.PatternPart): qplan.QNode = {
    pattern match {
      case p: oc.RelationshipsPattern => buildPatternHelper(p.getNodePattern, p.getChain.asScala)
      case p: oc.PatternElement => buildPatternHelper(p.getNodepattern, p.getChain.asScala)
      case p: oc.PatternElementChain => qplan.QStub()
      // general PatternPart instance
      case p => qplan.QStub()
    }
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
    // FIXME: handle missing names, and introduce VariableBuilder-alike
    var lastAttr = expr.VertexAttribute(n.getVariable.getName, BuilderUtil.parseToVertexLabelSet(n.getNodeLabels))
    var lastNode: qplan.QNode = qplan.GetVertices(lastAttr)

    for(el <- chain) {
      val v = expr.VertexAttribute(el.getNodePattern.getVariable.getName, BuilderUtil.parseToVertexLabelSet(el.getNodePattern.getNodeLabels))

      val ev = el.getRelationshipPattern.getDetail.getVariable
      val en = if (ev == null) {
        "tea"
      } else {
        ev.getName
      }

      val e = expr.EdgeAttribute(en, BuilderUtil.parseToEdgeLabelSet(el.getRelationshipPattern.getDetail.getTypes))
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
