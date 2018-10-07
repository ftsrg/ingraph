package ldbc

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader

import scala.collection.JavaConverters._
import scala.io.Source

class LdbcUpdateLoader(val csvDir: String,
                       val queryPrefix: String,
                       val queryPostfix: String) {

  def load(): Iterable[LdbcUpdate] = {
    implicit def longs(x: Any): Long = x.asInstanceOf[Long]
    implicit def string(x: Any): String = x.asInstanceOf[String]
    implicit def int(x: Any): Int = x.asInstanceOf[Int]
    implicit def longList(x: Any): List[Long] = x.asInstanceOf[java.lang.Iterable[Object]].asScala.map(_.asInstanceOf[Long]).toList
    implicit def stringList(x: Any): List[String] = x.asInstanceOf[java.lang.Iterable[Object]].asScala.map(_.asInstanceOf[String]).toList
    implicit def orgList(x: Any): List[Organization] = { if (x == null) return List(); Organization.parse(x.asInstanceOf[String]).toList }

    val loader = new LdbcUpdateStreamCsvLoader(csvDir)

    for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      updateType match {
        case 1 => Update1AddPerson(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14), u(15), u(16))
        case 2 => Update2AddPostLike(u(3), u(4), u(5))
        case 3 => Update3AddCommentLike(u(3), u(4), u(5))
        case 4 => Update4AddForum(u(3), u(4), u(5), u(6), u(7))
        case 5 => Update5AddForumMembership(u(3), u(4), u(5))
        case 6 => Update6AddPost(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14))
        case 7 => Update7AddComment(u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13))
        case 8 => Update8AddFriendship(u(3), u(4), u(5))
        case 9 => Update9RemovePost(u(3))
        case 10 => Update10RemoveForum(u(3))
        case 11 => Update11RemoveHasInterest(u(3), u(4))
      }
    }
  }

  def generateQuerySpecifications(): Iterable[String] = {
    val updates: Iterable[LdbcUpdate] = load()
    for (up <- updates) yield {
      val query = up match {
        case up: Update1AddPerson => update(up)
        case up: Update2AddPostLike => update(up)
        case up: Update3AddCommentLike => update(up)
        case up: Update4AddForum => update(up)
        case up: Update5AddForumMembership => update(up)
        case up: Update6AddPost => update(up)
        case up: Update7AddComment => update(up)
        case up: Update8AddFriendship => update(up)
        case up: Update9RemovePost => update(up)
        case up: Update10RemoveForum => update(up)
        case up: Update11RemoveHasInterest => update(up)
      }
      println(query)
      query
    }
  }

  def convertLongList(longs: List[Long]): String = {
    "[" + longs.mkString(", ") + "]"
  }

  def convertStringList(strings: List[String]): String = {
    "[" + strings.map(convertString).mkString(", ") + "]"
  }

  def convertOrgList(organizations: List[Organization]): String = {
    "[" +
      organizations.map(
        it => s"[${it.organizationId}, ${it.year}]"
      ).mkString(", ") +
      "]"
  }

  def convertString(str: String): String = s"'${str.replace("'", "\\'")}'"

  def update(u: Update1AddPerson): String = {
    val parameters = Map(
      "personId" -> u.personId,
      "personFirstName" -> convertString(u.personFirstName),
      "personLastName" -> convertString(u.personLastName),
      "gender" -> convertString(u.gender),
      "birthday" -> u.birthday,
      "creationDate" -> u.creationDate,
      "cityId" -> u.cityId,
      "locationIP" -> convertString(u.locationIP),
      "browserUsed" -> convertString(u.browserUsed),
      "languages" -> convertStringList(u.languages),
      "emails" -> convertStringList(u.emails),
      "tagIds" -> convertLongList(u.tagIds),
      "studyAt" -> convertOrgList(u.studyAt),
      "workAt" -> convertOrgList(u.workAt)
    )
    substituteParameters(1, parameters)
  }

  def update(u: Update2AddPostLike): String = {
    val parameters = Map(
      "personId" -> u.personId,
      "postId" -> u.postId,
      "creationDate" -> u.creationDate
    )
    substituteParameters(2, parameters)
  }

  def update(u: Update3AddCommentLike): String = {
    val parameters = Map(
      "personId" -> u.personId,
      "commentId" -> u.commentId,
      "creationDate" -> u.creationDate
    )
    substituteParameters(3, parameters)
  }

  def update(u: Update4AddForum): String = {
    val parameters = Map(
      "forumId" -> u.forumId,
      "forumTitle" -> convertString(u.forumTitle),
      "creationDate" -> u.creationDate,
      "moderatorPersonId" -> u.moderatorPersonId,
      "tagIds" -> convertLongList(u.tagIds)
    )
    substituteParameters(4, parameters)
  }

  def update(u: Update5AddForumMembership): String = {
    val parameters = Map(
      "personId" -> u.personId,
      "forumId" -> u.forumId,
      "joinDate" -> u.joinDate
    )
    substituteParameters(5, parameters)
  }

  def update(u: Update6AddPost): String = {
    val parameters = Map(
      "postId" -> u.postId,
      "imageFile" -> convertString(u.imageFile),
      "creationDate" -> u.creationDate,
      "locationIP" -> convertString(u.locationIP),
      "browserUsed" -> convertString(u.browserUsed),
      "language" -> convertString(u.language),
      "content" -> convertString(u.content),
      "length" -> u.length,
      "authorPersonId" -> u.authorPersonId,
      "forumId" -> u.forumId,
      "countryId" -> u.countryId,
      "tagIds" -> convertLongList(u.tagIds)
    )
    substituteParameters(6, parameters)
  }

  def update(u: Update7AddComment): String = {
    val parameters = Map(
      "commentId" -> u.commentId,
      "creationDate" -> u.creationDate,
      "locationIP" -> convertString(u.locationIP),
      "browserUsed" -> convertString(u.browserUsed),
      "content" -> convertString(u.content),
      "length" -> u.length,
      "authorPersonId" -> u.authorPersonId,
      "countryId" -> u.countryId,
      "replyToPostId" -> u.replyToPostId,
      "replyToCommentId" -> u.replyToCommentId,
      "tagIds" -> convertLongList(u.tagIds)
    )
    substituteParameters(7, parameters)
  }

  def update(u: Update8AddFriendship): String = {
    val parameters = Map(
      "person1Id" -> u.person1Id,
      "person2Id" -> u.person2Id,
      "creationDate" -> u.creationDate
    )
    substituteParameters(8, parameters)
  }

  def update(u: Update9RemovePost): String = {
    val parameters = Map(
      "postId" -> u.postId
    )
    substituteParameters(9, parameters)
  }

  def update(u: Update10RemoveForum): String = {
    val parameters = Map(
      "forumId" -> u.forumId
    )
    substituteParameters(10, parameters)
  }

  def update(u: Update11RemoveHasInterest): String = {
    val parameters = Map(
      "sourceId" -> u.sourceId,
      "targetId" -> u.targetId
    )
    substituteParameters(11, parameters)
  }

  def substituteParameters(query: Int, parameters: Map[String, Any]): String = {
    val queryFile = queryPrefix + query + queryPostfix
    val baseQuerySpecification = Source.fromFile(queryFile).getLines().mkString("\n")

    parameters.foldLeft(baseQuerySpecification)(
      (querySpecification, parameter) => querySpecification.replaceAllLiterally("$" + parameter._1.toString, parameter._2.toString)
    )
  }

}
