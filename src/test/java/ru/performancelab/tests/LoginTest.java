package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.performancelab.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void successfulLoginTest() {
        loginPage.login("standard_user", "secret_sauce");

        assertThat(driver.getCurrentUrl())
                .as("URL should contain /inventory.html after successful login")
                .contains("/inventory.html");

        boolean isInventoryDisplayed = driver.findElement(By.id("inventory_container")).isDisplayed();
        assertThat(isInventoryDisplayed)
                .as("Inventory container should be displayed")
                .isTrue();
    }

    @Test
    void failedLoginTest() {
        loginPage.login("standard_user", "wrong_password");

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should indicate invalid credentials")
                .contains("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    void emptyCredentialsTest() {
        loginPage.login("", "");

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should indicate username is required")
                .contains("Epic sadface: Username is required");
    }

    @Test
    void emptyPasswordTest() {
        loginPage.login("standard_user", "");

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should indicate password is required")
                .contains("Epic sadface: Password is required");
    }

    @Test
    void lockedOutUserTest() {
        loginPage.login("locked_out_user", "secret_sauce");

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should indicate user is locked out")
                .contains("Epic sadface: Sorry, this user has been locked out.");
    }
}
