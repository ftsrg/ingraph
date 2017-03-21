MATCH (:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(member:Person)<-[:hasMember]-(forum:Forum)
WITH forum, count(member) AS countMembers
ORDER BY countMembers DESC
LIMIT 100
MATCH (forum)-[:hasMember]-(person:Person)<-[:hasCreator]-(post:Post)
RETURN person.id, person.firstName, person.lastName, person.creationDate, count(post) AS postCount
ORDER BY postCount DESC
LIMIT 100
