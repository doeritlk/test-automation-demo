package com.doeritlk.test.pages.widgets;

import com.doeritlk.test.pages.UiComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextField extends UiComponent {
    public TextField(final WebDriver driver, final By locator) {
        super(driver, locator);
    }

    public void enter(String value) {
        find().sendKeys(value);
    }

    public String getValue() {
        return find().getText();
    }
}
