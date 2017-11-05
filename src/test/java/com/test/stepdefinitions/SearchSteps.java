package com.test.stepdefinitions;

import com.test.Configurations.FlickrAPIProcessor;
import com.test.screens.SearchScreen;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class SearchSteps {

    SearchScreen searchScreen;
    FlickrAPIProcessor flickrAPIProcessor;

    @Given("^User is on Search Screen$")
    public void userIsOnSearchScreen() throws Throwable {
        searchScreen = new SearchScreen();
        searchScreen.waitForSearchScreen();
    }

    @When("^User Search for a text \"([^\"]*)\" in Search Screen$")
    public void userSearchForATextInSearchScreen(String searchText) throws Throwable {
        searchScreen = new SearchScreen();
        searchScreen.searchForImagesByKeyword(searchText);
    }

    @Then("^Search results should display for search criteria \"([^\"]*)\"$")
    public void searchResultsShouldDisplayForSearchCriteria(String searchText) throws Throwable {
        searchScreen = new SearchScreen();
        flickrAPIProcessor = new FlickrAPIProcessor();
        List<String> searchResponse = flickrAPIProcessor.getSearchResponseTitles(searchText);
        Assert.assertTrue(searchScreen.getSearchResults(searchText).equals(searchResponse));
    }
}
