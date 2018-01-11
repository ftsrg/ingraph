package ingraph.sandbox

class BiCompilerTest extends CompilerTest {
  override val config = CompilerTestConfig(querySuitePath = Some("ldbc-snb-bi")
    , compileQPlanOnly = true
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = true
    , printCypher = false
    , printQPlan = true
    , printJPlan = true
    , printFPlan = true
  )

  ignore("bi-01 from file: Posting summary") {
    val stages=compileFromFile("bi-01")
  }

  ignore("bi-01: Posting summary") {
    val stages = compile(
      """// Q1. Posting summary
        |/*
        |  :param { date: 20110721220000000 }
        |*/
        |MATCH (message:Message)
        |WHERE message.creationDate <= $date
        |WITH toFloat(count(message)) AS totalMessageCount // this should be a subquery once Cypher supports it
        |MATCH (message:Message)
        |WHERE message.creationDate <= $date
        |  AND message.content IS NOT NULL
        |WITH
        |  totalMessageCount,
        |  message,
        |  message.creationDate/10000000000000 AS year
        |WITH
        |  totalMessageCount,
        |  year,
        |  (message:Comment) AS isComment,
        |  CASE
        |    WHEN message.length <  40 THEN 0
        |    WHEN message.length <  80 THEN 1
        |    WHEN message.length < 160 THEN 2
        |    ELSE                           3
        |  END AS lengthCategory,
        |  count(message) AS messageCount,
        |  floor(avg(message.length)) AS averageMessageLength,
        |  sum(message.length) AS sumMessageLength
        |RETURN
        |  year,
        |  isComment,
        |  lengthCategory,
        |  messageCount,
        |  averageMessageLength,
        |  sumMessageLength,
        |  messageCount / totalMessageCount AS percentageOfMessages
        |ORDER BY
        |  year DESC,
        |  isComment ASC,
        |  lengthCategory ASC
      """.stripMargin)
  }

  ignore("bi-02 from file: Top tags for country, age, gender, time") {
    val stages=compileFromFile("bi-02")
  }

