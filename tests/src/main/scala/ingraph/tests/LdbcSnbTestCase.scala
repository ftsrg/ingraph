package ingraph.tests

import java.util

import com.google.gson.Gson

import scala.collection.JavaConverters._

object LdbcSnbTestCase  {
  private val gson = new Gson()
}

class LdbcSnbTestCase(val workload: String, val number: Int, val csvDir: String, val csvPostfix: String,
                      val updateQuerySpecification: Option[String] = Option.empty
                     ) extends TestCase with CSVData {
  override def name: String = f"$workload-$number%02d"

  override def querySpecification: String = {
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

  override def vertexCsvPaths: Map[String, List[String]] = {
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
      case (file, labels) => (csvDir + file + csvPostfix) -> labels
    }
  }

  override def edgeCsvPaths: Map[String, (String, String, String)] = {
    Map(
      "comment_hasCreator_person"      -> ("Message",      "HAS_CREATOR",    "Person"),
      "comment_isLocatedIn_place"      -> ("Message",      "IS_LOCATED_IN",  "Place"),
      "comment_replyOf_comment"        -> ("Message",      "REPLY_OF",       "Message"),
      "comment_replyOf_post"           -> ("Message",      "REPLY_OF",       "Message"),
      "forum_containerOf_post"         -> ("Forum",        "CONTAINER_OF",   "Message"),
      "forum_hasMember_person"         -> ("Forum",        "HAS_MEMBER",     "Person"),
      "forum_hasModerator_person"      -> ("Forum",        "HAS_MODERATOR",  "Person"),
      "forum_hasTag_tag"               -> ("Forum",        "HAS_TAG",        "Tag"),
      "person_hasInterest_tag"         -> ("Person",       "HAS_INTEREST",   "Tag"),
      "person_isLocatedIn_place"       -> ("Person",       "IS_LOCATED_IN",  "Place"),
      "person_knows_person"            -> ("Person",       "KNOWS",          "Person"),
      "person_likes_comment"           -> ("Person",       "LIKES",          "Message"),
      "person_likes_post"              -> ("Person",       "LIKES",          "Message"),
      "place_isPartOf_place"           -> ("Place",        "IS_PART_OF",     "Place"),
      "post_hasCreator_person"         -> ("Message",      "HAS_CREATOR",    "Person"),
      "comment_hasTag_tag"             -> ("Message",      "HAS_TAG",        "Tag"),
      "post_hasTag_tag"                -> ("Message",      "HAS_TAG",        "Tag"),
      "post_isLocatedIn_place"         -> ("Message",      "IS_LOCATED_IN",  "Place"),
      "tagclass_isSubclassOf_tagclass" -> ("TagClass",     "IS_SUBCLASS_OF", "TagClass"),
      "tag_hasType_tagclass"           -> ("Tag",          "HAS_TYPE",       "TagClass"),
      "organisation_isLocatedIn_place" -> ("Organisation", "IS_LOCATED_IN",  "Place"),
      "person_studyAt_organisation"    -> ("Person",       "STUDY_OF",       "Organisation"),
      "person_workAt_organisation"     -> ("Person",       "WORK_AT",        "Organisation")
    ) map {
      case (file, labels) => (csvDir + file + csvPostfix) -> labels
    }
  }

}
