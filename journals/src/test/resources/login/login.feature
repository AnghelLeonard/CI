Feature: login

  Scenario: user will login to site
    When I go to login page
    Then I expect to have a form with class "login-container"
    And an input field for name "username"
    And an input field for password "password"
    And a button with text "Log in"
