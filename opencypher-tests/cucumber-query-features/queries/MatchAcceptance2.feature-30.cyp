
      MATCH (a:A)-->(n)-->(m)
      RETURN n.x, count(*)
        ORDER BY n.x
        LIMIT 1000