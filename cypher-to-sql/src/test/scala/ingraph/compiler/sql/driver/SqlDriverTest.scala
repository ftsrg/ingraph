package ingraph.compiler.sql.driver

import ingraph.compiler.sql.Util._
import org.neo4j.driver.v1.Statement
import org.neo4j.driver.v1.Values.parameters
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class SqlDriverTest extends FunSuite {
  test("Run SQL driver") {

    withResources(new SqlDriver) { driver =>
      withResources(driver.session) { session =>

        withResources(session.beginTransaction()) { tx =>
          tx.run("CREATE (a:Person {name: $name, title: $title})",
            parameters("name", "Arthur", "title", "King"))
          tx.success()
        }

        val queryStatement = new Statement(
          """MATCH (a:Person)
            |WHERE a.name = $name
            |RETURN a.name AS name, a.title AS title
          """.stripMargin,
          parameters("name", "Arthur"))

        withResources(session.beginTransaction()) { tx =>
          val result = tx.run(queryStatement)

          assert(result.keys().asScala == List("name", "title"))

          assert(result.hasNext)

          val record = result.next
          println(record.get("title").asString + " " + record.get("name").asString)

          assert(record.get("name").asString == "Arthur")
          assert(record.get("title").asString == "King")

          assert(!result.hasNext)
        }

        withResources(session.beginTransaction()) { tx =>
          val list = tx.run(queryStatement).list()

          assert(list.size() == 1)

          val record = list.get(0)

          assert(record.keys().asScala == List("name", "title"))
          assert(record.get("name").asString == "Arthur")
          assert(record.get("title").asString == "King")
        }

      }
    }

  }
}
