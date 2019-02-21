MATCH (m:Message {id:2061584476422})-[:HAS_CREATOR]->(p:Person)
RETURN
  p.id AS personId,
  p.firstName AS firstName,
  p.lastName AS lastName
