package ldbc

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import ingraph.ire.{Indexer, IngraphEdge, IngraphVertex}
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class LdbcUpdateLoaderTest extends FunSuite {

  implicit def longs(x: Any): Long = x.asInstanceOf[Long]
  implicit def string(x: Any): String = x.asInstanceOf[String]
  implicit def int(x: Any): Int = x.asInstanceOf[Int]
  implicit def longList(x: Any): List[Long] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList
  implicit def stringList(x: Any): List[String] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/update-streams/"
  val indexer = new Indexer()
  test("parse streams") {
    val loader = new LdbcUpdateStreamCsvLoader(CSV_DIR)

    val updates: Iterable[LdbcUpdate] = for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      updateType match {
        case 1 => Update1AddPerson         (u(3), u(4), u(5), u(6), u(7),
          u(8), u(9), u(10), u(11), u(12), u(13), u(14), null, null)
        case 2 => Update2_3AddMessageLike  (u(3), u(4), u(5))
        case 3 => Update2_3AddMessageLike  (u(3), u(4), u(5))
        case 4 => Update4AddForum          (u(3), u(4), u(5), u(6), u(7))
        case 5 => Update5AddForumMembership(u(3), u(4), u(5))
        case 6 => Update6AddPost           (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14))
        case 7 => Update7AddComment        (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13))
        case 8 => Update8AddFriendship     (u(3), u(4), u(5))
      }
    }

  }

  private val creationDate = "creationDate"

  def update(person: Update1AddPerson): Unit = {
    val p = IngraphVertex(person.personId, Set("Person"),
      Map("firstName" -> person.personFirstName,
        "lastName" -> person.personLastName,
        "gender" -> person.gender,
        "birthDay" -> person.birthday,
        creationDate -> person.creationDate,
        "locationIp" -> person.locationIp,
        "browserUsed" -> person.browserUsed,
        "speaks" -> person.languages,
        "email" -> person.emails
      )
    )
    indexer.addVertex(p)
    indexer.addEdge(indexer.newId(), person.personId, person.cityId, "IS_LOCATED_IN")
    for (tag <- person.tagIds)
      indexer.addEdge(indexer.newId(), person.personId, tag, "HAS_INTEREST")
    //    These are nulls in the data
    //    for (org <- person.studyAt)
    //      indexer.addEdge(indexer.newId(), person.personId, org.organizationId, "studyAt")
    //    for (w <- person.workAt)
    //      indexer.addEdge(indexer.newId(), person.personId, w.organizationId, "workAt")
  }

  def update(like: Update2_3AddMessageLike): Unit = {
   indexer.addEdge(indexer.newId(), like.personId, like.postId, "LIKES", Map(creationDate -> like.creationDate))
  }

  def update(forum: Update4AddForum): Unit = {
    indexer.addVertex(IngraphVertex(forum.forumId, Set("Forum"), Map(creationDate -> forum.creationDate)))
    indexer.addEdge(indexer.newId(), forum.forumId, forum.moderatorPersonId, "HAS_MODERATOR")
    for (tag <- forum.tagIds)
      indexer.addEdge(indexer.newId(), forum.forumId, tag, "HAS_TAG")
  }

  def update(member: Update5AddForumMembership): Unit = {
    indexer.addEdge(indexer.newId(), member.personId, member.forumId, "HAS_MEMBER", Map("joinDate" -> member.joinDate))
  }

  def update(post: Update6AddPost): Unit = {
    indexer.addVertex(IngraphVertex(post.postId, Set("Post"),
      Map("imageFile" -> post.imageFile,
      "creationDate" -> post.creationDate,
      "locationIp" -> post.locationIp,
      "browserUsed" -> post.browserUsed,
      "language" -> post.language,
      "content" -> post.content,
      "length" -> post.length)))
    indexer.addEdge(indexer.newId(), post.postId, post.authorPersonId, "HAS_CREATOR")
    indexer.addEdge(indexer.newId(), post.forumId, post.postId, "CONTAINER_OF")
    indexer.addEdge(indexer.newId(), post.postId, post.countryId, "IS_LOCATED_IN")
    for (tag <- post.tagIds)
      indexer.addEdge(indexer.newId(), post.postId, tag, "HAS_TAG")
  }

  def update(comment: Update7AddComment): Unit = {
    indexer.addVertex(IngraphVertex(comment.commentId, Set("Comment"),
      Map("creationDate" -> comment.creationDate,
        "locationIp" -> comment.locationIp,
        "browserUsed" -> comment.browserUsed,
        "content" -> comment.content,
        "length" -> comment.length)))
    indexer.addEdge(indexer.newId(), comment.commentId, comment.authorPersonId, "HAS_CREATOR")
    indexer.addEdge(indexer.newId(), comment.commentId,
      Math.max(comment.replyToCommentId, comment.replyToPostId), "REPLY_OF")
    for (tag <- comment.tagIds)
      indexer.addEdge(indexer.newId(), comment.commentId, tag, "HAS_TAG")
  }

  def update(friend: Update8AddFriendship): Unit = {
    indexer.addEdge(indexer.newId(), friend.person1Id, friend.person2Id, "KNOWS",
      Map("creationDate" -> friend.creationDate))
  }
}
