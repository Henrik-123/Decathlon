Feature: Registration
  Scenario: Register user 100m
    Given User navigates to the registration page
    Given Add competitor "Johan"
    And  Add copetion
    And Enter result name "Johan"
    And Select Event "1500m (s)"
    And Enter raw score "20"
    Then Save Score
    Then Score rank
    Then Use Score rank in calculation


  Scenario Outline: Calculate score for different events
    Given User navigates to the registration page
    Given Add competitor "<name>"
    And  Add copetion
    And Enter result name "<name>"
    And Select Event "<event>"
    And Enter raw score "<performance>"
    Then Save Score
    Then Score rank
    Then Use Score rank in calculation

    Examples:
      | name  | event            | performance |
      | Johan | 100m (s)         | 11          |
      | Johan | Long Jump (cm)   | 700         |
      | Johan | Shot Put (m)     | 14          |
      | Johan | hepHighJump (cm) | 49          |


