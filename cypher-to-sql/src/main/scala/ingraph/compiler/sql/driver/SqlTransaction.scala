package ingraph.compiler.sql.driver

import java.sql.Connection
import java.util.concurrent.CompletionStage
import java.util.{Map => javaMap}

import ingraph.compiler.sql.Util.withResources
import ingraph.compiler.sql.{CompileSql, ExportSteps}
import ingraph.model.fplan.Create
import org.neo4j.driver.v1._
import org.neo4j.driver.v1.types.{MapAccessor, TypeSystem}

import scala.collection.JavaConverters._

class SqlTransaction(val sqlSession: SqlSession) extends Transaction {

  def rawSqlConnection: Connection = sqlSession.sqlConnection

  var toBeCommitted: Option[Boolean] = None

  override def success(): Unit = if (toBeCommitted.isEmpty) toBeCommitted = Some(true)

  override def failure(): Unit = toBeCommitted = Some(false)

  override def close(): Unit = {
    toBeCommitted match {
      case Some(true) => sqlSession.closeTransaction(this, true)
      case _ => sqlSession.closeTransaction(this, false)
    }
  }

  override def run(statementTemplate: String, parameters: Value): StatementResult =
    run(new Statement(statementTemplate, parameters))

  override def run(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): StatementResult =
    run(new Statement(statementTemplate, statementParameters))

  override def run(statementTemplate: String, statementParameters: Record): StatementResult =
    run(statementTemplate, statementParameters.asMap())

  override def run(statementTemplate: String): StatementResult =
    run(new Statement(statementTemplate))

  override def run(statement: Statement): StatementResult = {
    val cypherQuery = statement.text
    val compilerOptions = sqlSession.sqlDriver.initialCompilerOptions
      .copy(
        parameters = (statement.parameters(): MapAccessor).asMap.asScala.toMap,
        gTop = sqlSession.sqlDriver.gTop)
    val sqlCompiler = new CompileSql(cypherQuery, compilerOptions)

    withResources(rawSqlConnection.createStatement)(sqlStatement => {
      val translateCreateQueries = sqlSession.sqlDriver.translateCreateQueries
      if (!translateCreateQueries && CompileSql.getNodes(sqlCompiler.fPlan).exists(_.isInstanceOf[Create])) {
        val cypherSession = sqlSession.sqlDriver.backendSession.get
        cypherSession.run("MATCH (n) DETACH DELETE n")

        cypherSession.run(cypherQuery, statement.parameters)

        ExportSteps.execute(cypherSession, sqlStatement.getConnection)

        EmptySqlStatementResult
      }
      else {
        val sqlQuery = sqlCompiler.run()

        // TODO check lifecycle of ResultSet after closing sqlStatement if lazy loading is used
        withResources(sqlStatement.executeQuery(sqlQuery)) { resultSet =>
          SqlStatementResult.createFromResultSet(resultSet)
        }
      }
    })
  }

  override def typeSystem(): TypeSystem = sqlSession.typeSystem()

  override def isOpen: Boolean = sqlSession.currentTransaction.contains(this)

  override def commitAsync(): CompletionStage[Void] = ???

  override def rollbackAsync(): CompletionStage[Void] = ???

  override def runAsync(statementTemplate: String, parameters: Value): CompletionStage[StatementResultCursor] = ???

  override def runAsync(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): CompletionStage[StatementResultCursor] = ???

  override def runAsync(statementTemplate: String, statementParameters: Record): CompletionStage[StatementResultCursor] = ???

  override def runAsync(statementTemplate: String): CompletionStage[StatementResultCursor] = ???

  override def runAsync(statement: Statement): CompletionStage[StatementResultCursor] = ???
}
