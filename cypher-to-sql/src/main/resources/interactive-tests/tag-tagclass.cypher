// id 1 appears only in Tags
MATCH (t {id: 1})<-[:HAS_TYPE]->(tc)
RETURN t.id, tc.id, t.name, tc.name
