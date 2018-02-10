package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import org.scalatest.FunSuite

class TckEngineTest extends FunSuite {

  val log = true

  def run(createQuery: String, readQuery: String): Iterable[Tuple] = {
    val indexer = new Indexer()

    if (createQuery != "") {
      val createAdapter = new IngraphSearchAdapter(createQuery, "create", indexer)
      createAdapter.terminate()
    }

    val readAdapter = new IngraphIncrementalAdapter(readQuery, "read", indexer)
    return readAdapter.result()
  }

  // MatchAcceptance.feature

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

  test("Filter out based on node prop name / fragment #1") {
    val results = run(
      """CREATE ()""",
      """MATCH (a)
        |WHERE a.name = 'x'
        |RETURN a""".stripMargin
    )
    assert(results.size == 0)
  }

  ignore("Filter out based on node prop name / fragment #2") {
    val results = run(
      """CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})""",
      """MATCH (a)
        |RETURN a""".stripMargin
    )
    assert(results.size == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
  ignore("Filter based on rel prop name") {
    val results = run(
      """CREATE (:A)<-[:KNOWS {name: 'monkey'}]-()-[:KNOWS {name: 'woot'}]->(:B)""",
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
    assert(results.size == 0)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L148
  test("Cope with shadowed variables") {
    val results = run(
      """
        |CREATE ({value: 1, name: 'King Kong'}),
        |  ({value: 2, name: 'Ann Darrow'})
      """.stripMargin,
      """MATCH (n)
        |WITH n.name AS n
        |RETURN n
      """.stripMargin
    )
    assert(results.size == 2)
  }

    // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L167
  test("Get neighbours") {
    val results = run(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})""",
      """MATCH (n1)-[rel:KNOWS]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L183
  test("Get two related nodes") {
    val results = run(
      """CREATE (a:A {value: 1}),
        |  (a)-[:KNOWS]->(b:B {value: 2}),
        |  (a)-[:KNOWS]->(c:C {value: 3})
      """.stripMargin,
      """MATCH ()-[rel:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L202
  ignore("Get related to related to") {
    val results = run(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})""",
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
  ignore("Handle comparison between node properties") {
    val results = run(
      """CREATE (a:A {animal: 'monkey'}),
        |  (b:B {animal: 'cow'}),
        |  (c:C {animal: 'monkey'}),
        |  (d:D {animal: 'cow'}),
        |  (a)-[:KNOWS]->(b),
        |  (a)-[:KNOWS]->(c),
        |  (d)-[:KNOWS]->(b),
        |  (d)-[:KNOWS]->(c)
      """.stripMargin,
      """MATCH (n)-[rel]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L243
  ignore("Return two subgraphs with bound undirected relationship") {
    val results = run(
      """CREATE (a:A {value: 1})-[:REL {name: 'r'}]->(b:B {value: 2})
      """.stripMargin,
      """MATCH (a)-[r {name: 'r'}]-(b)
        |RETURN a, b
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L260
  ignore("Return two subgraphs with bound undirected relationship and optional relationship") {
    val results = run(
      """CREATE (a:A {value: 1})-[:REL {name: 'r1'}]->(b:B {value: 2})-[:REL {name: 'r2'}]->(c:C {value: 3})
      """.stripMargin,
      """MATCH (a)-[r {name: 'r1'}]-(b)
        |OPTIONAL MATCH (b)-[r2]-(c)
        |WHERE r <> r2
        |RETURN a, b, c
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L323
  test("Handle OR in the WHERE clause") {
    val results = run(
      """CREATE (a:A {p1: 12}),
        |  (b:B {p2: 13}),
        |  (c:C)
      """.stripMargin,
      """MATCH (n)
        |WHERE n.p1 = 12 OR n.p2 = 13
        |RETURN n
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // MatchAcceptance2.feature

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L20
  test("Do not return non-existent nodes") {
    val results = run(
      "",
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
    assert(results.size == 0)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L31
  test("Do not return non-existent relationships") {
    val results = run(
      "",
      """MATCH ()-[r]->()
        |RETURN r
      """.stripMargin
    )
    assert(results.size == 0)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L107
  ignore("Zero-length variable length pattern in the middle of the pattern") {
    val results = run(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'}), ({name: 'D'}),
        |       ({name: 'E'})
        |CREATE (a)-[:CONTAINS]->(b),
        |       (b)-[:FRIEND]->(c)
      """.stripMargin,
      """MATCH (a {name: 'A'})-[:CONTAINS*0..1]->(b)-[:FRIEND*0..1]->(c)
        |RETURN a, b, c
      """.stripMargin
    )
    assert(results.size == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L129
  ignore("Simple variable length pattern") {
    val results = run(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'}), (d {name: 'D'})
        |CREATE (a)-[:CONTAINS]->(b),
        |       (b)-[:CONTAINS]->(c),
        |       (c)-[:CONTAINS]->(d)
      """.stripMargin,
      """MATCH (a {name: 'A'})-[*]->(x)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L191
  ignore("Returning bound nodes that are not part of the pattern") {
    val results = run(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'})
        |CREATE (a)-[:KNOWS]->(b)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (c {name: 'C'})
        |MATCH (a)-->(b)
        |RETURN a, b, c
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L210
  ignore("Two bound nodes pointing to the same node") {
    val results = run(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (x1 {name: 'x1'}), (x2 {name: 'x2'})
        |CREATE (a)-[:KNOWS]->(x1),
        |       (a)-[:KNOWS]->(x2),
        |       (b)-[:KNOWS]->(x1),
        |       (b)-[:KNOWS]->(x2)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (b {name: 'B'})
        |MATCH (a)-->(x)<-->(b)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L233
  ignore("Three bound nodes pointing to the same node") {
    val results = run(
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
        |MATCH (a)-->(x), (b)-->(x), (c)-->(x)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 2)
  }

// cont. later from https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L258

//  //
//  test("") {
//    val results = run(
//      """
//      """.stripMargin,
//      """
//      """.stripMargin
//    )
//    assert(results.size == )
//  }

}
