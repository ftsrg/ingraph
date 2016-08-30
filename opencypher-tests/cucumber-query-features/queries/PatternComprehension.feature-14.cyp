
      MATCH (n)
      RETURN [(n)-[:T]->(b) | b.prop] AS list