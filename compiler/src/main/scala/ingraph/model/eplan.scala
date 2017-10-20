package ingraph.model.eplan

import javax.print.DocFlavor.BYTE_ARRAY

import ingraph.compiler.qplan2iplan.SchemaInferencer
import ingraph.model.iplan
import ingraph.model.iplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

// abstract classes and traits
trait ENode extends LogicalPlan {
  def extraAttributes: Seq[NamedExpression]
  def inode: INode
  def internalSchema: Seq[NamedExpression]
  override def children: Seq[ENode]
  override def output: Seq[Attribute] = inode.output
}

abstract class LeafENode extends GenericLeafNode[ENode] with ENode {}
abstract class UnaryENode extends GenericUnaryNode[ENode] with ENode {
  override def internalSchema: Seq[NamedExpression] = child.internalSchema
}
abstract class BinaryENode extends GenericBinaryNode[ENode] with ENode {}

trait JoinLike extends BinaryENode {
  def internalCommon: Seq[NamedExpression] = left.internalSchema.filter(x => right.internalSchema.map(_.name).contains(x.name))
  lazy val leftMask  = SchemaToMap.schemaToIndices(this, left)
  lazy val rightMask = SchemaToMap.schemaToIndices(this, right)
}
trait EquiJoinLike extends JoinLike {
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema.filter(x => !left.internalSchema.map(_.name).contains(x.name))
}

// leaf nodes
case class GetVertices(extraAttributes: Seq[NamedExpression], inode: iplan.GetVertices) extends LeafENode {
  override def internalSchema: Seq[NamedExpression] = inode.output ++ extraAttributes
}

case class GetEdges(extraAttributes: Seq[NamedExpression], inode: iplan.GetEdges) extends LeafENode {
  override def internalSchema: Seq[NamedExpression] = inode.output ++ extraAttributes
}

case class Dual(inode: iplan.Dual) extends LeafENode {
  override def internalSchema = Seq()
  override def extraAttributes: Seq[NamedExpression] = Seq()
}


// unary nodes
case class AllDifferent(extraAttributes: Seq[NamedExpression],
                        inode: iplan.AllDifferent,
                        child: ENode
                        ) extends UnaryENode {}

case class DuplicateElimination(extraAttributes: Seq[NamedExpression],
                                inode: iplan.DuplicateElimination,
                                child: ENode
                               ) extends UnaryENode {}

case class Production(extraAttributes: Seq[NamedExpression],
                      inode: iplan.Production,
                      child: ENode
                     ) extends UnaryENode {}

case class Projection(extraAttributes: Seq[NamedExpression],
                      inode: iplan.Projection,
                      child: ENode
                     ) extends UnaryENode {
  ///// !!!!!!!
  override def internalSchema: Seq[NamedExpression] = inode.projectList.flatMap(SchemaInferencer.extractAttributes(_))
  //override def internalSchema: Seq[NamedExpression] = inode.projectList
}

case class Selection(extraAttributes: Seq[NamedExpression],
                     inode: iplan.Selection,
                     child: ENode
                    ) extends UnaryENode {}

case class Unwind(extraAttributes: Seq[NamedExpression],
                     inode: iplan.Unwind,
                     child: ENode
                    ) extends UnaryENode {
  override def internalSchema: Seq[NamedExpression] = Seq()
  // TODO fix this similarly to qplan and iplan
}

case class SortAndTop(extraAttributes: Seq[NamedExpression],
                      inode: iplan.SortAndTop,
                      child: ENode
                     ) extends UnaryENode {}

// binary nodes
case class Union(extraAttributes: Seq[NamedExpression],
                 inode: iplan.Union,
                 left: ENode,
                 right: ENode) extends BinaryENode {
  override def internalSchema: Seq[NamedExpression] = left.internalSchema
}

case class AntiJoin(extraAttributes: Seq[NamedExpression],
                    inode: iplan.AntiJoin,
                    left: ENode,
                    right: ENode
                   ) extends BinaryENode with JoinLike {
  override def internalSchema: Seq[NamedExpression] = left.internalSchema
}

case class Join(extraAttributes: Seq[NamedExpression],
                inode: iplan.Join,
                left: ENode,
                right: ENode
               ) extends BinaryENode with EquiJoinLike {}

case class LeftOuterJoin(extraAttributes: Seq[NamedExpression],
                         inode: iplan.LeftOuterJoin,
                         left: ENode,
                         right: ENode
                        ) extends BinaryENode with EquiJoinLike {}

case class ThetaLeftOuterJoin(
          extraAttributes: Seq[NamedExpression],
          inode: iplan.ThetaLeftOuterJoin,
          left: ENode,
          right: ENode) extends BinaryENode with EquiJoinLike {}

case class Create(extraAttributes: Seq[NamedExpression],
                  inode: iplan.Create,
                  child: ENode) extends UnaryENode {}

case class Delete(extraAttributes: Seq[NamedExpression],
                  inode: iplan.Delete,
                  child: ENode) extends UnaryENode {}

case class Merge(extraAttributes: Seq[NamedExpression],
                  inode: iplan.Merge,
                  child: ENode) extends UnaryENode {}

case class SetNode(extraAttributes: Seq[NamedExpression],
                  inode: iplan.SetNode,
                  child: ENode) extends UnaryENode {}

case class Remove(extraAttributes: Seq[NamedExpression],
                 inode: iplan.Remove,
                 child: ENode) extends UnaryENode {}


object SchemaToMap {
  def schemaToIndices(node: JoinLike, side: ENode): Seq[Int] = {
    val sideIndices = schemaToMapNames(side)
    node.internalCommon.map(attr => sideIndices(attr.name))
  }

  def schemaToMapNames(n: ENode): Map[String, Int] = {
    n.internalSchema.zipWithIndex.map(f => f._1.name -> f._2).toMap
  }
}
