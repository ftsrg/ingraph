
      MATCH (a)
      WITH DISTINCT a.bar AS bars
      ORDER BY a.bar
      RETURN *