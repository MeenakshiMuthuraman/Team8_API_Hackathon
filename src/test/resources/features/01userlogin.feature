
#@user
#Feature: User Login
#
#Scenario: Valid User Login
#
#Given the user makes a login request with correct "m.meenamca@gmail.com" and "Dove77"
#When user performs post operation for login
#Then the API should respond with a status code OK
#And the response should contain an authentication token

Feature: User Login

  Scenario Outline: Login with Different User Credentials
	Given the user makes a login request with credentials from JSON file "<filePath>"    
	When user performs post operation for login
    Then the API should respond with a status code OK
    And the response should contain an authentication token

  Examples:
    | filePath                     |
    | .//src/test/resources/TestData/credentials.json  |
#Scenario: Logout User
#When  User performs logout
#Then the API should respond with a status code OK
#And the response should contain logged out successfully
#

