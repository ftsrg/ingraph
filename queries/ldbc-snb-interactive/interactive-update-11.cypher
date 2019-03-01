// Interactive update 11
MATCH (:Person {id: $personId})-[i:HAS_INTEREST]->(:Tag {id: $tagId})
DELETE i
