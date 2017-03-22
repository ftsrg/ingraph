// Q7. Recent likes. For the specified Person get the most recent likes of any of the person's posts, and the latency between the corresponding post and the like. Flag Likes from outside the direct connections. Return top 20 Likes, ordered descending by creation date of the like.
MATCH (person:Person)<-[:hasCreator]-(message)<-[like:likes]-(liker:Person)
WITH liker, message, like.creationDate AS likeTime, person
ORDER BY
  likeTime DESC,
  message.id ASC
WITH liker, head(collect({msg: message, likeTime: likeTime})) AS latestLike, person
RETURN
  liker.id AS personId,
  liker.firstName AS personFirstName,
  liker.lastName AS personLastName,
  latestLike.likeTime AS likeTime,
  NOT((liker)-[:knows]-(person)) AS isNew,
  latestLike.msg.id AS messageId,
  latestLike.msg.content AS messageContent
  //latestLike.likeTime - latestLike.msg.creationDate AS latencyAsMilli
ORDER BY
  likeTime DESC,
  personId ASC
LIMIT 10
