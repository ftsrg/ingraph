package ingraph.compiler.sql

import java.sql.{Connection, DriverManager, Statement}

import ingraph.compiler.sql.Util.withResources
import ingraph.driver.CypherDriverFactory
import org.neo4j.driver.internal.value.{IntegerValue, NodeValue, StringValue}
import org.neo4j.driver.v1.{AuthTokens, Transaction}
import org.scalatest.FunSuite
import org.sqlite.SQLiteConfig

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

class CompileSqlTest extends FunSuite {

  private def compileAndRunQuery(createCypherQuery: String, selectCypherQuery: String): Unit = {
    val selectSqlQuery = new CompileSql(selectCypherQuery).run

    runGraphQuery(createCypherQuery, selectCypherQuery, selectSqlQuery)
  }

  def convertCypherCell(value: Any): Any = {
    value match {
      case value: IntegerValue => value.asLong()
      case value: NodeValue => value.asNode().id()
      case value: StringValue => value.asString()
      case _ => value
    }
  }

  def convertSqlCell(value: Any): Any = {
    value match {
      case _ => value
    }
  }

  def compareQueryResults(cypherTransaction: Transaction, selectCypherQuery: String, sqlConnection: Connection, selectSqlQuery: String): Unit = {
    withResources(sqlConnection.createStatement())(sqlStatement =>
      withResources(sqlStatement.executeQuery(selectSqlQuery))(sqlResultSet => {
        val cypherResult = cypherTransaction.run(selectCypherQuery)
        assertResult(cypherResult.keys.size)(sqlResultSet.getMetaData.getColumnCount)

        val cypherResultList = cypherResult.asScala.map(record => record.values().asScala.toArray).toArray

        val sqlResultBuffer = new ListBuffer[Array[Any]]()
        while (sqlResultSet.next()) {
          sqlResultBuffer += (1 to sqlResultSet.getMetaData.getColumnCount).map(i => sqlResultSet.getObject(i)).toArray
        }
        val sqlResultList = sqlResultBuffer.toArray

        assertResult(cypherResultList.length)(sqlResultList.length)

        for (rowIndex <- cypherResultList.indices) {
          val cypherRow = cypherResultList(rowIndex)
          val sqlRow = sqlResultList(rowIndex)

          assertResult(cypherRow.length)(sqlRow.length)
          for (columnIndex <- cypherRow.indices) {
            val cypherCell = convertCypherCell(cypherRow(columnIndex))
            val sqlCell = convertSqlCell(sqlRow(columnIndex))

            assertResult(cypherCell)(sqlCell)
          }
        }
      }))
  }

  private def runGraphQuery(createCypherQuery: String, selectCypherQuery: String, selectSqlQuery: String): Unit = {
    withResources(CypherDriverFactory.createNeo4jDriver("bolt://localhost:7687",
      AuthTokens.basic("neo4j", "admin")))(driver =>
      withResources(driver.session())(cypherSession =>
        withResources(cypherSession.beginTransaction)(cypherTransaction => {
          cypherTransaction.run(createCypherQuery)
          cypherTransaction.success()

          println("vvvvvvvv REFERENCE RESULT vvvvvvvv")
          val result = cypherTransaction.run(selectCypherQuery)
          for (record <- result.asScala) {
            for ((key, value) <- record.asMap().asScala) {
              println(s"""$key = $value""")
            }
            println("---")
          }
          println("----------------------------------")

          val config = new SQLiteConfig
          config.enforceForeignKeys(true)

          withResources(DriverManager.getConnection("jdbc:sqlite:", config.toProperties))(sqlConnection =>
            withResources(sqlConnection.createStatement)(sqlStatement => {
              sqlStatement.executeUpdate(SqlQueries.createTables)

              ExportSteps.execute(cypherTransaction, sqlConnection)

              dump(sqlStatement, selectSqlQuery)

              compareQueryResults(cypherTransaction, selectCypherQuery, sqlConnection, selectSqlQuery)
            })
          )
        })))
  }

  private def dumpTable(sqlStatement: Statement, tablename: String): Unit = {
    dump(sqlStatement, "SELECT * FROM " + tablename)
  }

