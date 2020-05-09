package org.personal.employee.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfiguration {

    public static Properties properties;

    private static final String fileName = ".env.properties";

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(fileName));
        } catch (IOException io) {
            System.err.println(io.getMessage());
        }
    }
}
