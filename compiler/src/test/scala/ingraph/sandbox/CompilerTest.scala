package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.emf.util.PrettyPrinter
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
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
                             ) {
  /**
    * Did we request anything to be printed?
    */
  def printAny: Boolean = printQuery || printCypher || printQPlan || printJPlan || printFPlan
}

class CompilerTest extends FunSuite {
  val config = CompilerTestConfig()
  val separatorLength = 77

  def constructQueryFilePath(fileBaseName: String): String = {
    if (config.querySuitePath.isEmpty) {
      throw new RuntimeException("QPlan compilation requested from file, but querySuitePath was not supplied in the compiler test config")
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
      println("=" * separatorLength)
      if (queryName.isDefined) {
        println(queryName.get)
        println("-" * separatorLength)
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

    val jplan = if (config.compileQPlanOnly) null else QPlanToJPlan.transform(qplan)
    if (config.printJPlan ) formatStuff(jplan)

    val fplan = if (config.compileQPlanOnly) null else SchemaInferencer.transform(jplan)
    if (config.printFPlan ) formatStuff(fplan)

    return CompilationStages(qplan, jplan, fplan)
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

  /**
    * Formats a {Q,J,F}Plan, or virtually anything and send to the out channel
    * @param stuff {Q,J,F}Plan instance, or any other that has toString. In sace null is passed, nothing will be sent to the out channel.
    * @param heading The heading line for the formatted plan. In case it was a {Q,J,F}Plan, this heading is inferred if omitted, otherwise this must be supplied.
    * @param out The out channel method, defaults to println
    */
  def formatStuff(stuff: Any, heading: Option[String] = None, out: String => Unit = println): Unit = {
    val _heading: String = heading.getOrElse(
      stuff match {
        case _: QNode => "QPlan"
        case _: JNode => "JPlan"
        case _: FNode => "FPlan"
        case null => return
      }
    )

    out(s"${_heading}:")
    out("-" * (_heading.length + 1))
    out(stuff.toString)
    out("-" * separatorLength)
  }
}

