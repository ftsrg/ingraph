# Neo4j Cypher operators

This snippet downloads the latest Cypher documentation, greps all ` +Operator` strings and creates a unique list from the `Operator` strings.

```bash
curl -s https://neo4j.com/docs/developer-manual/current/cypher/ | grep -oP " \\+\K\w+" | sort -u
```

The latest results (on 2016/08/21) are the following:

```
AllNodesScan
AntiConditionalApply
AntiSemiApply
Apply
Argument
AssertSameNode
CartesianProduct
ConditionalApply
CreateNode
CreateUniqueConstraint
Delete
DirectedRelationshipByIdSeekPipe
Distinct
Eager
EagerAggregation
EmptyResult
EmptyRow
Expand
Filter
LetAntiSemiApply
LetSemiApply
Limit
Merge
MergeCreateNode
NodeByIdSeek
NodeByLabelScan
NodeCountFromCountStore
NodeHashJoin
NodeIndexContainsScan
NodeIndexScan
NodeIndexSeek
NodeIndexSeekByRange
NodeUniqueIndexSeek
Optional
OptionalExpand
ProcedureCall
ProduceResults
Projection
RelationshipCountFromCountStore
SchemaIndex
SelectOrAntiSemiApply
SelectOrSemiApply
SemiApply
SetNodeProperty
ShortestPath
Skip
Sort
Top
Top1
TriadicSelection
UndirectedRelationshipByIdSeek
Union
Unwind
UpdateGraph
VarLengthExpand
```
