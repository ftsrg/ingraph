# The CASE construct

See the Cypher docs on [Case Expressions](https://neo4j.com/docs/developer-manual/3.0/cypher/#query-syntax-case).

There are two versions of `CASE`:

* Simple `CASE`

  ```
  CASE test
  WHEN value THEN result
  [WHEN ...]
  [ELSE default]
  END
  ```
* Generic `CASE`

  ```
  CASE
  WHEN predicate THEN result
  [WHEN ...]
  [ELSE default]
  END
  ```

In most cases (;-)), `CASE` can be replaced with a `UNION` of `WHERE` expressions.

Some example data:

```
CREATE
  (n0:Person {name:"Alice",age:38,eyes:"brown"}),
  (n1:Person {name:"Bob",age:25,eyes:"blue"}),
  (n2:Person {name:"Charlie",age:53,eyes:"green"}),
  (n3:Person {name:"Daniel",age:54,eyes:"brown"}),
  (n4:Person {name:"Eskil",age:41,eyes:"blue"})
```

Run the query (with some modifications to use vertex labels and return the `name` attribute):

```
MATCH (n:Person)
RETURN
  n.name AS name,
  CASE n.eyes
    WHEN 'blue'  THEN 1
    WHEN 'brown' THEN 2
  ELSE 3
  END AS eyeCode
```

The result:

```
╒═══════╤═══════╕
│name   │eyeCode│
╞═══════╪═══════╡
│Alice  │2      │
├───────┼───────┤
│Bob    │1      │
├───────┼───────┤
│Charlie│3      │
├───────┼───────┤
│Daniel │2      │
├───────┼───────┤
│Eskil  │1      │
└───────┴───────┘
```

We can produce the same without `CASE`:

```
MATCH (n:Person)
WHERE n.eyes = 'blue'
RETURN n.name AS name, 1 AS eyeCode
UNION
MATCH (n:Person)
WHERE n.eyes = 'brown'
RETURN n.name AS name, 2 AS eyeCode
UNION
MATCH (n:Person)
WHERE n.eyes <> 'blue' AND n.eyes <> 'brown'
RETURN n.name AS name, 3 AS eyeCode
```

```
╒═══════╤═══════╕
│name   │eyeCode│
╞═══════╪═══════╡
│Bob    │1      │
├───────┼───────┤
│Eskil  │1      │
├───────┼───────┤
│Alice  │2      │
├───────┼───────┤
│Daniel │2      │
├───────┼───────┤
│Charlie│3      │
└───────┴───────┘
```

Obviously, this has some limitations:
* We cannot sort the results.
* It may provide worse performance.
