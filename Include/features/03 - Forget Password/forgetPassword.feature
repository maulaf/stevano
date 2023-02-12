@ForgetPassword
Feature: Forget Password

  @Positive
  Scenario: Successful Password Reset
    Given user is on the login page
    When user clicks on the "Forgot Password" link
    And user enters their registered email address
    And user clicks on the submit button
    Then user should receive an email with instructions to reset their password

  @Negative-invalidEmail
  Scenario: Unsuccessful Password Reset - Invalid Email
    Given user is on the login page
    When user clicks on the "Forgot Password" link
    And user enters an invalid email address
    And user clicks on the submit button
    Then user should receive an error message indicating that the email is invalid

  @Negative-emailNotFound
  Scenario: Unsuccessful Password Reset - Email Not Found
    Given user is on the login page
    When user clicks on the "Forgot Password" link
    And user enters an email address that is not registered
    And user clicks on the submit button
    Then user should receive an error message indicating that the email address is not registered
