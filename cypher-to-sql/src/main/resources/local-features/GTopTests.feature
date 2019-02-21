Feature: GTopTests

  Scenario: Return property of type-filtered node
    Given an empty graph
    When executing query:
      """
      MATCH (m:movie)
      RETURN m.title
      """
    Then the result should be:
      | m.title                |
      | 'The Matrix'           |
      | 'The Devil s Advocate' |
      | 'Monster'              |
    And no side effects

  Scenario: Return property from type-filtered edge and connecting vertex
    Given an empty graph
    When executing query:
      """
      MATCH ()-[a:acted_in]->(m:movie)
      RETURN m.title, a.role
      """
    Then the result should be:
      | m.title                | a.role           |
      | 'The Matrix'           | 'Neo'            |
      | 'The Matrix'           | 'Trinity'        |
      | 'The Matrix'           | 'Morpheus'       |
      | 'The Matrix'           | 'Agent Smith'    |
      | 'The Devil s Advocate' | 'Kevin Lomax'    |
      | 'The Devil s Advocate' | 'Mary Ann Lomax' |
      | 'The Devil s Advocate' | 'John Milton'    |
      | 'Monster'              | 'Aileen'         |
    And no side effects

  Scenario: Return property from type-filtered edge and connecting vertex - reversed edge
    Given an empty graph
    When executing query:
      """
      MATCH (m:movie)<-[a:acted_in]-()
      RETURN m.title, a.role
      """
    Then the result should be:
      | m.title                | a.role           |
      | 'The Matrix'           | 'Neo'            |
      | 'The Matrix'           | 'Trinity'        |
      | 'The Matrix'           | 'Morpheus'       |
      | 'The Matrix'           | 'Agent Smith'    |
      | 'The Devil s Advocate' | 'Kevin Lomax'    |
      | 'The Devil s Advocate' | 'Mary Ann Lomax' |
      | 'The Devil s Advocate' | 'John Milton'    |
      | 'Monster'              | 'Aileen'         |
    And no side effects

  Scenario: Return property from type-filtered edge and connecting vertex - undirected edge
    Given an empty graph
    When executing query:
      """
      MATCH ()-[a:acted_in]-(m:movie)
      RETURN m.title, a.role
      """
    Then the result should be:
      | m.title                | a.role           |
      | 'The Matrix'           | 'Neo'            |
      | 'The Matrix'           | 'Trinity'        |
      | 'The Matrix'           | 'Morpheus'       |
      | 'The Matrix'           | 'Agent Smith'    |
      | 'The Devil s Advocate' | 'Kevin Lomax'    |
      | 'The Devil s Advocate' | 'Mary Ann Lomax' |
      | 'The Devil s Advocate' | 'John Milton'    |
      | 'Monster'              | 'Aileen'         |
    And no side effects
