package org.personal.employee.connections;

import lombok.AllArgsConstructor;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.personal.employee.dao.BaseDao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class BaseConnection {

    private static Connection connection;

    private final String URL;

    private final String USER_NAME;

    private final String PASSWORD;

    private final String DRIVER;

    public Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                try {
                    String fileName = BaseDao.CONNECTION_TYPE.equalsIgnoreCase("postgres") ? "employeemgmt-postgres.sql" : "employeemgmt.sql";
                    Reader reader = new BufferedReader(new FileReader(fileName));
                    scriptRunner.runScript(reader);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
