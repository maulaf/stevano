@Login
Feature: Login

  @Positive-successLogin
  Scenario Outline: Login with correct credentials
    Given user is on the login page
    When user enters correct <email> and <password>
    And click sign in
    Then user should be successfully logged in

    Examples: 
      | email                   | password   |
      | fetty.maula98@gmail.com | jakarta123 |

  @Negative-failedLogin
  Scenario: Login with incorrect credentials
    Given user is on the login page
    When user enters incorrect email and password
    And click sign in
    Then user should be failed logged in

  @Negative-missingRequiredField
  Scenario: Login with correct credentials
    Given user is on the login page
    And click sign in
    Then system should display an error message login

  @Negative-invalidEmailFormat
  Scenario: Login with invalid email format
    Given user is on the login page
    When user enters an invalid email format
    Then system should display an error message indicating that the email format is invalid
