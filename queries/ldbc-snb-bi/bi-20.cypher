// Q20. High-level topics
/*
  :param { tagClasses: ['Writer', 'Single', 'Country'] }
*/
MATCH
  (tagClass:TagClass)<-[:IS_SUBCLASS_OF*0..]-
  (:TagClass)<-[:HAS_TYPE]-(tag:Tag)<-[:HAS_TAG]-(message:Message)
WHERE tagClass.name IN $tagClasses
RETURN
  tagClass.name,
  count(DISTINCT message) AS messageCount
ORDER BY
  messageCount DESC,
  tagClass.name ASC
LIMIT 100
