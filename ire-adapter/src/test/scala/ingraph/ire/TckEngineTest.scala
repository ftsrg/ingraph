package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import org.scalatest.FunSuite

class TckEngineTest extends FunSuite {

  val log = true

  def run(createQuery: String, readQuery: String): Iterable[Tuple] = {
    val indexer = new Indexer()

    if (createQuery != "") {
      val createAdapter = new IngraphOneTimeAdapter(createQuery, "create", indexer)
      createAdapter.terminate()
    }

    val readAdapter = new IngraphIncrementalAdapter(readQuery, "read", indexer)
    readAdapter.result()
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
  test("Filter out based on node prop name") {
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

  test("Filter out based on node prop name / fragment #2") {
    val results = run(
      """CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})""",
      """MATCH (a)
        |RETURN a""".stripMargin
    )
    assert(results.size == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
  test("Filter based on rel prop name") {
    val results = run(
      """CREATE (:A)<-[:KNOWS {name: 'monkey'}]-()-[:KNOWS {name: 'woot'}]->(:B)""",
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
    assert(results.size == 1)
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
  test("Get related to related to / untyped") {
    intercept[AssertionError] {
    run(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})""",
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin

    ) }
  }

  test("Get related to related to / typed") {
    val results = run(
      """CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})""",
      """MATCH (n)-[:KNOWS]->(a)-[:FRIEND]->(b)
        |RETURN b
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
  test("Handle comparison between node properties") {
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
      """MATCH (n)-[rel:KNOWS]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L243
  test("Return two subgraphs with bound undirected relationship") {
    val results = run(
      """CREATE (a:A {value: 1})-[:REL {name: 'r'}]->(b:B {value: 2})
      """.stripMargin,
      """MATCH (a)-[r:REL {name: 'r'}]-(b)
        |RETURN a, b
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L260
  test("Return two subgraphs with bound undirected relationship and optional relationship") {
    val results = run(
      """CREATE (a:A {value: 1})-[:REL {name: 'r1'}]->(b:B {value: 2})-[:REL {name: 'r2'}]->(c:C {value: 3})
      """.stripMargin,
      """MATCH (a)-[r:REL {name: 'r1'}]-(b)
        |OPTIONAL MATCH (b)-[r2:REL]-(c)
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
      """MATCH ()-[r:LOLZ]->()
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
  test("Returning bound nodes that are not part of the pattern") {
    val results = run(
      """CREATE (a {name: 'A'}), (b {name: 'B'}),
        |       (c {name: 'C'})
        |CREATE (a)-[:KNOWS]->(b)
      """.stripMargin,
      """MATCH (a {name: 'A'}), (c {name: 'C'})
        |MATCH (a)-[:KNOWS]->(b)
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
        |MATCH (a)-[:KNOWS]->(x)<-[:KNOWS]->(b)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L233
  test("Three bound nodes pointing to the same node") {
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
        |MATCH (a)-[:KNOWS]->(x), (b)-[:KNOWS]->(x), (c)-[:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
    assert(results.size == 2)
  }

// add more MATCH tests later
// from https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance2.feature#L258

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L20
  ignore("Passing on pattern nodes") {
    val results = run(
      """CREATE (:A)-[:REL]->(:B)""",
      """MATCH (a:A)
        |WITH a
        |MATCH (a)-->(b)
        |RETURN *
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L38
  test("ORDER BY and LIMIT can be used - edited") {
    val results = run(
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
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L59
  test("No dependencies between the query parts") {
    val results = run(
      """CREATE (:A), (:B)
      """.stripMargin,
      """|MATCH (a)
         |WITH a
         |MATCH (b)
         |RETURN a, b
      """.stripMargin
    )
    assert(results.size == 4)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L80
  test("Aliasing") {
    val results = run(
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
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L101
  test("Handle dependencies across WITH") {
    val results = run(
      """CREATE (a:End {prop: 42, id: 0}),
        |       (:End {prop: 3}),
        |       (:Begin {prop: a.id})
      """.stripMargin,
      """MATCH (a:Begin)
        |WITH a.prop AS property
        |  LIMIT 1
        |MATCH (b)
        |WHERE b.id = property
        |RETURN b
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L123
  test("Handle dependencies across WITH with SKIP") {
    val results = run(
      """CREATE (a {prop: 'A', key: 0, id: 0}),
        |       ({prop: 'B', key: a.id, id: 1}),
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
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L146
  test("WHERE after WITH should filter results") {
    val results = run(
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
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L166
  test("WHERE after WITH can filter on top of an aggregation") {
    val results = run(
      """CREATE (a {name: 'A'}),
        |       (b {name: 'B'})
        |CREATE (a)-[:REL]->(),
        |       (a)-[:REL]->(),
        |       (a)-[:REL]->(),
        |       (b)-[:REL]->()
      """.stripMargin,
      """MATCH (a)-[:REL]->()
        |WITH a, count(*) AS relCount
        |WHERE relCount > 1
        |RETURN a
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L189
  ignore("ORDER BY on an aggregating key") {
    val results = run(
      """CREATE ({bar: 'A'}),
        |       ({bar: 'A'}),
        |       ({bar: 'B'})
      """.stripMargin,
      """MATCH (a)
        |WITH a.bar AS bars, count(*) AS relCount
        |ORDER BY a.bar
        |RETURN *
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L210
  ignore("ORDER BY a DISTINCT column") {
    val results = run(
      """CREATE ({bar: 'A'}),
        |       ({bar: 'A'}),
        |       ({bar: 'B'})
      """.stripMargin,
      """MATCH (a)
        |WITH DISTINCT a.bar AS bars
        |ORDER BY a.bar
        |RETURN *
      """.stripMargin
    )
    assert(results.size == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L231
  ignore("WHERE on a DISTINCT column") {
    val results = run(
      """CREATE ({bar: 'A'}),
        |       ({bar: 'A'}),
        |       ({bar: 'B'})
      """.stripMargin,
      """MATCH (a)
        |WITH DISTINCT a.bar AS bars
        |WHERE a.bar = 'B'
        |RETURN *
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L251
  test("A simple pattern with one bound endpoint") {
    val results = run(
      """CREATE (:A)-[:REL]->(:B)""",
      """MATCH (a:A)-[r:REL]->(b:B)
        |WITH a AS b, b AS tmp, r AS r
        |WITH b AS a, r
        |LIMIT 1
        |MATCH (a)-[r]->(b)
        |RETURN a, r, b
      """.stripMargin
    )
    assert(results.size == 1)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L271
  ignore("Null handling") {
    val results = run( //
      "",
      """OPTIONAL MATCH (a:Start)
        |WITH a
        |MATCH (a)-->(b)
        |RETURN *
      """.stripMargin
    )
    assert(results.size == 0)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/WithAcceptance.feature#L316
  ignore("Single WITH using a predicate and aggregation") {
    val results = run(
      """CREATE ({prop: 43}), ({prop: 42})""",
      """MATCH (n)
        |WITH n
        |WHERE n.prop = 42
        |RETURN count(*)
      """.stripMargin
    )
    assert(results.size == 1)
  }

  //
  test("Multiple WITHs using a predicate and aggregation") {
    val results = run(
      """CREATE (a {name: 'David'}),
        |       (b {name: 'Other'}),
        |       (c {name: 'NotOther'}),
        |       (d {name: 'NotOther2'}),
        |       (a)-[:REL]->(b),
        |       (a)-[:REL]->(c),
        |       (a)-[:REL]->(d),
        |       (b)-[:REL]->(),
        |       (b)-[:REL]->(),
        |       (c)-[:REL]->(),
        |       (c)-[:REL]->(),
        |       (d)-[:REL]->()
      """.stripMargin,
      """MATCH (david {name: 'David'})-[:REL]-(otherPerson)-[:REL]->()
        |WITH otherPerson, count(*) AS foaf
        |WHERE foaf > 1
        |WITH otherPerson
        |WHERE otherPerson.name <> 'NotOther'
        |RETURN count(*)
      """.stripMargin
    )
    assert(results.size == 1)
  }


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
