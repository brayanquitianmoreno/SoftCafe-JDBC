package co.softcafe.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centraliza la conexión JDBC con MySQL. */
public final class DatabaseConnection {
    private static final String URL = System.getenv().getOrDefault(
            "SOFTCAFE_DB_URL", "jdbc:mysql://localhost:3306/softcafe?serverTimezone=UTC");
    private static final String USER = System.getenv().getOrDefault("SOFTCAFE_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("SOFTCAFE_DB_PASSWORD", "");

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
