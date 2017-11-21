import java.io.File
import java.nio.charset.Charset

import com.google.common.io.Files
import org.apache.commons.exec.{CommandLine, DefaultExecutor}
import org.apache.spark.sql.catalyst.analysis.UnresolvedRelation
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf

object TexConverter {

  def toStandaloneDoc(tex: String, ingraphDir: String): String = {
    //s"""\\documentclass[varwidth=100cm,convert={density=120}]{standalone}
    s"""\\documentclass[convert={density=120}]{standalone}
       |\\input{${ingraphDir}/visualization/inputs/relalg-packages}
       |\\input{${ingraphDir}/visualization/inputs/relalg-commands}
       |\\usepackage[active,tightpage]{preview}
       |
       |\\begin{document}
       |\\begin{preview}
       |\\[${tex}\\]
       |\\end{preview}
       |\\end{document}
       |""".stripMargin
  }

  def toFile(tex: String, ingraphDir: String): Unit = {
    val doc = toStandaloneDoc(tex, ingraphDir)
    Files.asCharSink(new File("/tmp/query-plan.tex"), Charset.forName("UTF-8")).write(doc)
  }

  def compile(s: String): Unit = {
    val executor = new DefaultExecutor
    val commandLine = new CommandLine("/bin/bash")
    commandLine.addArgument("-c")
    commandLine.addArgument("cd /tmp && pdflatex -shell-escape -interaction=nonstopmode query-plan", false)
    executor.execute(commandLine)
  }

  def toTex(p: LogicalPlan): String = {
    p match {
      case u: UnaryNode => s"${unaryToTex(u)} ${toTex(u.child)}"
      case b: BinaryNode => s"${toTex(b.left)} ${binaryToTeX(b)} ${toTex(b.right)}"
      case u: UnresolvedRelation => u.tableName
      case r: LocalRelation => r.producedAttributes.head.name
      case _ => p.getClass().toString
    }
  }

  def unaryToTex(u: UnaryNode): String = {
    u match {
      case f: Filter => {
        println(f.condition)
        s"\\selection{}"
      }
      case p: Project => {
        val projectList = p.projectList.map(e => s"\\atom{${e.name}}").mkString(", ")
        s"\\projection{$projectList}"
      }
      case _ => u.getClass().toString()
    }
  }

  def binaryToTeX(b: BinaryNode): String = {
    b match {
      case j: Join => "\\join"
      case _ => ???
    }
  }

  def parse(s: String): LogicalPlan = {
    val conf = new SQLConf
    val parser = new SparkSqlParser(conf)
    val plan = parser.parsePlan(s)
    return plan
  }

  def expressionToTex(f: Filter): String = {
//    f match {
//      //case e: EqualTo =>
//    }
    ""
  }

}
