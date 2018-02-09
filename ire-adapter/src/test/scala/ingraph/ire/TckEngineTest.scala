package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import org.scalatest.FunSuite

class TckEngineTest extends FunSuite {

  val log = true

  def run(createQuery: String, readQuery: String): Iterable[Tuple] = {
    val indexer = new Indexer()

    val createAdapter = new IngraphSearchAdapter(createQuery, "create", indexer)
    createAdapter.terminate()

    val readAdapter = new IngraphIncrementalAdapter(readQuery, "read", indexer)
    return readAdapter.result()
  }

//  test("Hello World") {
//    val results = run(
//      """MATCH (n)
//        |RETURN n
//      """.stripMargin
//    )
//  }
//
//  test("Filtering for vertices in MATCH") {
//    val results = run(
//      """MATCH (n {name: 'John'})
//        |RETURN n
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }
//
//  test("Filtering for edges in MATCH") {
//    val results = run(
//      """MATCH (n)-[:REL {prop: 'value'}]->(m)
//        |RETURN n, m
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L52
  test("Use multiple MATCH clauses to do a Cartesian product") {
    val results = run(
      """CREATE ({value: 1}),
        |  ({value: 2}),
        |  ({value: 3})
      """.stripMargin,
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m
      """.stripMargin
    )
    assert(results.size == 9)
  }

  test("Filter out based on node prop name / fragment #1") {
    val results = run(
      """CREATE ()""",
      """MATCH (a)
        |WHERE a.name = 'x'
        |RETURN a""".stripMargin
    )
    assert(results.size == 3)
  }

  test("Filter out based on node prop name / fragment #2") {
    val results = run(
      """CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})""",
      """MATCH (a)
        |RETURN a""".stripMargin
    )
    assert(results.size == 3)
  }


  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L97
  ignore("Filter out based on node prop name") {
    val results = run(
      """CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})""",
      """MATCH ()-[rel:X]-(a)
        |WHERE a.name = 'Andres'
        |RETURN a
      """.stripMargin
    )
    assert(results.size == 1)
  }

//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
//  test("Filter based on rel prop name") {
//    val results = run(
//      """MATCH (node)-[r:KNOWS]->(a)
//        |WHERE r.name = 'monkey'
//        |RETURN a
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L167
//  test("Get neighbours") {
//    val results = run(
//      """MATCH (n1)-[rel:KNOWS]->(n2)
//        |RETURN n1, n2
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L183
//  test("Get two related nodes") {
//    val results = run(
//      """MATCH ()-[rel:KNOWS]->(x)
//        |RETURN x
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L202
//  test("Get related to related to") {
//    val results = run(
//      """MATCH (n)-->(a)-->(b)
//        |RETURN b
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
//  test("Handle comparison between node properties") {
//    val results = run(
//      """MATCH (n)-[rel]->(x)
//        |WHERE n.animal = x.animal
//        |RETURN n, x
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L323
//  test("Handle OR in the WHERE clause") {
//    val results = run(
//      """MATCH (n)
//        |WHERE n.p1 = 12 OR n.p2 = 13
//        |RETURN n
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L456
//  test("Return relationships by collecting them as a list - undirected") {
//    val results = run(
//      """MATCH (a:Start)-[r:REL*2..2]-(b)
//        |RETURN r
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }

}
