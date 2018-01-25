MERGE (qs:QualifierSystem)

MERGE (qs)-[:_instance]->(:Qualifier:EqualsZero)
MERGE (qs)-[:_instance]->(:Qualifier:NegativeNumeric)
MERGE (qs)-[:_instance]->(:Qualifier:ExceptionThrown)
