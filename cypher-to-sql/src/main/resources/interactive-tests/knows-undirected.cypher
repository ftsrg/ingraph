MATCH (p:Person {id: 8796093022222})<-[:KNOWS]->(friend)
// check for error in the SQL compiler when wrong property value appears
WHERE p.id <> friend.id
RETURN p.firstName, friend.firstName, friend.id
ORDER BY friend.id
