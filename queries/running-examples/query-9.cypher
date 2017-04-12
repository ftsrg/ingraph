// Q9. Latest Posts. Find the most recent 20 posts and comments from all friends, or friends-of-friends of Person, but created before a Date. Return posts, their creators and creation dates, sort descending by creation date.
MATCH (:Person)-[:knows*1..2]-(friend:Person)<-[:hasCreator]-(message:Message)
WHERE message.creationDate < "2050-01-01T00:00:00.000+0000"
RETURN DISTINCT message, friend
ORDER BY message.creationDate DESC
LIMIT 10
