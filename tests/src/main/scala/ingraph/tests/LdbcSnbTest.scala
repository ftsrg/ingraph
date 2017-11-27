package ingraph.tests

import com.google.gson.Gson
import jdk.nashorn.internal.parser.JSONParser
import org.scalatest.{FunSuite, Ignore}

import scala.io.Source
import scala.util.parsing.json.{JSON, JSONObject, JSONType}

abstract class LdbcSnbTest extends FunSuite {

  def modelPath(entityName: String)(implicit number: Int) = s"../graphs/bi/$number/${entityName}_0_0.csv"
  def parameterPath(number: String) = s"../graphs/bi/$number/parameters"

  def queryPath(workload: String, query: Int): String = s"../queries/ldbc-snb-${workload}/${workload}-${query}.cypher"


  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight(".cypher".length) + "-50.bin"

  val nodeFilenames: Int => Map[String, List[String]] = (n: Int) => {
    implicit val x = n
    Map(
      modelPath("comment") -> List("Message", "Comment"),
      modelPath("forum") -> List("Forum"),
      modelPath("organisation") -> List("Organisation"),
      modelPath("person") -> List("Person"),
      modelPath("place") -> List("Place"),
      modelPath("post") -> List("Message", "Post"),
      modelPath("tagclass") -> List("TagClass"),
      modelPath("tag") -> List("Tag")
    )
  }

  val relationshipFilenames = (n: Int) => {
    implicit val x = n
    Map(
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
  }
  val gson = new Gson()
  case class TestCase(workload: String, n: Int) {
    val number = f"$n%02d"
    import scala.collection.JavaConverters._
    val parameters = gson.fromJson(readToString(parameterPath(number)), classOf[java.util.Map[String, String]]).asScala
  }

  def readToString(path: String): String = Source.fromFile(s"$path").getLines().mkString("\n")

  val testCases: Seq[TestCase] = 1 to 25 map(TestCase("bi", _))

  testCases.foreach(
    t =>
      test(s"${t.workload}-${t.number}-size-1") {
        val parameters = t.parameters

        val queryName = s"ldbc-snb-${t.workload}-${t.number}"

        val baseQuerySpecification = readToString(queryPath(t.workload, t.n))
        println(parameters)
        val querySpecification = parameters.foldLeft(baseQuerySpecification)((a, b) =>
          a.replaceAllLiterally(b._1.toString, b._2.toString))
        println(querySpecification)

        runQuery(t.workload, t.number, queryName, querySpecification)
      })

  def runQuery(workload: String, queryNumber: String, queryName: String, querySpecification: String): Unit = {
    throw new Exception(s"$workload/$queryNumber not implemented")
  }
}
