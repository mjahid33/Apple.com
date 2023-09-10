Feature: Verify Wikipedia language search result

  @Scenario1
  Scenario: I search language in wikipedia
 	Given I Navigate to the Wikipedia home page
 	When I Search for a given string in "English"
 	And I validate that the first heading of the search results page matches the search "English"
 	And I verify that the search results page is available in other languages "Español"
 	And I navigate to the search results page in a different language "Español"
 	And I verify that the search results page is available in other languages "English"
