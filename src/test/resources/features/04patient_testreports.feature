Feature: Patient test report 
 
 Scenario: Logout User
When  User performs logout
Then the API should respond with a status code OK
And the response should contain logged out successfully

Scenario: Patient Login
  Given the user makes a login request with correct "Merlinl@lee.com" and "test"
	When user performs post operation for login
	Then the API should respond with a status code OK
	And the response should contain an authentication token

Scenario: Patient test Report
 
 Given the patient makes a request to view testreports
 When the API should respond with a status code OK
 Then the response should contain test reports
 
 Scenario: patient view test reports as files with invalid fileid
 
 Given the patient makes a request to view testreports with file
 When 	the API should respond with a status code OK
 Then the response should contain error message
	
#	Scenario: patient view test reports as files with valid fileid
 #
 #Given the patient makes a request to view testreports with valid fileid
 #When 	the API should respond with a status code OK
 #Then the response should contain report