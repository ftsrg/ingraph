package ingraph.model.fplan

import ingraph.compiler.qplan2jplan.SchemaInferencer
import ingraph.model.jplan
import ingraph.model.jplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

// abstract classes and traits
trait FNode extends LogicalPlan {
  def extraAttributes: Seq[NamedExpression]
  def jnode: JNode
  def internalSchema: Seq[NamedExpression]
  override def children: Seq[FNode]
  override def output: Seq[Attribute] = jnode.output
}

abstract class LeafFNode extends GenericLeafNode[FNode] with FNode {}
abstract class UnaryFNode extends GenericUnaryNode[FNode] with FNode {
  override def internalSchema: Seq[NamedExpression] = child.internalSchema
}
abstract class BinaryFNode extends GenericBinaryNode[FNode] with FNode {}

trait JoinLike extends BinaryFNode {
  def internalCommon: Seq[NamedExpression] = left.internalSchema.filter(x => right.internalSchema.map(_.name).contains(x.name))
  lazy val leftMask  = SchemaToMap.schemaToIndices(this, left)
  lazy val rightMask = SchemaToMap.schemaToIndices(this, right)
}
trait EquiJoinLike extends JoinLike {
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema.filter(x => !left.internalSchema.map(_.name).contains(x.name))
}

// leaf nodes
case class GetVertices(extraAttributes: Seq[NamedExpression], jnode: jplan.GetVertices) extends LeafFNode {
  override def internalSchema: Seq[NamedExpression] = jnode.output ++ extraAttributes
}

case class GetEdges(extraAttributes: Seq[NamedExpression], jnode: jplan.GetEdges) extends LeafFNode {
  override def internalSchema: Seq[NamedExpression] = jnode.output ++ extraAttributes
}

case class Dual(jnode: jplan.Dual) extends LeafFNode {
  override def internalSchema = Seq()
  override def extraAttributes: Seq[NamedExpression] = Seq()
}


// unary nodes
case class AllDifferent(extraAttributes: Seq[NamedExpression],
                        jnode: jplan.AllDifferent,
                        child: FNode
                        ) extends UnaryFNode {}

case class DuplicateElimination(extraAttributes: Seq[NamedExpression],
                                jnode: jplan.DuplicateElimination,
                                child: FNode
                               ) extends UnaryFNode {}

case class Production(extraAttributes: Seq[NamedExpression],
                      jnode: jplan.Production,
                      child: FNode
                     ) extends UnaryFNode {}

case class Projection(extraAttributes: Seq[NamedExpression],
                      jnode: jplan.Projection,
                      child: FNode
                     ) extends UnaryFNode {
  ///// !!!!!!!
  override def internalSchema: Seq[NamedExpression] = jnode.projectList.flatMap(SchemaInferencer.extractAttributes(_))
  //override def internalSchema: Seq[NamedExpression] = inode.projectList
}

case class Selection(extraAttributes: Seq[NamedExpression],
                     jnode: jplan.Selection,
                     child: FNode
                    ) extends UnaryFNode {}

case class Unwind(extraAttributes: Seq[NamedExpression],
                  jnode: jplan.Unwind,
                  child: FNode
                    ) extends UnaryFNode {
  override def internalSchema: Seq[NamedExpression] = Seq()
  // TODO fix this similarly to qplan and jplan
}

case class SortAndTop(extraAttributes: Seq[NamedExpression],
                      jnode: jplan.SortAndTop,
                      child: FNode
                     ) extends UnaryFNode {}

// binary nodes
case class Union(extraAttributes: Seq[NamedExpression],
                 jnode: jplan.Union,
                 left: FNode,
                 right: FNode) extends BinaryFNode {
  override def internalSchema: Seq[NamedExpression] = left.internalSchema
}

case class AntiJoin(extraAttributes: Seq[NamedExpression],
                    jnode: jplan.AntiJoin,
                    left: FNode,
                    right: FNode
                   ) extends BinaryFNode with JoinLike {
  override def internalSchema: Seq[NamedExpression] = left.internalSchema
}

case class Join(extraAttributes: Seq[NamedExpression],
                jnode: jplan.Join,
                left: FNode,
                right: FNode
               ) extends BinaryFNode with EquiJoinLike {}

case class LeftOuterJoin(extraAttributes: Seq[NamedExpression],
                         jnode: jplan.LeftOuterJoin,
                         left: FNode,
                         right: FNode
                        ) extends BinaryFNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(
                               extraAttributes: Seq[NamedExpression],
                               jnode: jplan.ThetaLeftOuterJoin,
                               left: FNode,
                               right: FNode) extends BinaryFNode with EquiJoinLike {}

case class Create(extraAttributes: Seq[NamedExpression],
                  jnode: jplan.Create,
                  child: FNode) extends UnaryFNode {}

case class Delete(extraAttributes: Seq[NamedExpression],
                  jnode: jplan.Delete,
                  child: FNode) extends UnaryFNode {}

case class Merge(extraAttributes: Seq[NamedExpression],
                 jnode: jplan.Merge,
                 child: FNode) extends UnaryFNode {}

case class SetNode(extraAttributes: Seq[NamedExpression],
                   jnode: jplan.SetNode,
                   child: FNode) extends UnaryFNode {}

case class Remove(extraAttributes: Seq[NamedExpression],
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
