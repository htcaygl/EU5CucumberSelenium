Feature: Login as different users
  Scenario: Login as a driver user
    Given the user is on the login page
    When the user logs in using "user10" and "UserUser123"
    Then the user should be able to login
    And the title contains "Dashboard"

    #homework
  Scenario: Login as a driver
    Given the user logged in as "driver"
    Then the user should be able to login
    And the title contains "Dashboard"
    #driver, storemanager,salesmanager