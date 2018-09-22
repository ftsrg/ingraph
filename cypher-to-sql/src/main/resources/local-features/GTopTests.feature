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
      | 'The Devil's Advocate' |
      | 'Monster'              |
    And no side effects
