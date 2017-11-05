Feature: Search in Flickr

  Scenario Outline: validate search results

    Given User is on Search Screen
    When User Search for a text "<keyword>" in Search Screen
    Then Search results should display for search criteria "<keyword>"

    Examples:
      | keyword  |
      | London   |
      | Manchester  |
      | Sample      |
      | Arsenatian  |
      | Endlichite  |
