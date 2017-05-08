package DataRepresentationBackend.Services;

import DataRepresentationBackend.Models.TemporaryData;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
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
        } catch (ClassNotFoundException e) {
            System.err.println("Fatal Error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.err.println("Error at saving changes.");
        }
    }

    public int test() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from test");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception ex) {
            System.err.println("Error at generating test querry: " + ex.getMessage());
        }
        return -1;
    }

    public void addData(TemporaryData data) throws Exception {
        PreparedStatement statement = connection.prepareStatement("insert into TEMPORARY_DATA(elementType, x1, y1, x2, y2, floor, room, isExitWay) values(?,?,?,?,?,?,?,?)");
        statement.setString(1, data.elementType);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
        statement.setInt(8, data.isExitWay);

        statement.executeUpdate();
    }

    public void deleteData(TemporaryData data) throws Exception {
        PreparedStatement statementCheck = connection.prepareStatement( "select count(*) FROM TEMPORARY_DATA WHERE elementType =? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ? and isExitWay = ?");
        statementCheck.setString(1, data.elementType);
        statementCheck.setInt(2, data.x1);
        statementCheck.setInt(3, data.y1);
        statementCheck.setInt(4, data.x2);
        statementCheck.setInt(5, data.y2);
        statementCheck.setInt(6, data.floor);
        statementCheck.setString(7, data.room);
        statementCheck.setInt(8, data.isExitWay);

        ResultSet resultSet = statementCheck.executeQuery();
        resultSet.next();
        int response = resultSet.getInt(1);
        if(response < 1) {
            throw new OperationNotSupportedException("No data to delete.");
        }
        else if(response > 1) {
            throw new OperationNotSupportedException("Multiple deletion operation.");
        }

        PreparedStatement statement = connection.prepareStatement("delete from TEMPORARY_DATA WHERE elementType = ? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ? and isExitWay = ?");
        statement.setString(1, data.elementType);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
        statement.setInt(8, data.isExitWay);

        statement.executeUpdate();
    }
}
