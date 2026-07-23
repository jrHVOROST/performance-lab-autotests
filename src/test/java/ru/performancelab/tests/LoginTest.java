package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import ru.performancelab.models.User;
import ru.performancelab.models.UserFactory;
import ru.performancelab.pages.LoginPage;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void successfulLoginTest() {
        loginPage.open().login(UserFactory.getStandardUser());

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
                Arguments.of(UserFactory.getInvalidPasswordUser(), "Epic sadface: Username and password do not match any user in this service"),
                Arguments.of(UserFactory.getEmptyCredentialsUser(), "Epic sadface: Username is required"),
                Arguments.of(UserFactory.getEmptyPasswordUser(), "Epic sadface: Password is required"),
                Arguments.of(UserFactory.getLockedOutUser(), "Epic sadface: Sorry, this user has been locked out.")
        );
    }

    @ParameterizedTest(name = "Login with invalid user should fail with {1}")
    @MethodSource("incorrectLoginData")
    void checkIncorrectLogin(User user, String errorMessage) {
        loginPage.open().loginInvalid(user);

        String errorText = loginPage.getErrorMessage();
        assertThat(errorText)
                .as("Error message should be correct")
                .contains(errorMessage);
    }
}
