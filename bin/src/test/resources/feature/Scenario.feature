Feature: Verify contact page functionality 

  @Scenario
  Scenario: Submit contact form with valid data
 	Given I am on the google page
 	And I search "SecurePay" from google and click link "www.securepay.com.au"
    When I navigate contact us page
    And I fill contact us form with dynamic data
    And I verify form submitted successfully
 