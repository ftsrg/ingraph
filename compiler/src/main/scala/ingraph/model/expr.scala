package ingraph.model.expr

import ingraph.model.expr.labeltypes.{EdgeLabel, VertexLabel}
import ingraph.model.expr.types.{TPropertyMap}
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedException}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.apache.spark.sql.catalyst.expressions.codegen.{CodegenContext, ExprCode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, ExprId, Expression, LeafExpression}
import org.apache.spark.sql.types.{DataType, Metadata, StringType}

// FIXME: merge into types
package object labeltypes {
  type VertexLabel = String
  type EdgeLabel = String
}
package object types {
  type TPropertyMap = Map[String, cExpr.Expression]
}

trait ExpressionBase extends Expression {}

/**
  * A stub expression node indicating incomplete compilation,
  * i.e. something is not implemented but would be needed to compile the particular query.
  * @param note An optional note
  */
case class EStub(note: String = "FIXME") extends LeafExpression with ExpressionBase {
  override def nullable = ???

  override def eval(input: InternalRow) = ???

  override protected def doGenCode(ctx: CodegenContext, ev: ExprCode) = ???

  override def dataType = StringType //use of Alias(EStub(), "foo") requires this
}

case class FunctionInvocation(functor: ingraph.model.misc.Function, children: Seq[Expression], isDistinct: Boolean) extends ExpressionBase {
  override def nullable = ???

  override def eval(input: InternalRow) = ???

  override protected def doGenCode(ctx: CodegenContext, ev: ExprCode) = ???

  override def dataType = ???
}

// formerly GraphElementVariable
abstract class GraphAttribute(override val name: String) extends Attribute {
  override def exprId: ExprId = throw new UnresolvedException(this, "exprId")
  override def dataType: DataType = throw new UnresolvedException(this, "dataType")
  override def nullable: Boolean = throw new UnresolvedException(this, "nullable")
  override def qualifier: Option[String] = throw new UnresolvedException(this, "qualifier")
  override lazy val resolved = false

  override def newInstance(): GraphAttribute = this
  override def withNullability(newNullability: Boolean): GraphAttribute = this
  override def withQualifier(newQualifier: Option[String]): GraphAttribute = this
  override def withName(newName: String): UnresolvedAttribute = UnresolvedAttribute.quoted(newName)
  override def withMetadata(newMetadata: Metadata): Attribute = this

  override def eval(input: InternalRow): Any = ???
  override protected def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = ???
}

abstract class LabelSet(status: LabelSetStatus = Empty)

trait NavigationDescriptor {
  def src: VertexAttribute
  def trg: VertexAttribute
  def edge: EdgeAttribute
  def dir: Direction
}

/*
 * A vertex satisfies a label set constraint iff it has all the labels in the label set.
 *
 * The vertex itself might has more labels, so in case of more than one label set constraint
 * for a single vertex variable, it is always satisfiable, i.e.
 * LabelSet.status should never be LabelSetStatus.CONTRADICTING.
 */
case class VertexLabelSet(vertexLabels: Set[VertexLabel] = Set(), status: LabelSetStatus = Empty) extends LabelSet(status)

/*
 * An edge satisfies a label set constraint iff it has any of the labels in the label set.
 * An edge might have at most one label.
 *
 * For an edge variable, the combination of the following label set constraints
 * is contradicting, thus un-satisfiable:
 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
 *  2. [edge1:C], so edge1 needs to have label C.
 * These are contradicting as a single edge instance can have at most one label.
 */
case class EdgeLabelSet(edgeLabels: Set[EdgeLabel] = Set(), status: LabelSetStatus = Empty) extends LabelSet(status)

// formerly GraphElementVariable
abstract class ElementAttribute(name: String, properties: TPropertyMap) extends GraphAttribute(name)

abstract class VertexAttribute(override val name: String, val labels: VertexLabelSet = VertexLabelSet(), val properties: TPropertyMap = Map()) extends ElementAttribute(name, properties)
abstract class EdgeAttribute(override val name: String, val labels: EdgeLabelSet, val properties: TPropertyMap = Map()) extends ElementAttribute(name, properties)

// also Anonymous*Attribute has names, though generated unique names like _eN to facilitate reading of text representation
// but they can be identified in a type-safe manner
case class AnonymousVertexAttribute(override val name: String, override val labels: VertexLabelSet = VertexLabelSet(), override val properties: TPropertyMap = Map()) extends VertexAttribute(name, labels, properties)
case class AnonymousEdgeAttribute(override val name: String, override val labels: EdgeLabelSet, override val properties: TPropertyMap = Map()) extends EdgeAttribute(name, labels, properties)
case class NamedVertexAttribute(override val name: String, override val labels: VertexLabelSet = VertexLabelSet(), override val properties: TPropertyMap = Map()) extends VertexAttribute(name, labels, properties)
case class NamedEdgeAttribute(override val name: String, override val labels: EdgeLabelSet, override val properties: TPropertyMap = Map()) extends EdgeAttribute(name, labels, properties)


// formerly AttributeVariable
case class PropertyAttribute(override val name: String, elementAttribute: ElementAttribute) extends GraphAttribute(name)


/*
 * Indicates if the labelset represented is theoretically satisfiable or not.
 *
 * If it is undefined, it matches every labels.
 * If it is defined, but empty, matching semantics differs:
 *  1. a vertex must have *all* of the labels
 *  2. an edge must have *one* of the labels
 *
 * A labelset can be non-satisfiable (false, also known as contradicting) if,
 * for the same edge variable edge1, the following labelset constraints
 * were given in the query:
 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
 *  2. [edge1:C], so edge1 needs to have label C.
 *  These are contradicting as a single edge instance can have at most one label.
 */
sealed trait LabelSetStatus

/*
 * Each edge and vertex satisfies an empty labelset constraint
 */
case object Empty extends LabelSetStatus
/*
 * Theoretically, a non-empty labelset constraint is satisfiable,
 * but matching semantics differ for different variables:
 *  1. a vertex must have *all* of the labels
 *  2. an edge must have *one* of the labels
 */
case object NonEmpty extends LabelSetStatus
/*
 * A labelset constraint might be non-satisfiable,
 * in case it is a combination of multiple  labelset constraints.
 *
 * For an edge variable, the combination of the following labelset constraints
 * is contradicting, thus un-satisfiable:
 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
 *  2. [edge1:C], so edge1 needs to have label C.
 * These are contradicting as a single edge instance can have at most one label.
 */
case object Contradicting extends LabelSetStatus

case class VertexLabelUpdate(vertex: VertexAttribute, vertexLabels: Set[VertexLabel])

sealed trait Direction
case object Both extends Direction
case object In extends Direction
case object Out extends Direction
