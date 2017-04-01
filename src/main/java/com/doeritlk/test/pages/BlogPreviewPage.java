package com.doeritlk.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BlogPreviewPage extends UiComponent implements WithExplicitWait {

    private final By headerXpath = xpath("//body//main[@class='content']/article/header/h1");
    private final By contentXpath = xpath("//body//main[@class='content']/article/section/p");

    public BlogPreviewPage(final WebDriver webDriver, final String previewLink) {
        super(webDriver, xpath("//body//main[@class='content']"));
        driver.get(previewLink);

        waitUntil(driver, visibilityOf(find().findElement(headerXpath)), Duration.ofSeconds(10));
    }

    public String header() {
        return find().findElement(headerXpath).getText();
    }

    public String content() {
        return find().findElement(contentXpath).getText();
    }
}
