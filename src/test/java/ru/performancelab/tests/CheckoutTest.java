package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import ru.performancelab.models.UserFactory;
import ru.performancelab.pages.BasketPage;
import ru.performancelab.pages.CheckoutCompletePage;
import ru.performancelab.pages.CheckoutPage;
import ru.performancelab.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends BaseTest {

    @Test
    void successfulCheckoutTest() {
        ProductsPage productsPage = loginPage
                .open()
                .login(UserFactory.getStandardUser());

        BasketPage basketPage = productsPage
                .addProductToCart("Sauce Labs Backpack")
                .goToBasket();

        CheckoutCompletePage completePage = basketPage
                .clickCheckout()
                .fillInfo("Ivan", "Ivanov", "123456")
                .clickContinue()
                .clickFinish();

        assertThat(completePage.getCompleteHeaderText())
                .as("Success message should be displayed")
                .isEqualTo("Thank you for your order!");
    }

    @Test
    void checkoutWithEmptyPostalCodeFails() {
        ProductsPage productsPage = loginPage
                .open()
                .login(UserFactory.getStandardUser());

        CheckoutPage checkoutPage = productsPage
                .addProductToCart("Sauce Labs Backpack")
                .goToBasket()
                .clickCheckout()
                .fillInfo("Ivan", "Ivanov", "")
                .clickContinueInvalid();

        assertThat(checkoutPage.getErrorMessage())
                .as("Error message should be shown when postal code is empty")
                .contains("Error: Postal Code is required");
    }
}
