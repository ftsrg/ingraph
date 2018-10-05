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
      CREATE (:Label {id: 42})
      """
    And having executed:
      """
      MATCH (n)
      CREATE (:Label {id: n.id + 1})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n.id
      """
    Then the result should be:
      | n.id |
      | 42   |
      | 43   |
    And no side effects

  Scenario: Dependant CREATE with single row - with aliased attribute
    Given an empty graph
    And having executed:
      """
      CREATE (:Label {id: 42})
      """
    And having executed:
      """
      MATCH (n)
      WITH n.id + 1 AS new_id
      CREATE (:Label {id: new_id})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n.id
      """
    Then the result should be:
      | n.id |
      | 42   |
      | 43   |
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
