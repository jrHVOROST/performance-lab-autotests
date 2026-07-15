package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;

public class CheckoutOverviewPage extends BasePage {
    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutCompletePage clickFinish() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(finishButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        return new CheckoutCompletePage(driver);
    }
}
