package com.doeritlk.test.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

public final class Drivers {
    private Drivers() {
    }

    public enum DriverType {
        Chrome,
        Firefox,
        Headless
    }

    public static WebDriver make(final String webDriverType) {
        DriverType driverType = Optional
                .ofNullable(webDriverType)
                .flatMap(v -> Optional.of(DriverType.valueOf(v)))
                .orElse(DriverType.Headless);

        switch (driverType) {
            case Chrome:
                String chromeDriverPath = Optional
                        .ofNullable(System.getProperty("webdriver.path"))
                        .orElseThrow(() -> new RuntimeException("path to chrome driver is not specified"));
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
            case Firefox:
                String firefoxDriverPath = Optional
                        .ofNullable(System.getProperty("webdriver.path"))
                        .orElseThrow(() -> new RuntimeException("path to firefox driver is not specified"));
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                System.setProperty("webdriver.firefox.marionette", "false");

                return new FirefoxDriver();
            case Headless:
            default:
                throw new UnsupportedOperationException("headless browser support is not yet implemented!");
        }
    }
}
