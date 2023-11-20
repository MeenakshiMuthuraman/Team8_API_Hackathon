#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
Feature: Get patient Record
Background:
When the user makes a login request with correct username and password
Then the API should respond with a status code OK
#And the response should contain an authentication token

Scenario: user create with valid endpoint and request body from excelsheet "<Sheetname>" and <RowNumber>
Given user creates post request from  "<Sheetname>" and <RowNumber>
    When user send post request with valid requestbody and valid endpoint
  Then user will receive 201 created with response for user module