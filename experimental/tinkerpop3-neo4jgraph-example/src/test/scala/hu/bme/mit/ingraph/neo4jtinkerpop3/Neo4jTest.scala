import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph
import org.apache.tinkerpop.gremlin.structure.{Graph, Transaction}

import collection.mutable.Stack
import org.scalatest._
import scala.collection.JavaConversions._

class Neo4jTest extends FlatSpec with Matchers {

  val DATABASE_DIR: String = "/home/szarnyasg/neo4j/neo4j-community-3.0.4/data/databases/graph.db"

  "Neo4j Base Test" should "create two nodes and an edge" in {
    val g : Graph = Neo4jGraph.open(DATABASE_DIR)

    g.tx().onReadWrite(Transaction.READ_WRITE_BEHAVIOR.AUTO)

    // (ms1)
    val ms1 = g.addVertex("microservice")
    ms1.property("ip", "1.1.1.1")

    // (ms2)
    val ms2 = g.addVertex("microservice")
    ms2.property("ip", "1.1.1.2")

    // (ms1)-[:linked]->(ms2)
    ms1.addEdge("linked", ms2, "latency", "50")

    g.tx.commit()

    val vs = g.vertices()
    vs.foreach(it => println(it.label() + " " + it.id()))

    g.close()
  }

}
