package com.test.Configurations;


import cucumber.api.java.After;
import cucumber.api.java.Before;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class Hooks {

    static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public AppiumDriverLocalService getService() {
        return service.get();
    }

    /*
    *   Start Appium Server Programmatically before each scenario
    */
    @Before
    public void startServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1");
        service.set(appiumServiceBuilder.build());
        service.get().start();

        if (service == null || !service.get().isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }

        File appDir = new File("src/test/resources/");
        File app = new File(appDir, "FlickrBrowser-cal.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver.set(new IOSDriver<IOSElement>(service.get().getUrl(), capabilities));
    }

    /*
    *   Stop Appium Server Programmatically before each scenario
    */
    @After
    public void stopServer() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        if (service.get() != null) {
            service.get().stop();
        }
    }
}
