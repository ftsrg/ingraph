MATCH (n {prop: 'start'})-[:T*]->(m {prop: 'end'})
RETURN m