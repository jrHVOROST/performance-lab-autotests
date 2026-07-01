package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

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

    static Stream<Arguments> incorrectLoginData() {
        return Stream.of(
                Arguments.of("standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"),
                Arguments.of("", "", "Epic sadface: Username is required"),
                Arguments.of("standard_user", "", "Epic sadface: Password is required"),
                Arguments.of("locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out.")
        );
    }

    @ParameterizedTest(name = "Login with {0} and {1} should fail with {2}")
    @MethodSource("incorrectLoginData")
    void checkIncorrectLogin(String username, String password, String errorMessage) {
        loginPage.login(username, password);

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should be correct")
                .contains(errorMessage);
    }
}
