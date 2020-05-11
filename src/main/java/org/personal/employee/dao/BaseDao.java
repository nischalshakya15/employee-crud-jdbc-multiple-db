package org.personal.employee.dao;

import org.personal.employee.configuration.PropertiesConfiguration;
import org.personal.employee.connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {

    protected final Connection connection;

    protected PreparedStatement preparedStatement;

    public static final String CONNECTION_TYPE = PropertiesConfiguration.properties.getProperty("ACTIVE.DATABASE");

    protected ResultSet resultSet;

    protected BaseDao() {
        connection = ConnectionFactory.getConnection(CONNECTION_TYPE);
    }
}
