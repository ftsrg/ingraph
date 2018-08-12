package ingraph.util.tex

import java.io.File
import java.nio.charset.Charset

import com.google.common.io.Files
import ingraph.model.expr
import ingraph.model.expr._
import ingraph.model.gplan.Expand
import ingraph.model.plan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.commons.exec.{CommandLine, DefaultExecutor}
import org.apache.spark.sql.catalyst.expressions.{Ascending, BinaryOperator, Descending, Expression, IsNotNull, IsNull, Literal, Not, SortDirection, SortOrder, UnaryExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.types.StringType

class TexConverter[T <: LogicalPlan] {
  /**
    * Creates a standalone tex file
    * @param tex
    * @param ingraphDir
    * @return
    */
  def toStandaloneDoc(tex: String, ingraphDir: String): String = {
    s"""\\documentclass[convert={density=120}]{standalone}
       |\\input{${ingraphDir}/inputs/relalg-packages}
       |\\input{${ingraphDir}/inputs/relalg-commands}
       |\\usepackage[active,tightpage]{preview}
       |
       |\\begin{document}
       |\\begin{preview}
       |$tex
       |\\end{preview}
       |\\end{document}
       |""".stripMargin
  }

  def toFile(tex: String, ingraphDir: String, filePath: String): Unit = {
    val doc = toStandaloneDoc(tex, ingraphDir)
    Files.asCharSink(new File(filePath), Charset.forName("UTF-8")).write(doc)
  }

  def compile(s: String): Unit = {
    val executor = new DefaultExecutor
    val commandLine = new CommandLine("pdflatex")
    commandLine.addArgument(s)
    executor.execute(commandLine)
  }

  /**
    * Creates tex representation of logical plan
    * @param p
    * @return
    */
  def toTex(p: LogicalPlan): String = {
    p match {
      case p: TProduction => toTex(p.child)  // ignore production operator
      case u: GenericUnaryNode[T] => s"${unaryToTex(u)} ${toTex(u.child)}"
      case b: GenericBinaryNode[T] => s"${toTex(b.left)} ${binaryToTeX(b)} ${toTex(b.right)}"
      case l: GenericLeafNode[T] => s"${leafToTex(l)}"
      case _ => s"${escapeTex(p.nodeName)}"
    }
  }

  /**
    * Tex representation of unary operators
    * @param u
    * @return
    */
  protected[this] def unaryToTex(u: GenericUnaryNode[T]): String = {
     u match {
      case _: TDuplicateElimination => this.duplicateElimination
      case s: TSortAndTop => this.sortAndTop(s.order, s.skipExpr, s.limitExpr)
      case s: TSort => this.sort(s.order)
      case t: TTop => this.top(t.skipExpr, t.limitExpr)
      case g: TGrouping =>
      val projectionList = for (p <- g.projectList) yield toTex(p)
      val aggregationCriteria = for (a <- g.aggregationCriteria) yield toTex(a)
      this.grouping(projectionList, aggregationCriteria)

      case u: TUnwind => this.unwind(u.unwindAttribute)
      case s: TSelection => this.selection(s.condition)
      case p: TAbstractProjection =>
        val projectList =
        for (attr <- p.projectList)
        yield toTex(attr)
        this.projection(projectList)
      case a: TAllDifferent => this.allDifferent(a.edges)

      case e: Expand =>
        val op = e.dir match {
          case expr.Both => "\\expandbothop"
          case expr.Out => "\\expandoutop"
          case expr.In => "\\expandinop"
          case _ => ???
        }
        val srcname = escapeTex(e.src.name)
        val trgname = escapeTex(e.trg.name)
        val trglabels = concat(e.trg.labels.vertexLabels, ", ")
        val edge = escapeTex(s"${e.edge.name} : ${e.edge.labels.edgeLabels.mkString(", ")}")
        s"$$${op}_\\atom{${srcname}}^\\atom{${trgname}:${trglabels}} \\atom{[${edge}]}$$"


      case _: TCreate => this.create()
      case _: TDelete => this.delete()
      case _: TMerge => this.merge()
      case _: TSetNode => this.setNode()
      case _: TRemove => this.remove()

      case _ => s"?? ${u.nodeName}"
    }
  }
  /**
    * Tex representation of binary operators
    * @param b
    * @return
    */
  protected[this] def binaryToTeX(b: GenericBinaryNode[T]): String = {
    b match {
      case j: TJoinLike => this.join(j)
      case u: TUnion => this.union
      case _ => s"?? ${escapeTex(b.nodeName)}"
    }
  }
  /**
    * Tex representation of leaf operators
    * @param l
    * @return
    */
  protected[this] def leafToTex(l: GenericLeafNode[T]): String = {
    l match {
      case gv: TGetVertices => this.getVertices(gv.v)
      case ge: TGetEdges => this.getEdges(ge.directed, ge.src, ge.trg, ge.edge)
      case _: TDual => this.dual
      case _ => s"?? ${escapeTex(l.nodeName)}"
    }
  }

  /**
    * Tex representation of catalyst expression
    * @param exp
    * @return
    */
  protected[this] def toTex(exp: Expression): String = {
    exp match {
      case null => ""
      case n: IsNull => s"(${toTex(n.child)}\\ IS\\ NULL)"
      case n: IsNotNull =>
        s"(${toTex(n.child)}\\ IS\\ NOT\\ NULL)"
      case u: UnwindAttribute => s"${toTex(u.list)} \\rightarrow ${u.name}"
      case e: AbstractEdgeAttribute => escapeTex(e.name)
      case p: ElementAttribute =>
        p.resolvedName match {
          case Some(s) => escapeTex(s.baseName.replace('$', '.' ))
          case a => s"?? ${a.toString()}"
        }
      case p: PropertyAttribute =>
        p.resolvedName match {
          case Some(s) => escapeTex(s.baseName.replace('$', '.' ))
          case a => s"?? ${a.toString()}"
        }
      case l: Literal =>
        l.dataType match {
          case StringType => s"'$l'"
          case _ => l.toString()
        }
      case l: ListExpression =>
        val elements = for (e <- l.list) yield toTex(e)
        s"[${elements.mkString(",\\ ")}]"
      case s: Not =>
        s"NOT (${toTex(s.child)})"
      case f: FunctionInvocation =>
        val params = for (p <- f.children) yield toTex(p)
        escapeTex(s"${f.functor}(${params.mkString(", ")})")
      case r: ReturnItem => toTex(r.child)
      case e: ExpressionAttribute => toTex(e.expr)
      case v: VertexLabelSet => concat(v.vertexLabels, ":")
      case e: EdgeLabelSet => concat(e.edgeLabels, ":")

      case b: BinaryOperator => s"${toTex(b.left)}\\ ${symbolToTex(b.symbol)}\\ ${toTex(b.right)}"
      case u: UnaryExpression => s"${u.toString()} ${toTex(u.child)}"
      case c => s"?? ${c.toString()}"
    }
  }

  /**
    * Logical operator symbols to tex.
    * @param symbol
    * @return
    */
  protected[this] def symbolToTex(symbol: String): String = {
    symbol match {
      case "&&" => "\\wedge"
      case "||" => "\\vee"
      case p => p
    }
  }

  /**
    * Tex representaion of SortDirection
    * @param d
    * @return
    */
  protected[this] def directionToTex(d: SortDirection): String = {
    d match {
      case Ascending => "\\uparrow"
      case Descending => "\\downarrow"
      case _ => ???
    }
  }

  /**
    * Escape all tex special characters that occurs in names.
    * @param s
    * @return
    */
  protected[this] def escapeTex(s: String): String = {
    s.replace("_", "\\_").replace("#", "\\#").replace(" ", "\\ ").replace("$", "\\$")
  }

  /**
    * Concat and escape an iterable
    * @param s
    * @param separator
    * @return
    */
  protected[this] def concat(s: Iterable[String], separator: String): String = {
    escapeTex(s.mkString(separator))
  }

  protected[this] def duplicateElimination: String = {
    "$\\duplicateelimination$"
  }
  protected[this] def grouping(projcetionList: Iterable[String], aggregationCriteria: Iterable[String]): String = {
    s"$$\\grouping{${concat(aggregationCriteria, ", ")}}{${concat(projcetionList, ", ")}}$$"
  }
  protected[this] def unwind(u: UnwindAttribute): String = {
    s"$$\\unwind{${toTex(u)}}$$"
  }
  protected[this] def projection(projectList: Iterable[String]): String = {
    s"$$\\projection{\\atom{${concat(projectList, ", ")}}}$$"
  }
  protected[this] def allDifferent(edges: Seq[AbstractEdgeAttribute]): String = {
    val param = for (e <- edges) yield toTex(e)
    s"$$\\alldifferent{\\atom{${param.mkString(", ")}}}$$"
  }
  protected[this] def selection(condition: Expression): String = {
    s"$$\\selection{\\atom{${toTex(condition)}}}$$"
  }
  protected[this] def join(j: TJoinLike): String = {
    val operator = j match {
      case _: TJoin => "\\join"
      case _: TThetaLeftOuterJoin => "\\leftouterjoin"
      case _: TAntiJoin => "\\antijoin"
      case _: TLeftOuterJoin => "\\leftouterjoin"
      case _: TTransitiveJoin =>  "\\transitivejoin"
      case _ => ???
    }
    val common = for (c <- j.commonAttributes) yield toTex(c)
    s"$$${operator}_{\\atom{${common.mkString(",\\ ")}}}$$"
  }
  protected[this] def getVertices(v: VertexAttribute): String = {
    s"$$\\getverticesop_{\\atom{${escapeTex(v.name)}}}^{\\atom{${toTex(v.labels)}}}$$"
  }
  protected[this] def dual: String = {
    "$\\dual$"
  }
  protected[this] def getEdges(directed: Boolean, src: VertexAttribute, trg: VertexAttribute, edge: EdgeAttribute): String = {
    val op = if (directed) "\\getedgesdirected" else "\\getedgesundirected"
    s"$$$op{${src.name}}{${toTex(src.labels)}}{${escapeTex(trg.name)}}{${toTex(trg.labels)}}{${escapeTex(edge.name)}}{${toTex(edge.labels)}}$$"
  }
  protected[this] def union: String = {
    "$\\union$"
  }
  protected[this] def sort(order: Seq[SortOrder]): String = {
    var params = for (e <- order) yield s"${toTex(e.child)} ${directionToTex(e.direction)}"
    s"$$\\sort{${concat(params, ", ")}}$$"
  }
  protected[this] def top(skipExpr: Option[Expression], limitExpr: Option[Expression]): String = {
    s"$$\\topp{${toTex(skipExpr.orNull)}}{${toTex(limitExpr.orNull)}}$$"
  }
  protected[this] def sortAndTop(sortOrder: Seq[SortOrder], skipExpr: Option[Expression], limitExpr: Option[Expression]): String = {
    s"${sort(sortOrder)}\\\\${top(skipExpr, limitExpr)}"
  }
  protected[this] def create(): String = {
    "create"
  }
  protected[this] def delete(): String = {
    "delete"
  }
  protected[this] def merge(): String = {
    "merge"
  }
  protected[this] def setNode(): String = {
    "setNode"
  }
  protected[this] def remove(): String = {
    "remove"
  }

}
