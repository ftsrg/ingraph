package ingraph.compiler.sql.driver

import java.sql.{Connection, DriverManager}
import java.util.{Map => javaMap}

import org.neo4j.driver.v1._
import org.neo4j.driver.v1.exceptions.ClientException
import org.neo4j.driver.v1.types.TypeSystem

class SqlSession(val sqlDriver: SqlDriver) extends Session {
  val sqlConnection: Connection = DriverManager.getConnection(sqlDriver.url)
  sqlConnection.setAutoCommit(false)

  private[driver] var currentTransaction: Option[SqlTransaction] = None

  override def beginTransaction(): SqlTransaction = {
    if (currentTransaction.isDefined) {
      throw new ClientException("You cannot begin a transaction on a session with an open transaction;" + " either run from within the transaction or use a different session.")
    }
    else {
      val transaction = new SqlTransaction(this)
      currentTransaction = Some(transaction)

      transaction
    }
  }

  private[driver] def closeTransaction(sqlTransaction: SqlTransaction, toBeCommitted: Boolean): Unit = {
    if (currentTransaction.contains(sqlTransaction) && !sqlConnection.isClosed) {
      if (toBeCommitted)
        sqlConnection.commit()
      else
        sqlConnection.rollback()

      currentTransaction = None
    }
  }

  override def beginTransaction(bookmark: String): Transaction = ???

  override def readTransaction[T](work: TransactionWork[T]): T = ???

  override def writeTransaction[T](work: TransactionWork[T]): T = ???

  override def lastBookmark(): String = ???

  override def reset(): Unit = ???

  override def close(): Unit = {
    currentTransaction.foreach(_.close())
    sqlConnection.close()
  }

  override def run(statementTemplate: String, parameters: Value): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: Record): StatementResult = ???

  override def run(statementTemplate: String): StatementResult = ???

  override def run(statement: Statement): StatementResult = ???

  override def typeSystem(): TypeSystem = ???

  override def isOpen: Boolean = !sqlConnection.isClosed
}
