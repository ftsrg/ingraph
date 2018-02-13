MATCH
  (lit:LiteralNumericExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`Number`)

MERGE
  (lit)-[:`_type`]->(tag)
