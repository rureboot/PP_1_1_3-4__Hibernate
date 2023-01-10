package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_1.1.4?autoReconnect=true&useSSL=false";
    private static final String DB_UR2 = "jdbc:mysql://localhost:3306/db_1.1.4?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1233";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            System.out.println("fail create connection");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
