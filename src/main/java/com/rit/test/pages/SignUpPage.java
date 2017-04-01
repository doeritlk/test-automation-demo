package com.rit.test.pages;

import com.rit.test.pages.widgets.Button;
import com.rit.test.pages.widgets.Label;
import com.rit.test.pages.widgets.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SignUpPage extends UiComponent implements WithExplicitWait {

    public SignUpPage(final WebDriver driver, String url) {
        super(driver, By.xpath("//body/div/div/main"));

        driver.navigate().to(url);
        driver.switchTo().window(driver.getWindowHandle());
    }

    public void enterFullName(String fullName) {
        new TextField(driver, By.id("signup-name")).enter(fullName);
    }

    public void enterEmail(final String email) {
        new TextField(driver, By.id("signup-email")).enter(email);
    }

    public void password(final String password) {
        new TextField(driver, By.id("signup-password")).enter(password);
    }

    public void register() {
        By buttonXpath = By.xpath(".//form//button[@value='Register']");
        new Button(driver, buttonXpath).click();

        waitUntil(driver, titleIs("Account"), Duration.ofSeconds(10));
    }

    public String pageHeader() {
        return new Label(driver, By.xpath("//body/div/div/main//header/div[contains(@class, 'gh-myghost-head-content')]/h1")).text();
    }
}
