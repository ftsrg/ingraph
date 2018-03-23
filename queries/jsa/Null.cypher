MATCH
  (lit:LiteralNullExpression),
  (ts:TypeSystem)-[:`_instance`]->(tag:Tag:`Null`)

MERGE
  (lit)-[:`_type`]->(tag)
