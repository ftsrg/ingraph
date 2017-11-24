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
//    val stages = compile(
//      """MATCH (n)
//        |RETURN n
//      """.stripMargin
//    )
//  }
//
//  test("Filtering for vertices in MATCH") {
//    val stages = compile(
//      """MATCH (n {name: 'John'})
//        |RETURN n
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }
//
//  test("Filtering for edges in MATCH") {
//    val stages = compile(
//      """MATCH (n)-[:REL {prop: 'value'}]->(m)
//        |RETURN n, m
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L52
  ignore("TCK test: Use multiple MATCH clauses to do a Cartesian product") {
    val results = run(
      """CREATE ({value: 1}),
        |  ({value: 2}),
        |  ({value: 3})
      """.stripMargin,
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m
      """.stripMargin
    )
    println(results)
  }

//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L97
//  test("TCK test: Filter out based on node prop name") {
//    val stages = compile(
//      """MATCH ()-[rel:X]-(a)
//        |WHERE a.name = 'Andres'
//        |RETURN a
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
//  test("TCK test: Filter based on rel prop name") {
//    val stages = compile(
//      """MATCH (node)-[r:KNOWS]->(a)
//        |WHERE r.name = 'monkey'
//        |RETURN a
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L167
//  test("TCK test: Get neighbours") {
//    val stages = compile(
//      """MATCH (n1)-[rel:KNOWS]->(n2)
//        |RETURN n1, n2
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L183
//  test("TCK test: Get two related nodes") {
//    val stages = compile(
//      """MATCH ()-[rel:KNOWS]->(x)
//        |RETURN x
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L202
//  test("TCK test: Get related to related to") {
//    val stages = compile(
//      """MATCH (n)-->(a)-->(b)
//        |RETURN b
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
//  test("TCK test: Handle comparison between node properties") {
//    val stages = compile(
//      """MATCH (n)-[rel]->(x)
//        |WHERE n.animal = x.animal
//        |RETURN n, x
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L323
//  test("TCK test: Handle OR in the WHERE clause") {
//    val stages = compile(
//      """MATCH (n)
//        |WHERE n.p1 = 12 OR n.p2 = 13
//        |RETURN n
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
//  }
//
//  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L456
//  test("TCK test: Return relationships by collecting them as a list - undirected") {
//    val stages = compile(
//      """MATCH (a:Start)-[r:REL*2..2]-(b)
//        |RETURN r
//      """.stripMargin
//    )
//    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
//  }

}
