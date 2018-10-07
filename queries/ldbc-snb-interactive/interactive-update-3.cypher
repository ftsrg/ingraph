// Interactive update 3
MATCH (person:Person {id:$personId}),(comment:Comment {id:$commentId})
CREATE (person)-[:LIKES {creationDate:$creationDate}]->(comment)
