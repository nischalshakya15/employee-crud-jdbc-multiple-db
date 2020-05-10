package org.personal.employee.connections;

import org.personal.employee.configuration.PropertiesConfiguration;

public class H2Connection extends BaseConnection {

    private static H2Connection h2Connection;

    private static final String URL = PropertiesConfiguration.properties.getProperty("H2.URL");

    private static final String USER_NAME = PropertiesConfiguration.properties.getProperty("H2.USERNAME");

    private static final String PASSWORD = PropertiesConfiguration.properties.getProperty("H2.PASSWORD");

    private static final String DRIVER = PropertiesConfiguration.properties.getProperty("H2.DRIVER");

    private H2Connection() {
        super(URL, USER_NAME, PASSWORD, DRIVER);
    }

    public static H2Connection getInstance() {
        if (h2Connection == null) {
            h2Connection = new H2Connection();
        }
        return h2Connection;
    }
}
