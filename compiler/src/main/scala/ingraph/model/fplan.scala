package ingraph.model.fplan

import ingraph.model.expr.ResolvableName
import ingraph.model.jplan
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.Attribute
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait FNode extends LogicalPlan {
  def extraAttributes: Seq[ResolvableName]
  def jnode: jplan.JNode
  def internalSchema: Seq[ResolvableName]
  override def children: Seq[FNode]
  override def output: Seq[Attribute] = jnode.output
}

abstract class LeafFNode extends GenericLeafNode[FNode] with FNode {}
abstract class UnaryFNode extends GenericUnaryNode[FNode] with FNode {
  override def internalSchema: Seq[ResolvableName] = child.internalSchema
}
abstract class BinaryFNode extends GenericBinaryNode[FNode] with FNode {}

trait JoinLike extends BinaryFNode {
  def internalCommon: Seq[ResolvableName] = left.internalSchema.filter(x => right.internalSchema.map(_.resolvedName).contains(x.resolvedName))
  lazy val leftMask  = SchemaToMap.schemaToIndices(this, left)
  lazy val rightMask = SchemaToMap.schemaToIndices(this, right)
}
trait EquiJoinLike extends JoinLike {
  override def internalSchema: Seq[ResolvableName] =
    left.internalSchema ++ right.internalSchema.filter(x => !left.internalSchema.map(_.resolvedName).contains(x.resolvedName))
}

// leaf nodes
case class GetVertices(extraAttributes: Seq[ResolvableName], jnode: jplan.GetVertices) extends LeafFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.output ++ extraAttributes
}

case class GetEdges(extraAttributes: Seq[ResolvableName], jnode: jplan.GetEdges) extends LeafFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.output ++ extraAttributes
}

case class GetEdgeLists(extraAttributes: Seq[ResolvableName], jnode: jplan.GetEdgeLists) extends LeafFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.output ++ extraAttributes
}

case class Dual(jnode: jplan.Dual) extends LeafFNode {
  override def internalSchema = Seq()
  override def extraAttributes: Seq[ResolvableName] = Seq()
}


// unary nodes
case class AllDifferent(extraAttributes: Seq[ResolvableName],
                        jnode: jplan.AllDifferent,
                        child: FNode
                        ) extends UnaryFNode {}

case class DuplicateElimination(extraAttributes: Seq[ResolvableName],
                                jnode: jplan.DuplicateElimination,
                                child: FNode
                               ) extends UnaryFNode {}

case class Production(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Production,
                      child: FNode
                     ) extends UnaryFNode {}

case class Projection(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Projection,
                      child: FNode
                     ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.projectList ++ extraAttributes
}

case class Grouping(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Grouping,
                      child: FNode
                     ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.projectList ++ extraAttributes
}

case class Selection(extraAttributes: Seq[ResolvableName],
                     jnode: jplan.Selection,
                     child: FNode
                    ) extends UnaryFNode {}

case class Unwind(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Unwind,
                  child: FNode
                    ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = Seq()
  // TODO fix this similarly to qplan and jplan
}

case class SortAndTop(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.SortAndTop,
                      child: FNode
                     ) extends UnaryFNode {}

// binary nodes
case class Union(extraAttributes: Seq[ResolvableName],
                 jnode: jplan.Union,
                 left: FNode,
                 right: FNode) extends BinaryFNode {
  override def internalSchema: Seq[ResolvableName] = left.internalSchema
}

case class AntiJoin(extraAttributes: Seq[ResolvableName],
                    jnode: jplan.AntiJoin,
                    left: FNode,
                    right: FNode
                   ) extends BinaryFNode with JoinLike {
  override def internalSchema: Seq[ResolvableName] = left.internalSchema
}

case class Join(extraAttributes: Seq[ResolvableName],
                jnode: jplan.Join,
                left: FNode,
                right: FNode
               ) extends BinaryFNode with EquiJoinLike {}

case class LeftOuterJoin(extraAttributes: Seq[ResolvableName],
                         jnode: jplan.LeftOuterJoin,
                         left: FNode,
                         right: FNode
                        ) extends BinaryFNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(extraAttributes: Seq[ResolvableName],
                              jnode: jplan.ThetaLeftOuterJoin,
                              left: FNode,
                              right: FNode) extends BinaryFNode with EquiJoinLike {}

case class Create(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Create,
                  child: FNode) extends UnaryFNode {}

case class Delete(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Delete,
                  child: FNode) extends UnaryFNode {}

case class Merge(extraAttributes: Seq[ResolvableName],
                 jnode: jplan.Merge,
                 child: FNode) extends UnaryFNode {}

case class SetNode(extraAttributes: Seq[ResolvableName],
                   jnode: jplan.SetNode,
                   child: FNode) extends UnaryFNode {}

case class Remove(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Remove,
                  child: FNode) extends UnaryFNode {}


object SchemaToMap {
  def schemaToIndices(node: JoinLike, side: FNode): Seq[Int] = {
    val sideIndices = schemaToMapNames(side)
    node.internalCommon.map(attr => sideIndices(attr.name))
  }

  def schemaToMapNames(n: FNode): Map[String, Int] = {
    n.internalSchema.zipWithIndex.map(f => f._1.name -> f._2).toMap
  }
}