  private def dump(sqlStatement: Statement, query: String): Unit = {
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

  test("Filter out based on node prop name / fragment #1") {
    compileAndRunQuery(
      """CREATE ()""",
      """MATCH (a)
        |WHERE a.name = 'x'
        |RETURN a""".stripMargin
    )
  }

  test("Filter out based on node prop name / fragment #2") {
    compileAndRunQuery(
      """CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})""",
      """MATCH (a)
        |RETURN a""".stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
  test("Filter based on rel prop name") {
    compileAndRunQuery(
      """CREATE (:A)<-[:KNOWS {name: 'monkey'}]-()-[:KNOWS {name: 'woot'}]->(:B)""",
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L148
  test("Cope with shadowed variables") {
    compileAndRunQuery(
      """
        |CREATE ({value: 1, name: 'King Kong'}),
        |  ({value: 2, name: 'Ann Darrow'})
      """.stripMargin,
      """MATCH (n)
        |WITH n.name AS n
        |RETURN n
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L167
  test("Get neighbours") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})""",
      """MATCH (n1)-[rel:KNOWS]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
  }

  test("Get neighbours / plus edges with different type, multiple possible types in query") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND_OF]->(c:C {value: 3}), (:V)-[:OTHER]->(:V)""",
      """MATCH (n1)-[rel:KNOWS|FRIEND_OF]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
  }

  test("Get neighbours / undirected edge with type constraint") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND_OF]->(c:C {value: 3}), (:V)-[:OTHER]->(:V)""",
      """MATCH (n1)-[rel:KNOWS]-(n2)
        |RETURN n1, n2
      """.stripMargin
    )
  }

  test("Get neighbours / without edge type constraint") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND_OF]->(c:C {value: 3}), (:V)-[:OTHER]->(:V)""",
      """MATCH (n1)-[rel]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L183
  test("Get two related nodes") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1}),
        |  (a)-[:KNOWS]->(b:B {value: 2}),
        |  (a)-[:KNOWS]->(c:C {value: 3})
      """.stripMargin,
      """MATCH ()-[rel:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L202
  ignore("Get related to related to / untyped") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})""",
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin
    )
  }

  ignore("Get related to related to / typed") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})""",
      """MATCH (n)-[:KNOWS]->(a)-[:FRIEND]->(b)
        |RETURN b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
  ignore("Handle comparison between node properties") {
    compileAndRunQuery(
      """CREATE (a:A {animal: 'monkey'}),
        |  (b:B {animal: 'cow'}),
        |  (c:C {animal: 'monkey'}),
        |  (d:D {animal: 'cow'}),
        |  (a)-[:KNOWS]->(b),
        |  (a)-[:KNOWS]->(c),
        |  (d)-[:KNOWS]->(b),
        |  (d)-[:KNOWS]->(c)
      """.stripMargin,
      """MATCH (n)-[rel:KNOWS]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L243
  ignore("Return two subgraphs with bound undirected relationship") {
    compileAndRunQuery(
      """CREATE (a:A {value: 1})-[:REL {name: 'r'}]->(b:B {value: 2})
      """.stripMargin,
      """MATCH (a)-[r:REL {name: 'r'}]-(b)
        |RETURN a, b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L323
  ignore("Handle OR in the WHERE clause") {
    compileAndRunQuery(
      """CREATE (a:A {p1: 12}),
        |  (b:B {p2: 13}),
        |  (c:C)
      """.stripMargin,
      """MATCH (n)
        |WHERE n.p1 = 12 OR n.p2 = 13
        |RETURN n
      """.stripMargin
    )
  }

  // MatchAcceptance2.feature

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L20
  ignore("Do not return non-existent nodes") {
    compileAndRunQuery(
      "",
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L31
  ignore("Do not return non-existent relationships") {
    compileAndRunQuery(
      "",
      """MATCH ()-[r:LOLZ]->()
        |RETURN r
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L129
  ignore("Simple variable length pattern") {
    compileAndRunQuery(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'}), (d {name: 'D'})
        |CREATE (a)-[:CONTAINS]->(b),
        |       (b)-[:CONTAINS]->(c),
        |       (c)-[:CONTAINS]->(d)
      """.stripMargin,
      """MATCH (a {name: 'A'})-[:CONTAINS*]->(x)
        |RETURN x
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L191
  ignore("Returning bound nodes that are not part of the pattern") {
    compileAndRunQuery(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'})
        |CREATE (a)-[:KNOWS]->(b)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (c {name: 'C'})
        |MATCH (a)-[:KNOWS]->(b)
        |RETURN a, b, c
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L210
  ignore("Two bound nodes pointing to the same node") {
    compileAndRunQuery(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (x1 {name: 'x1'}), (x2 {name: 'x2'})
        |CREATE (a)-[:KNOWS]->(x1),
        |       (a)-[:KNOWS]->(x2),
        |       (b)-[:KNOWS]->(x1),
        |       (b)-[:KNOWS]->(x2)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (b {name: 'B'})
        |MATCH (a)-[:KNOWS]->(x)<-[:KNOWS]->(b)
        |RETURN x
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L233
  ignore("Three bound nodes pointing to the same node") {
    compileAndRunQuery(
      """CREATE (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'}),
        |       (x1 {name: 'x1'}), (x2 {name: 'x2'})
        |CREATE (a)-[:KNOWS]->(x1),
        |       (a)-[:KNOWS]->(x2),
        |       (b)-[:KNOWS]->(x1),
        |       (b)-[:KNOWS]->(x2),
        |       (c)-[:KNOWS]->(x1),
        |       (c)-[:KNOWS]->(x2)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
        |MATCH (a)-[:KNOWS]->(x), (b)-[:KNOWS]->(x), (c)-[:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
  }

  // add more MATCH tests later
  // from https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L258

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L38
  ignore("ORDER BY and LIMIT can be used - edited") {
    compileAndRunQuery(
      """CREATE (a:A {name: 'X'}),
        |(a)-[:REL]->()
      """.stripMargin,
      """MATCH (a:A)
        |WITH a
        |ORDER BY a.name
        |LIMIT 1
        |MATCH (a)-[:REL]->(b)
        |RETURN a
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L59
  ignore("No dependencies between the query parts") {
    compileAndRunQuery(
      """CREATE (:A), (:B)
      """.stripMargin,
      """|MATCH (a)
         |WITH a
         |MATCH (b)
         |RETURN a, b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L80
  ignore("Aliasing") {
    compileAndRunQuery(
      """CREATE (:Begin {prop: 42}),
        |       (:End {prop: 42}),
        |       (:End {prop: 3})
      """.stripMargin,
      """MATCH (a:Begin)
        |WITH a.prop AS property
        |MATCH (b:End)
        |WHERE property = b.prop
        |RETURN b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L101
  ignore("Handle dependencies across WITH - CREATE edited") {
    compileAndRunQuery(
      """CREATE (a:End {prop: 42, id: 0}),
        |       (:End {prop: 3}),
        |       (:Begin {prop: 0})
      """.stripMargin,
      """MATCH (a:Begin)
        |WITH a.prop AS property
        |  ORDER BY property
        |  LIMIT 1
        |MATCH (b)
        |WHERE b.id = property
        |RETURN b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L123
  ignore("Handle dependencies across WITH with SKIP - CREATE edited") {
    compileAndRunQuery(
      """CREATE ({prop: 'A', key: 0, id: 0}),
        |       ({prop: 'B', key: 0, id: 1}),
        |       ({prop: 'C', key: 0, id: 2})
      """.stripMargin,
      """MATCH (a)
        |WITH a.prop AS property, a.key AS idToUse
        |  ORDER BY property
        |  SKIP 1
        |MATCH (b)
        |WHERE b.id = idToUse
        |RETURN DISTINCT b
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L146
  ignore("WHERE after WITH should filter results") {
    compileAndRunQuery(
      """CREATE ({name: 'A'}),
        |       ({name: 'B'}),
        |       ({name: 'C'})
      """.stripMargin,
      """MATCH (a)
        |WITH a
        |WHERE a.name = 'B'
        |RETURN a
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L166
  ignore("WHERE after WITH can filter on top of an aggregation - edited") {
    compileAndRunQuery(
      """CREATE (a {name: 'A'}),
        |       (b {name: 'B'})
        |CREATE (a)-[:REL]->(),
        |       (a)-[:REL]->(),
        |       (a)-[:REL]->(),
        |       (b)-[:REL]->()
      """.stripMargin,
      """MATCH (a)-[:REL]->(b)
        |WITH a, count(b) AS relCount
        |WHERE relCount > 1
        |RETURN a
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L251
  ignore("A simple pattern with one bound endpoint - edited") {
    compileAndRunQuery(
      """CREATE (:A {x: 'x1'})-[:REL]->(:B {x: 'x2'})""",
      """MATCH (a:A)-[r:REL]->(b:B)
        |WITH a AS b, b AS tmp, r AS r
        |WITH b AS a, r
        |ORDER BY a.x, b.x
        |LIMIT 1
        |MATCH (a)-[r:REL]->(b)
        |RETURN a, r, b
      """.stripMargin
    )
  }

  ignore("Simple collect") {
    compileAndRunQuery(
      """CREATE (), ()
      """.stripMargin,
      """MATCH (n)
        |RETURN collect(n) AS ns
      """.stripMargin
    )
  }

  ignore("Property collect") {
    compileAndRunQuery(
      """CREATE ({p: 1}), ({p: 2})
      """.stripMargin,
      """MATCH (n)
        |RETURN collect(n.p) AS ns
      """.stripMargin
    )
  }

  ignore("Property collect on edge") {
    compileAndRunQuery(
      """CREATE
        |  (x:X {id: 99}),
        |  (x)-[:REL]->({p: 1}),
        |  (x)-[:REL]->({p: 2})
      """.stripMargin,
      """MATCH (x:X)-[:REL]->(n)
        |RETURN x.id, collect(n.p) AS ns
      """.stripMargin
    )
  }

  ignore("List selectors") {
    compileAndRunQuery(
      """CREATE (:X { list: ['a', 'b'] })
      """.stripMargin,
      """MATCH (x:X)
        |RETURN x.list[1]
      """.stripMargin
    )
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/OptionalMatchAcceptance.feature#L57
  ignore("Respect predicates on the OPTIONAL MATCH") {
    compileAndRunQuery(
      """CREATE (s:Single)""",
      """MATCH (n:Single)
        |OPTIONAL MATCH (n)-[r:REL]-(m)
        |WHERE m.prop = 42
        |RETURN m
      """.stripMargin
    )
  }

  // custom aggregation test
  ignore("Count DISTINCT") {
    compileAndRunQuery(
      """CREATE
        | (p1:Person),
        | (p2:Person),
        | (c:City),
        | (p1)-[:LIVES_IN]->(c),
        | (p2)-[:LIVES_IN]->(c)
      """.stripMargin,
      """MATCH (p:Person)-[:LIVES_IN]->(c:City)
        |RETURN count(DISTINCT c) AS cc
      """.stripMargin
    )
  }

  // custom aggregation test
  ignore("Count DISTINCT properties") {
    compileAndRunQuery(
      """CREATE
        | (p1:Person {name: 'Alan'}),
        | (p2:Person {name: 'Alan'}),
        | (c:City),
        | (p1)-[:LIVES_IN]->(c),
        | (p2)-[:LIVES_IN]->(c)
      """.stripMargin,
      """MATCH (p:Person)-[:LIVES_IN]->(c:City)
        |RETURN count(DISTINCT p.name) AS ps
      """.stripMargin
    )
  }

  ignore("count empty on OPTIONAL MATCH") {
    compileAndRunQuery("",
      """OPTIONAL MATCH (n)
        |RETURN count(n) AS cn
      """.stripMargin
    )
  }

  ignore("count empty on MATCH") {
    compileAndRunQuery("",
      """MATCH (n)
        |RETURN count(n) AS cn
      """.stripMargin
    )
  }
}
