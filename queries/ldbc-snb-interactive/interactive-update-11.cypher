MATCH (:Person {personId: $personId})-[i:HAS_INTEREST]->(:Tag {tagId: $tagId})
DELETE i
