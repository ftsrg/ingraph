
      MATCH (a)
      WITH a.bar AS bars, count(*) AS relCount
      ORDER BY a.bar
      RETURN *