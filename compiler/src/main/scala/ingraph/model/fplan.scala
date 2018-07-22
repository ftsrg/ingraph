package ingraph.model.fplan

import ingraph.model.expr._
import ingraph.model.nplan
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait FNode extends LogicalPlan {
  def nnode: nplan.NNode
  def flatSchema: Seq[ResolvableName]
  override def children: Seq[FNode]
  override def output: Seq[Attribute] = Seq()
}

abstract class LeafFNode extends GenericLeafNode[FNode] with FNode {}
abstract class UnaryFNode extends GenericUnaryNode[FNode] with FNode {
  override def flatSchema = child.flatSchema
}
abstract class BinaryFNode extends GenericBinaryNode[FNode] with FNode {}

trait JoinLike extends BinaryFNode {
  def flatCommon: Seq[ResolvableName] = left.flatSchema.filter(x => right.flatSchema.map(_.resolvedName).contains(x.resolvedName))
  lazy val leftMask:  Seq[Int] = SchemaMapper.schemaToIndices(this, left)
  lazy val rightMask: Seq[Int] = SchemaMapper.schemaToIndices(this, right)
}
trait EquiJoinLike extends JoinLike {
  override def flatSchema =
    left.flatSchema ++ right.flatSchema.filter(x => !left.flatSchema.map(_.resolvedName).contains(x.resolvedName))
}

// leaf nodes
case class GetVertices(requiredProperties: Seq[ResolvableName], nnode: nplan.GetVertices) extends LeafFNode {
  override def flatSchema = nnode.output ++ requiredProperties
}

case class GetEdges(requiredProperties: Seq[ResolvableName], nnode: nplan.GetEdges) extends LeafFNode {
  override def flatSchema = nnode.output ++ requiredProperties
}

case class Dual(nnode: nplan.Dual) extends LeafFNode {
  override def flatSchema = Seq()
}

// unary nodes
case class AllDifferent(nnode: nplan.AllDifferent,
                        child: FNode
                        ) extends UnaryFNode {

}

case class DuplicateElimination(nnode: nplan.DuplicateElimination,
                                child: FNode
                               ) extends UnaryFNode {}

case class Production(nnode: nplan.Production,
                      child: FNode
                     ) extends UnaryFNode {
  override def output = nnode.output
  def outputNames: Iterable[String] = output.map(_.resolvedName.get.resolvedName.replaceAll("#\\d+$", "").replace('$', '.'))
}

case class Projection(requiredProperties: Seq[ResolvableName],
                      nnode: nplan.Projection,
                      child: FNode
                     ) extends UnaryFNode {
  override def flatSchema = nnode.projectList ++ requiredProperties
  lazy val projectionTuple: Seq[Expression] =
    nnode.projectList.map(_.child).map(SchemaMapper.transformExpression(_, child.flatSchema)) ++
      requiredProperties.map(SchemaMapper.transformExpression(_, child.flatSchema))
}

case class Grouping(requiredProperties: Seq[ResolvableName],
                    nnode: nplan.Grouping,
                    child: FNode
                     ) extends UnaryFNode {
  override def flatSchema = nnode.projectList ++ requiredProperties
  lazy val aggregationCriteria: Seq[Expression] =
    nnode.aggregationCriteria.map(SchemaMapper.transformExpression(_, child.flatSchema)) ++
      requiredProperties.map(SchemaMapper.transformExpression(_, child.flatSchema))
  lazy val projectionTuple: Seq[Expression] =
    nnode.projectList.map(_.child).map(SchemaMapper.transformExpression(_, child.flatSchema)) ++
      requiredProperties.map(SchemaMapper.transformExpression(_, child.flatSchema))
}

case class Selection(nnode: nplan.Selection,
                     child: FNode
                    ) extends UnaryFNode {
  lazy val condition: Expression =
    SchemaMapper.transformExpression(nnode.condition, child.flatSchema)
}

case class Unwind(nnode: nplan.Unwind,
                  child: FNode
                 ) extends UnaryFNode {
  override def flatSchema = child.nnode.output ++ Seq(unwindAttribute)
  lazy val unwindAttribute: UnwindAttribute =
    UnwindAttribute(
      SchemaMapper.transformExpression(nnode.unwindAttribute.list, child.flatSchema),
      nnode.unwindAttribute.name,
      nnode.unwindAttribute.resolvedName
    )
}

case class SortAndTop(nnode: nplan.SortAndTop,
                      child: FNode
                     ) extends UnaryFNode {
  lazy val skipExpr:  Option[Expression] = nnode.skipExpr
  lazy val limitExpr: Option[Expression] = nnode.limitExpr
  val order: Seq[SortOrder] = nnode.order.map(SchemaMapper.transformExpression(_, child.flatSchema).asInstanceOf[SortOrder])
}

// binary nodes
case class Union(nnode: nplan.Union,
                 left: FNode,
                 right: FNode) extends BinaryFNode {
  override def flatSchema = left.flatSchema
}

