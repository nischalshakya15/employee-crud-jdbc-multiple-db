package org.personal.employee.connections;

import java.sql.Connection;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection(String connectionType) {
        if (connectionType.equalsIgnoreCase("mysql")) {
            connection = MySqlConnection.getInstance().getConnection();
        }
        if (connectionType.equalsIgnoreCase("h2")) {
            connection = H2Connection.getInstance().getConnection();
        }
        if (connectionType.equalsIgnoreCase("postgres")) {
            connection = PostgresConnection.getInstance().getConnection();
        }
        return connection;
    }
}
