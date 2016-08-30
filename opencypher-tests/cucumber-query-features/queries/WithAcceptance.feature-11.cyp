
      MATCH (a)
      WITH DISTINCT a.bar AS bars
      WHERE a.bar = 'B'
      RETURN *