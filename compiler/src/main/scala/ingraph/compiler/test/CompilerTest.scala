package ingraph.compiler.test

import ingraph.compiler.CypherToGPlan
import ingraph.compiler.cypher2gplan.{CypherParser, GPlanResolver}
import ingraph.compiler.exceptions.{CompilerConfigurationException, IncompleteCompilationException, IncompleteResolutionException}
import ingraph.compiler.plantransformers.{GPlanToNPlan, NPlanToFPlan}
import ingraph.compiler.util.FormatterUtil
import ingraph.emf.util.PrettyPrinter
import ingraph.model.expr.{EStub, ResolvableName}
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.nplan.NNode
import ingraph.model.gplan.{GNode, GStub, UnresolvedDelete, UnresolvedProjection}
import ingraph.model.treenodes.{ExpressionTreeNode, GPlanTreeNode, IngraphTreeNode}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedFunction}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.scalatest.{BeforeAndAfter, FunSuite}

case class CompilerTestConfig(querySuitePath: Option[String] = None
                              , skipGPlanResolve: Boolean = false
                              , skipGPlanBeautify: Boolean = false
                              , compileGPlanOnly: Boolean = false
                              , printQuery: Boolean = true
                              , printCypher: Boolean = false
                              , printGPlan: Boolean = true
                              , printNPlan: Boolean = true
                              , printFPlan: Boolean = true
                              , printTPlan: Boolean = true
                              , resetResolverNameCounters: Boolean = true
                             ) {
  /**
    * Did we request anything to be printed?
    */
  def printAny: Boolean = printQuery || printCypher || printGPlan || printNPlan || printFPlan || printTPlan
}

abstract class CompilerTest extends FunSuite with BeforeAndAfter {
  val config = CompilerTestConfig()
  val separatorLength = FormatterUtil.separatorLength
  val printlnSuppressIfIngraph = FormatterUtil.printlnSuppressIfIngraph
  def formatPlan(stuff: Any, heading: Option[String] = None, out: String => Unit = printlnSuppressIfIngraph): Unit = FormatterUtil.formatPlan(stuff, heading, out)

  before {
    if (config.resetResolverNameCounters)
      GPlanResolver.SNR.resetResolverNameCounters_IKnowWhatImDoing
  }

  def constructQueryFilePath(fileBaseName: String): String = {
    if (config.querySuitePath.isEmpty) {
      throw new CompilerConfigurationException("GPlan compilation requested from file, but querySuitePath was not supplied in the compiler test config")
    }
    s"../queries/${config.querySuitePath.get}/${fileBaseName}.cypher"
  }

  case class CompilationStages(gplan: GNode,
                               nplan: NNode,
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

    val gplan = CypherToGPlan.build_IKnowWhatImDoing(cypher
      , queryName
      , skipResolve = config.skipGPlanResolve
      , skipBeautify = config.skipGPlanBeautify
    )
    if (config.printGPlan ) formatPlan(gplan)

    assertNoStub(gplan)
    if (!config.skipGPlanResolve) assertResolved(gplan)

    val nplan = if (config.compileGPlanOnly) null else GPlanToNPlan.transform(gplan)
    if (config.printNPlan ) formatPlan(nplan)

    val fplan = if (config.compileGPlanOnly) null else NPlanToFPlan.transform(nplan)
    if (config.printFPlan ) formatPlan(fplan)

    return CompilationStages(gplan, nplan, fplan)
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

  def assertNoStub(q: GNode): Unit = {
    val itn = IngraphTreeNode(q)
    IngraphTreeNode.find( (n) => n match {
      case GPlanTreeNode(GStub(_)) => true
      case ExpressionTreeNode(EStub(_)) => true
      case _ => false
    }, itn).fold[Unit](Unit)( e => throw new IncompleteCompilationException(e.toString))
  }

  def assertResolved(q: GNode): Unit = {
    val itn = IngraphTreeNode(q)
    IngraphTreeNode.find( (n) => n match {
      case GPlanTreeNode(UnresolvedDelete(_, _, _)) => true
      case GPlanTreeNode(UnresolvedProjection(_, _, _, _, _, _, _)) => true
      case ExpressionTreeNode(etn) => etn match {
        case UnresolvedAttribute(_) => true
        case UnresolvedFunction(_, _, _) => true
        case rn: ResolvableName => rn.resolvedName.isEmpty
        case _ => false
      }
      case _ => false
    }, itn).fold[Unit](Unit)( e => throw new IncompleteResolutionException(e.toString))
  }

  def findFirstByType[T <: LogicalPlan](findRoot: LogicalPlan, clazz: Class[T]): T = {
    findRoot.find( p => clazz.isInstance(p) ).get.asInstanceOf[T]
  }

  /** Override println hack. Use printlnSuppressIfIngraph() instead") */
  @Deprecated
  def println(x: Any) = printlnSuppressIfIngraph(x.toString)
}

