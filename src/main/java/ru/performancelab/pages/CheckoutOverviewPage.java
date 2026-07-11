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
        for (int i = 0; i < 3; i++) {
            try {
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
                btn.click();
                wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
                return new CheckoutCompletePage(driver);
            } catch (TimeoutException e) {
                if (i == 2) throw e;
                System.out.println("Finish click failed to navigate, retrying with JS...");
                try {
                    WebElement btn = driver.findElement(finishButton);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
                    return new CheckoutCompletePage(driver);
                } catch (Exception ex) {
                    System.out.println("JS click also failed...");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {}
            }
        }
        return new CheckoutCompletePage(driver);
    }
}
