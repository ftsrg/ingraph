package ingraph.compiler.sql

import org.opencypher.tools.tck.api._
import org.opencypher.tools.tck.values.CypherValue

object TckScenarioMain extends App {
  val scenario = CypherTCK.parseClasspathFeatures("/features").flatMap(_.scenarios).find(sc => sc.name == "Use multiple MATCH clauses to do a Cartesian product").get
  println(scenario)

  scenario(new Graph {
    override def cypher(query: String, params: Map[String, CypherValue], meta: QueryType): Result = {
      println((query, params, meta))

      val result = (1 to 3).flatMap(n => (1 to 3).map(m => Map("n" -> n.toString, "m" -> m.toString))).toList

      meta match {
        case ExecQuery => CypherValueRecords.fromRows(List("n", "m"), result, false)
        case _ => CypherValueRecords.empty
      }
    }

    override def close(): Unit = super.close()
  }).execute()
}
