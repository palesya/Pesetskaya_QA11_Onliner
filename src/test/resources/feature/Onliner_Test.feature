Feature: Onliner test

  Background: Open catalog page
    Given i load catalog page

  Scenario: Check hint for fire sign
    When i select house appliance
    And i select cleaning
    And i select vacuumcleaner
    And i search for fire sign
    Then i click on fire sign
    And i check appeared hint text
