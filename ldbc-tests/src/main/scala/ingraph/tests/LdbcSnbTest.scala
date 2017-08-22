package ingraph.tests

import org.scalatest.FunSuite

import scala.io.Source

abstract class LdbcSnbTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"../queries/ldbc-snb-${workload}/${workload}-${query}.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  val nodeFilenames: Map[String, List[String]] = Map(
    modelPath("comment") -> List("Message", "Comment"),
    modelPath("forum") -> List("Forum"),
    modelPath("organisation") -> List("Company", "University"),
    modelPath("person") -> List("Person"),
    modelPath("place") -> List("Place"),
    modelPath("post") -> List("Message", "Post"),
    modelPath("tagclass") -> List("TagClass"),
    modelPath("tag") -> List("Tag")
  )

  val relationshipFilenames: Map[String, String] = Map(
    modelPath("comment_hasCreator_person") -> "hasCreator",
    modelPath("comment_isLocatedIn_place") -> "isLocatedIn",
    modelPath("comment_replyOf_comment") -> "replyOf",
    modelPath("comment_replyOf_post") -> "replyOf",
    modelPath("forum_containerOf_post") -> "containerOf",
    modelPath("forum_hasMember_person") -> "hasMember",
    modelPath("forum_hasModerator_person") -> "hasModerator",
    modelPath("forum_hasTag_tag") -> "hasTag",
    modelPath("person_hasInterest_tag") -> "hasInterest",
    modelPath("person_isLocatedIn_place") -> "isLocatedIn",
    modelPath("person_knows_person") -> "knows",
    modelPath("person_likes_comment") -> "likes",
    modelPath("person_likes_post") -> "likes",
    modelPath("place_isPartOf_place") -> "isPartOf",
    modelPath("post_hasCreator_person") -> "hasCreator",
    modelPath("comment_hasTag_tag") -> "hasTag",
    modelPath("post_hasTag_tag") -> "hasTag",
    modelPath("post_isLocatedIn_place") -> "isLocatedIn",
    modelPath("tagclass_isSubclassOf_tagclass") -> "isSubclassOf",
    modelPath("tag_hasType_tagclass") -> "hasType",
    modelPath("organisation_isLocatedIn_place") -> "isLocatedIn",
    modelPath("person_studyAt_organisation") -> "studyAt",
    modelPath("person_workAt_organisation") -> "workAt"
  )

  case class TestCase(workload: String, number: Int)

  val testCases =
    List(2, 3, 4, 5, 6, 7, 9, 12, 13, 14, 15, 24).map(new TestCase("bi", _)) ++
    List(1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 16, 19, 21, 22, 23, 24).map(new TestCase("simple", _))
//      List(7).map(new TestCase("simple", _))

  // BI
  // 1 // CASE
  // 8 // PATH
  // 10 // CASE
  // 11 // requires list comprehensions
  // 16 // PATH
  // 17 // no Cypher implementation yet
  // 18 // no Cypher implementation yet
  // 19 // antijoin
  // 21 // no Cypher implementation yet
  // 22 // no Cypher implementation yet
  // 23 // no Cypher implementation yet
  // 25 // no cypher implementation yet

  testCases.filter(_ != null) //
    .foreach(
    t =>
      test(s"${t.workload}-${t.number}-size-1") {

      val queryName = s"ldbc-snb-${t.workload}-${t.number}"
      val querySpecification = Source.fromFile(queryPath(t.workload, t.number)).getLines().mkString("\n")

      runQuery(t.workload, t.number, queryName, querySpecification)
    })

  def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String)
}
