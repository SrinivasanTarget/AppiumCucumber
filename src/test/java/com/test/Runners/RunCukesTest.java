package com.test.Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "html:target/cucumberHtmlReport"},
        features = "src/test/resources/features/",
        glue = {"com.test"}
)
public class RunCukesTest {



}
