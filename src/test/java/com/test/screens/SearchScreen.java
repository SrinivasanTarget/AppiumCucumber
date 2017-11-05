package com.test.screens;

import com.test.Configurations.FlickrAPIProcessor;
import com.test.Configurations.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class SearchScreen {

    private AppiumDriver driver;
    private FlickrAPIProcessor flickrAPIProcessor;

    /*
    *   Search Screen constructor initializes screen elements
    */
    public SearchScreen() {
        this.driver = new Hooks().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }

    @iOSFindBy(className = "SearchField")
    private IOSElement searchField;

    @iOSFindBy(className = "StaticText")
    private List<IOSElement> searchResults;

    /*
    *   Wait for Search Screen to display
    */
    public void waitForSearchScreen() {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField));
    }

    /*
    *   Search for images using keywords
    */
    public void searchForImagesByKeyword(String searchText) {
        waitForSearchScreen();
        searchField.click();
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.RETURN);
        ((IOSDriver) driver).hideKeyboard("Return");
    }

    /*
    *   Returns List of Search Results
    */
    public List<String> getSearchResults(String searchText) throws ParseException {
        flickrAPIProcessor = new FlickrAPIProcessor();
        List<String> searchResult = new ArrayList<>();
        int count = flickrAPIProcessor.getSearchResponseTitles(searchText).size();
        searchResults.forEach(iosElement -> searchResult.add(iosElement.getText()));
        while (searchResult.size() !=  count) {
            System.out.println("Search Results Size: " + searchResult.size());
            Dimension dimensions = driver.manage().window().getSize();
            Double screenHeightStart = dimensions.getHeight() * 0.5;
            int scrollStart = screenHeightStart.intValue();
            Double screenHeightEnd = dimensions.getHeight() * 0.30;
            int scrollEnd = screenHeightEnd.intValue();
            ((AppiumDriver<MobileElement>) driver).swipe(0, scrollStart, 0, scrollEnd, 1000);
            List<String> tempArray = new ArrayList<>();
            searchResults.forEach(iosElement -> tempArray.add(iosElement.getText()));
            for(int i = tempArray.lastIndexOf(searchResult.get(searchResult.size()-1)); searchResult.size() < count; i++ ) {
                searchResult.add(tempArray.get(i+1));
            }
        }
        return searchResult;
    }

}
