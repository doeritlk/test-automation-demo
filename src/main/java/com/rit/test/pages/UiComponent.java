package com.rit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class UiComponent {
    protected final WebDriver driver;
    private final By locator;

    protected UiComponent(final WebDriver driver, final By locator) {
        this.driver = driver;
        this.locator = locator;
        Logger.getLogger(ExpectedConditions.class.getName()).setLevel(Level.SEVERE);
    }

    protected WebElement find() {
        return driver.findElement(locator);
    }
}
