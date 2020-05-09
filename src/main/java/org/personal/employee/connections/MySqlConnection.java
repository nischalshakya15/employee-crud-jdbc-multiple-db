package org.personal.employee.connections;

import org.personal.employee.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static MySqlConnection mySqlConnection;

    private static Connection connection;

    private static final String URL = PropertiesConfiguration.properties.getProperty("MYSQL.URL");

    private static final String USER_NAME = PropertiesConfiguration.properties.getProperty("MYSQL.USERNAME");

    private static final String PASSWORD = PropertiesConfiguration.properties.getProperty("MYSQL.PASSWORD");


    private MySqlConnection() {
        connection = getConnection();
    }

    public static MySqlConnection getInstance() {
        if (mySqlConnection == null) {
            mySqlConnection = new MySqlConnection();
        }
        return mySqlConnection;
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }
}
