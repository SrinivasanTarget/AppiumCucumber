package com.test.Configurations;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FlickrAPIProcessor {

    /*
    *   Get Search response from flickr search endpoint for given search keyword
    */
    public List<String> getSearchResponseTitles(String searchKey) throws ParseException {
        RestAssured.baseURI = "https://api.flickr.com/services/feeds/photos_public.gne";
        Response response = given().config( RestAssured.config().sslConfig(
                new SSLConfig().relaxedHTTPSValidation() ) )
                .contentType( "application/json" )
                .accept( "application/json" )
                .param("format","json")
                .param("nojsoncallback","1")
                .param("tags",searchKey)
                .request().get();
        String responseOutput = response.body().asString();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseOutput);
        List<JSONObject> jsonObjects = (List<JSONObject>) jsonObject.get("items");
        List<String> searchResults = new ArrayList<>();
        jsonObjects.forEach(jsonObject1 -> searchResults.add(jsonObject1.get("title").toString()));
        return searchResults;
    }
}
