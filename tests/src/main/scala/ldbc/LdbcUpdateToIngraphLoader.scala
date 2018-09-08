package ldbc

import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}
import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import ingraph.ire.Indexer

import scala.collection.JavaConverters._

class LdbcUpdateToIngraphLoader(val indexer: Indexer, val csvDir: String) {

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
        case 1 => Update1AddPerson         (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14), null, null)
        case 2 => Update2_3AddMessageLike  (u(3), u(4), u(5))
        case 3 => Update2_3AddMessageLike  (u(3), u(4), u(5))
        case 4 => Update4AddForum          (u(3), u(4), u(5), u(6), u(7))
        case 5 => Update5AddForumMembership(u(3), u(4), u(5))
        case 6 => Update6AddPost           (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14))
        case 7 => Update7AddComment        (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13))
        case 8 => Update8AddFriendship     (u(3), u(4), u(5))
      }
    }

    for (up <- updates) {
      up match {
        case up: Update1AddPerson          => update(up)
        case up: Update2_3AddMessageLike   => update(up)
//        case up: Update4AddForum           => update(up)
//        case up: Update5AddForumMembership => update(up)
//        case up: Update6AddPost            => update(up)
//        case up: Update7AddComment         => update(up)
//        case up: Update8AddFriendship      => update(up)
      }
    }
  }

  def update(person: Update1AddPerson): Unit = {
    val v = new CsvVertex(person.personId,
      Set("Person").asJava,
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
    val ili = new CsvEdge(person.personId, indexer.newId(),  person.cityId)
    indexer.addVertex(v, Seq())
    indexer.addEdge(ili, "Person", "IS_LOCATED_IN", "Place")
    for (tag <- person.tagIds) {
      val hi = new CsvEdge(indexer.newId(), person.personId, tag)
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

//  def update(forum: Update4AddForum): Unit = {
//    indexer.addVertex(new CsvVertex(forum.forumId, Set("Forum").asJava, Map("creationDate" -> forum.creationDate).asJava))
//    indexer.addEdge(new CsvEdge(indexer.newId(), "HAS_MODERATOR", forum.forumId, "Forum", forum.moderatorPersonId, "Person"))
//    for (tag <- forum.tagIds) {
//      indexer.addEdge(new CsvEdge(indexer.newId(), "HAS_TAG", forum.forumId, "Forum", tag, "Tag"))
//    }
//  }
//
//  def update(member: Update5AddForumMembership): Unit = {
//    indexer.addEdge(new CsvEdge(
//      indexer.newId(), "HAS_MEMBER",
//      member.personId, "Person",
//      member.forumId, "Forum",
//      Map("joinDate" -> member.joinDate).asJava
//    ))
//  }
//
//  def update(post: Update6AddPost): Unit = {
//    indexer.addVertex(new CsvVertex(post.postId, Set("Post").asJava,
//      Map("imageFile" -> post.imageFile,
//        "creationDate" -> post.creationDate,
//        "locationIp" -> post.locationIp,
//        "browserUsed" -> post.browserUsed,
//        "language" -> post.language,
//        "content" -> post.content,
//        "length" -> post.length).asJava))
//    indexer.addEdge(new CsvEdge(indexer.newId(), "HAS_CREATOR",   post.postId,  "Message", post.authorPersonId, "Person" ))
//    indexer.addEdge(new CsvEdge(indexer.newId(), "CONTAINER_OF",  post.forumId, "Forum",   post.postId,         "Message"))
//    indexer.addEdge(new CsvEdge(indexer.newId(), "IS_LOCATED_IN", post.postId,  "Message", post.countryId,      "Country"))
//    for (tag <- post.tagIds) {
//      indexer.addEdge(new CsvEdge(indexer.newId(), "HAS_TAG", post.postId, "", tag, "Tag"))
//    }
//  }
//
//  def update(comment: Update7AddComment): Unit = {
//    indexer.addVertex(new CsvVertex(comment.commentId, Set("Comment").asJava,
//      Map("creationDate" -> comment.creationDate,
//        "locationIp" -> comment.locationIp,
//        "browserUsed" -> comment.browserUsed,
//        "content" -> comment.content,
//        "length" -> comment.length).asJava))
//    indexer.addEdge(new CsvEdge(
//      indexer.newId(), "HAS_CREATOR",
//      comment.commentId, "Message",
//      comment.authorPersonId, "Person"
//    ))
//    indexer.addEdge(new CsvEdge(
//      indexer.newId(), "REPLY_OF",
//      comment.commentId, "Comment",
//      Math.max(comment.replyToCommentId, comment.replyToPostId), "Message"
//    ))
//    for (tag <- comment.tagIds) {
//      indexer.addEdge(new CsvEdge(
//        indexer.newId(), "HAS_TAG",
//        comment.commentId, "Message",
//        tag, "Tag"
//      ))
//    }
//  }
//
//  def update(friend: Update8AddFriendship): Unit = {
//    indexer.addEdge(new CsvEdge(
//      indexer.newId(), "KNOWS",
//      friend.person1Id, "Person",
//      friend.person2Id, "Person",
//      Map("creationDate" -> friend.creationDate).asJava
//    ))
//  }

}
