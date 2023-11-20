@tag
Feature: patient login
  I want to use this template for my feature file

  @tag1 @plogin
  Scenario Outline: create a patientlogin
    Given The POST endpoint and the reqeust payload from "<Sheetname>" and <RowNumber> for login
    When I send a POST reqeust for creating an token
    Then The program is successfully created 200

    Examples: 
      | Sheetname | RowNumber |
      | Sheet1    |         0|
      | Sheet1    |         1 |

  @tag2 @testreport
  Scenario: testreport for patients Morbility Details
    Given patient creates GET request for dietience portal
    When I send the GET request with the MorbilityID
    Then the status could be 200

  @tag3 @ViewFile
  Scenario: patient need to view the file with patient login
    Given: Patient creates a GET request with file id for dietieance portal
    When: patient send the GET request with the fileID
    Then: status returns to be 200

