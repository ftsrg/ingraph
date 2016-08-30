
      UNWIND [42] AS props
      WITH props WHERE props > 32
      WITH DISTINCT props AS p
      MERGE (a:A {prop: p})
      RETURN a.prop AS prop