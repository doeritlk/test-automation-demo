package com.rit.test.pages;

import com.rit.test.pages.widgets.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BlogEditorPage extends UiComponent implements WithExplicitWait {
    private static final Duration FIVE_SECONDS = Duration.ofSeconds(5);
    private final By headerXpath = xpath("//body//div//main//section//input[@id='entry-title']");
    private final By contentEntryXpath = xpath("//body//section[@id='entry-markdown-content']//textarea");
    private final By contentViewXpath = xpath("//body//section[contains(@class,'entry-preview-content')]//p");
    private final By previewLinkXpath = xpath("//body//section[@class='gh-view']//section[contains(@class, 'entry-preview')]//a[text()='Preview']");

    public BlogEditorPage(final WebDriver driver) {
        super(driver, xpath("//body"));
    }

    public void clickNewPost() {
        new Link(driver,
                xpath("//body//div[contains(@class, 'gh-viewport')]//section[contains(@class, 'gh-nav-body')]" +
                        "//a[@href='/ghost/editor/']")
        )
                .click();
    }


    public void entersContent(final String content) {
        find().findElement(contentEntryXpath).sendKeys(content);
    }

    public void entersHeader(final String title) {
        find().findElement(headerXpath).sendKeys(title);
    }


    public void saveDraft() throws InterruptedException {
        WebElement saveButton = driver.findElement(xpath("//body//section[contains(@class,'js-publish-splitbutton')]"));
        saveButton.click();

        waitUntilSaveComplete(saveButton);
    }

    public String header() {
        return find().findElement(headerXpath).getText();
    }

    public String content() {
        return find().findElement(contentViewXpath).getText();
    }

    public void refresh() {
        driver.navigate().refresh();

        waitUntil(driver, refreshed(visibilityOfElementLocated(headerXpath)), FIVE_SECONDS);
    }

    public BlogPreviewPage preview() {
        WebElement previewLink = driver.findElement(previewLinkXpath);
        String href = previewLink.getAttribute("href");
        return new BlogPreviewPage(driver, href);
    }

    private void waitUntilSaveComplete(final WebElement saveButton) throws InterruptedException {
        waitUntil(driver, stalenessOf(saveButton), FIVE_SECONDS);

        TimeUnit.SECONDS.sleep(FIVE_SECONDS.getSeconds());
    }
}
