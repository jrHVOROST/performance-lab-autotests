package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class BasketPage extends BasePage {
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By title = By.cssSelector(".title");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        return new CheckoutPage(driver);
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }
}
