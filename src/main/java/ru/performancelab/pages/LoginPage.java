package ru.performancelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.performancelab.models.User;
import ru.performancelab.utils.PropertyReader;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector("[data-test='username']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(PropertyReader.getProperty("saucedemo.url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        if (username != null && !username.isEmpty()) {
            driver.findElement(usernameInput).sendKeys(username);
        }
        return this;
    }

    public LoginPage enterPassword(String password) {
        if (password != null && !password.isEmpty()) {
            driver.findElement(passwordInput).sendKeys(password);
        }
        return this;
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public ProductsPage login(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
        return new ProductsPage(driver);
    }
    
    public LoginPage loginInvalid(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
