package ldbc

import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}
import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import ingraph.ire.Indexer

import scala.collection.JavaConverters._

class LdbcUpdateLoader(val indexer: Indexer, val csvDir: String) {

  implicit def longs(x: Any): Long = x.asInstanceOf[Long]

  implicit def string(x: Any): String = x.asInstanceOf[String]

  implicit def int(x: Any): Int = x.asInstanceOf[Int]

  implicit def longList(x: Any): List[Long] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList

  implicit def stringList(x: Any): List[String] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList

  def load() {
    val loader = new LdbcUpdateStreamCsvLoader(csvDir)

    val updates: Iterable[LdbcUpdate] = for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      updateType match {
        case 1 => Update1AddPerson(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14), null, null)
        case 2 => Update2_3AddMessageLike(u(3), u(4), u(5))
        case 3 => Update2_3AddMessageLike(u(3), u(4), u(5))
        case 4 => Update4AddForum(u(3), u(4), u(5), u(6), u(7))
        case 5 => Update5AddForumMembership(u(3), u(4), u(5))
        case 6 => Update6AddPost(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14))
        case 7 => Update7AddComment(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13))
        case 8 => Update8AddFriendship(u(3), u(4), u(5))
      }
    }

    for (up <- updates) {
      up match {
        case up: Update1AddPerson => update(up)
        case up: Update2_3AddMessageLike => update(up)
        case up: Update4AddForum => update(up)
        case up: Update5AddForumMembership => update(up)
        case up: Update6AddPost => update(up)
        case up: Update7AddComment => update(up)
        case up: Update8AddFriendship => update(up)
      }
    }
  }

  def update(person: Update1AddPerson): Unit = {
    val v = new CsvVertex(person.personId,
      Map("firstName" -> person.personFirstName,
        "lastName" -> person.personLastName,
        "gender" -> person.gender,
        "birthDay" -> person.birthday,
        "creationDate" -> person.creationDate,
        "locationIp" -> person.locationIp,
        "browserUsed" -> person.browserUsed,
        "speaks" -> person.languages,
        "email" -> person.emails
      ).asJava
    )
    val ili = new CsvEdge(person.personId, indexer.newId(), person.cityId)
    indexer.addVertex(v, Seq("Person"))
    indexer.addEdge(ili, "Person", "IS_LOCATED_IN", "Place")
    for (tagId <- person.tagIds) {
      val hi = new CsvEdge(indexer.newId(), person.personId, tagId)
      indexer.addEdge(hi, "Person", "HAS_INTEREST", "Tag")
    }
    //    These are nulls in the data
    //    for (org <- person.studyAt)
    //      indexer.addEdge( TODO indexer.newId(), person.personId, org.organizationId, "studyAt")
    //    for (w <- person.workAt)
    //      indexer.addEdge( TODO indexer.newId(), person.personId, w.organizationId, "workAt")
  }

  def update(like: Update2_3AddMessageLike): Unit = {
    indexer.addEdge(
      new CsvEdge(like.personId, indexer.newId(), like.postId, Map("creationDate" -> like.creationDate).asJava),
      "Person", "LIKES", "Message"
    )
  }

  def update(forum: Update4AddForum): Unit = {
    indexer.addVertex(new CsvVertex(forum.forumId, Map("creationDate" -> forum.creationDate).asJava), Set("Forum"))
    indexer.addEdge(
      new CsvEdge(forum.forumId, indexer.newId(), forum.moderatorPersonId),
      "HAS_MODERATOR", "Forum", "Person"
    )
    for (tagId <- forum.tagIds) {
      indexer.addEdge(new CsvEdge(forum.forumId, indexer.newId(), tagId), "HAS_TAG", "Forum", "Tag")
    }
  }

  def update(member: Update5AddForumMembership): Unit = {
    indexer.addEdge(
      new CsvEdge(member.personId, indexer.newId(), member.forumId, Map("joinDate" -> member.joinDate).asJava),
      "Person", "HAS_MEMBER", "Forum"
    )
  }

  def update(post: Update6AddPost): Unit = {
    indexer.addVertex(
      new CsvVertex(
        post.postId,
        Map("imageFile" -> post.imageFile,
          "creationDate" -> post.creationDate,
          "locationIp" -> post.locationIp,
          "browserUsed" -> post.browserUsed,
          "language" -> post.language,
          "content" -> post.content,
          "length" -> post.length).asJava
      ),
      Set("Post")
    )
    indexer.addEdge(new CsvEdge(post.postId, indexer.newId(), post.authorPersonId), "Message", "HAS_CREATOR", "Person")
    indexer.addEdge(new CsvEdge(post.forumId, indexer.newId(), post.postId), "Forum", "CONTAINER_OF", "Message")
    indexer.addEdge(new CsvEdge(post.postId, indexer.newId(), post.countryId), "Message", "IS_LOCATED_IN", "Country")
    for (tagId <- post.tagIds) {
      indexer.addEdge(new CsvEdge(post.postId, indexer.newId(), tagId), "Post", "HAS_TAG", "Tag")
    }
  }

  def update(comment: Update7AddComment): Unit = {
    indexer.addVertex(
      new CsvVertex(comment.commentId,
        Map("creationDate" -> comment.creationDate,
          "locationIp" -> comment.locationIp,
          "browserUsed" -> comment.browserUsed,
          "content" -> comment.content,
          "length" -> comment.length).asJava),
      "Comment"
    )
    indexer.addEdge(
      new CsvEdge(comment.commentId, indexer.newId(), comment.authorPersonId),
      "Message", "HAS_CREATOR", "Person"
    )
    indexer.addEdge(
      new CsvEdge(comment.commentId, indexer.newId(), Math.max(comment.replyToCommentId, comment.replyToPostId)),
      "Comment", "REPLY_OF", "Message"
    )
    for (tagId <- comment.tagIds) {
      indexer.addEdge(
        new CsvEdge(comment.commentId, indexer.newId(), tagId),
        "Message", "HAS_TAG", "Tag"
      )
    }
  }

  def update(friendship: Update8AddFriendship): Unit = {
    indexer.addEdge(
      new CsvEdge(
        friendship.person1Id, indexer.newId(), friendship.person2Id,
        Map("creationDate" -> friendship.creationDate).asJava
      ),
      "Person", "KNOWS", "Person"
    )
  }

}
