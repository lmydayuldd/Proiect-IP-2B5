package DataRepresentationBackend.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 10-May-17.
 */
public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DATA_REPRESENTATION", "DATA_REPRESENTATION");

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("  CONNECTED TO ORACLE DATABASE  ");
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        } catch (ClassNotFoundException e) {
            System.err.println("FATAL ERROR: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("DATABASE ERROR: " + e.getMessage());
        }
    }

    public static void resetConnection() {
        connection = null;
    }
}
