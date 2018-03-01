package ingraph.tests

import java.util

import com.google.gson.Gson

import scala.collection.JavaConverters._

object LdbcSnbTestCase  {
  private val gson = new Gson()
}

class LdbcSnbTestCase(workload: String, number: Int) extends TestCase with CSVData with GraphMLData {
  override def name: String = f"$workload-$number%02d"

//  override def graphMLPath: String = f"../graphs/ldbc-snb-${workload}/graphmls/${workload}-${number}%02d.graphml"
//  def csvDir(file: String): String = f"../graphs/ldbc-snb-${workload}/${number}%02d/${file}_0_0.csv"

  override def graphMLPath: String = f"../graphs/ldbc-snb-bi/graphmls/sf-tiny.graphml"
  def csvDir: String = f"../graphs/ldbc-snb-bi/sf03/"
  def csvDir(file: String): String = csvDir + f"${file}_0_0.csv"

  override def query: String = {
    def convert(v: Any): String = {
      v match {
        case d: Double => f"$d%.0f"
        case s: String => "'" + s.toString + "'"
        case seq: util.ArrayList[Any] => "[" + seq.toArray.map(convert).mkString(",") + "]"
        case _ => v.toString
      }
    }

    val baseQuerySpecification: String = readToString(s"../queries/ldbc-snb-${workload}/${name}.cypher")
    val parameters: Map[String, String] = LdbcSnbTestCase.gson
      .fromJson(readToString(f"../graphs/ldbc-snb-bi/$number%02d/parameters"), classOf[java.util.Map[String, Object]])
      .asScala
      .toMap
      .map { case (k, v) => (k, convert(v)) }
    parameters.foldLeft(baseQuerySpecification)((a, b) =>
      a.replaceAllLiterally("$" + b._1.toString, b._2.toString))
  }

  override def nodeCSVPaths: Map[String, List[String]] = {
    Map(
      "comment" -> List("Message", "Comment"),
      "forum" -> List("Forum"),
      "organisation" -> List("Organisation"),
      "person" -> List("Person"),
      "place" -> List("Place"),
      "post" -> List("Message", "Post"),
      "tagclass" -> List("TagClass"),
      "tag" -> List("Tag")
    ) map {
      case (file, labels) => csvDir(file) -> labels
    }
  }

  override def relationshipCSVPaths: Map[String, String] = {
    Map(
      "comment_hasCreator_person" -> "HAS_CREATOR",
      "comment_isLocatedIn_place" -> "IS_LOCATED_IN",
      "comment_replyOf_comment" -> "REPLY_OF",
      "comment_replyOf_post" -> "REPLY_OF",
      "forum_containerOf_post" -> "CONTAINER_OF",
      "forum_hasMember_person" -> "HAS_MEMBER",
      "forum_hasModerator_person" -> "HAS_MODERATOR",
      "forum_hasTag_tag" -> "HAS_TAG",
      "person_hasInterest_tag" -> "HAS_INTEREST",
      "person_isLocatedIn_place" -> "IS_LOCATED_IN",
      "person_knows_person" -> "KNOWS",
      "person_likes_comment" -> "LIKES",
      "person_likes_post" -> "LIKES",
      "place_isPartOf_place" -> "IS_PART_OF",
      "post_hasCreator_person" -> "HAS_CREATOR",
      "comment_hasTag_tag" -> "HAS_TAG",
      "post_hasTag_tag" -> "HAS_TAG",
      "post_isLocatedIn_place" -> "IS_LOCATED_IN",
      "tagclass_isSubclassOf_tagclass" -> "IS_SUBCLASS_OF",
      "tag_hasType_tagclass" -> "HAS_TYPE",
      "organisation_isLocatedIn_place" -> "IS_LOCATED_IN",
      "person_studyAt_organisation" -> "STUDY_OF",
      "person_workAt_organisation" -> "WORK_AT"
    ) map {
      case (file, labels) => csvDir(file) -> labels
    }
  }

}
