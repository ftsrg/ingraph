// Interactive update 6
MATCH (author:Person {id: $authorPersonId}), (country:Country {id: $countryId}), (forum:Forum {id: $forumId})
CREATE (author)<-[:HAS_CREATOR]-(p:Post:Message {id: $postId, creationDate: $creationDate, locationIP: $locationIP, browserUsed: $browserUsed, content: CASE $content WHEN '' THEN null ELSE $content END, imageFile: CASE $imageFile WHEN '' THEN null ELSE $imageFile END, length: $length})<-[:CONTAINER_OF]-(forum), (p)-[:IS_LOCATED_IN]->(country)
WITH p
MATCH (t:Tag)
WHERE t.id IN $tagIds
CREATE (p)-[:HAS_TAG]->(t)
