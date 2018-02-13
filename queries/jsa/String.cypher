MATCH
  (lit:LiteralStringExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`String`)

MERGE
  (lit)-[:`_type`]->(tag)
