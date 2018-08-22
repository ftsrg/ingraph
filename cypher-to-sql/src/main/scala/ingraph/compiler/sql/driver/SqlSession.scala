package ingraph.compiler.sql.driver

import java.util.{Map => javaMap}

import org.neo4j.driver.v1._
import org.neo4j.driver.v1.types.TypeSystem

class SqlSession(val sqlDriver: SqlDriver) extends Session {
  override def beginTransaction(): Transaction = new SqlTransaction(this)

  override def beginTransaction(bookmark: String): Transaction = ???

  override def readTransaction[T](work: TransactionWork[T]): T = ???

  override def writeTransaction[T](work: TransactionWork[T]): T = ???

  override def lastBookmark(): String = ???

  override def reset(): Unit = ???

  override def close(): Unit = {}

  override def run(statementTemplate: String, parameters: Value): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: Record): StatementResult = ???

  override def run(statementTemplate: String): StatementResult = ???

  override def run(statement: Statement): StatementResult = ???

  override def typeSystem(): TypeSystem = ???

  override def isOpen: Boolean = true
}
