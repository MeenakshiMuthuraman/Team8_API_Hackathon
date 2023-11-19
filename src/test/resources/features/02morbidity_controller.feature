Feature: Morbidity Controller

#Background:
#Given the user makes a login request with correct "m.meenamca@gmail.com" and "Dove77"
#When user performs post operation for login
#Then the API should respond with a status code OK
#And the response should contain an authentication token

Scenario: User gets all morbidity details

When the user requests to get all morbidity details with bearer token
Then the API should respond with a status code OK
And the response should contain all morbidity details

#Scenario: Logout User
#When  User performs logout
#Then the API should respond with a status code OK