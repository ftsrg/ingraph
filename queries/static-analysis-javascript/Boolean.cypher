MATCH
  (lit:LiteralBooleanExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`Boolean`)

MERGE
  (lit)-[:`_type`]->(tag)