case class AntiJoin(nnode: nplan.AntiJoin,
                    left: FNode,
                    right: FNode
                   ) extends BinaryFNode with JoinLike {
  override def flatSchema = left.flatSchema
}

case class Join(nnode: nplan.Join,
                left: FNode,
                right: FNode
               ) extends BinaryFNode with EquiJoinLike {}

case class TransitiveJoin(nnode: nplan.TransitiveJoin,
                          left: FNode,
                          right: GetEdges) extends BinaryFNode with EquiJoinLike {
  lazy val edgeList: EdgeListAttribute = nnode.edgeList

  override def flatSchema =
    left.flatSchema ++
    Seq(edgeList) ++
    right.flatSchema.filter(x => !x.isInstanceOf[EdgeAttribute] && !left.flatSchema.map(_.resolvedName).contains(x.resolvedName))
}

case class LeftOuterJoin(nnode: nplan.LeftOuterJoin,
                         left: FNode,
                         right: FNode
                        ) extends BinaryFNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(nnode: nplan.ThetaLeftOuterJoin,
                              left: FNode,
                              right: FNode) extends BinaryFNode with EquiJoinLike {
  lazy val condition: Expression =
    SchemaMapper.transformExpression(nnode.condition, left.flatSchema, right.flatSchema, leftMask.size)
}

case class Create(nnode: nplan.Create,
                  child: FNode) extends UnaryFNode {
  lazy val attribute: ResolvableName = nnode.attribute match {
    case VertexAttribute(name, labels, properties, isAnonymous, resolvedName) =>
      val tProperties = properties.map((kv) => kv._1 -> SchemaMapper.transformExpression(kv._2, child.flatSchema))
      VertexAttribute(name, labels, tProperties, isAnonymous, resolvedName)
    case RichEdgeAttribute(src, trg, EdgeAttribute(name, labels, properties, isAnonymous, resolvedName), dir) =>
      val tSrc = SchemaMapper.transformExpression2(src, child.flatSchema)
      val tTrg = SchemaMapper.transformExpression2(trg, child.flatSchema)
      val tEdge = EdgeAttribute(
        name, labels,
        properties.map((kv) => kv._1 -> SchemaMapper.transformExpression(kv._2, child.flatSchema)),
        isAnonymous, resolvedName
      )
      TupleEdgeAttribute(tSrc, tTrg, tEdge, dir)
  }
  override def flatSchema = nnode.output ++ Seq(attribute)
}

case class Delete(nnode: nplan.Delete,
                  child: FNode) extends UnaryFNode {
  lazy val attributes: Seq[TupleIndexLiteralAttribute] =
    nnode.attributes.map(SchemaMapper.transformExpression(_, child.flatSchema).asInstanceOf[TupleIndexLiteralAttribute])
  lazy val detach: Boolean = nnode.detach
}

case class Merge(nnode: nplan.Merge,
                 child: FNode) extends UnaryFNode {}

case class SetNode(nnode: nplan.SetNode,
                   child: FNode) extends UnaryFNode {}

case class Remove(nnode: nplan.Remove,
                  child: FNode) extends UnaryFNode {}


object SchemaMapper {
  def schemaToIndices(node: JoinLike, side: FNode): Seq[Int] = {
    val sideIndices = schemaToMapNames(side)
    node.flatCommon.map(attr => sideIndices(attr.resolvedName.get.resolvedName))
  }

  def schemaToMapNames(n: FNode): Map[String, Int] = {
    n.flatSchema.zipWithIndex.map(f => f._1.resolvedName.get.resolvedName -> f._2).toMap
  }

  def transformExpression(expression: Expression, flatSchema: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => TupleIndexLiteralAttribute(flatSchema.map(_.resolvedName).indexOf(a.resolvedName), isVertex = a.isInstanceOf[VertexAttribute])
      case e: Expression => e
    }
  }

  def transformExpression2(expression: Expression, flatSchema: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => TupleIndexLiteralAttribute(flatSchema.map(_.name).indexOf(a.name))
      case e: Expression => e
    }
  }

  def transformExpression(expression: Expression,
                          flatSchemaLeft: Seq[ResolvableName],
                          flatSchemaRight: Seq[ResolvableName],
                          maskWidth: Int): Expression = {
    expression.transform {
      case a: ResolvableName => {
        val left = flatSchemaLeft.map(_.resolvedName)
        val right = flatSchemaRight.map(_.resolvedName)
        val resolvedName = a.resolvedName
        if (left.contains(resolvedName)) {
          TupleIndexLiteralAttribute(left.indexOf(resolvedName), Option(Left()))
        } else {
          TupleIndexLiteralAttribute(flatSchemaLeft.size - maskWidth + right.indexOf(resolvedName), Option(Right()))
        }
      }
      case e: Expression => e
    }
  }

}
