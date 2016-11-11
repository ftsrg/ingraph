MATCH
  (lit:LiteralRegExpExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`RegExp`)

MERGE
  (lit)-[:`_type`]->(tag)
