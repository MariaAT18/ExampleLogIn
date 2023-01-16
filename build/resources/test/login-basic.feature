Feature: Login with basic authentication
  @US-1 @Acceptance @Smoke
  Scenario: Login with valid account
    Given that I am on login basic endpoint
    When I send a username and password
    Then I get all data of the user

  @US-1 @Acceptance
  Scenario: Login with invalid account
    Given that I am on login endpoint
    When I send an invalid user "<login>" or "<password>"
    Then I should get an error message "<message>"

    Examples:
      | loggin           | password     | message                           |
      | aaaa             | aaaa         | Incorrect username or password.   |
      | MariaHurtadoAT18 | ssss         | Incorrect username or password.   |
      | vvvv             | maria123     | Incorrect username or password.   |