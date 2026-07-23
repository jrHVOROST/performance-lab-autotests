package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
