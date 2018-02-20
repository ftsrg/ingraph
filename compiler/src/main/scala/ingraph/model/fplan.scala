package ingraph.model.fplan

import ingraph.model.expr._
import ingraph.model.jplan
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait FNode extends LogicalPlan {
  def extraAttributes: Seq[ResolvableName]
  def jnode: jplan.JNode
  def internalSchema: Seq[ResolvableName]
  override def children: Seq[FNode]
  override def output: Seq[Attribute] = Seq()
}

abstract class LeafFNode extends GenericLeafNode[FNode] with FNode {}
abstract class UnaryFNode extends GenericUnaryNode[FNode] with FNode {
  override def internalSchema: Seq[ResolvableName] = child.internalSchema
}
abstract class BinaryFNode extends GenericBinaryNode[FNode] with FNode {}

trait JoinLike extends BinaryFNode {
  def internalCommon: Seq[ResolvableName] = left.internalSchema.filter(x => right.internalSchema.map(_.resolvedName).contains(x.resolvedName))
  lazy val leftMask:  Seq[Int] = SchemaMapper.schemaToIndices(this, left)
  lazy val rightMask: Seq[Int] = SchemaMapper.schemaToIndices(this, right)
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

case class Dual(jnode: jplan.Dual) extends LeafFNode {
  override def internalSchema = Seq()
  override def extraAttributes: Seq[ResolvableName] = Seq()
}

// unary nodes
case class AllDifferent(extraAttributes: Seq[ResolvableName],
                        jnode: jplan.AllDifferent,
                        child: FNode
                        ) extends UnaryFNode {

}

case class DuplicateElimination(extraAttributes: Seq[ResolvableName],
                                jnode: jplan.DuplicateElimination,
                                child: FNode
                               ) extends UnaryFNode {}

case class Production(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Production,
                      child: FNode
                     ) extends UnaryFNode {
  override def output = jnode.output
  def outputNames: Iterable[String] = output.map(_.resolvedName.get.resolvedName)
}

case class Projection(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Projection,
                      child: FNode
                     ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.projectList ++ extraAttributes
  lazy val projectionTuple: Seq[Expression] =
    jnode.projectList.map(_.child).map(SchemaMapper.transformExpression(_, child.internalSchema)) ++
      extraAttributes.map(SchemaMapper.transformExpression(_, child.internalSchema))
}

case class Grouping(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.Grouping,
                      child: FNode
                     ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = jnode.projectList ++ extraAttributes
  lazy val aggregationCriteria: Seq[Expression] =
    jnode.aggregationCriteria.map(SchemaMapper.transformExpression(_, child.internalSchema))
  lazy val projectionTuple: Seq[Expression] =
    jnode.projectList.map(_.child).map(SchemaMapper.transformExpression(_, child.internalSchema)) ++
      extraAttributes.map(SchemaMapper.transformExpression(_, child.internalSchema))
}

case class Selection(extraAttributes: Seq[ResolvableName],
                     jnode: jplan.Selection,
                     child: FNode
                    ) extends UnaryFNode {
  lazy val condition: Expression =
    SchemaMapper.transformExpression(jnode.condition, child.internalSchema)
}

case class Unwind(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Unwind,
                  child: FNode
                    ) extends UnaryFNode {
  override def internalSchema: Seq[ResolvableName] = child.jnode.output ++ extraAttributes ++ Seq(unwindAttribute)
  lazy val unwindAttribute: UnwindAttribute = jnode.unwindAttribute
}

case class SortAndTop(extraAttributes: Seq[ResolvableName],
                      jnode: jplan.SortAndTop,
                      child: FNode
                     ) extends UnaryFNode {
  lazy val skipExpr:  Option[Expression] = jnode.skipExpr
  lazy val limitExpr: Option[Expression] = jnode.limitExpr
  val order: Seq[SortOrder] = jnode.order.map(SchemaMapper.transformExpression(_, child.internalSchema).asInstanceOf[SortOrder])
}

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

case class TransitiveJoin(extraAttributes: Seq[ResolvableName],
                          jnode: jplan.TransitiveJoin,
                          left: FNode,
                          right: FNode) extends BinaryFNode with EquiJoinLike {
  lazy val edgeList: EdgeListAttribute = jnode.edgeList
}

case class LeftOuterJoin(extraAttributes: Seq[ResolvableName],
                         jnode: jplan.LeftOuterJoin,
                         left: FNode,
                         right: FNode
                        ) extends BinaryFNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(extraAttributes: Seq[ResolvableName],
                              jnode: jplan.ThetaLeftOuterJoin,
                              left: FNode,
                              right: FNode) extends BinaryFNode with EquiJoinLike {
  lazy val condition: Expression =
    SchemaMapper.transformExpression(jnode.condition, left.internalSchema, right.internalSchema)
}

case class Create(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Create,
                  child: FNode) extends UnaryFNode {
  lazy val attribute: ResolvableName = jnode.attribute match {
    case VertexAttribute(name, labels, properties, isAnonymous, resolvedName) =>
      val tProperties = properties.map((kv) => kv._1 -> SchemaMapper.transformExpression(kv._2, child.internalSchema))
      VertexAttribute(name, labels, tProperties, isAnonymous, resolvedName)
    case RichEdgeAttribute(src, trg, EdgeAttribute(name, labels, properties, isAnonymous, resolvedName), dir) =>
      val tSrc = SchemaMapper.transformExpression2(src, child.internalSchema)
      val tTrg = SchemaMapper.transformExpression2(trg, child.internalSchema)
      val tEdge = EdgeAttribute(
        name, labels,
        properties.map((kv) => kv._1 -> SchemaMapper.transformExpression(kv._2, child.internalSchema)),
        isAnonymous, resolvedName
      )
      TupleEdgeAttribute(tSrc, tTrg, tEdge, dir)
  }
  override def internalSchema = jnode.output ++ extraAttributes ++ Seq(attribute)
}

case class Delete(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Delete,
                  child: FNode) extends UnaryFNode {
  lazy val attributes: Seq[TupleIndexLiteralAttribute] =
    jnode.attributes.map(SchemaMapper.transformExpression(_, child.internalSchema).asInstanceOf[TupleIndexLiteralAttribute])
  lazy val detach: Boolean = jnode.detach
}

case class Merge(extraAttributes: Seq[ResolvableName],
                 jnode: jplan.Merge,
                 child: FNode) extends UnaryFNode {}

case class SetNode(extraAttributes: Seq[ResolvableName],
                   jnode: jplan.SetNode,
                   child: FNode) extends UnaryFNode {}

case class Remove(extraAttributes: Seq[ResolvableName],
                  jnode: jplan.Remove,
                  child: FNode) extends UnaryFNode {}


object SchemaMapper {
  def schemaToIndices(node: JoinLike, side: FNode): Seq[Int] = {
    val sideIndices = schemaToMapNames(side)
    node.internalCommon.map(attr => sideIndices(attr.resolvedName.get.resolvedName))
  }

