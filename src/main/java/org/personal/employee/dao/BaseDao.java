package org.personal.employee.dao;

import org.personal.employee.connections.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {

    protected final Connection connection;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    protected BaseDao() {
        connection = MySqlConnection.getInstance().getConnection();
    }
}
