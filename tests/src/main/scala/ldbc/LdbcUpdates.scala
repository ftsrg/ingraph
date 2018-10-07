package ldbc

trait LdbcUpdate

case class Organization(organizationId: Long, year: Int)
object Organization {
  // Parse horrific csv list format:
  // 321,2008;3,2004
  def parse(s: String): Iterable[Organization] = {
    for (org <- s.split(";"))
      yield Organization(org(0).toLong, org(1).toInt)
  }
}

case class Update1AddPerson(
                             personId: Long,
                             personFirstName: String,
                             personLastName: String,
                             gender: String,
                             birthday: Long,
                             creationDate: Long,
                             locationIP: String,
                             browserUsed: String,
                             cityId: Long,
                             languages: List[String],
                             emails: List[String],
                             tagIds: List[Long],
                             studyAt: List[Organization],
                             workAt: List[Organization]
                           ) extends LdbcUpdate

case class Update2AddPostLike(
                                  personId: Long,
                                  postId: Long,
                                  creationDate: Long
                                ) extends LdbcUpdate

case class Update3AddCommentLike(
                                  personId: Long,
                                  commentId: Long,
                                  creationDate: Long
                                ) extends LdbcUpdate

case class Update4AddForum(
                            forumId: Long,
                            forumTitle: String,
                            creationDate: Long,
                            moderatorPersonId: Long,
                            tagIds: List[Long]
                          ) extends LdbcUpdate

case class Update5AddForumMembership(
                                      forumId: Long,
                                      personId: Long,
                                      joinDate: Long
                                    ) extends LdbcUpdate

case class Update6AddPost(
                           postId: Long,
                           imageFile: String,
                           creationDate: Long,
                           locationIP: String,
                           browserUsed: String,
                           language: String,
                           content: String,
                           length: Int,
                           authorPersonId: Long,
                           forumId: Long,
                           countryId: Long,
                           tagIds: List[Long]
                         ) extends LdbcUpdate

case class Update7AddComment(
                              commentId: Long,
                              creationDate: Long,
                              locationIP: String,
                              browserUsed: String,
                              content: String,
                              length: Int,
                              authorPersonId: Long,
                              countryId: Long,
                              replyToPostId: Long,
                              replyToCommentId: Long,
                              tagIds: List[Long]
                            ) extends LdbcUpdate

case class Update8AddFriendship(
                                     person1Id: Long,
                                     person2Id: Long,
                                     creationDate: Long
                                   ) extends LdbcUpdate

case class Update9RemovePost(postId: Long) extends LdbcUpdate

case class Update10RemoveForum(forumId: Long) extends LdbcUpdate

case class Update11RemoveXXX(sourceId: Long, targetId: Long) extends LdbcUpdate
