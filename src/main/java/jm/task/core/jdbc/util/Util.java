package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.boot.registry.S

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL_JDBC = "jdbc:mysql://localhost:3306/db_1.1.4?autoReconnect=true&useSSL=false";
    private static final String DB_URL_HIBERNATE = "jdbc:mysql://localhost:3306/db_1.1.5?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1233";

    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", DB_URL_HIBERNATE);
        configuration.setProperty("hibernate.connection.username", DB_USER);
        configuration.setProperty("hibernate.connection.password", DB_PASS);

        configuration.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");



        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());


    }

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL_JDBC, DB_USER, DB_PASS);
        } catch (SQLException e) {
            System.out.println("fail create connection");
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
