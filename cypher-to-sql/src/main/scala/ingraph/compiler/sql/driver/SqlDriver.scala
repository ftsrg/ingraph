package ingraph.compiler.sql.driver

import java.lang
import java.sql.{DriverManager, Statement => sqlStatement}

import ingraph.compiler.sql.Util.withResources
import ingraph.compiler.sql._
import ingraph.compiler.sql.driver.SqlDriver.{Database, PostgresDatabase, addIdColumnToGTopAttributes}
import ingraph.driver.{CypherDriver, CypherDriverFactory}
import org.apache.log4j.{Level, LogManager}
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.implementation.relational.Attribute
import org.neo4j.driver.v1._
import org.neo4j.driver.v1.exceptions.NoSuchRecordException
import org.postgresql.jdbc.PgArray
import org.postgresql.util.PGobject

import scala.collection.JavaConverters._

class SqlDriver(val translateCreateQueries: Boolean = false,
                val gTop: Option[GTop] = None,
                val database: Database = new PostgresDatabase,
                val initializeDb: Boolean = true)
  extends CypherDriver {

  gTop.foreach(addIdColumnToGTopAttributes)

  // TODO implement CREATE command instead of using Neo4j
  val backendDriver: Option[CypherDriver] =
    if (translateCreateQueries)
      None
    else
      Some(CypherDriverFactory.createNeo4jDriver())
  val backendSession: Option[Session] = backendDriver.map(_.session)

  val url = database.url

  if (initializeDb) {
    withResources(DriverManager.getConnection(url)) { sqlConnection =>
      withResources(sqlConnection.createStatement) {
        _.executeUpdate(SqlQueries.createTables)
      }
    }
  }

  override def session: SqlSession = new SqlSession(this)

  override def close(): Unit = {
    database.close()

    backendSession.foreach(_.close)
    backendDriver.foreach(_.close)
  }

  override def isEncrypted = false

  override def session(mode: AccessMode): Session = ???

  override def session(bookmark: String): Session = ???

  override def session(mode: AccessMode, bookmark: String): Session = ???

  override def session(bookmarks: lang.Iterable[String]): Session = ???

  override def session(mode: AccessMode, bookmarks: lang.Iterable[String]): Session = ???
}

object SqlDriver {

  trait Database extends AutoCloseable {
    def url: String
  }

  class PostgresDatabase extends Database {
    LogManager.getRootLogger.setLevel(Level.ERROR)
    val postgres = new EmbeddedPostgresWrapper

    override def url: String = postgres.Url

    override def close(): Unit = postgres.close()
  }

  case class ExternalDatabase(url: String) extends Database {
    // the database should be closed from outside
    override def close(): Unit = {}
  }

  def dumpTable(sqlStatement: sqlStatement, tablename: String): Unit = {
    dump(sqlStatement, "SELECT * FROM " + tablename)
  }

  def dump(sqlStatement: sqlStatement, query: String): Unit = {
    withResources(sqlStatement.executeQuery(query))(rs => {
      println("vvvvvvvvvvv SQL RESULT vvvvvvvvvvv")

      val columnIndices = 1 to rs.getMetaData.getColumnCount
      columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + ": " + rs.getMetaData.getColumnTypeName(i)))

      while (rs.next()) {
        println("---")
        columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + " = " + rs.getObject(i)))
      }

      println("----------------------------------")
    })
  }

  def jsonToObject(jsonString: String): Value = {
    ValueJsonConversion.gson.fromJson(jsonString, classOf[Value])
  }

  def toValue(value: Any): Value = {
    value match {
      case value: PGobject if value.getType == "jsonb" => {
        toValue(jsonToObject(value.getValue))
      }
      case value: PgArray => {
        val array = value.getArray.asInstanceOf[Array[_]]
          .map { element =>
            if (value.getBaseTypeName == "jsonb")
              jsonToObject(element.asInstanceOf[String])
            else
              element
          }
          .map(toValue)

        toValue(array)
      }
      case default => Values.value(default)
    }
  }

  def throwNoMoreRecords =
    throw new NoSuchRecordException("No more records")

  def throwMoreRecordsThanOne =
    throw new NoSuchRecordException("Expected a result with a single record, but this result contains at least one more.")

  def throwNoSingleRecordInEmptyResult =
    throw new NoSuchRecordException("Cannot retrieve a single record, because this result is empty.")

  def throwCannotPeekPastLastRecord =
    throw new NoSuchRecordException("Cannot peek past the last record")

  def addIdColumnToGTopAttributes(gTop: GTop): Unit = {
    val idAttributeName = "id"

    gTop.getAbstractionLevel.getAbstractionNodes.asScala.foreach { node =>
      val attributes = node.getAttributes
      if (!attributes.asScala.contains(idAttributeName))
        attributes.add(idAttributeName)
    }

    gTop.getImplementationLevel.getImplementationNodes.asScala.foreach { node =>
      assert(node.getId.size == 1, "Only supports single key")
      val nodeId = node.getId.get(0)

      if (!node.getAttributes.asScala.exists(_.getAbstractionLevelName == idAttributeName))
        node.getAttributes.add(new Attribute(nodeId.getColumnName, idAttributeName, nodeId.getDatatype))
    }
  }
}
