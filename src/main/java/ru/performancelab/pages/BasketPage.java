package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

public class BasketPage extends BasePage {
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By title = By.cssSelector(".title");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickCheckout() {
        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
                wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
                return new CheckoutPage(driver);
            } catch (TimeoutException e) {
                if (i == 2) throw e;
                System.out.println("Checkout click failed to navigate, retrying...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {}
            }
        }
        return new CheckoutPage(driver);
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }
}
