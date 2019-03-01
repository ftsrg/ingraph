Feature: Local

  Scenario: Get integer property
    Given an empty graph
    And having executed:
      """
      CREATE ({number: 1})
      """
    When executing query:
      """
      MATCH (n)
      RETURN n, n.number, n.number AS number, n.number + 1 AS sum, 3 AS const
      """
    Then the result should be:
      | n             | n.number | number | sum | const |
      | ({number: 1}) | 1        | 1      | 2   | 3     |
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