  def schemaToMapNames(n: FNode): Map[String, Int] = {
    n.internalSchema.zipWithIndex.map(f => f._1.resolvedName.get.resolvedName -> f._2).toMap
  }

  def transformExpression(expression: Expression, internalSchema: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => TupleIndexLiteralAttribute(internalSchema.map(_.resolvedName).indexOf(a.resolvedName), isVertex = a.isInstanceOf[VertexAttribute])
      case e: Expression => e
    }
  }

  def transformExpression2(expression: Expression, internalSchema: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => TupleIndexLiteralAttribute(internalSchema.map(_.name).indexOf(a.name))
      case e: Expression => e
    }
  }

  def transformExpression(expression: Expression, internalSchemaLeft: Seq[ResolvableName],
                          internalSchemaRight: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => {
        val left = internalSchemaLeft.map(_.resolvedName)
        val right = internalSchemaRight.map(_.resolvedName)
        val resolvedName = a.resolvedName
        if (left.contains(resolvedName)) {
          TupleIndexLiteralAttribute(left.indexOf(resolvedName), Option(Left()))
        } else {
          TupleIndexLiteralAttribute(right.indexOf(resolvedName), Option(Right()))
        }
      }
      case e: Expression => e
    }
  }

}

object PlanPrettyPrinter {

  def clean(plan: String): String = {
     plan
       .replaceAll("Some", "")
       .replaceAll("#0", "")
       .replaceAll("\\$", ".")
       .replaceAll("vertexattribute", "v")
       .replaceAll("edgeattribute", "e")
       .replaceAll("propertyattribute", "p")
       .replaceAll("expressionattribute", "expr")
       .replaceAll("vertexlabelset\\((.*?)\\)", "{$1}")
       .replaceAll("edgelabelset\\((.*?)\\)", "{$1}")
       .replaceAll(", NonEmpty\\}", "}")
       .replaceAll("\\{Empty\\}", "{}")
       .replaceAll("functioninvocation", "fun")
       .replaceAll("returnitem", "ret")
       .replaceAll("(\\w+) '\\1", "$1")
  }

}
