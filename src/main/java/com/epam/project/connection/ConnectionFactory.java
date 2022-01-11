package com.epam.project.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DATABASE_PROPERTY_FILE = "config";
    private static final String DATABASE_URL_PROPERTY = "db.url";
    private static final String DATABASE_DRIVER_PROPERTY = "db.driver";
    private static final String DATABASE_USER_PROPERTY = "db.username";
    private static final String DATABASE_PASSWORD_PROPERTY = "db.password";
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTY_FILE);
        String driver;
        if (resourceBundle.containsKey(DATABASE_DRIVER_PROPERTY)) {
            driver = resourceBundle.getString(DATABASE_DRIVER_PROPERTY);
        } else {
            LOGGER.fatal("Error of retrieving driver property value");
            throw new RuntimeException("Error of retrieving driver property value");
        }
        if (resourceBundle.containsKey(DATABASE_URL_PROPERTY)) {
            URL = resourceBundle.getString(DATABASE_URL_PROPERTY);
        } else {
            LOGGER.fatal("Error of retrieving url property value");
            throw new RuntimeException("Error of retrieving url property value");
        }
        if (resourceBundle.containsKey(DATABASE_USER_PROPERTY)) {
            USER = resourceBundle.getString(DATABASE_USER_PROPERTY);
        } else {
            LOGGER.fatal("Error of retrieving user property value");
            throw new RuntimeException("Error of retrieving user property value");
        }
        if (resourceBundle.containsKey(DATABASE_PASSWORD_PROPERTY)) {
            PASSWORD = resourceBundle.getString(DATABASE_PASSWORD_PROPERTY);
        } else {
            LOGGER.fatal("Error of retrieving password property value");
            throw new RuntimeException("Error of retrieving password property value");
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException exception) {
            LOGGER.fatal("Driver {} wasn't found: {}", driver, exception);
            throw new RuntimeException("Driver " + driver + " wasn't found: ", exception);
        }
    }

    private ConnectionFactory() {
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}