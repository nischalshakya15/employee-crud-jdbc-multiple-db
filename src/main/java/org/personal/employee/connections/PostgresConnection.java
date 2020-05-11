package org.personal.employee.connections;

import org.personal.employee.configuration.PropertiesConfiguration;

public class PostgresConnection extends BaseConnection {

    private static PostgresConnection postgresConnection;

    private static final String URL = PropertiesConfiguration.properties.getProperty("POSTGRES.URL");

    private static final String USER_NAME = PropertiesConfiguration.properties.getProperty("POSTGRES.USERNAME");

    private static final String PASSWORD = PropertiesConfiguration.properties.getProperty("POSTGRES.PASSWORD");

    private static final String DRIVER = PropertiesConfiguration.properties.getProperty("POSTGRES.DRIVER");

    public PostgresConnection() {
        super(URL, USER_NAME, PASSWORD, DRIVER);
    }

    public static PostgresConnection getInstance() {
        if (postgresConnection == null) {
            postgresConnection = new PostgresConnection();
        }
        return postgresConnection;
    }
}
