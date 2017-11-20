package ingraph.tests

import org.scalatest.{FunSuite, Ignore}

import scala.io.Source

abstract class LdbcSnbTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"queries/ldbc-snb-${workload}/${workload}-${query}.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  val nodeFilenames: Map[String, List[String]] = Map(
    modelPath("comment") -> List("Message", "Comment"),
    modelPath("forum") -> List("Forum"),
    modelPath("organisation") -> List("Organisation"),
    modelPath("person") -> List("Person"),
    modelPath("place") -> List("Place"),
    modelPath("post") -> List("Message", "Post"),
    modelPath("tagclass") -> List("TagClass"),
    modelPath("tag") -> List("Tag")
  )

  val relationshipFilenames: Map[String, String] = Map(
    modelPath("comment_hasCreator_person") -> "HAS_CREATOR",
    modelPath("comment_isLocatedIn_place") -> "IS_LOCATED_IN",
    modelPath("comment_replyOf_comment") -> "REPLY_OF",
    modelPath("comment_replyOf_post") -> "REPLY_OF",
    modelPath("forum_containerOf_post") -> "CONTAINER_OF",
    modelPath("forum_hasMember_person") -> "HAS_MEMBER",
    modelPath("forum_hasModerator_person") -> "HAS_MODERATOR",
    modelPath("forum_hasTag_tag") -> "HAS_TAG",
    modelPath("person_hasInterest_tag") -> "HAS_INTEREST",
    modelPath("person_isLocatedIn_place") -> "IS_LOCATED_IN",
    modelPath("person_knows_person") -> "KNOWS",
    modelPath("person_likes_comment") -> "LIKES",
    modelPath("person_likes_post") -> "LIKES",
    modelPath("place_isPartOf_place") -> "IS_PART_OF",
    modelPath("post_hasCreator_person") -> "HAS_CREATOR",
    modelPath("comment_hasTag_tag") -> "HAS_TAG",
    modelPath("post_hasTag_tag") -> "HAS_TAG",
    modelPath("post_isLocatedIn_place") -> "IS_LOCATED_IN",
    modelPath("tagclass_isSubclassOf_tagclass") -> "IS_SUBCLASS_OF",
    modelPath("tag_hasType_tagclass") -> "HAS_TYPE",
    modelPath("organisation_isLocatedIn_place") -> "IS_LOCATED_IN",
    modelPath("person_studyAt_organisation") -> "STUDY_OF",
    modelPath("person_workAt_organisation") -> "WORK_AT"
  )

  case class TestCase(workload: String, number: Int)

  val testCases =
    Map(new TestCase("bi", 17) -> Map("$country" -> "'Austria'"))

  testCases.filter(_ != null) //
    .foreach(
    t =>
      test(s"${t._1.workload}-${t._1.number}-size-1") {
      val parameters = t._2

      val queryName = s"ldbc-snb-${t._1.workload}-${t._1.number}"

      val baseQuerySpecification = Source.fromFile(queryPath(t._1.workload, t._1.number)).getLines().mkString("\n")
      val querySpecification = parameters.foldLeft(baseQuerySpecification)((a, b) => a.replaceAllLiterally(b._1, b._2))

      runQuery(t._1.workload, t._1.number, queryName, querySpecification)
    })

  def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String)
}
