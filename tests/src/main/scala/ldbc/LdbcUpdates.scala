package ldbc

trait LdbcUpdate

case class Organization(organizationId: Long, year: Int)

case class Update1AddPerson(
                             personId: Long,
                             personFirstName: String,
                             personLastName: String,
                             gender: String,
                             birthday: Long,
                             creationDate: Long,
                             locationIp: String,
                             browserUsed: String,
                             cityId: Long,
                             languages: List[String],
                             emails: List[String],
                             tagIds: List[Long],
                             studyAt: List[Organization],
                             workAt: List[Organization]
                           ) extends LdbcUpdate

case class Update2_3AddMessageLike(
                               personId: Long,
                               postId: Long,
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
                           locationIp: String,
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
                              locationIp: String,
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
