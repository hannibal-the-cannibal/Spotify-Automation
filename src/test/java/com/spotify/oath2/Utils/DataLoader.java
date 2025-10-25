package com.spotify.oath2.Utils;


import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.loadProperties("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getplaylistid() {
        String prop = properties.getProperty("get_playlist_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Client_id not found");
        }
    }

    public String updateplaylistid() {
        String prop = properties.getProperty("update_playlist_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("client_secret not found in config.properties");
        }
    }


    }

