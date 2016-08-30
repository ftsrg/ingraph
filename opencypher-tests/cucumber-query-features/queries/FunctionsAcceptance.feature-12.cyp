
      MATCH (n:Person)
      WHERE exists(n['prop'])
      RETURN n