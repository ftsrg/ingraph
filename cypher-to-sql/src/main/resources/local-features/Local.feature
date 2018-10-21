Feature: Local

  Scenario: Return vertices and edges
    Given an empty graph
    And having executed:
      """
      CREATE (a:LabelA:Label1 {id: 'a'}), (b {id: 'b'}), (c {id: 'c'}), (d {id: 'd'})
      CREATE (a)-[:KNOWS {name: 'ab'}]->(b)
      CREATE (c)-[:KNOWS {name: 'cd'}]->(d)
      """
    When executing query:
      """
      MATCH (a {id: 'a'})-[r:KNOWS]-(b)
      RETURN a, r, b
      """
    Then the result should be:
      | a                          | r                     | b           |
      | (:LabelA:Label1 {id: 'a'}) | [:KNOWS {name: 'ab'}] | ({id: 'b'}) |
    And no side effects

  Scenario: Return vertices and edges with integer properties
    Given an empty graph
    And having executed:
      """
      CREATE (a:LabelA:Label1 {id: 1}), (b {id: 2}), (c {id: 3}), (d {id: 4})
      CREATE (a)-[:KNOWS {name: 'ab'}]->(b)
      CREATE (c)-[:KNOWS {name: 'cd'}]->(d)
      """
    When executing query:
      """
      MATCH (a {id: 1})-[r:KNOWS]-(b)
      RETURN a, r, b
      """
    Then the result should be:
      | a                        | r                     | b         |
      | (:LabelA:Label1 {id: 1}) | [:KNOWS {name: 'ab'}] | ({id: 2}) |
    And no side effects

  Scenario: Unnamed columns
    Given an empty graph
    And having executed:
      """
      CREATE (:Label {id: 42})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n, (n :Label), n. /**/ id, count(n)
      """
    Then the result should be:
      | n                 | (n :Label) | n. /**/ id | count(n) |
      | (:Label {id: 42}) | true       | 42         | 1        |
    And no side effects

  Scenario: Dependant CREATE with single row
    Given an empty graph
    And having executed:
      """
      CREATE (:Person {age: 42})
      """
    And having executed:
      """
      MATCH (n)
      CREATE (:Person {age: n.age})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n.age
      """
    Then the result should be:
      | n.age |
      | 42    |
      | 42    |
    And no side effects

  Scenario: Dependant CREATE with single row - with aliased attribute
    Given an empty graph
    And having executed:
      """
      CREATE (:Person {age: 42})
      """
    And having executed:
      """
      MATCH (n)
      WITH n.age AS new_age
      CREATE (:Person {age: new_age})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n.age
      """
    Then the result should be:
      | n.age |
      | 42    |
      | 42    |
    And no side effects

  Scenario: Integer attribute + constant
    Given an empty graph
    And having executed:
      """
      CREATE (:Label {id: 42})
      """
    And having executed:
      """
      MATCH (n)
      RETURN n.id + 1 AS id
      """
    Then the result should be:
      | id |
      | 43 |
    And no side effects

# https://neo4j.com/docs/developer-manual/3.4/cypher/syntax/expressions/#syntax-simple-case
  Scenario: Simple CASE
    Given an empty graph
    And having executed:
      """
      CREATE (:Person {eyes: 'brown'})
      CREATE (:Person {eyes: 'blue'})
      CREATE (:Person)
      CREATE (:Person {eyes: 'brown'})
      CREATE (:Person {eyes: 'blue'})
      CREATE (:Person {eyes: 'red'})
      """
    When executing query:
      """
      MATCH (n:Person)
      RETURN
      CASE n.eyes
        WHEN 'blue' THEN 1
        WHEN 'brown' THEN 2
        ELSE 3
      END AS result
      """
    Then the result should be:
      | result |
      | 2      |
      | 1      |
      | 3      |
      | 2      |
      | 1      |
      | 3      |
    And no side effects

