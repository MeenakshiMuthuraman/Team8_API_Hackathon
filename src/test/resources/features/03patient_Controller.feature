Feature: Patient Controller

Scenario: Get All Patient Details

When the user request to get all patient details
Then the API should respond with a status code OK
And  the response should contain successful message


Scenario: Dietician creates patient deatils

When the user requests to create patient record with valid credential
Then the API should respond with a status code created
And the response should contain successful message

Scenario: Dietician updates patient details
When the user requests to update patient record with valid credential
Then the API shhould respond with a status code updated
And the responde should contain successful message

Scenario: Dietician delete patient details

When the user request to delete patient with valid patient id 
Then the API should respond with a status code OK
And the response should contain successful message
