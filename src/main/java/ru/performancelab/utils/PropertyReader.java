package ru.performancelab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Sorry, unable to find config.properties");
                }
                properties.load(input);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }
}
