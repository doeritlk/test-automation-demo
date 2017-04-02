package com.doeritlk.test.pages;

import com.doeritlk.test.pages.widgets.Button;
import com.doeritlk.test.pages.widgets.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends UiComponent implements WithExplicitWait {

    private final Duration TEN_SECONDS = Duration.ofSeconds(10);

    public LoginPage(final WebDriver driver, final String loginUrl) {
        super(driver, By.xpath("//body[contains(@class, 'ghost-login')]"));
        driver.get(loginUrl);
    }

    private TextField email() {
        return new TextField(driver, xpath("//body//main[@role='main']//div//input[@placeholder='Email Address']"));
    }

    private TextField password() {
        return new TextField(driver, xpath("//body//main[@role='main']//div//input[@placeholder='Password']"));
    }

    private Button loginButton() {
        return new Button(driver, xpath("//body//main[@role='main']//div//button[@type='submit']"));
    }


    public void login(final String username, final String password) {
        email().enter(username);
        password().enter(password);

        waitUntil(driver, visibilityOf(loginButton().find()), TEN_SECONDS).click();
        waitUntil(driver, titleContains("Content"), TEN_SECONDS);
    }

    public void logsOut(final String logsOutUrl) {
        driver.navigate().to(logsOutUrl);
        waitUntil(driver, ExpectedConditions.titleContains("Sign In"), TEN_SECONDS);
    }

}
