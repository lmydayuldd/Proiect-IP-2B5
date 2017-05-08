package DataRepresentationBackend.Services;

import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
@Service
public class ConcreteDatabaseService implements DatabaseService {
    private Connection connection = null;

    public ConcreteDatabaseService() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DATA_REPRESENTATION", "DATA_REPRESENTATION");

            System.out.println("================================");
            System.out.println(">>CONNECTED TO ORACLE DATABASE<<");
            System.out.println("================================");
        }
        catch (ClassNotFoundException e){
            System.err.println("Fatal Error: " + e.getMessage());
        }
        catch (SQLException e){
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    public int test() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("select count(*) from test");
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (Exception ex) {
            System.err.println("Error at generating test querry: " + ex.getMessage());
        }
        return -1;
    }
}
