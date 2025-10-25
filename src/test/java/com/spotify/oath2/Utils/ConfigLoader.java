package com.spotify.oath2.Utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.loadProperties("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String prop = properties.getProperty("client_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Client_id not found");
        }
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("client_secret not found in config.properties");
        }
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("refresh_token not found in config.properties");
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("grant_type not found in config.properties");
        }
    }

    public String getRedirectUri() {
        String prop = properties.getProperty("redirect_uri");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("redirect_uri not found in config.properties");
        }
    }

    public String getCode() {
        String prop = properties.getProperty("code");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("code not found in config.properties");
        }
    }
}
