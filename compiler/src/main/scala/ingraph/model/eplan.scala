package ingraph.model.eplan

import ingraph.model.iplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait ENode[TINode <: INode] extends LogicalPlan {
  def extraAttributes: Seq[NamedExpression]
  def internalSchema: Seq[NamedExpression]
  def inode: TINode
  override def output: Seq[Attribute] = inode.output
}

case class LeafENode(val extraAttributes: Seq[NamedExpression],
                     val inode: LeafINode
                    ) extends GenericLeafNode[ENode[_]] with ENode[LeafINode] {
  override def internalSchema: Seq[NamedExpression] = output ++ extraAttributes
}

case class UnaryENode(val extraAttributes: Seq[NamedExpression],
                      val inode: UnaryINode,
                      val child: ENode[_]
                     ) extends GenericUnaryNode[ENode[_]] with ENode[UnaryINode] {
  override def internalSchema: Seq[NamedExpression] = inode match {
    case o: Projection => o.projectList
    case o: UnaryINode => child.internalSchema
  }
}

abstract class BinaryENode(val extraAttributes: Seq[NamedExpression],
                       val inode: BinaryINode,
                       val left: ENode[_],
                       val right: ENode[_]
                      ) extends GenericBinaryNode[ENode[_]] with ENode[BinaryINode] {
  override def internalSchema: Seq[NamedExpression] = inode match {
    case o: Union => left.internalSchema
    case o: AntiJoin => left.internalSchema
    case o: EquiJoinLike => left.internalSchema ++ right.internalSchema.filter(o.common.contains(_))
  }
}

case class UnionENode(override val extraAttributes: Seq[NamedExpression],
                      override val inode: Union,
                      override val left: ENode[_],
                      override val right: ENode[_]
                     ) extends BinaryENode(extraAttributes, inode, left, right) with ENode[JoinLike] {
  override def internalSchema: Seq[NamedExpression] = left.internalSchema
}


case class JoinLikeENode(override val extraAttributes: Seq[NamedExpression],
                         override val inode: JoinLike,
                         override val left: ENode[_],
                         override val right: ENode[_]
                      ) extends BinaryENode(extraAttributes, inode, left, right) with ENode[JoinLike] {
  override def internalSchema: Seq[NamedExpression] = inode match {
    case o: AntiJoin => left.internalSchema
    case o: EquiJoinLike => left.internalSchema ++ right.internalSchema.filter(o.common.contains(_))
  }

  lazy val leftMask  = SchemaToMap.schemaToIndices(inode, left)
  lazy val rightMask = SchemaToMap.schemaToIndices(inode, right)
}


object SchemaToMap {
  def schemaToIndices(node: JoinLike, side: ENode[_]): Seq[Int] = {
    val sideIndices = schemaToMapNames(side)
    node.common.map(attr => sideIndices.get(attr.toString))
  }

  def schemaToMapNames(n: ENode[_]): Map[String, Int] = {
    n.internalSchema.zipWithIndex.map(f => f._1.toString() -> f._2).toMap
  }
}
