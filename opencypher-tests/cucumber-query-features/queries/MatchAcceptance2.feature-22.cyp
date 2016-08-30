
      MATCH (n)-->(x0)
      OPTIONAL MATCH (x0)-->(x1)
      WHERE x1.foo = 'bar'
      RETURN x0.name