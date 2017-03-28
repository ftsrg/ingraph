// Tag evolution
MATCH (tag:Tag)<-[:hasTag]-(message:Message)
WHERE toInt(substring(message.creationDate, 0, 4)) = 2010
  AND toInt(substring(message.creationDate, 5, 2)) = 11
WITH tag, count(message) AS countMonth1
MATCH (tag)<-[:hasTag]-(message:Message)
WHERE toInt(substring(message.creationDate, 0, 4)) = 2010
  AND toInt(substring(message.creationDate, 5, 2)) = 12
WITH tag, countMonth1, count(message) AS countMonth2
RETURN tag.name, countMonth1, countMonth2, abs(countMonth1-countMonth2) AS diff
ORDER BY diff desc, tag.name ASC
LIMIT 100
