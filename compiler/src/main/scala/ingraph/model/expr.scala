package ingraph.model.expr

import ingraph.model.expr.types._
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedException}
import org.apache.spark.sql.catalyst.expressions.codegen.{CodegenContext, ExprCode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, ExprId, Expression, LeafExpression, NamedExpression, UnaryExpression}
import org.apache.spark.sql.catalyst.{InternalRow, expressions => cExpr}
import org.apache.spark.sql.types.{DataType, Metadata, StringType}

package object types {
  type TPropertyMap = Map[String, cExpr.Expression]
  type TProjectList = Seq[ReturnItem]
  type TResolvedName = Option[TResolvedNameValue]
  type VertexLabel = String
  type EdgeLabel = String

  /**
    * TL;DR: Outside String context you probably want to retrieve resolvedName String member.
    *
    * Represents a resolved name along with the name it originated from.
    *
    * Historically this used to be a string representing only the resolved name.
    * To retain backward compatibility, toString returns only the resolvedName.
    */
  case class TResolvedNameValue(val baseName: String, val resolvedName: String) {
    override def toString: String = resolvedName
  }
}
trait ProjectionDescriptor {
  def projectList: TProjectList
}

trait HasExtraChildren {
  def extraChildren: Seq[Expression]
}


trait ExpressionBase extends Expression {
  override def nullable: Boolean = true
  override def dataType: DataType = throw new UnresolvedException(this, "dataType")
  override def eval(input: InternalRow): Any = ???
  override protected def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = ???
}

/**
  * This represents a name that should be created upon reference resolution.
  * For each plan, if two stuff has resolvedName in common, they refer to the same thing.
  */
trait ResolvableName extends Attribute {
  def resolvedName: TResolvedName
}

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

//TODO: extract isAnonymous to a trait
case class ReturnItem(child: Expression, alias: Option[String] = None, override val resolvedName: TResolvedName = None) extends AbstractReturnItem(child, alias, resolvedName) {
}

abstract class AbstractReturnItem(child: Expression, alias: Option[String] = None, override val resolvedName: TResolvedName = None) extends ResolvableName {
  def isAnonymous: Boolean = alias.isEmpty

  override def nullable: Boolean = ???
  override def dataType: DataType = child.dataType
  override def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = ???
  override def withNullability(newNullability: Boolean): Attribute = ???
  override def withQualifier(newQualifier: Option[String]): Attribute = ???
  override def withName(newName: String): Attribute = ???
  override def withMetadata(newMetadata: Metadata): Attribute = ???
  override def newInstance(): Attribute = ???
  override def name: String = alias.getOrElse(child match {
    case a: Attribute => a.name
  })
  override def exprId: ExprId = ???
  override def qualifier: Option[String] = ???
  override def eval(input: InternalRow): Any = ???

  override def toAttribute: Attribute = this.child match {
    case a: Attribute => a
    case e => ExpressionAttribute(e, "_expr", None)
  }

  //override def children: Seq[ResolvableName] = ???
}

abstract class AttributeBase extends Attribute {
  override def exprId: ExprId = throw new UnresolvedException(this, "exprId")
  override def dataType: DataType = throw new UnresolvedException(this, "dataType")
  override def nullable: Boolean = throw new UnresolvedException(this, "nullable")
  override def qualifier: Option[String] = throw new UnresolvedException(this, "qualifier")
  override lazy val resolved = false

  override def newInstance(): AttributeBase = this
  override def withNullability(newNullability: Boolean): AttributeBase = this
  override def withQualifier(newQualifier: Option[String]): AttributeBase = this
  override def withName(newName: String): UnresolvedAttribute = UnresolvedAttribute.quoted(newName)
  override def withMetadata(newMetadata: Metadata): Attribute = this

  override def eval(input: InternalRow): Any = ???
  override protected def doGenCode(ctx: CodegenContext, ev: ExprCode): ExprCode = ???
}

case class TupleIndexLiteralAttribute(index: Int, side: Option[Side] = None, isVertex: Boolean = false) extends AttributeBase {
  assert(index >= 0, s"Cannot index an array with $index")
  override def name: String = ???
}
abstract class Side
case class Left() extends Side
case class Right() extends Side

case class Parameter(name: String) extends ExpressionBase {
  override def children: Seq[Expression] = Seq()
}

// just wraps an expression into "? :> Attribute"
case class ExpressionAttribute(expr: Expression, override val name: String,
                               override val resolvedName: TResolvedName = None
                              ) extends AttributeBase with ResolvableName with HasExtraChildren {
  override def extraChildren: Seq[Expression] = Seq(expr)
}

// this is the attribute built by unwinding a list
case class UnwindAttribute(list: Expression, override val name: String, override val resolvedName: TResolvedName = None) extends AttributeBase with ResolvableName

/** Represents a list expression.
  *
  * In openCypher, list is a first class citizen among data types,
  * and also properties may hold lists.
  *
  * This is different that of in Spark Catalyst, where this is represented directly as Seq[Expression]
  * used only for "value in list"-like expressions.
  *
  * @param list the sequence of list elements
  */