# https://neo4j.com/docs/developer-manual/3.4/cypher/syntax/expressions/#syntax-generic-case
  Scenario: Generic CASE
    Given an empty graph
    And having executed:
      """
      CREATE (:Person {age: 38, eyes: 'brown'})
      CREATE (:Person {age: 25, eyes: 'blue' })
      CREATE (:Person {age: 53, eyes: 'green'})
      CREATE (:Person {         eyes: 'brown'})
      CREATE (:Person {age: 41, eyes: 'blue' })
      """
    When executing query:
      """
      MATCH (n)
      RETURN
      CASE
        WHEN n.eyes = 'blue' THEN 1
        WHEN n.age < 40 THEN 2
        ELSE 3
      END AS result
      """
    Then the result should be:
      | result |
      | 2      |
      | 1      |
      | 3      |
      | 3      |
      | 1      |
    And no side effects

  Scenario: LDBC SNB small - GetVertices
    Given an empty graph
    And having executed:
      """
      CREATE
      (:Person {id: 'b', name: 'Bob', age: 53, speaks: ['en', 'de']})
      <-[:KNOWS {id: 1, since: 2014}]-
      (:Person:Student {id: 'a', name: 'Alice', age: 24, speaks: ['en']})
      -[:INTEREST {id: 2, level: 4}]->
      (:Tag {id: 'c', topic: 'Neofolk'})
      -[:CLASS {id: 3}]->
      (:Class {id: 'd', subject: 'Folk'})
      -[:SUBCLASS_OF {id: 4}]->
      (:Class {id: 'e', subject: 'Music'})
      -[:SUBCLASS_OF {id: 5}]->
      (:Class {id: 'f', subject: 'Art'})
      """
    When executing query:
      """
      MATCH (p:Person:Student)
      RETURN p.id AS p, p.name
      """
    Then the result should be:
      | p   | p.name  |
      | 'a' | 'Alice' |
    And no side effects

  Scenario: OPTIONAL MATCH and WHERE
    And having executed:
      """
      CREATE
        (:X {val: 1})-[:E1]->(:Y {val: 2})-[:E2]->(:Z {val: 3}),
        (:X {val: 4})-[:E1]->(:Y {val: 5}),
        (:X {val: 6})
      """
    When executing query:
      """
      MATCH (x:X)
      OPTIONAL MATCH (x)-[:E1]->(y:Y)
      WHERE x.val < y.val
      RETURN x, y
      """
    Then the result should be:
      | x             | y             |
      | (:X {val: 1}) | (:Y {val: 2}) |
      | (:X {val: 4}) | (:Y {val: 5}) |
      | (:X {val: 6}) | null          |
    And no side effects

  Scenario: OPTIONAL MATCH on two relationships and WHERE
    And having executed:
      """
      CREATE
        (:X {val: 1})-[:E1]->(:Y {val: 2})-[:E2]->(:Z {val: 3}),
        (:X {val: 4})-[:E1]->(:Y {val: 5}),
        (:X {val: 6})
      """
    When executing query:
      """
      MATCH (x:X)
      OPTIONAL MATCH (x)-[:E1]->(y:Y)-[:E2]->(z:Z)
      WHERE x.val < z.val
      RETURN x, y, z
      """
    Then the result should be:
      | x             | y             | z             |
      | (:X {val: 1}) | (:Y {val: 2}) | (:Z {val: 3}) |
      | (:X {val: 4}) | null          | null          |
      | (:X {val: 6}) | null          | null          |
    And no side effects

  Scenario: Two OPTIONAL MATCH clauses and WHERE
    And having executed:
      """
      CREATE
        (:X {val: 1})-[:E1]->(:Y {val: 2})-[:E2]->(:Z {val: 3}),
        (:X {val: 4})-[:E1]->(:Y {val: 5}),
        (:X {val: 6})
      """
    When executing query:
      """
      MATCH (x:X)
      OPTIONAL MATCH (x)-[:E1]->(y:Y)
      OPTIONAL MATCH (y)-[:E2]->(z:Z)
      WHERE x.val < z.val
      RETURN x, y, z
      """
    Then the result should be:
      | x             | y             | z             |
      | (:X {val: 1}) | (:Y {val: 2}) | (:Z {val: 3}) |
      | (:X {val: 4}) | (:Y {val: 5}) | null          |
      | (:X {val: 6}) | null          | null          |
    And no side effects
