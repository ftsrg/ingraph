
      MATCH (n)
      WITH n.prop1 AS foo
        ORDER BY max(n.prop2)
      RETURN foo AS foo