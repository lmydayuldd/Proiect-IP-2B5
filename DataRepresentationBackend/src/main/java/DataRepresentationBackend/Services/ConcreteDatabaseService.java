package DataRepresentationBackend.Services;

import DataRepresentationBackend.Models.SingleObject;
import DataRepresentationBackend.Models.TemporaryData;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
@Service
public class ConcreteDatabaseService implements DatabaseService {

    public void commit() {
        try {
            DatabaseConnection.getConnection().commit();
        } catch (SQLException ex) {
            System.err.println("Error at saving changes.");
        }
    }

    public int test() {
        try {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from test");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception ex) {
            System.err.println("Error at generating test query: " + ex.getMessage());
        }
        return -1;
    }

    public Boolean checkExistsData(TemporaryData data) throws Exception {
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("select count(*) from " +
                "TEMPORARY_DATA WHERE type = ? and x1 = ? and y1 = ? and x2 = ? and y2 = ? and floor = ? " +
                "and room = ? and isExitWay = ? and isExterior = ?");
        statement.setString(1, data.type);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
        statement.setInt(8, data.isExitWay);
        statement.setInt(9, data.isExterior);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int response = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        return response == 1;
    }

    public void addData(TemporaryData data) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("insert into " +
                "TEMPORARY_DATA(type, x1, y1, x2, y2, floor, room, isExitWay, isExterior) values(?,?,?,?,?,?,?,?,?)");
        statement.setString(1, data.type);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
        statement.setInt(8, data.isExitWay);
        statement.setInt(9, data.isExterior);
        statement.executeUpdate();
        statement.close();
    }

    public void deleteData(TemporaryData data) throws Exception {
        PreparedStatement statementCheck = DatabaseConnection.getConnection().prepareStatement("select count(*) FROM" +
                " TEMPORARY_DATA WHERE type =? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ? " +
                "and isExitWay = ? and isExterior = ?");
        statementCheck.setString(1, data.type);
        statementCheck.setInt(2, data.x1);
        statementCheck.setInt(3, data.y1);
        statementCheck.setInt(4, data.x2);
        statementCheck.setInt(5, data.y2);
        statementCheck.setInt(6, data.floor);
        statementCheck.setString(7, data.room);
        statementCheck.setInt(8, data.isExitWay);
        statementCheck.setInt(9, data.isExterior);
        ResultSet resultSet = statementCheck.executeQuery();
        resultSet.next();
        int response = resultSet.getInt(1);
        if (response < 1) {
            throw new OperationNotSupportedException("No data to delete.");
        }

        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("delete from TEMPORARY_DATA " +
                "WHERE type = ? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ? and isExitWay = ? " +
                "and isExterior = ?");
        statement.setString(1, data.type);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
        statement.setInt(8, data.isExitWay);
        statement.setInt(9, data.isExterior);
        statement.executeUpdate();
        resultSet.close();
        statement.close();
    }

    public void deleteRoom(SingleObject data) throws Exception {
        String sql = "DELETE FROM TEMPORARY_DATA WHERE room like ?";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setString(1, data.getName());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteFloor(SingleObject data) throws Exception {
        String sql = "DELETE FROM TEMPORARY_DATA WHERE floor like ?";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setString(1, data.getName());
        statement.executeUpdate();
        statement.close();
    }

    public void replicateData() throws Exception {
        String plsql = "BEGIN REPLICATE_DATA; END;";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(plsql);
        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<TemporaryData> getTemporaryDataElements() throws SQLException {
        TemporaryData element = new TemporaryData();
        ArrayList<TemporaryData> elements = new ArrayList<>();
        String sql = "SELECT TYPE, x1, y1, x2, y2, floor, room, isExitWay, isExterior from temporary_data";
        PreparedStatement statementCheck = DatabaseConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statementCheck.executeQuery();
        while (resultSet.next()) {
            element.setType(resultSet.getString(1));
            element.setX1(resultSet.getInt(2));
            element.setY1(resultSet.getInt(3));
            element.setX2(resultSet.getInt(4));
            element.setY2(resultSet.getInt(5));
            element.setFloor(resultSet.getInt(6));
            element.setRoom(resultSet.getString(7));
            element.setIsExitWay(resultSet.getInt(8));
            element.setIsExterior(resultSet.getInt(9));
            elements.add(element);
        }
        return elements;
    }

    /*
    public void saveFinalData() throws Exception{
        TemporaryData element = new TemporaryData;
        List<TemporaryData> elements = this.getTableElements();
        int i=0;

        //apel catre functia de validare a datelor

        if (validare_elemente(elements) == 1) {
            System.out.println("Datele sunt valide");
            while (i < elements.size()) {
                element = elements.get(i);
                PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("insert into FINAL_DATA(type, x1, y1, x2, y2, floor, room, isExit, isExterior) values(?,?,?,?,?,?,?,?)");
                statement.setString(1, element.getType);
                statement.setInt(2, element.getX1);
                statement.setInt(3, element.getY1);
                statement.setInt(4, element.getX2);
                statement.setInt(5, element.getY2);
                statement.setInt(6, element.getFloor);
                statement.setInt(7, element.getRoom);
                statement.setInt(8, element.getIsExit);
                statement.setInt(9, element.getIsExterior);
                statement.executeUpdate();
                i++;
            }
        }
        else
                System.out.printl("Datele trebuie revazute");

    }
    */
}