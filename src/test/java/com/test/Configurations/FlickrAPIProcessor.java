package com.test.Configurations;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class FlickrAPIProcessor {

    /*
    *   Get Search response from flickr search endpoint for given search keyword
    */
    public List<String> getSearchResponseTitles(String searchKey) {
        RestAssured.baseURI = "https://api.flickr.com/services/feeds/photos_public.gne";
        Response response = given().config( RestAssured.config().sslConfig(
                new SSLConfig().relaxedHTTPSValidation() ) )
                .contentType( "application/json" )
                .accept( "application/json" )
                .param("format","json")
                .param("nojsoncallback","1")
                .param("tags",searchKey)
                .request().get();
        String responseOut = response.body().asString();
        return from( responseOut ).get( "items.title" );
    }
}
