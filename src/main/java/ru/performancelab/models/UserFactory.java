package ru.performancelab.models;

import ru.performancelab.utils.PropertyReader;

public class UserFactory {

    public static User getStandardUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User getLockedOutUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.locked_user"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }
    
    public static User getEmptyPasswordUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                ""
        );
    }
    
    public static User getEmptyLoginUser() {
        return new User(
                "",
                PropertyReader.getProperty("saucedemo.password")
        );
    }
    
    public static User getEmptyCredentialsUser() {
        return new User("", "");
    }
    
    public static User getInvalidPasswordUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                "wrong_password"
        );
    }
}
