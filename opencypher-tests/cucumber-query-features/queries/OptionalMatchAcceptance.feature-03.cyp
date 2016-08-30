
      MATCH (n:Single)
      OPTIONAL MATCH (n)-[r]-(m)
      WHERE m.prop = 42
      RETURN m