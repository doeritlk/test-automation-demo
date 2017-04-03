package com.doeritlk.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BlogPreviewPage extends UiComponent implements WithExplicitWait {

    private final Duration FIVE_SECONDS = Duration.ofSeconds(5);
    private final By headerXpath = xpath("//body//main[@class='content']/article/header/h1");
    private final By contentXpath = xpath("//body//main[@class='content']/article/section/p");

    public BlogPreviewPage(final WebDriver webDriver, final String previewLink) {
        super(webDriver, xpath("//body//main[@class='content']"));
        driver.get(previewLink);

        waitUntil(driver, visibilityOf(find().findElement(headerXpath)), FIVE_SECONDS);
    }

    public String header() {
        return waitUntil(driver, visibilityOfElementLocated(headerXpath), FIVE_SECONDS).getText();
    }

    public String content() {
        return waitUntil(driver, visibilityOfElementLocated(contentXpath), FIVE_SECONDS).getText();
    }
}
