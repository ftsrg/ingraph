package ldbc

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import ingraph.ire.{Indexer, IngraphVertex}

import scala.collection.JavaConverters._

class LdbcUpdateLoader(val indexer: Indexer, val csvDir: String) {

  implicit def longs(x: Any): Long = x.asInstanceOf[Long]

  implicit def string(x: Any): String = x.asInstanceOf[String]

  implicit def int(x: Any): Int = x.asInstanceOf[Int]

  implicit def longList(x: Any): List[Long] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList

  implicit def stringList(x: Any): List[String] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList

  implicit def orgList(x: Any): List[Organization] = {
    if (x == null) return List()
    Organization.parse(x.asInstanceOf[String]).toList
  }
  def load() {
    val loader = new LdbcUpdateStreamCsvLoader(csvDir)

    val updates: Iterable[LdbcUpdate] = for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      updateType match {
        case 1 => Update1AddPerson(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14), u(15), u(16))
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
    val v = IngraphVertex(person.personId,
      Set("Person"),
      Map("firstName" -> person.personFirstName,
        "lastName" -> person.personLastName,
        "gender" -> person.gender,
        "birthDay" -> person.birthday,
        "creationDate" -> person.creationDate,
        "locationIp" -> person.locationIp,
        "browserUsed" -> person.browserUsed,
        "speaks" -> person.languages,
        "email" -> person.emails
      )
    )
    indexer.addVertex(v)
    indexer.addEdge(indexer.newId(), person.personId, person.cityId, "IS_LOCATED_IN")
    for (tagId <- person.tagIds) {
      indexer.addEdge(indexer.newId(), person.personId, tagId, "HAS_INTEREST")
    }
    for (org <- person.studyAt)
      indexer.addEdge( indexer.newId(), person.personId, org.organizationId, "studyAt")
    for (w <- person.workAt)
      indexer.addEdge( indexer.newId(), person.personId, w.organizationId, "workAt")
  }

  def update(like: Update2_3AddMessageLike): Unit = {
    indexer.addEdge(
      indexer.newId(), like.personId, like.postId, "LIKES", Map("creationDate" -> like.creationDate))

  }

  def update(forum: Update4AddForum): Unit = {
    indexer.addVertex(IngraphVertex(forum.forumId, Set("Forum"), Map("creationDate" -> forum.creationDate)))
    indexer.addEdge(
      indexer.newId(), forum.forumId, forum.moderatorPersonId,
      "HAS_MODERATOR"
    )
    for (tagId <- forum.tagIds) {
      indexer.addEdge(indexer.newId(), forum.forumId, tagId, "HAS_TAG")
    }
  }

  def update(member: Update5AddForumMembership): Unit = {
    indexer.addEdge(indexer.newId(), member.personId , member.forumId, "HAS_MEMBER",
      Map("joinDate" -> member.joinDate))
  }

  def update(post: Update6AddPost): Unit = {
    indexer.addVertex(
      IngraphVertex(
        post.postId,
        Set("Post"),
        Map("imageFile" -> post.imageFile,
          "creationDate" -> post.creationDate,
          "locationIp" -> post.locationIp,
          "browserUsed" -> post.browserUsed,
          "language" -> post.language,
          "content" -> post.content,
          "length" -> post.length)
      )
    )
    indexer.addEdge(indexer.newId(), post.postId, post.authorPersonId, "HAS_CREATOR")
    indexer.addEdge(indexer.newId(), post.forumId, post.postId, "CONTAINER_OF")
    indexer.addEdge(indexer.newId(), post.postId, post.countryId, "IS_LOCATED_IN")
    for (tagId <- post.tagIds) {
      indexer.addEdge(indexer.newId(), post.postId, tagId, "HAS_TAG")
    }
  }

  def update(comment: Update7AddComment): Unit = {
    indexer.addVertex(
      IngraphVertex(comment.commentId, Set("Comment"),
        Map("creationDate" -> comment.creationDate,
          "locationIp" -> comment.locationIp,
          "browserUsed" -> comment.browserUsed,
          "content" -> comment.content,
          "length" -> comment.length))
    )
    indexer.addEdge(indexer.newId(), comment.commentId, comment.authorPersonId, "HAS_CREATOR")
    indexer.addEdge(indexer.newId(), comment.commentId, Math.max(comment.replyToCommentId, comment.replyToPostId),
      "REPLY_OF")
    for (tagId <- comment.tagIds) {
      indexer.addEdge(
        indexer.newId(), comment.commentId, tagId, "HAS_TAG"
      )
    }
  }

  def update(friendship: Update8AddFriendship): Unit = {
    indexer.addEdge(
        indexer.newId(), friendship.person1Id, friendship.person2Id, "KNOWS",
        Map("creationDate" -> friendship.creationDate)
    )
  }

}
