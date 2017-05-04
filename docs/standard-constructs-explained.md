# Standard constructs explained

Based on the [Standardisation scope of Cypher](https://github.com/opencypher/openCypher/blob/master/docs/standardisation-scope.adoc) document.

See also the [Standard constructs](standard-constructs.md) document.

## Standard vs. legacy features

### Clauses

#### Standard

* Query constructs
  * `MATCH`
  * `RETURN`
  * `UNWIND`
  * `OPTIONAL MATCH`
  * `WITH`
  * `UNION`
* Data manipulation
  * `CREATE`
  * `MERGE`
  * `SET`
  * `DELETE`
  * `DETACH DELETE`
  * `REMOVE`
* Procedure calls
  * `CALL ... YIELD`

#### Legacy

* `FOREACH`
* `CREATE UNIQUE`
* `START`
* `LOAD CSV`

#### Summary

* The [`FOREACH`](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-foreach) clause is not included (I guess it is quite difficult to implement).
* The [`CREATE UNIQUE`](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-create-unique) construct is not included in openCypher, as indexing and constraints are not in the scope of OpenCypher.
* The `START` clause is deprecated since Neo4j 2.x.
* [`LOAD CSV`](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-load-csv) is not included, as it is more of a technical detail.

### Sub-clauses

#### Standard

* `ON CREATE`
* `ON MATCH`
* `WHERE`
* `ORDER BY`
* `SKIP`
* `LIMIT`

#### Legacy

There are no legacy sub-clauses.

### Hints

#### Standard

There are no hints in standard OpenCypher.

#### Legacy

* `USING PERIODIC COMMIT`
* `USING INDEX`
* `USING SCAN`
* `USING JOIN`

#### Commands

#### Standard

There are no commands in standard OpenCypher.

#### Legacy

* `CREATE INDEX`
* `CREATE CONSTRAINT`

### Operators

#### Standard

* `DISTINCT`
* String operations
  * `STARTS WITH`
  * `CONTAINS`
  * `ENDS WITH`
* Arithmetic comparison
  * `=`
  * `<>`
  * `<`
  * `>`
  * `\<=`
  * `>=`
* Arithmetic operators
  * `+`
  * `-`
  * `/`
  * `*`
* Logical operators
  * `NOT`
  * `AND`
  * `OR`
  * `XOR`
* NULLs
  * `IS NULL`
  * `IS NOT NULL`
* Accessors
  * `.` (property access)
  * `[]` (subscript)

#### Legacy

* `=~` (regular expression)

#### Summary

Only regular expressions are legacy (for [good reason](http://quotes.yourdictionary.com/author/donald-knuth/170328)).

### Expressions

#### Standard

* [List comprehensions](https://neo4j.com/docs/developer-manual/3.0/cypher/#_list_comprehension)
* [Pattern comprehensions](https://github.com/neo4j/neo4j/commit/c067fd918c23aebb156f4cec268e134b1f61c08e#diff-726176c1183002e63d5bb9361f5e1c52R114)
* Parameters with new syntax (`$`)
* Literals
* [Aggregation functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-clause)
  * `avg()`
  * `collect()`
  * `count()`
  * `max()`
  * `min()`
  * `sum()`
* [Statistical functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#_statistics)
  * `stdDev()`
  * `stdDevP()`
* `CASE` - though the grammar for the standard form is not published as of 2017-04-21

#### Legacy

* Parameters with old syntax (`{}`)
* [List functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-list)
  * `extract`
  * `filter`
  * `reduce`
* [Predicate functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-predicates)
  * `any`
  * `all`
  * `none`
  * `single`

#### Summary

* [`CASE`](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-syntax-case), implements a switch-case construct. This would be difficult to implement in a generic way.
* [`extract`](https://neo4j.com/docs/developer-manual/3.0/cypher/#functions-extract) extracts a single property from a list of nodes/relationships. [Why was it excluded from the standard?]
* [`filter`](https://neo4j.com/docs/developer-manual/3.0/cypher/#functions-filter) performs a filtering according to a boolean predicate. [Why was it excluded from the standard?]
* [`reduce`](https://neo4j.com/docs/developer-manual/3.0/cypher/#functions-reduce) is not standard as it has very high expressive power.
* Note that the [`exists` function](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-function) is standard (listed in the [Functions](#Functions) section).

### Functions

#### Standard

* [Predicate functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-predicates)
  * `exists()`
* [Scalar functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-scalar)
  * `coalesce()`
  * `endNode()`
  * `head()`
  * `length()`
  * `last()`
  * `properties()`
  * `size()`
  * `startNode()`
  * `type()`
  * `toFloat()`
* [List functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-list)
  * `relationships()`
  * `tail()`
  * `keys()`
  * `labels()`
  * `nodes()`
  * `range()`
* [Numeric functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-numeric)
  * `abs()`
  * `ceil()`
  * `floor()`
  * `rand()`
  * `round()`
  * `sign()`
* [Logarithmic functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-logarithmic)
  * `e()`
  * `exp()`
  * `log()`
  * `log10()`
  * `sqrt()`
* [Trigonometric functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-trigonometric)
  * `acos()`
  * `asin()`
  * `atan()`
  * `atan2()`
  * `cos()`
  * `cot()`
  * `degrees()`
  * `pi()`
  * `radians()`
  * `sin()`
  * `tan()`
* [String functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-string)
  * `left()`
  * `lTrim()`
  * `trim()`
  * `replace()`
  * `reverse()`
  * `right()`
  * `rTrim()`
  * `split()`
  * `substring()`
  * `toLower()`
  * `toString()`
  * `toUpper()`
* [Statistical functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#_statistics)
  * `percentileCont()`
  * `percentileDisc()`
* Not included in Cypher:
  * `toBoolean()`
  * `toInteger()` (called `toInt()`)

#### Legacy

* [Scalar functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-scalar)
  * `timestamp()`
  * `id()`
* [List functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-list)
  * `rels()` (called `relationships` in Cypher)
* [Trigonometric functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-trigonometric)
  * `haversin()`
* [String functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-functions-string)
  * `lower()`
  * `upper()`
* [Shortesth path functions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-shortest-path). Note that these functions expect a pattern and return a path (`shortestPath()`) or a collection of paths (`allShortestPaths()`).
  * `shortestPath()`
  * `allShortestPaths()`
* Not included in Cypher:
  * `distance()`
  * `point()`

#### Summary

* Functions related to geospatial use cases (`distance`, `point`, `haversin`) were removed.
* `id()` was removed (openCypher does not require the data model to provide numeric IDs).
* Date-related functions (`timestamp`) were removed.
* `lower` and `upper` were excluded from the standard to favor `toLower` and `toUpper`.
* `rels` was exluded as it was a shorthand for `relationships`.

### Types

#### Standard

* primitives
* list
* map
* node
* relationship
* path

#### Legacy

* datetime types
* point

#### Summary

Geospatial and date-related features were excluded from the standard.
