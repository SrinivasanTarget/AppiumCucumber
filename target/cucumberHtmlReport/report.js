$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/FlickrSearch.feature");
formatter.feature({
  "line": 1,
  "name": "Search in Flickr",
  "description": "",
  "id": "search-in-flickr",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "validate search results in app with Flickr API",
  "description": "",
  "id": "search-in-flickr;validate-search-results-in-app-with-flickr-api",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User is on Search Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User Search for a text \"London\" in Search Screen",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Search results should display for search criteria",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});