Feature: Private crocodile api
  @US-2 @Acceptance @Smoke
  Scenario: Login with token authentication to create a new crocodile
    Given An authenticated session token
    When I create a "male" crocodile "Samuel" born in "2002-08-06"
    Then the crocodile age should be 20 years old
