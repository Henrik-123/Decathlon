Feature: Registration and scoring
  Scenario: Register user in 1500m event
    Given User navigates to the registration page
    And Select competition "Decathlon"
    And Add competitor "Johan"
    And Add competition
    And Enter result name "Johan"
    And Select Event "1500m (s)"
    And Enter raw score "170"
    Then Save Score
    Then Score rank
    Then Use Score rank in calculation

  Scenario Outline: Calculate score for multiple events
    Given User navigates to the registration page
    And Select competition "Decathlon"
    And Add competitor "<name>"
    And Add competition
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
      | Johan | High Jump (cm)   | 190         |
      | Johan | 400m (s)         | 49          |
      | Johan | 110m Hurdles (s) | 15          |
      | Johan | Discus (m)       | 45          |
      | Johan | Pole Vault (cm)  | 490         |
      | Johan | Javelin (m)      | 60          |
      | Johan | 1500m (s)        | 270         |


  Scenario Outline: Calculate Heptathlon scores for multiple events
    Given User navigates to the registration page
    And Select competition "Heptathlon"
    And Add competitor "<name>"
    And Add competition
    And Enter result name "<name>"
    And Select Event "<event>"
    And Enter raw score "<performance>"
    Then Save Score
    Then Score rank
    Then Use Score rank in calculation

    Examples:
      | name | event                | performance |
      | Anna | 100m Hurdles (s)     | 13.5        |
      | Anna | High Jump (cm)       | 180         |
      | Anna | Shot Put (m)         | 14          |
      | Anna | 200m (s)             | 24.0        |
      | Anna | Long Jump (cm)       | 620         |
      | Anna | Javelin (m)          | 50          |
      | Anna | 800m (s)             | 125         |
