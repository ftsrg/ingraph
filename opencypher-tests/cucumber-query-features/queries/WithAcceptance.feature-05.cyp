
      MATCH (a:Begin)
      WITH a.prop AS property
        LIMIT 1
      MATCH (b)
      WHERE b.id = property
      RETURN b