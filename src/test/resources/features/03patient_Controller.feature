Feature: Morbidity Controller


Scenario: Dietician creates patient deatils

When the user requests to create patient record with valid credential
Then the API should respond with a status code created
And the response should contain successful creation