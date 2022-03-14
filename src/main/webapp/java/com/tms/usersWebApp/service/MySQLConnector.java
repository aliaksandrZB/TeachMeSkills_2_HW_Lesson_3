package com.tms.usersWebApp.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

    private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTIVITY_PROPERTY_FILE_PATH = "src" + File.separator + "main" + File.separator +
                                                                  "resources" + File.separator + "UserServiceProperties";

    private static Properties properties;
    private static Connection connection;

    public MySQLConnector() {
        init();
    }

    public static void init() {
        try {
            properties = loadProperties();
            Class.forName(MYSQL_DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            MyWriter.println("Failed to load driver");
            e.printStackTrace();
        } catch (IOException e) {
            MyWriter.println("Unable to read property file");
            e.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            setConnection(DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            ));
        } catch (SQLException e) {
            MyWriter.println("Unable to get connection to MySQL schema.");
            e.printStackTrace();
        }

        return connection;
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get(CONNECTIVITY_PROPERTY_FILE_PATH))) {
            properties.load(input);
        }
        return properties;
    }

}
