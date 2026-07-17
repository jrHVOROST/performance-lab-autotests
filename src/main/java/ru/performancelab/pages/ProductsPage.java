package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartIcon = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage addProductToCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.cssSelector("[data-test='add-to-cart-" + formattedName + "']");
        org.openqa.selenium.WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        // Wait for the cart badge to show up, meaning the item is actually in the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return this;
    }

    public String getCartItemCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }
    
    public BasketPage goToBasket() {
        org.openqa.selenium.WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(cartIcon));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        wait.until(ExpectedConditions.urlContains("cart.html"));
        return new BasketPage(driver);
    }
}
