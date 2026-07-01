package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(String productName) {
        // e.g. "Sauce Labs Backpack" -> sauce-labs-backpack
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.cssSelector("[data-test='add-to-cart-" + formattedName + "']");
        driver.findElement(addToCartButton).click();
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }
}
