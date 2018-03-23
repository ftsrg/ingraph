MATCH
  (lit:LiteralInfinityExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`Infinity`)

MERGE
  (lit)-[:`_type`]->(tag)
