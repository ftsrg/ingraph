package ingraph.sandbox

import ingraph.compiler.test.CompilerTest
import ingraph.model.expr.{IndexLookupExpression, IndexRangeExpression}
import ingraph.model.fplan._
import ingraph.model.gplan

class TckCompilerTest extends CompilerTest {

  test("Hello World") {
    val stages = compile(
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 1)
  }

  test("Filtering for vertices in MATCH") {
    val stages = compile(
      """MATCH (n {name: 'John'})
        |RETURN n
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 2)
  }

  test("Filtering for edges in MATCH") {
    val stages = compile(
      """MATCH (n)-[:REL {prop: 'value'}]->(m)
        |RETURN n, m
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 4)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L52
  test("TCK test: Use multiple MATCH clauses to do a Cartesian product") {
    val createStages = compile(
      """CREATE ({value: 1}),
        |  ({value: 2}),
        |  ({value: 3})
      """.stripMargin)
    println(createStages)

    val readStages = compile(
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m
      """.stripMargin
    )
    assert(getLeafNodes(readStages.fplan)(0).flatSchema.length == 2)
    assert(getLeafNodes(readStages.fplan)(1).flatSchema.length == 2)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L97
  test("TCK test: Filter out based on node prop name") {
    val stages = compile(
      """MATCH ()-[rel:X]-(a)
        |WHERE a.name = 'Andres'
        |RETURN a
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 4)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L131
  test("TCK test: Filter based on rel prop name") {
    val stages = compile(
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 4)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L167
  test("TCK test: Get neighbours") {
    val stages = compile(
      """MATCH (n1)-[rel:KNOWS]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L183
  test("TCK test: Get two related nodes") {
    val stages = compile(
      """MATCH ()-[rel:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L202
  test("TCK test: Get related to related to") {
    val stages = compile(
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L218
  test("TCK test: Handle comparison between node properties") {
    val stages = compile(
      """MATCH (n)-[rel]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 5)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L323
  test("TCK test: Handle OR in the WHERE clause") {
    val stages = compile(
      """MATCH (n)
        |WHERE n.p1 = 12 OR n.p2 = 13
        |RETURN n
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 3)
  }

  // https://github.com/opencypher/openCypher/blob/5a2b8cc8037225b4158e231e807a678f90d5aa1d/tck/features/MatchAcceptance.feature#L456
  test("TCK test: Return relationships by collecting them as a list - undirected") {
    val stages = compile(
      """MATCH (a:Start)-[r:REL*2..2]-(b)
        |RETURN r
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).flatSchema.length == 1)
  }

  def getNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) plan :: Nil
    else plan.children.flatMap(x => getNodes(x)) :+ plan
  }

  def variableLengthPatternTest(testName: String, boundsPart: String, lowerBound: Option[Int], upperBound: Option[Int]): Unit = {
    test(testName) {
      val stages = compile(
        s"""MATCH (a)-[e*$boundsPart]-(b)
           |RETURN *
        """.stripMargin
      )
      val edgeListAttribute = getNodes(stages.fplan)
        .collectFirst { case node: TransitiveJoin => node }
        .get.nnode.edgeList

      assert(edgeListAttribute.minHops == lowerBound)
      assert(edgeListAttribute.maxHops == upperBound)
    }
  }

  variableLengthPatternTest("Variable-length pattern: no bounds", "", None, None)
  variableLengthPatternTest("Variable-length pattern: no bounds with range", "..", None, None)
  variableLengthPatternTest("Variable-length pattern: exact bound", "2", Some(2), Some(2))
  variableLengthPatternTest("Variable-length pattern: lower bound", "2..", Some(2), None)
  variableLengthPatternTest("Variable-length pattern: upper bound", "..2", None, Some(2))
  variableLengthPatternTest("Variable-length pattern: same lower and upper bounds", "2..2", Some(2), Some(2))
  variableLengthPatternTest("Variable-length pattern: zero as lower bound", "0..", Some(0), None)

  test("Index expression") {
    val stages = compile(
      "RETURN range(0,10)[2]"
    )

    val actualIndex = stages.gplan.asInstanceOf[gplan.Production]
      .child.asInstanceOf[gplan.Projection]
      .projectList
      .head
      .child.asInstanceOf[IndexLookupExpression]
      .index

    assert(2 == actualIndex)
  }

  def indexRangeExpressionTest(testName: String, lowerBound: Option[Int], upperBound: Option[Int]): Unit = {
    test(testName) {
      val lowerBoundString = lowerBound.getOrElse("")
      val upperBoundString = upperBound.getOrElse("")

      val stages = compile(
        s"RETURN range(0,10)[$lowerBoundString..$upperBoundString]"
      )

      val indexRangeExpression = stages.gplan.asInstanceOf[gplan.Production]
        .child.asInstanceOf[gplan.Projection]
        .projectList
        .head
        .child.asInstanceOf[IndexRangeExpression]

      assert(lowerBound == indexRangeExpression.lower)
      assert(upperBound == indexRangeExpression.upper)
    }
  }

  indexRangeExpressionTest("Index range expression: both bound", Some(2), Some(5))
  indexRangeExpressionTest("Index range expression: lower bound", Some(2), None)
  indexRangeExpressionTest("Index range expression: upper bound", None, Some(5))

  ignore("Start with WITH") {
    val stages = compile(
      """WITH 1 AS x
        |MATCH (some:Thing {prop: x})
        |RETURN some.prop
      """.stripMargin
    )
    findFirstByType(stages.fplan, classOf[Selection]).conditionTuple
  }

  test("Unnamed columns") {
    val stages = compile(
      """MATCH (n)
        |RETURN `n`, (n :Label), n. /**/ `id`, count(n), 1 + 1, $`param`
      """.stripMargin
    )
    val expectedColumnNames = Seq("n", "(n :Label)", "n. /**/ `id`", "count(n)", "1 + 1", "$`param`")
    val actualColumnNames = stages.fplan.asInstanceOf[Production].outputNames.toSeq
    assert(expectedColumnNames == actualColumnNames)
  }

  ignore("Placeholder for debugging plans") {
    val stages = compile(
      """
      """.stripMargin
    )
  }

}
