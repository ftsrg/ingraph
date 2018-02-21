package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.exceptions.{CompilerConfigurationException, IncompleteCompilationException, IncompleteResolutionException}
import ingraph.compiler.qplan2jplan.{JPlanToFPlan, QPlanToJPlan}
import ingraph.compiler.util.FormatterUtil
import ingraph.emf.util.PrettyPrinter
import ingraph.model.expr.{EStub, ResolvableName}
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.jplan.JNode
import ingraph.model.qplan.{QNode, QStub, UnresolvedDelete, UnresolvedProjection}
import ingraph.model.fplan.FNode
import ingraph.model.treenodes.{ExpressionTreeNode, IngraphTreeNode, QPlanTreeNode}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedFunction}
import org.scalatest.FunSuite

case class CompilerTestConfig(querySuitePath: Option[String] = None
                             , skipQPlanResolve: Boolean = false
                             , skipQPlanBeautify: Boolean = false
                             , compileQPlanOnly: Boolean = false
                             , printQuery: Boolean = true
                             , printCypher: Boolean = false
                             , printQPlan: Boolean = true
                             , printJPlan: Boolean = true
                             , printFPlan: Boolean = true
                             , printTPlan: Boolean = true
                             ) {
  /**
    * Did we request anything to be printed?
    */
  def printAny: Boolean = printQuery || printCypher || printQPlan || printJPlan || printFPlan || printTPlan
}

abstract class CompilerTest extends FunSuite {
  val config = CompilerTestConfig()
  val separatorLength = FormatterUtil.separatorLength
  val printlnSuppressIfIngraph = FormatterUtil.printlnSuppressIfIngraph
  def formatPlan(stuff: Any, heading: Option[String] = None, out: String => Unit = printlnSuppressIfIngraph): Unit = FormatterUtil.formatPlan(stuff, heading, out)


  def constructQueryFilePath(fileBaseName: String): String = {
    if (config.querySuitePath.isEmpty) {
      throw new CompilerConfigurationException("QPlan compilation requested from file, but querySuitePath was not supplied in the compiler test config")
    }
    s"../queries/${config.querySuitePath.get}/${fileBaseName}.cypher"
  }

  case class CompilationStages(qplan: QNode,
                               jplan: JNode,
                               fplan: FNode)

  /**
    * Compile a query read from a cypher file.
    *
    * Finds the file using constructQueryFilePath, so override it if default implementation is not enough.
    *
    * @param queryFileBaseName The basename of the file, i.e. w/o extension, that holds the query to be compiled.
    * @return the {Q,J,F}Plan stages of the compilation
    */
  def compileFromFile(queryFileBaseName: String): CompilationStages = {
    val source = scala.io.Source.fromFile(constructQueryFilePath(queryFileBaseName))
    val queryString = try source.getLines.mkString("\n") finally source.close()

    compile(queryString, Some(queryFileBaseName))
  }

  def compile(query: String, queryName: Option[String] = None): CompilationStages = {
    if (config.printAny) {
      printlnSuppressIfIngraph("=" * separatorLength)
      if (queryName.isDefined) {
        printlnSuppressIfIngraph(queryName.get)
        printlnSuppressIfIngraph("-" * separatorLength)
      }
    }
    if (config.printQuery ) formatPlan(query, Some("Query"))

    val cypher = CypherParser.parseString(query)
    if (config.printCypher) formatPlan(PrettyPrinter.format(cypher), Some("Parsed query"))

    val qplan = CypherToQPlan.build_IKnowWhatImDoing(cypher
      , queryName
      , skipResolve = config.skipQPlanResolve
      , skipBeautify = config.skipQPlanBeautify
    )
    if (config.printQPlan ) formatPlan(qplan)

    assertNoStub(qplan)
    if (!config.skipQPlanResolve) assertResolved(qplan)

    val jplan = if (config.compileQPlanOnly) null else QPlanToJPlan.transform(qplan)
    if (config.printJPlan ) formatPlan(jplan)

    val fplan = if (config.compileQPlanOnly) null else JPlanToFPlan.transform(jplan)
    if (config.printFPlan ) formatPlan(fplan)

    return CompilationStages(qplan, jplan, fplan)
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

  def assertNoStub(q: QNode): Unit = {
    val itn = IngraphTreeNode(q)
    IngraphTreeNode.find( (n) => n match {
      case QPlanTreeNode(QStub(_)) => true
      case ExpressionTreeNode(EStub(_)) => true
      case _ => false
    }, itn).fold[Unit](Unit)( e => throw new IncompleteCompilationException(e.toString))
  }

  def assertResolved(q: QNode): Unit = {
    val itn = IngraphTreeNode(q)
    IngraphTreeNode.find( (n) => n match {
      case QPlanTreeNode(UnresolvedDelete(_, _, _)) => true
      case QPlanTreeNode(UnresolvedProjection(_, _)) => true
      case ExpressionTreeNode(etn) => etn match {
        case UnresolvedAttribute(_) => true
        case UnresolvedFunction(_, _, _) => true
        case rn: ResolvableName => rn.resolvedName.isEmpty
        case _ => false
      }
      case _ => false
    }, itn).fold[Unit](Unit)( e => throw new IncompleteResolutionException(e.toString))
  }

  /** Override println hack. Use printlnSuppressIfIngraph() instead") */
  @Deprecated
  def println(x: Any) = printlnSuppressIfIngraph(x.toString)
}

