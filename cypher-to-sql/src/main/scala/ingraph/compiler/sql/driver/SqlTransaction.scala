package ingraph.compiler.sql.driver

import java.sql.{Connection, DriverManager}
import java.util.{Map => javaMap}

import ingraph.compiler.sql.Util.withResources
import ingraph.compiler.sql.{CompileSql, ExportSteps}
import ingraph.model.fplan.Create
import org.neo4j.driver.v1._
import org.neo4j.driver.v1.types.TypeSystem

import scala.collection.JavaConverters._

class SqlTransaction(val sqlSession: SqlSession) extends Transaction {

  val sqlConnection: Connection = DriverManager.getConnection(sqlSession.sqlDriver.url)
  sqlConnection.setAutoCommit(false)

  var toBeCommitted: Option[Boolean] = None

  override def success(): Unit = if (toBeCommitted.isEmpty) toBeCommitted = Some(true)

  override def failure(): Unit = toBeCommitted = Some(false)

  override def close(): Unit = {
    toBeCommitted match {
      case Some(true) => sqlConnection.commit()
      case _ => sqlConnection.rollback()
    }

    sqlConnection.close()
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
    val sqlCompiler = new CompileSql(cypherQuery, statement.parameters().asMap.asScala.toMap)

    withResources(sqlConnection.createStatement)(sqlStatement => {
      val translateCreateQueries = sqlSession.sqlDriver.translateCreateQueries
      if (!translateCreateQueries && CompileSql.getNodes(sqlCompiler.fplan).exists(_.isInstanceOf[Create])) {
        val cypherSession = sqlSession.sqlDriver.backendSession.get
        cypherSession.run("MATCH (n) DETACH DELETE n")

        cypherSession.run(cypherQuery, statement.parameters)

        ExportSteps.execute(cypherSession, sqlConnection)

        EmptySqlStatementResult
      }
      else {
        val sqlQuery = sqlCompiler.run()

        // TODO check lifecycle of ResultSet after closing sqlStatement if lazy loading is used
        new SqlStatementResult(sqlStatement.executeQuery(sqlQuery))
      }
    })
  }

  override def typeSystem(): TypeSystem = sqlSession.typeSystem()

  override def isOpen: Boolean = !sqlConnection.isClosed
}
