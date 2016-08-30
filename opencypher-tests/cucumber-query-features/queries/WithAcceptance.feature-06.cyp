
      MATCH (a)
      WITH a.prop AS property, a.key AS idToUse
        ORDER BY property
        SKIP 1
      MATCH (b)
      WHERE b.id = idToUse
      RETURN DISTINCT b