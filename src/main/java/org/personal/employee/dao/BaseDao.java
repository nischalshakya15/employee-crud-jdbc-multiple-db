package org.personal.employee.dao;

import org.personal.employee.connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {

    protected final Connection connection;

    protected PreparedStatement preparedStatement;

    public static String CONNECTION_TYPE;

    protected ResultSet resultSet;

    protected BaseDao() {
        connection = ConnectionFactory.getConnection(CONNECTION_TYPE);
    }
}
