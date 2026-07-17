package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.cssSelector("[data-test='firstName']");
    private final By lastNameInput = By.cssSelector("[data-test='lastName']");
    private final By postalCodeInput = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillInfo(String firstName, String lastName, String postalCode) {
        fillInput(firstNameInput, firstName);
        fillInput(lastNameInput, lastName);
        fillInput(postalCodeInput, postalCode);
        return this;
    }

    private void fillInput(By locator, String value) {
        if (value != null) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"), org.openqa.selenium.Keys.BACK_SPACE);
            if (!value.isEmpty()) {
                element.sendKeys(value);
            }
        }
    }

    public CheckoutOverviewPage clickContinue() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        return new CheckoutOverviewPage(driver);
    }
    
    public CheckoutPage clickContinueInvalid() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}
