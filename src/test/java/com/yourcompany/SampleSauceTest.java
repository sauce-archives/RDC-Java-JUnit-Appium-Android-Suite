package com.yourcompany;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.appium.junit.TestObjectTestResultWatcher;
import org.testobject.rest.api.appium.common.TestObject;
import org.testobject.appium.junit.TestObjectAppiumSuite;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.OutputType;


@TestObject(testObjectApiKey = "40F6FBD7A4EB4D369ADCBF5BAF3D3FC6", testObjectSuiteId = 7)
@RunWith(TestObjectAppiumSuite.class)
public class SampleSauceTest {

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

    private IOSDriver driver;

    @Before
    public void setUp() throws Exception {


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", resultWatcher.getApiKey());
         capabilities.setCapability("testobject_test_report_id", resultWatcher.getTestReportId());


        driver = new IOSDriver(new URL("http://appium.testobject.com/wd/hub"), capabilities);

        resultWatcher.setAppiumDriver(driver);

    }

    @Test
    public void addContactTest() {
        long startTime = System.nanoTime();

        for (int i = 1; i < 3; i++) {
            WebElement x = driver.findElementByAccessibilityId("TextField1");
            x.clear();
            x.sendKeys("12");

            x = driver.findElementByAccessibilityId("TextField2");
            x.clear();
            x.sendKeys(String.valueOf(i));

            driver.findElementByAccessibilityId("ComputeSumButton").click();

            String result = driver.findElementByClassName("UIAStaticText").getText();

            Assert.assertEquals("calculation failed", String.valueOf(12 + i), result);

            driver.findElementByAccessibilityId("show alert").click();

            System.out.println(driver.getPageSource());

            Assert.assertTrue(driver.findElementByAccessibilityId("this alert is so cool.").isDisplayed());

            driver.findElementByXPath("//UIAButton[@name='OK']").click();
        }

        System.out.println("\n\nestimated time is ");
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println((double)elapsedTime / 1000000000.0 );
        System.out.println("\n\n");
    }

}
