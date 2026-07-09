package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import ru.performancelab.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test
    void checkGoodsAdded() {
        loginPage.login("standard_user", "secret_sauce");
        
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        String itemCount = productsPage.getCartItemCount();
        
        assertThat(itemCount)
                .as("Cart item count should be 2 after adding two products")
                .isEqualTo("2");
    }
}
