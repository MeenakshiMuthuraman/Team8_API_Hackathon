Feature: Morbidity Controller


Scenario: User gets all morbidity details

When the user requests to get all morbidity details with bearer token
Then the API should respond with a status code OK
And the response should contain all morbidity details

Scenario Outline: User retrieve Morbidity condition by Test name

When the user request to get morbidity condition by valid "<TestName>"
Then the API should respond with a status code OK
And the response should contain morbidity details of requested "<TestName>"

Examples:
|TestName|
|Fasting Glucose|
|Average Glucose|

#Scenario: Logout User
#When  User performs logout
#Then the API should respond with a status code OK