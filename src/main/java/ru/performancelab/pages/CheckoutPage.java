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
            for (int i = 0; i < 5; i++) {
                if (!element.getAttribute("value").isEmpty()) {
                    element.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"), org.openqa.selenium.Keys.BACK_SPACE);
                }
                element.sendKeys(value);
                if (value.equals(element.getAttribute("value"))) {
                    return;
                }
                try { Thread.sleep(200); } catch (Exception e) {}
            }
        }
    }

    public CheckoutOverviewPage clickContinue() {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
                btn.click();
                wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
                return new CheckoutOverviewPage(driver);
            } catch (TimeoutException e) {
                if (i == 2) throw e;
                System.out.println("Continue click failed to navigate, retrying with JS...");
                try {
                    WebElement btn = driver.findElement(continueButton);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
                    return new CheckoutOverviewPage(driver);
                } catch (Exception ex) {
                    System.out.println("JS click also failed...");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {}
            }
        }
        return new CheckoutOverviewPage(driver);
    }
    
    public CheckoutPage clickContinueInvalid() {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
                btn.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
                return this;
            } catch (TimeoutException e) {
                if (i == 2) throw e;
                System.out.println("Continue click failed to show error, retrying with JS...");
                try {
                    WebElement btn = driver.findElement(continueButton);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
                    return this;
                } catch (Exception ex) {
                    System.out.println("JS click also failed...");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {}
            }
        }
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}
