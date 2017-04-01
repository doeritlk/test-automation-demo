package com.rit.test;

import com.github.lranasingha.yatspec.YatSpecTestBase;
import com.github.lranasingha.yatspec.diagram.ImageData;
import com.github.lranasingha.yatspec.hints.ProvidesHttpInteractionHints;
import com.googlecode.yatspec.junit.SpecRunner;
import com.rit.test.driver.Drivers;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@RunWith(SpecRunner.class)
public abstract class WebUiTestBase extends YatSpecTestBase implements ProvidesHttpInteractionHints {

    WebDriver webDriver;

    @Before
    public void aNewWebDriver() {
        webDriver = Drivers.make(System.getProperty("webdriver.type"));
        webDriver.manage().window().maximize();
    }

    @After
    public void quitDriver() throws IOException {
        webDriver.quit();
    }

    protected void takeScreenShot() throws IOException {
        if (webDriver instanceof TakesScreenshot) {
            TakesScreenshot screenshotCapturer = (TakesScreenshot) this.webDriver;
            byte[] screenshot = screenshotCapturer.getScreenshotAs(OutputType.BYTES);
            capturedInputAndOutputs.add(testName.getMethodName() + " Screenshot", new ImageData(screenshot));
        }
    }
}
