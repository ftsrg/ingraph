package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.exceptions.{CompilerConfigurationException, IncompleteCompilationException, IncompleteResolutionException}
import ingraph.compiler.qplan2jplan.{FPlanToTPlan, JPlanToFPlan, QPlanToJPlan}
import ingraph.emf.util.PrettyPrinter
import ingraph.model.expr.EStub
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.jplan.JNode
import ingraph.model.qplan.{QNode, QStub, UnresolvedDelete, UnresolvedProjection}
import ingraph.model.tplan.TNode
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
  val separatorLength = 77

  def constructQueryFilePath(fileBaseName: String): String = {
    if (config.querySuitePath.isEmpty) {
      throw new CompilerConfigurationException("QPlan compilation requested from file, but querySuitePath was not supplied in the compiler test config")
    }
    s"../queries/${config.querySuitePath.get}/${fileBaseName}.cypher"
  }

  case class CompilationStages(qplan: QNode,
                               jplan: JNode,
                               fplan: FNode,
                               tplan: TNode)

  /**
    * Compile a query read from a cypher file.
    *
    * Finds the file using constructQueryFilePath, so override it if default implementation is not enough.
    *
    * @param queryFileBaseName The basename of the file, i.e. w/o extension, that holds the query to be compiled.
    * @return the {Q,J,F,T}Plan stages of the compilation
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
    if (config.printQuery ) formatStuff(query, Some("Query"))

    val cypher = CypherParser.parseString(query)
    if (config.printCypher) formatStuff(PrettyPrinter.format(cypher), Some("Parsed query"))

    val qplan = CypherToQPlan.build_IKnowWhatImDoing(cypher
      , queryName
      , skipResolve = config.skipQPlanResolve
      , skipBeautify = config.skipQPlanBeautify
    )
    if (config.printQPlan ) formatStuff(qplan)

    assertNoStub(qplan)
    if (!config.skipQPlanResolve) assertResolved(qplan)

    val jplan = if (config.compileQPlanOnly) null else QPlanToJPlan.transform(qplan)
    if (config.printJPlan ) formatStuff(jplan)

    val fplan = if (config.compileQPlanOnly) null else JPlanToFPlan.transform(jplan)
    if (config.printFPlan ) formatStuff(fplan)

    val tplan = if (config.compileQPlanOnly) null else FPlanToTPlan.transform(fplan)
    if (config.printFPlan ) formatStuff(tplan)

    return CompilationStages(qplan, jplan, fplan, tplan)
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

  /**
    * Formats a {Q,J,F,T}Plan, or virtually anything and send to the out channel
    * @param stuff {Q,J,F,T}Plan instance, or any other that has toString. In sace null is passed, nothing will be sent to the out channel.
    * @param heading The heading line for the formatted plan. In case it was a {Q,J,F,T}Plan, this heading is inferred if omitted, otherwise this must be supplied.
    * @param out The out channel method, defaults to suppressing output if INGRAPH_COMPILER_TEST_SUPPRESS_PRINTLN environment variable is defined, and println otherwise
    */
  def formatStuff(stuff: Any, heading: Option[String] = None, out: String => Unit = printlnSuppressIfIngraph): Unit = {
    val _heading: String = heading.getOrElse(
      stuff match {
        case _: QNode => "QPlan"
        case _: JNode => "JPlan"
        case _: FNode => "FPlan"
        case _: TNode => "TPlan"
        case null => return
      }
    )

    out(s"${_heading}:")
    out("-" * (_heading.length + 1))
    out(stuff.toString)
    out("-" * separatorLength)
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
      case ExpressionTreeNode(UnresolvedAttribute(_)) => true
      case ExpressionTreeNode(UnresolvedFunction(_, _, _)) => true
      case _ => false
    }, itn).fold[Unit](Unit)( e => throw new IncompleteResolutionException(e.toString))
  }

  /**
    * println is returned unless condition holds.
    */
  def printlnSuppressIf(condition: Boolean): String => Unit =
    if (condition) _ => {} else Predef.println

  def printlnSuppressIfIngraph = printlnSuppressIf(sys.env.get("INGRAPH_COMPILER_TEST_SUPPRESS_PRINTLN").isDefined)

  /** Override println hack. Use printlnSuppressIfIngraph() instead") */
  @Deprecated
  def println(x: Any) = printlnSuppressIfIngraph(x.toString)
}

