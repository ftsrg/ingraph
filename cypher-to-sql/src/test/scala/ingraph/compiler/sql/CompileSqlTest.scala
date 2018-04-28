package ingraph.compiler.sql

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}

import ingraph.driver.{CypherDriver, CypherDriverFactory}
import org.neo4j.driver.v1.{AuthTokens, Driver, Session, Transaction}
import org.scalatest.FunSuite
import org.sqlite.SQLiteConfig
import ingraph.compiler.sql.Util.withResources

class CompileSqlTest extends FunSuite {

  private def compileAndRunQuery(createCypherQuery: String, selectCypherQuery: String) = {
    val selectSqlQuery = new CompileSql(selectCypherQuery).run

    runGraphQuery(createCypherQuery, selectSqlQuery)
  }

  private def runGraphQuery(createCypherQuery: String, selectSqlQuery: String): Unit = {
    withResources(CypherDriverFactory.createNeo4jDriver("bolt://localhost:7687",
      AuthTokens.basic("neo4j", "admin")))(driver => {
      withResources(driver.session())(cypherSession => {
        withResources(cypherSession.beginTransaction)(tx => {
          tx.run(createCypherQuery)
          tx.success()
        })

        val config = new SQLiteConfig
        config.enforceForeignKeys(true)

        withResources(DriverManager.getConnection("jdbc:sqlite:", config.toProperties))(sqlConnection =>
          withResources(sqlConnection.createStatement)(sqlStatement => {
            sqlStatement.executeUpdate(SqlQueries.createTables)

            ExportSteps.execute(cypherSession, sqlConnection)

            dump(sqlStatement, selectSqlQuery)
          })
        )
      })
    })
  }

  private def dumpTable(sqlStatement: Statement, tablename: String): Unit = {
    dump(sqlStatement, "SELECT * FROM " + tablename)
  }

  private def dump(sqlStatement: Statement, query: String): Unit = {
    println(query + ": ")
    withResources(sqlStatement.executeQuery(query))(rs => {
      val columnIndices = 1 to rs.getMetaData.getColumnCount

      columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + ": " + rs.getMetaData.getColumnTypeName(i)))

      while (rs.next()) {
        println("---")
        columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + " = " + rs.getObject(i)))
      }

      println("----------------------------------")
    })
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L52
  test("Use multiple MATCH clauses to do a Cartesian product") {
    compileAndRunQuery("CREATE ({value: 1}), ({value: 2}), ({value: 3})",
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m""".stripMargin)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L97
  test("Filter out based on node prop name") {
    compileAndRunQuery("CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})",
      """MATCH ()-[rel:X]-(a)
        |WHERE a.name = 'Andres'
        |RETURN a""".stripMargin)
  }
}
