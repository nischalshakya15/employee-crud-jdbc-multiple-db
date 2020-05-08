package org.personal.employee.connections;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresConnection {

    private static final String url = "jdbc:postgresql://localhost/multi_tenant";
    private static final String user = "postgres";
    private static final String password = "root";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static void main(String[] args) {
        Connection postgresConnection = new PostgresConnection().connect();
        postgresConnection.setSchema("tenant_two");
        PreparedStatement preparedStatement = postgresConnection.prepareStatement("select *from department");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println("Name : " + rs.getString("name"));
        }
    }
}
