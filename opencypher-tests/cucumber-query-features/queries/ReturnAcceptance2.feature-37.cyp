
      MATCH (n)
      WITH DISTINCT {foo: n.list} AS map
      RETURN count(*)