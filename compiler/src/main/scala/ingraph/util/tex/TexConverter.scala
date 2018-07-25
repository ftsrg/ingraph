package ingraph.util.tex

import java.io.File
import java.nio.charset.Charset

import com.google.common.io.Files
import ingraph.model.expr._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.commons.exec.{CommandLine, DefaultExecutor}
import org.apache.spark.sql.catalyst.expressions.{Ascending, BinaryOperator, Descending, Expression, IsNotNull, IsNull, Literal, Not, SortDirection, SortOrder, UnaryExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.types.StringType

abstract class TexConverter[T <: LogicalPlan] {
  /**
    * Creates a standalone tex file
    * @param tex
    * @param ingraphDir
    * @return
    */
  def toStandaloneDoc(tex: List[String], ingraphDir: String, comment: String): String = {
    // FIXME comment only for debug puporses, remove it
    s"""\\documentclass[convert={density=120}]{standalone}
       |\\input{${ingraphDir}/inputs/relalg-packages}
       |\\input{${ingraphDir}/inputs/relalg-commands}
       |\\usepackage[active,tightpage]{preview}
       |
       |\\begin{document}
       |\\begin{preview}
       |${comment.concat("\\\\").replace("\n", "\\\\")}
       |${tex.mkString("\n\n")}
       |\\end{preview}
       |\\end{document}
       |""".stripMargin
  }

  def toFile(tex: List[String], ingraphDir: String, filePath: String, comment: String): Unit = {
    val doc = toStandaloneDoc(tex, ingraphDir, comment)
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
  protected[this] def unaryToTex(u: GenericUnaryNode[T]): String
  /**
    * Tex representation of binary operators
    * @param b
    * @return
    */
  protected[this] def binaryToTeX(b: GenericBinaryNode[T]): String
  /**
    * Tex representation of leaf operators
    * @param l
    * @return
    */
  protected[this] def leafToTex(l: GenericLeafNode[T]): String

  /**
    * Tex representation of catalyst expression
    * @param exp
    * @return
    */
  protected[this] def toTex(exp: Expression): String = {
    exp match {
      case null => ""
      case n: IsNull => s"(${toTex(n.child)} IS NULL)"
      case n: IsNotNull => s"(${toTex(n.child)} IS NOT NULL)"
      case e: AbstractEdgeAttribute => escapeTex(e.name)
      case p: ElementAttribute =>
        p.resolvedName match {
          case Some(s) => s.baseName.replace('$', '.' )
          case a => s"?? ${a.toString()}"
        }
      case p: PropertyAttribute =>
        p.resolvedName match {
          case Some(s) => s.baseName.replace('$', '.' )
          case a => s"?? ${a.toString()}"
        }
      case l: Literal =>
        l.dataType match {
          case StringType => s"'$l'"
          case _ => l.toString()
        }
      case l: ListExpression =>
        val elements = for (e <- l.list) yield toTex(e)
        s"[${elements.mkString(", ")}]"
      case s: Not =>
        s"NOT (${toTex(s.child)})"
      case f: FunctionInvocation =>
        val params = for (p <- f.children) yield toTex(p)
        s"${f.functor}(${params.mkString(", ")})"
      case r: ReturnItem => toTex(r.child)
      case e: ExpressionAttribute => toTex(e.expr)
      case v: VertexLabelSet => concat(v.vertexLabels, ":")
      case e: EdgeLabelSet => concat(e.edgeLabels, ":")

      case b: BinaryOperator => s"${toTex(b.left)} ${symbolToTex(b.symbol)} ${toTex(b.right)}"
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
    s"$$\\unwindop_$u$$"
  }
  protected[this] def projection(projectList: Iterable[String]): String = {
    s"$$\\projection{\\atom{${concat(projectList, ", ")}}}$$"
  }
  protected[this] def allDifferent(edges: Seq[AbstractEdgeAttribute]): String = {
    val param = for (e <- edges) yield toTex(e)
    s"$$\\alldifferent{${param.mkString(", ")}}$$"
  }
  protected[this] def selection(condition: Expression): String = {
    s"$$\\selection{\\atom{${escapeTex(toTex(condition))}}}$$"
  }
  protected[this] def join(operator: String, commonAttributes: Iterable[String]): String = {
    s"$$${operator}_{\\atom{${concat(commonAttributes, ", ")}}}$$"
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