case class ListExpression(list: Seq[Expression]) extends ExpressionBase {
  override def children: Seq[Expression] = list
}

/**
  * Index lookup on a collection, where lower and upper bound defines a slice.
  *
  * The slice ranges from (including) the lower bound up to (not including) the upper bound, if defined.
  */
abstract class AbstractIndexExpression(collection: Expression, lower: Option[Int], upper: Option[Int]) extends ExpressionBase {
  override def children: Seq[Expression] = Seq(collection)
}

/**
  * Note: this should return a list.
  */
case class IndexRangeExpression(collection: Expression, lower: Option[Int], upper: Option[Int]) extends AbstractIndexExpression(collection, lower, upper)

/**
  * An index expression, where only a single element is retrieved as a scalar.
  *
  * i.e. IndexRangeExpression having lower and upper defined to the value of index.
  */
case class IndexLookupExpression(collection: Expression, index: Int) extends AbstractIndexExpression(collection, Some(index), Some(index+1))

// formerly GraphElementVariable
abstract class GraphAttribute(override val name: String) extends AttributeBase

abstract class LabelSet(status: LabelSetStatus = Empty) extends ExpressionBase {
  override def children: Seq[Expression] = Seq()
}

trait NavigationDescriptor {
  def src: VertexAttribute
  def trg: VertexAttribute
  def edge: AbstractEdgeAttribute
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
abstract class ElementAttribute(name: String, val properties: TPropertyMap, isAnonymous: Boolean, override val resolvedName: TResolvedName) extends GraphAttribute(name) with ResolvableName

abstract class AbstractVertexAttribute(
  override val name: String, val labels: VertexLabelSet = VertexLabelSet(),
  override val properties: TPropertyMap = Map(), val isAnonymous: Boolean,
  resolvedName: TResolvedName) extends ElementAttribute(name, properties, isAnonymous, resolvedName)
abstract class AbstractEdgeAttribute(override val name: String, val labels: EdgeLabelSet, override val properties: TPropertyMap = Map(), val isAnonymous: Boolean, resolvedName: TResolvedName) extends ElementAttribute(name, properties, isAnonymous, resolvedName)

/*
 * Represents an edge along with its vertices and direction.
 *
 * Actually, this encapsulates an edge and its source and target vertices.
 * As a descendant of ElementATtribute, it gives access to the name and properties of the encapsulated EdgeAttribute.
 */
case class RichEdgeAttribute(src: VertexAttribute,
                             trg: VertexAttribute,
                             edge: EdgeAttribute,
                             dir: Direction) extends ElementAttribute(edge.name, edge.properties, edge.isAnonymous, edge.resolvedName) with NavigationDescriptor with HasExtraChildren {
  override def extraChildren: Seq[Expression] = Seq(src, trg, edge)
}

case class TupleEdgeAttribute(src: Expression,
                             trg: Expression,
                             edge: EdgeAttribute,
                             dir: Direction) extends ElementAttribute(edge.name, edge.properties, edge.isAnonymous, edge.resolvedName)

// also Anonymous*Attribute has names, though generated unique names like _eN to facilitate reading of text representation
// but they can be identified in a type-safe manner
//case class AnonymousVertexAttribute(override val name: String, override val labels: VertexLabelSet = VertexLabelSet(), override val properties: TPropertyMap = Map()) extends VertexAttribute(name, labels, properties)
//case class AnonymousEdgeAttribute(override val name: String, override val labels: EdgeLabelSet, override val properties: TPropertyMap = Map()) extends EdgeAttribute(name, labels, properties)
case class VertexAttribute(override val name: String,
  override val labels: VertexLabelSet = VertexLabelSet(), override val properties: TPropertyMap = Map(),
  override val isAnonymous: Boolean = false, override val resolvedName: TResolvedName = None
                          ) extends AbstractVertexAttribute(name, labels, properties, isAnonymous, resolvedName)
case class EdgeAttribute(override val name: String, override val labels: EdgeLabelSet = EdgeLabelSet(), override val properties: TPropertyMap = Map(), override val isAnonymous: Boolean = false, override val resolvedName: TResolvedName = None) extends AbstractEdgeAttribute(name, labels, properties, isAnonymous, resolvedName)
case class EdgeListAttribute(override val name: String, override val labels: EdgeLabelSet = EdgeLabelSet(), override val properties: TPropertyMap = Map(), override val isAnonymous: Boolean = false, minHops: Option[Int], maxHops: Option[Int], override val resolvedName: TResolvedName = None) extends AbstractEdgeAttribute(name, labels, properties, isAnonymous, resolvedName)


// formerly AttributeVariable
case class PropertyAttribute(override val name: String, elementAttribute: ElementAttribute, override val resolvedName: TResolvedName = None) extends GraphAttribute(name) with ResolvableName


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
