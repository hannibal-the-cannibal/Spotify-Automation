package com.spotify.oath2.Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private PropertyUtils() {}

    public static String getProperty(String filePath, String key) {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("⚠️ Failed to load properties file at: " + filePath, e);
        }

        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("❌ Missing property for key: " + key + " in " + filePath);
        }

        return value.trim();
    }

    /**
     * Optionally load all properties into a Properties object (for iteration or reuse)
     */
    public static Properties loadProperties(String filePath) {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("⚠️ Failed to load properties file at: " + filePath, e);
        }

        return props;
    }
}

