package com.doeritlk.test.pages.widgets;

import com.doeritlk.test.pages.UiComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Link extends UiComponent {

    public Link(final WebDriver driver, final By locator) {
        super(driver, locator);
    }

    public void click() {
        find().click();
    }

}
