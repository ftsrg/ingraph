package ingraph.tests

import org.scalatest.{FunSuite, Ignore}

import scala.io.Source

abstract class LdbcSnbTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/bi/01/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"../queries/ldbc-snb-${workload}/${workload}-${query}.cypher"

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

  val testCases: Map[TestCase, Map[String, String]] =
    Map(
      TestCase("bi", 1) -> Map("date" -> 20110721220000000),
      TestCase("bi", 2) -> Map("date1" -> 20081231230000000, "date2" -> 20161107230000000, "country1" -> India, "country2" -> China),
      TestCase("bi", 3) -> Map("year" -> 2011, "month" -> 6),
      TestCase("bi", 4) -> Map("tagClass" -> Person, "country" -> Austria),
      TestCase("bi", 5) -> Map("country" -> India),
      TestCase("bi", 6) -> Map("tag" -> Hamid_Karzai),
      TestCase("bi", 7) -> Map("tag" -> Wolfgang_Amadeus_Mozart),
      TestCase("bi", 8) -> Map("tag" -> Wolfgang_Amadeus_Mozart),
      TestCase("bi", 9) -> Map("tagClass1" -> OfficeHolder, "tagClass2" -> Person, "threshold" -> 1),
      TestCase("bi", 10) -> Map("tag" -> Hamid_Karzai, "date" -> 20110817060540590),
      TestCase("bi", 11) -> Map("country" -> India, "blacklist" -> one, has, David),
      TestCase("bi", 12) -> Map("date" -> 20110817060540590, "likeThreshold" -> 1),
      TestCase("bi", 13) -> Map("country" -> India),
      TestCase("bi", 14) -> Map("begin" -> 2008817060540595, "end" -> 20160817060540596),
      TestCase("bi", 15) -> Map("country" -> India),
      TestCase("bi", 16) -> Map("personId" -> 0, "country" -> India, "tagClass" -> OfficeHolder, "minPathDistance" -> 1, "maxPathDistance" -> 2),
      TestCase("bi", 17) -> Map("country" -> Austria),
      TestCase("bi", 18) -> Map("date" -> 20080822040000000, "lengthThreshold" -> 240, "languages" -> uz),
      TestCase("bi", 19) -> Map("date" -> 1, "tagClass1" -> Person, "tagClass2" -> OfficeHolder),
      TestCase("bi", 20) -> Map("tagClasses" -> Person),
      TestCase("bi", 21) -> Map("country" -> India, "endDate" -> 20100128063958900),
      TestCase("bi", 22) -> Map("country1" -> India, "country2" -> USA),
      TestCase("bi", 23) -> Map("country" -> India),
      TestCase("bi", 24) -> Map("tagClass" -> OfficeHolder),
      TestCase("bi", 25) -> Map("person1Id" -> 0, "person2Id" -> 2, "startDate" -> 20000601040000000, "endDate" -> 20190701040000000),

    )

  testCases.foreach(
    t =>
      test(s"${t._1.workload}-${t._1.number}-size-1") {
        val parameters = t._2

        val queryName = s"ldbc-snb-${t._1.workload}-${t._1.number}"

        val baseQuerySpecification = Source.fromFile(queryPath(t._1.workload, t._1.number)).getLines().mkString("\n")
        val querySpecification = parameters.foldLeft(baseQuerySpecification)((a, b) => a.replaceAllLiterally(b._1, b._2))
        println(querySpecification)

        //runQuery(t._1.workload, t._1.number, queryName, querySpecification)
      })

  def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String)
}
