package ingraph.sandbox

class RandomCompilationTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = None
    , compileQPlanOnly = false
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = false
    , printCypher = true
    , printQPlan = true
    , printJPlan = true
    , printFPlan = true
  )

  test("Random test from cypher string") {
    compile(
      """MATCH (segment:Segment)
        |WHERE NOT NOT NOT (segment.length > 0)
        |RETURN segment, segment.length AS length
        |     , case segment.length when +0 then 'zero' when --+-1 then 'almost OK' else 'too bad' end AS lengthComment""".stripMargin)

  }

  test("Random double edge variable in the same MATCH") {
    compile(
      """MATCH
        |  (a)-[e]->(b),
        |  (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

  test("Random double edge variable in separate MATCHes") {
    compile(
      """MATCH (a)-[e]->(b)
        |MATCH (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

  test("Random: no edge variable in pattern") {
    compile(
      """MATCH (a)
        |OPTIONAL MATCH (a)-->(b)
        |WHERE 1=2
        |RETURN b""".stripMargin
    )
  }

  ignore("Random: create w/ vertex and edge properties") {
    compile(
      """CREATE (:Foo {a: 23, b: 'bar'})-[:FooEdge {a:11, c:'edge'}]->(:Foo2), (:Foo3)
        |RETURN 1 AS one""".stripMargin
    )
  }

  test("Random: vertex pattern with property map") {
    compile(
      """MATCH (n {id: "n1"})
        |WHERE n.length=1
        |RETURN n""".stripMargin
    )
  }

  ignore("Random: create after match") {
    compile(
      """MATCH (n:Foo)
        |CREATE (n)-[:Bar]->(m:Foo2)-[:Bar2]->(o:Foo3)
        |RETURN n, m""".stripMargin
    )
  }

  ignore("Random: various expressions") {
    compile(
      """MATCH (segment:Segment), (foo:Foo), (bar:Bar)
        |WHERE 1=1
        |  AND segment.name STARTS WITH 'alibaba'
        |  AND 'rablo' ENDS WITH segment.name
        |  AND 'mese' CONTAINS 'uveghegy'
        |  AND segment.length <= 0
        |  AND 2<3
        |  AND NOT 1=1
        |  OR 0=1
        |  OR 1=1
        |  AND sin(3)+4*2^6 > segment.length
        |  AND sin(segment.length) > 2
        |  AND segment.length IS NULL
        |RETURN DISTINCT *, sum(segment.width) as w, segment as l, [1, segment.length, cos(3)] as list, count(*) as ize
        |ORDER BY 2+3 DESC, l.width ASC""".stripMargin
    )
  }

  test("Random: expression IS NULL") {
    compile(
      """MATCH (n:Person)
        |WHERE n.name IS NULL
        |RETURN n, n.name""".stripMargin
    )
  }

  test("Random: expression IS NOT NULL") {
    compile(
      """MATCH (n:Person)
        |WHERE n.name IS NOT NULL
        |RETURN n, n.name""".stripMargin
    )
  }

  test("Random: LIMIT w/o SKIP") {
    compile(
      """MATCH (p:Person)
        |RETURN p
        |ORDER BY 1
        |LIMIT 10""".stripMargin
    )
  }

  test("Random: SKIP w/o LIMIT") {
    compile(
      """MATCH (p:Person)
        |RETURN p
        |ORDER BY 1
        |SKIP 3""".stripMargin
    )
  }

  test("Random: SKIP w/ LIMIT") {
    compile(
      """MATCH (p:Person)
        |RETURN p
        |ORDER BY 1
        |SKIP 3
        |LIMIT 10""".stripMargin
    )
  }

  test("Random: ORDER BY Person.name, w/o SKIP/LIMIT") {
    compile(
      """MATCH (p:Person)
        |RETURN p
        |ORDER BY p.name""".stripMargin
    )
  }

  test("Random: ORDER BY multiple expressions, w/o SKIP/LIMIT") {
    compile(
      """MATCH (p:Person)
        |RETURN p
        |ORDER BY p.age, p.age asc, p.name desc""".stripMargin
    )
  }

  test("Random: ORDER BY with aliases exchanged") {
    compile(
      """MATCH (p:Person)-->(c:Car)
        |RETURN p as c, c as p
        |ORDER BY c.name""".stripMargin
    )
  }

  // See: slizaa/slizaa-opencypher-xtext#24
  ignore("Random: WITH..WHERE") {
    compile(
      """MATCH (p:Person)-->(c:Car)
        |WITH p, count(c) as carNumber
        |WHERE carNumber > 1
        |MATCH (p)-->(c:Car)
        |RETURN p, c
      """.stripMargin
    )
  }

  test("Random: WITH..WHERE simple") {
    compile(
      """MATCH (p:Person)-->(c:Car)
        |WITH p, count(c) as carNumber
        |WHERE carNumber > 1
        |RETURN p
      """.stripMargin
    )
  }

  test("Random: variable length path") {
    compile(
      """MATCH (n1)-[p*3..]->(n2)
        |RETURN n1, n2
      """.stripMargin)
  }

  test("Random: 2-part query featuring WITH") {
    compile(
      """MATCH (p:Person)
        |WITH DISTINCT p
        |MATCH (p)-->(c:Car)
        |RETURN p, c
      """.stripMargin
    )
  }

  test("Random: compile list literal") {
    compile(
      """RETURN [1, 2+3, 'foo', 'bar'] AS l
      """.stripMargin)
  }

  test("Random: compile IN expression") {
    compile(
      """MATCH (p:Person)
        |WHERE 'en' IN p.languages
        |RETURN p.name, p.languages
      """.stripMargin)
  }

  test("Random: compile STARTS WITH") {
    compile(
      """RETURN 'foo' STARTS WITH 'f' AS b
      """.stripMargin)
  }

  test("Random: compile ENDS WITH") {
    compile(
      """RETURN 'foo' ENDS WITH 'oo' AS b
      """.stripMargin)
  }

  test("Random: compile CONTAINS") {
    compile(
      """RETURN 'bar' CONTAINS 'a' AS b
      """.stripMargin)
  }

  test("Random: compile regexp matching") {
    compile(
      """RETURN 'foo' =~ 'o+' AS b
      """.stripMargin)
  }

  test("Random: multiple labels in the label predicate") {
    compile(
      """MATCH (p)
        |WHERE p:Person:Teacher
        |RETURN p
      """.stripMargin
    )
  }

  test("Random: property lookup and multiple labels in the label predicate") {
    compile(
      """MATCH (p)
        |WHERE p.foo:Person:Teacher
        |RETURN p
      """.stripMargin
    )
  }

  ignore("Random: property lookup on map and multiple labels in the label predicate") {
    compile(
      """MATCH (p)
        |WHERE {foo: p}.foo:Person:Teacher
        |RETURN p
      """.stripMargin
    )
  }

  test("Random: multiple property lookup on map and multiple labels in the label predicate") {
    compile(
      """MATCH (p)
        |WHERE p:Person:Teacher
        |RETURN p
      """.stripMargin
    )
  }

  ignore("Random: REMOVE") {
    compile(
      """MATCH (a:Person {name: 'Alice'})-[:KNOWS]->(b:Person {name: 'Bob'})
        |REMOVE CASE WHEN a.age>b.age THEN a ELSE b END.age.hair.boot
        |RETURN a, b
      """.stripMargin
    )
  }

  test("Random: count(*)") {
    compile(
      """MATCH (a:Person {name: 'Alice'})-[:KNOWS]->(b:Person)
        |RETURN a, count(*)
      """.stripMargin
    )
  }
}

/** Random compiler tests that must stop after QPlan compilation.
  *
  * This is because of limitations of the following stages.
  * See note for each of the cases.
  *
  * On the long term, this should contain no test cases.
  */
class RandomQPlanCompilationTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = None
    , compileQPlanOnly = true
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = false
    , printCypher = true
    , printQPlan = true
    , printJPlan = true
    , printFPlan = true
  )

  /* FPlan error:
'Unwind unwindattribute(listexpression(1, 2, 3), li, Some(li#0))
+- Dual
 (of class ingraph.model.jplan.Unwind)
scala.MatchError: 'Unwind unwindattribute(listexpression(1, 2, 3), li, Some(li#0))
+- Dual
 (of class ingraph.model.jplan.Unwind)
	at ingraph.compiler.qplan2jplan.SchemaInferencer$.transform(SchemaInferencer.scala:18)
   */
  test("Random: compile UNWIND w/ list literal") {
    compile(
      """UNWIND [1, 2, 3] as li
        |RETURN li AS l
      """.stripMargin)
  }
}
