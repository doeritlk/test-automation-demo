package com.doeritlk.test.driver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
                System.setProperty("webdriver.chrome.driver", webDriverPath("chromedriver"));

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
            case Firefox:
                System.setProperty("webdriver.gecko.driver", webDriverPath("geckodriver"));
                System.setProperty("webdriver.firefox.marionette", "false");

                return new FirefoxDriver();
            case Headless:
            default:
                DesiredCapabilities capabilities = (DesiredCapabilities) Settings.builder()
                        .headless(true)
                        .javascript(true)
                        .ignoreDialogs(true)
                        .javaOptions("-Duser.language=en", "-Duser.country=GB")
                        .buildCapabilities();
                capabilities.setCapability("nativeEvents", "true");
                capabilities.setCapability("cssSelectorsEnabled", "true");

                return new JBrowserDriver(capabilities);
        }
    }

    private static String webDriverPath(String driverName) {
        return Optional
                .ofNullable(System.getProperty("webdriver.path"))
                .flatMap(path -> Optional.of(path + driverName))
                .orElseThrow(() -> new RuntimeException("path to driver binary is not specified"));
    }
}