  ignore("bi-02: Top tags for country, age, gender, time") {
    val stages = compile(
      """// Q2. Top tags for country, age, gender, time
        |/*
        |  :param {
        |    date1: 20091231230000000,
        |    date2: 20101107230000000,
        |    country1: 'Ethiopia',
        |    country2: 'Belarus'
        |  }
        |*/
        |MATCH
        |  (country:Country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person:Person)
        |  <-[:HAS_CREATOR]-(message:Message)-[:HAS_TAG]->(tag:Tag)
        |WHERE message.creationDate >= $startDate
        |  AND message.creationDate <= $endDate
        |  AND (country.name = $country1 OR country.name = $country2)
        |WITH
        |  country.name AS countryName,
        |  message.creationDate/100000000000%100 AS month,
        |  person.gender AS gender,
        |  floor((20130101 - person.birthday) / 10000 / 5.0) AS ageGroup,
        |  tag.name AS tagName,
        |  message
        |WITH
        |  countryName, month, gender, ageGroup, tagName, count(message) AS messageCount
        |WHERE messageCount > 100
        |RETURN
        |  countryName,
        |  month,
        |  gender,
        |  ageGroup,
        |  tagName,
        |  messageCount
        |ORDER BY
        |  messageCount DESC,
        |  tagName ASC,
        |  ageGroup ASC,
        |  gender ASC,
        |  month ASC,
        |  countryName ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-03 from file: Tag evolution") {
    val stages=compileFromFile("bi-03")
  }

  ignore("bi-03: Tag evolution") {
    val stages = compile(
      """// Q3. Tag evolution
        |/*
        |  :param {
        |    year: 2010,
        |    month: 10
        |  }
        |*/
        |WITH
        |  $year AS year1,
        |  $month AS month1,
        |  $year + toInteger($month / 12.0) AS year2,
        |  $month % 12 + 1 AS month2
        |// year-month 1
        |MATCH (tag:Tag)
        |OPTIONAL MATCH (message1:Message)-[:HAS_TAG]->(tag)
        |  WHERE message1.creationDate/10000000000000   = year1
        |    AND message1.creationDate/100000000000%100 = month1
        |WITH year2, month2, tag, count(message1) AS countMonth1
        |// year-month 2
        |OPTIONAL MATCH (message2:Message)-[:HAS_TAG]->(tag)
        |  WHERE message2.creationDate/10000000000000   = year2
        |    AND message2.creationDate/100000000000%100 = month2
        |WITH
        |  tag,
        |  countMonth1,
        |  count(message2) AS countMonth2
        |RETURN
        |  tag.name,
        |  countMonth1,
        |  countMonth2,
        |  abs(countMonth1-countMonth2) AS diff
        |ORDER BY
        |  diff DESC,
        |  tag.name ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-04 from file: Popular topics in a country") {
    val stages=compileFromFile("bi-04")
  }

  ignore("bi-04: Popular topics in a country") {
    val stages = compile(
      """// Q4. Popular topics in a country
        |/*
        |  :param {
        |    tagClass: 'MusicalArtist',
        |    country: 'Burma'
        |  }
        |*/
        |MATCH
        |  (:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-
        |  (person:Person)<-[:HAS_MODERATOR]-(forum:Forum)-[:CONTAINER_OF]->
        |  (post:Post)-[:HAS_TAG]->(:Tag)-[:HAS_TYPE]->(:TagClass {name: $tagClass})
        |RETURN
        |  forum.id,
        |  forum.title,
        |  forum.creationDate,
        |  person.id,
        |  count(DISTINCT post) AS postCount
        |ORDER BY
        |  postCount DESC,
        |  forum.id ASC
        |LIMIT 20      """.stripMargin)
  }

  ignore("bi-05 from file: Top posters in a country") {
    val stages=compileFromFile("bi-05")
  }

  ignore("bi-05: Top posters in a country") {
    val stages = compile(
      """// Q5. Top posters in a country
        |/*
        |  :param { country: 'Belarus' }
        |*/
        |MATCH
        |  (:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-
        |  (person:Person)<-[:HAS_MEMBER]-(forum:Forum)
        |WITH forum, count(person) AS numberOfMembers
        |ORDER BY numberOfMembers DESC, forum.id ASC
        |LIMIT 100
        |WITH collect(forum) AS popularForums
        |UNWIND popularForums AS forum
        |MATCH
        |  (forum)-[:HAS_MEMBER]->(person:Person)
        |OPTIONAL MATCH
        |  (person)<-[:HAS_CREATOR]-(post:Post)<-[:CONTAINER_OF]-(popularForum:Forum)
        |WHERE popularForum IN popularForums
        |RETURN
        |  person.id,
        |  person.firstName,
        |  person.lastName,
        |  person.creationDate,
        |  count(DISTINCT post) AS postCount
        |ORDER BY
        |  postCount DESC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-06 from file: Most active Posters of a given Topic") {
    val stages=compileFromFile("bi-06")
  }

  ignore("bi-06: Most active Posters of a given Topic") {
    val stages = compile(
      """// Q6. Most active Posters of a given Topic
        |/*
        |  :param { tag: 'Abbas_I_of_Persia' }
        |*/
        |MATCH (tag:Tag {name: $tag})<-[:HAS_TAG]-(message:Message)-[:HAS_CREATOR]->(person:Person)
        |OPTIONAL MATCH (:Person)-[like:LIKES]->(message)
        |OPTIONAL MATCH (message)<-[:REPLY_OF]-(comment:Comment)
        |WITH person, count(DISTINCT like) AS likeCount, count(DISTINCT comment) AS replyCount, count(DISTINCT message) AS messageCount
        |RETURN
        |  person.id,
        |  messageCount,
        |  replyCount,
        |  likeCount,
        |  1*messageCount + 2*replyCount + 10*likeCount AS score
        |ORDER BY
        |  score DESC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-07 from file: Most authoritative users on a given topic") {
    val stages=compileFromFile("bi-07")
  }

  ignore("bi-07: Most authoritative users on a given topic") {
    val stages = compile(
      """// Q7. Most authoritative users on a given topic
        |/*
        |  :param { tag: 'Arnold_Schwarzenegger' }
        |*/
        |MATCH (tag:Tag {name: $tag})
        |MATCH (tag)<-[:HAS_TAG]-(message1:Message)-[:HAS_CREATOR]->(person1:Person)
        |MATCH (tag)<-[:HAS_TAG]-(message2:Message)-[:HAS_CREATOR]->(person1)
        |OPTIONAL MATCH (message2)<-[:LIKES]-(person2:Person)
        |OPTIONAL MATCH (person2)<-[:HAS_CREATOR]-(message3:Message)<-[like:LIKES]-(p3:Person)
        |RETURN
        |  person1.id,
        |  count(DISTINCT like) AS authorityScore
        |ORDER BY
        |  authorityScore DESC,
        |  person1.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-08 from file: Related Topics") {
    val stages=compileFromFile("bi-08")
  }

  ignore("bi-08: Related Topics") {
    val stages = compile(
      """// Q8. Related Topics
        |/*
        |  :param { tag: 'Genghis_Khan' }
        |*/
        |MATCH
        |  (tag:Tag {name: $tag})<-[:HAS_TAG]-(message:Message),
        |  (message)<-[:REPLY_OF]-(comment:Comment)-[:HAS_TAG]->(relatedTag:Tag)
        |  // there is no need to filter for relatedTag.name != $tag, as the edge uniqueness constraint takes care of that
        |WHERE NOT (comment)-[:HAS_TAG]->(tag)
        |RETURN
        |  relatedTag.name,
        |  count(DISTINCT comment) AS count
        |ORDER BY
        |  count DESC,
        |  relatedTag.name ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-09 from file: Forum with related Tags") {
    val stages=compileFromFile("bi-09")
  }

  ignore("bi-09: Forum with related Tags") {
    val stages = compile(
      """// Q9. Forum with related Tags
        |/*
        |  :param {
        |    tagClass1: 'BaseballPlayer',
        |    tagClass2: 'ChristianBishop',
        |    threshold: 200
        |  }
        |*/
        |MATCH
        |  (forum:Forum)-[:HAS_MEMBER]->(person:Person)
        |WITH forum, count(person) AS members
        |WHERE members > $threshold
        |MATCH
        |  (forum)-[:CONTAINER_OF]->(post1:Post)-[:HAS_TAG]->
        |  (:Tag)-[:HAS_TYPE]->(:TagClass {name: $tagClass1})
        |WITH forum, count(DISTINCT post1) AS count1
        |MATCH
        |  (forum)-[:CONTAINER_OF]->(post2:Post)-[:HAS_TAG]->
        |  (:Tag)-[:HAS_TYPE]->(:TagClass {name: $tagClass2})
        |WITH forum, count1, count(DISTINCT post2) AS count2
        |RETURN
        |  forum.id,
        |  count1,
        |  count2
        |ORDER BY
        |  abs(count2-count1) DESC,
        |  forum.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-10 from file: Central Person for a Tag") {
    val stages=compileFromFile("bi-10")
  }

  ignore("bi-10: Central Person for a Tag") {
    val stages = compile(
      """// Q10. Central Person for a Tag
        |/*
        |  :param {
        |    tag: 'John_Rhys-Davies',
        |    date: 20120122000000000
        |  }
        |*/
        |MATCH (tag:Tag {name: $tag})
        |// score
        |OPTIONAL MATCH (tag)<-[interest:HAS_INTEREST]-(person:Person)
        |WITH tag, collect(person) AS interestedPersons
        |OPTIONAL MATCH (tag)<-[:HAS_TAG]-(message:Message)-[:HAS_CREATOR]->(person:Person)
        |         WHERE message.creationDate > $date
        |WITH tag, interestedPersons + collect(person) AS persons
        |UNWIND persons AS person
        |// poor man's disjunct union (should be changed to UNION + post-union processing in the future)
        |WITH DISTINCT tag, person
        |OPTIONAL MATCH (tag)<-[interest:HAS_INTEREST]-(person:Person)
        |WITH
        |  tag,
        |  person,
        |  100 * count(interest) AS score
        |OPTIONAL MATCH (tag)<-[:HAS_TAG]-(message:Message)-[:HAS_CREATOR]->(person)
        |         WHERE message.creationDate > $date
        |WITH
        |  tag,
        |  person,
        |  score + count(message) AS score
        |MATCH (person)-[:KNOWS]-(friend)
        |WITH
        |  tag,
        |  person,
        |  score,
        |  friend
        |OPTIONAL MATCH (tag)<-[interest:HAS_INTEREST]-(friend:Person)
        |WITH
        |  tag,
        |  person,
        |  score,
        |  friend,
        |  100 * count(interest) AS friendScore
        |OPTIONAL MATCH (tag)<-[:HAS_TAG]-(message:Message)-[:HAS_CREATOR]->(friend)
        |         WHERE message.creationDate > $date
        |WITH
        |  person,
        |  score,
        |  friend,
        |  friendScore + count(message) AS friendScore
        |RETURN
        |  person.id,
        |  score,
        |  sum(friendScore) AS friendsScore
        |ORDER BY
        |  score + friendsScore DESC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-11 from file: Unrelated replies") {
    val stages=compileFromFile("bi-11")
  }

  ignore("bi-11: Unrelated replies") {
    val stages = compile(
      """// Q11. Unrelated replies
        |/*
        |  :param {
        |    country: 'Germany',
        |    blacklist: ['also', 'Pope', 'that', 'James', 'Henry', 'one', 'Green']
        |  }
        |*/
        |WITH $blacklist AS blacklist
        |MATCH
        |  (country:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-
        |  (person:Person)<-[:HAS_CREATOR]-(reply:Comment)-[:REPLY_OF]->(message:Message),
        |  (reply)-[:HAS_TAG]->(tag:Tag)
        |WHERE NOT (message)-[:HAS_TAG]->(:Tag)<-[:HAS_TAG]-(reply)
        |  AND size([word IN blacklist WHERE reply.content CONTAINS word | word]) = 0
        |OPTIONAL MATCH
        |  (:Person)-[like:LIKES]->(reply)
        |RETURN
        |  person.id,
        |  tag.name,
        |  count(DISTINCT like) AS countLikes,
        |  count(DISTINCT reply) AS countReplies
        |ORDER BY
        |  countLikes DESC,
        |  person.id ASC,
        |  tag.name ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-12 from file: Trending Posts") {
    val stages=compileFromFile("bi-12")
  }

  ignore("bi-12: Trending Posts") {
    val stages = compile(
      """// Q12. Trending Posts
        |/*
        |  :param {
        |    date: 20110721220000000,
        |    likeThreshold: 400
        |  }
        |*/
        |MATCH
        |  (message:Message)-[:HAS_CREATOR]->(creator:Person),
        |  (message)<-[like:LIKES]-(:Person)
        |WHERE message.creationDate > $date
        |WITH message, creator, count(like) AS likeCount
        |WHERE likeCount > $likeThreshold
        |RETURN
        |  message.id,
        |  message.creationDate,
        |  creator.firstName,
        |  creator.lastName,
        |  likeCount
        |ORDER BY
        |  likeCount DESC,
        |  message.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-13 from file: Popular Tags per month in a country") {
    val stages=compileFromFile("bi-13")
  }

  ignore("bi-13: Popular Tags per month in a country") {
    val stages = compile(
      """// Q13. Popular Tags per month in a country
        |/*
        |  :param { country: 'Burma' }
        |*/
        |MATCH (:Country {name: $country})<-[:IS_LOCATED_IN]-(message:Message)
        |OPTIONAL MATCH (message)-[:HAS_TAG]->(tag:Tag)
        |WITH
        |  message.creationDate/10000000000000   AS year,
        |  message.creationDate/100000000000%100 AS month,
        |  message,
        |  tag
        |WITH year, month, count(message) AS popularity, tag
        |ORDER BY popularity DESC, tag.name ASC
        |WITH
        |  year,
        |  month,
        |  collect([tag.name, popularity]) AS popularTags
        |WITH
        |  year,
        |  month,
        |  [popularTag IN popularTags WHERE popularTag[0] IS NOT NULL] AS popularTags
        |RETURN
        |  year,
        |  month,
        |  [i IN range(0, (CASE size(popularTags) < 5 WHEN true THEN size(popularTags) ELSE 5 END)-1)
        |  | popularTags[i]] AS topPopularTags
        |ORDER BY
        |  year DESC,
        |  month ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-14 from file: Top thread initiators") {
    val stages=compileFromFile("bi-14")
  }

  ignore("bi-14: Top thread initiators") {
    val stages = compile(
      """// Q14. Top thread initiators
        |/*
        |  :param {
        |    startDate: 20120531220000000,
        |    endDate: 20120630220000000
        |  }
        |*/
        |MATCH (person:Person)<-[:HAS_CREATOR]-(post:Post)<-[:REPLY_OF*0..]-(reply:Message)
        |WHERE  post.creationDate >= $startDate
        |  AND  post.creationDate <= $endDate
        |  AND reply.creationDate >= $startDate
        |  AND reply.creationDate <= $endDate
        |RETURN
        |  person.id,
        |  person.firstName,
        |  person.lastName,
        |  count(DISTINCT post) AS threadCount,
        |  count(DISTINCT reply) AS messageCount
        |ORDER BY
        |  messageCount DESC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-15 from file: Social normals") {
    val stages=compileFromFile("bi-15")
  }

  ignore("bi-15: Social normals") {
    val stages = compile(
      """// Q15. Social normals
        |/*
        |  :param { country: 'Burma' }
        |*/
        |MATCH
        |  (country:Country {name: $country})
        |MATCH
        |  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person1:Person)
        |OPTIONAL MATCH
        |  // start a new MATCH as friend might live in the same City
        |  // and thus can reuse the IS_PART_OF edge
        |  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(friend1:Person),
        |  (person1)-[:KNOWS]-(friend1)
        |WITH country, person1, count(friend1) AS friend1Count
        |WITH country, floor(avg(friend1Count)) AS socialNormal
        |MATCH
        |  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(person2:Person)
        |MATCH
        |  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(friend2:Person),
        |  (person2)-[:KNOWS]-(friend2)
        |WITH country, person2, count(friend2) AS friend2Count, socialNormal
        |WHERE friend2Count = socialNormal
        |RETURN
        |  person2.id,
        |  friend2Count AS count
        |ORDER BY
        |  person2.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-16 from file: Experts in social circle") {
    val stages=compileFromFile("bi-16")
  }

  ignore("bi-16: Experts in social circle") {
    val stages = compile(
      """// Q16. Experts in social circle
        |/*
        |  :param {
        |    personId: 6597069777419,
        |    country: 'Pakistan',
        |    tagClass: 'MusicalArtist',
        |    minPathDistance: 3,
        |    maxPathDistance: 5
        |  }
        |*/
        |// This query will not work in a browser as is. I tried alternatives approaches,
        |// e.g. enabling path of arbitrary lengths, saving the path to a variable p and
        |// checking for `$minPathDistance <= length(p)`, but these could not be
        |// evaluated due to the excessive amount of paths.
        |// If you would like to test the query in the browser, replace the values of
        |// $minPathDistance and $maxPathDistance to a constant.
        |MATCH
        |  (:Person {id: $personId})-[:KNOWS*$minPathDistance..$maxPathDistance]-
        |  (person:Person)-[:IS_LOCATED_IN]->(:City)-[:IS_PART_OF]->
        |  (:Country {name: $country}),
        |  (person)<-[:HAS_CREATOR]-(message:Message)-[:HAS_TAG]->(:Tag)-[:HAS_TYPE]->
        |  (:TagClass {name: $tagClass})
        |MATCH (message)-[:HAS_TAG]->(tag:Tag)
        |RETURN
        |  person.id,
        |  tag.name,
        |  count(message) AS messageCount
        |ORDER BY
        |  messageCount DESC,
        |  tag.name ASC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-17 from file: Friend triangles") {
    val stages=compileFromFile("bi-17")
  }

  ignore("bi-17: Friend triangles") {
    val stages = compile(
      """// Q17. Friend triangles
        |/*
        |  :param { country: 'Spain' }
        |*/
        |MATCH (country:Country {name: $country})
        |MATCH (a:Person)-[:IS_LOCATED_IN]->(:City)-[:IS_PART_OF]->(country)
        |MATCH (b:Person)-[:IS_LOCATED_IN]->(:City)-[:IS_PART_OF]->(country)
        |MATCH (c:Person)-[:IS_LOCATED_IN]->(:City)-[:IS_PART_OF]->(country)
        |MATCH (a)-[:KNOWS]-(b), (b)-[:KNOWS]-(c), (c)-[:KNOWS]-(a)
        |WHERE a.id < b.id
        |  AND b.id < c.id
        |RETURN count(*)
        |// as a less elegant solution, count(a) also works
      """.stripMargin)
  }

  ignore("bi-18 from file: How many persons have a given number of posts") {
    val stages=compileFromFile("bi-18")
  }

  ignore("bi-18: How many persons have a given number of posts") {
    val stages = compile(
      """// Q18. How many persons have a given number of posts
        |/*
        |  :param {
        |    date: 20110722000000000,
        |    lengthThreshold: 20,
        |    languages: ['ar']
        |  }
        |*/
        |MATCH (person:Person)
        |OPTIONAL MATCH (person)<-[:HAS_CREATOR]-(message:Message)-[:REPLY_OF*0..]->(post:Post)
        |WHERE message.content IS NOT NULL
        |  AND message.length < $lengthThreshold
        |  AND message.creationDate > $date
        |  AND post.language IN $languages
        |WITH
        |  person,
        |  count(message) AS messageCount
        |RETURN
        |  messageCount,
        |  count(person) AS personCount
        |ORDER BY
        |  personCount DESC,
        |  messageCount DESC
      """.stripMargin)
  }

  ignore("bi-19 from file: Stranger's interaction") {
    val stages=compileFromFile("bi-19")
  }

  ignore("bi-19: Stranger's interaction") {
    val stages = compile(
      """// Q19. Stranger's interaction
        |/*
        |  :param {
        |    date: 19890101,
        |    tagClass1: 'MusicalArtist',
        |    tagClass2: 'OfficeHolder'
        |  }
        |*/
        |MATCH
        |  (:TagClass {name: $tagClass1})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-
        |  (forum1:Forum)-[:HAS_MEMBER]->(stranger:Person)
        |WITH DISTINCT stranger
        |MATCH
        |  (:TagClass {name: $tagClass2})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-
        |  (forum2:Forum)-[:HAS_MEMBER]->(stranger)
        |WITH DISTINCT stranger
        |MATCH
        |  (person:Person)<-[:HAS_CREATOR]-(:Message)-[:REPLY_OF]-
        |  (:Message)-[:HAS_CREATOR]->(stranger)
        |WHERE person.birthday > $date
        |  AND person <> stranger
        |  AND NOT (person)-[:KNOWS]-(stranger)
        |WITH person, stranger
        |OPTIONAL MATCH
        |  (person)<-[:HAS_CREATOR]-(comment1:Comment)-[:REPLY_OF]->(:Message)-[:HAS_CREATOR]->(stranger)
        |OPTIONAL MATCH
        |  (stranger)<-[:HAS_CREATOR]-(comment2:Comment)-[:REPLY_OF]->(:Message)-[:HAS_CREATOR]->(person)
        |WITH
        |  person,
        |  count(stranger) AS strangersCount,
        |  count(comment1) AS comment1Count,
        |  count(comment2) AS comment2Count
        |RETURN
        |  person.id,
        |  strangersCount,
        |  comment1Count + comment2Count AS interactionCount
        |ORDER BY
        |  interactionCount DESC,
        |  person.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-20 from file: High-level topics") {
    val stages=compileFromFile("bi-20")
  }

  ignore("bi-20: High-level topics") {
    val stages = compile(
      """// Q20. High-level topics
        |/*
        |  :param { tagClasses: ['Writer', 'Single', 'Country'] }
        |*/
        |UNWIND $tagClasses AS tagClassName
        |MATCH
        |  (tagClass:TagClass {name: tagClassName})<-[:IS_SUBCLASS_OF*0..]-
        |  (:TagClass)<-[:HAS_TYPE]-(tag:Tag)<-[:HAS_TAG]-(message:Message)
        |RETURN
        |  tagClass.name,
        |  count(message) AS postCount
        |ORDER BY
        |  postCount DESC,
        |  tagClass.name ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-21 from file: Zombies in a country") {
    val stages=compileFromFile("bi-21")
  }

  ignore("bi-21: Zombies in a country") {
    val stages = compile(
      """// Q21. Zombies in a country
        |/*
        |  :param {
        |    country: 'Ethiopia',
        |    endDate: 20130101000000000
        |  }
        |*/
        |MATCH (country:Country {name: $country})
        |WITH
        |  country,
        |  $endDate/10000000000000   AS endDateYear,
        |  $endDate/100000000000%100 AS endDateMonth
        |MATCH
        |  (country)<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-(zombie:Person)
        |OPTIONAL MATCH
        |  (zombie)<-[:HAS_CREATOR]-(message:Message)
        |WHERE zombie.creationDate  < $endDate
        |  AND message.creationDate < $endDate
        |WITH
        |  country,
        |  zombie,
        |  endDateYear,
        |  endDateMonth,
        |  zombie.creationDate/10000000000000   AS zombieCreationYear,
        |  zombie.creationDate/100000000000%100 AS zombieCreationMonth,
        |  count(message) AS messageCount
        |WITH
        |  country,
        |  zombie,
        |  12 * (endDateYear  - zombieCreationYear )
        |     + (endDateMonth - zombieCreationMonth)
        |     + 1 AS months,
        |  messageCount
        |WHERE messageCount / months < 1
        |WITH
        |  country,
        |  collect(zombie) AS zombies
        |UNWIND zombies AS zombie
        |OPTIONAL MATCH
        |  (zombie)<-[:HAS_CREATOR]-(message:Message)<-[:LIKES]-(likerZombie:Person)
        |WHERE likerZombie IN zombies
        |WITH
        |  zombie,
        |  count(likerZombie) AS zombieLikeCount
        |OPTIONAL MATCH
        |  (zombie)<-[:HAS_CREATOR]-(message:Message)<-[:LIKES]-(likerPerson:Person)
        |WHERE likerPerson.creationDate < $endDate
        |WITH
        |  zombie,
        |  zombieLikeCount,
        |  count(likerPerson) AS totalLikeCount
        |RETURN
        |  zombie.id,
        |  zombieLikeCount,
        |  totalLikeCount,
        |  CASE totalLikeCount
        |    WHEN 0 THEN 0
        |    ELSE zombieLikeCount / toFloat(totalLikeCount)
        |  END AS zombieScore
        |ORDER BY
        |  zombieScore DESC,
        |  zombie.id ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-22 from file: International dialog") {
    val stages=compileFromFile("bi-22")
  }

  ignore("bi-22: International dialog") {
    val stages = compile(
      """// Q22. International dialog
        |/*
        |  :param {
        |    country1: 'Mexico',
        |    country2: 'Indonesia'
        |  }
        |*/
        |MATCH
        |  (country1:Country {name: $country1})<-[:IS_PART_OF]-(city1:City)<-[:IS_LOCATED_IN]-(person1:Person),
        |  (country2:Country {name: $country2})<-[:IS_PART_OF]-(city2:City)<-[:IS_LOCATED_IN]-(person2:Person)
        |WITH person1, person2, city1, 0 AS score
        |// subscore 1
        |OPTIONAL MATCH (person1)<-[:HAS_CREATOR]-(c:Comment)-[:REPLY_OF]->(:Message)-[:HAS_CREATOR]->(person2)
        |WITH DISTINCT person1, person2, city1, score + (CASE c WHEN null THEN 0 ELSE  4 END) AS score
        |// subscore 2
        |OPTIONAL MATCH (person1)<-[:HAS_CREATOR]-(m:Message)<-[:REPLY_OF]-(:Comment)-[:HAS_CREATOR]->(person2)
        |WITH DISTINCT person1, person2, city1, score + (CASE m WHEN null THEN 0 ELSE  1 END) AS score
        |// subscore 3
        |OPTIONAL MATCH (person1)-[k:KNOWS]-(person2)
        |WITH DISTINCT person1, person2, city1, score + (CASE k WHEN null THEN 0 ELSE 15 END) AS score
        |// subscore 4
        |OPTIONAL MATCH (person1)-[:LIKES]->(m:Message)-[:HAS_CREATOR]->(person2)
        |WITH DISTINCT person1, person2, city1, score + (CASE m WHEN null THEN 0 ELSE 10 END) AS score
        |// subscore 5
        |OPTIONAL MATCH (person1)<-[:HAS_CREATOR]-(m:Message)<-[:LIKES]-(person2)
        |WITH DISTINCT person1, person2, city1, score + (CASE m WHEN null THEN 0 ELSE  1 END) AS score
        |// preorder
        |ORDER BY
        |  city1.name ASC,
        |  score DESC,
        |  person1.id ASC,
        |  person2.id ASC
        |WITH
        |  city1,
        |  // using a list might be faster, but the browser query editor does not like it
        |  collect({score: score, person1: person1, person2: person2})[0] AS top
        |RETURN
        |  top.person1.id,
        |  top.person2.id,
        |  city1.name,
        |  top.score
        |ORDER BY
        |  top.score DESC,
        |  top.person1.id ASC,
        |  top.person2.id ASC
      """.stripMargin)
  }

  ignore("bi-23 from file: Holiday destinations") {
    val stages=compileFromFile("bi-23")
  }

  ignore("bi-23: Holiday destinations") {
    val stages = compile(
      """// Q23. Holiday destinations
        |/*
        |  :param { country: 'Egypt' }
        |*/
        |MATCH
        |  (home:Country {name: $country})<-[:IS_PART_OF]-(:City)<-[:IS_LOCATED_IN]-
        |  (:Person)<-[:HAS_CREATOR]-(message:Message)-[:IS_LOCATED_IN]->(destination:Country)
        |WHERE home <> destination
        |WITH
        |  message,
        |  destination,
        |  message.creationDate/100000000000%100 AS month
        |RETURN
        |  count(message) AS messageCount,
        |  destination.name,
        |  month
        |ORDER BY
        |  messageCount DESC,
        |  destination.name ASC,
        |  month ASC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-24 from file: Messages by Topic and Continent") {
    val stages=compileFromFile("bi-24")
  }

  ignore("bi-24: Messages by Topic and Continent") {
    val stages = compile(
      """// Q24. Messages by Topic and Continent
        |/*
        |  :param { tagClass: 'Single' }
        |*/
        |MATCH (:TagClass {name: $tagClass})<-[:HAS_TYPE]-(:Tag)<-[:HAS_TAG]-(message:Message)
        |WITH DISTINCT message
        |MATCH (message)-[:IS_LOCATED_IN]->(:Country)-[:IS_PART_OF]->(continent:Continent)
        |OPTIONAL MATCH (message)<-[like:LIKES]-(:Person)
        |WITH
        |  message,
        |  message.creationDate/10000000000000   AS year,
        |  message.creationDate/100000000000%100 AS month,
        |  like,
        |  continent
        |RETURN
        |  count(DISTINCT message) AS messageCount,
        |  count(like) AS likeCount,
        |  year,
        |  month,
        |  continent.name
        |ORDER BY
        |  year ASC,
        |  month ASC,
        |  continent.name DESC
        |LIMIT 100
      """.stripMargin)
  }

  ignore("bi-25 from file: Weighted paths") {
    val stages=compileFromFile("bi-25")
  }

  ignore("bi-25: Weighted paths") {
    val stages = compile(
      """// Q25. Weighted paths
        |/*
        |  :param {
        |    person1Id: 2199023264119,
        |    person2Id: 8796093028894,
        |    startDate: 20100601040000000,
        |    endDate: 20100701040000000
        |  }
        |*/
        |MATCH path = (p1:Person {id: $person1Id})-[:KNOWS*]-(p2:Person {id: $person2Id})
        |WITH p1, p2, path
        |ORDER BY length(path)
        |WITH p1, p2, collect(path)[0] AS path // select the shortest path
        |UNWIND relationships(path) AS k
        |WITH
        |  path,
        |  startNode(k) AS pA,
        |  endNode(k) AS pB,
        |  0 AS weight
        |// A to B
        |// every reply (by one of the Persons) to a Post (by the other Person): 1.0
        |OPTIONAL MATCH
        |  (pA)<-[:HAS_CREATOR]-(c:Comment)-[:REPLY_OF]->(m:Post)-[:HAS_CREATOR]->(pB),
        |  (m)<-[:CONTAINER_OF]-(forum:Forum)
        |WHERE forum.creationDate >= $startDate AND forum.creationDate <= $endDate
        |WITH path, pA, pB, weight + count(c)*1.0 AS weight
        |// A to B
        |// every reply (by ones of the Persons) to a Comment (by the other Person): 0.5
        |OPTIONAL MATCH
        |  (pA)<-[:HAS_CREATOR]-(c:Comment)-[:REPLY_OF]->(m:Comment)-[:HAS_CREATOR]->(pB),
        |  (m)<-[:CONTAINER_OF]-(forum:Forum)
        |WHERE forum.creationDate >= $startDate AND forum.creationDate <= $endDate
        |WITH path, pA, pB, weight + count(c)*0.5 AS weight
        |// B to A
        |// every reply (by one of the Persons) to a Post (by the other Person): 1.0
        |OPTIONAL MATCH
        |  (pB)<-[:HAS_CREATOR]-(c:Comment)-[:REPLY_OF]->(m:Post)-[:HAS_CREATOR]->(pA),
        |  (m)<-[:CONTAINER_OF]-(forum:Forum)
        |WHERE forum.creationDate >= $startDate AND forum.creationDate <= $endDate
        |WITH path, pA, pB, weight + count(c)*1.0 AS weight
        |// B to A
        |// every reply (by ones of the Persons) to a Comment (by the other Person): 0.5
        |OPTIONAL MATCH
        |  (pB)<-[:HAS_CREATOR]-(c:Comment)-[:REPLY_OF]->(m:Comment)-[:HAS_CREATOR]->(pA),
        |  (m)<-[:CONTAINER_OF]-(forum:Forum)
        |WHERE forum.creationDate >= $startDate AND forum.creationDate <= $endDate
        |WITH path, pA, pB, weight + count(c)*0.5 AS weight
        |RETURN
        |  [person IN nodes(path) | person.id]
        |ORDER BY
        |  weight DESC
      """.stripMargin)
  }
}
