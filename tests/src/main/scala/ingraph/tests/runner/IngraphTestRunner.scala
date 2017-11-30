package ingraph.tests.runner

import ingraph.driver.CypherDriverFactory
import ingraph.tests.LdbcSnbTestCase

import scala.collection.JavaConverters._

object IngraphTestRunner {
  val nodeCSVs = Map(
    "comment" -> List("Message", "Comment"),
    "forum" -> List("Forum"),
    "organisation" -> List("Organisation"),
    "person" -> List("Person"),
    "place" -> List("Place"),
    "post" -> List("Message", "Post"),
    "tagclass" -> List("TagClass"),
    "tag" -> List("Tag")
  )

  val relationshipCSVs = Map(
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
  )
}

class IngraphTestRunner(tc: LdbcSnbTestCase) extends TestRunner(tc) {

  val tcNodeCsvs = IngraphTestRunner.nodeCSVs.mapValues(s"../graphs/bi/${tc.id}/${_}.csv")
  val tcRelationshipCsvs = IngraphTestRunner.relationshipCSVs.mapValues(s"../graphs/bi/${tc.id}/${_}.csv")

  override def getResults: Seq[Map[String, AnyRef]] = {
    try {
      val driver = CypherDriverFactory.createIngraphDriver
      try {
        val session = driver.session
        session.readCsv(
          IngraphTestRunner.nodeCSVs.mapValues(_.asJavaCollection).asJava,
          IngraphTestRunner.relationshipCSVs.asJava
        )
        val transaction = session.beginTransaction
        transaction
          .run("MATCH (n) RETURN n LIMIT 5")
          .asScala
          .map { rec => rec.asMap().asScala.toMap }
          .toList
      } finally if (driver != null) {
        driver.close()
      }
    }
  }
}