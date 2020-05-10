package org.personal.employee.connections;

import org.personal.employee.configuration.PropertiesConfiguration;

public class MySqlConnection extends BaseConnection {

    private static MySqlConnection mySqlConnection;

    private static final String URL = PropertiesConfiguration.properties.getProperty("MYSQL.URL");

    private static final String USER_NAME = PropertiesConfiguration.properties.getProperty("MYSQL.USERNAME");

    private static final String PASSWORD = PropertiesConfiguration.properties.getProperty("MYSQL.PASSWORD");

    private static final String DRIVER = PropertiesConfiguration.properties.getProperty("MYSQL.DRIVER");

    private MySqlConnection() {
        super(URL, USER_NAME, PASSWORD, DRIVER);
    }

    public static MySqlConnection getInstance() {
        if (mySqlConnection == null) {
            mySqlConnection = new MySqlConnection();
        }
        return mySqlConnection;
    }
}
