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
      CREATE (:Label {id:n.id+1})
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
