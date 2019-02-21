package ingraph.compiler.sql

import ingraph.compiler.test.CompilerTest
import ingraph.model.fplan.FNode

trait SqlCompiler {
  this: CompilerTest =>

  def stages: CompilationStages

  def fPlan: FNode

  def sqlNode: SqlNode

  def sql: String
}

object SqlCompiler {
  def apply(cypherQuery: String, compilerOptions: CompilerOptions = CompilerOptions()): SqlCompiler = {
    CompileSql.getOverriddenSqlStatic(cypherQuery) match {
      case Some(overriddenSql) if compilerOptions.gTop.isEmpty => new OverriddenCompileSql(overriddenSql)
      case _ => new CompileSql(cypherQuery, compilerOptions)
    }
  }
}
