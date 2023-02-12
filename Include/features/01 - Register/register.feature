@Register
Feature: Register

  @Positive
  Scenario: Register with valid information
    Given user is on the registration page
    When user enters required information
    And click register button
    Then message to input verification code should be displayed
    And the user's information should be stored in the database

  @Negative-missingRequiredField
  Scenario: Register with missing required fields
    Given user is on the registration page
    And click register button
    Then system should display an error message register

  @Negative-invalidEmailFormat
  Scenario: Register with invalid email format
    Given user is on the registration page
    When user enters an invalid email format
    Then system should display an error message indicating that the email format is invalid

  @Negative-invalidPassword
  Scenario: Register with invalid password
    Given user is on the registration page
    When user enter invalid password
    Then system should display an error message indicating why the password is not valid

  @Negative-invalidCharacters
  Scenario: Register with invalid characters in fields
    Given user is on the registration page
    When user enter invalid characters
    Then system should display an error message indicating which characters are not allowed

  @Negative
  Scenario: negative scenario
    Given user is on the registration page
    And user input detail registrasi
