Feature: Account Types

  @wip
  Scenario: Driver user
    Given the user logged in as "driver"
    When the user navigates to "Activities", "Calendar Events"
    Then the title contains "Calendar Events - Activities"

   @wip
  Scenario: Sales manager user
    Given the user logged in as "sales manager"
    When the user navigates to "Customers", "Accounts"
    Then the title contains "Accounts - CustomerS"

  @wip
  Scenario: Store manager user
    Given the user logged in as "store manager"
    When the user navigates to "Customers", "Contacts"
    Then the title contains "Contacts - Customers"


  Scenario Outline: Login with different accounts <userType>
    Given the user logged in as "<userTypes>"
    When the user navigates to "<tab>", "<module>"
    Then the title contains "<title>"

    Examples:
      | userTypes     | tab        | module          | title                        |
      | driver        | Activities | Calendar Events | Calendar Events - Activities |
      | store manager | Customers  | Accounts        | Accounts - Customers         |
      | sales manager | Customers  | Contacts        | Contacts - Customers         |


  Scenario Outline: login as a given user <user>
    Given the user is on the login page
    When the user logs in using following credentials
      | username  | <user>      |
      | password  | UserUser123 |
      | firstname | <firstname> |
      | lastname  | <lastname>  |
    Then the user should be able to login

    Examples:
      | user           | firstname | lastname  |
      | user10         | Brenden   | Schneider |
      | storemanager85 | Stephan   | Haley     |


  Scenario Outline: Different user types
    Given the user logged in as "<userType>"

    Examples:
      | userType      |
      | driver        |
      | admin         |
      | store manager |
      | sales manager |


