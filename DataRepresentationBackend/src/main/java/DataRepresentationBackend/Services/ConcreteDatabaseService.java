package DataRepresentationBackend.Services;

import DataRepresentationBackend.Models.SingleObject;
import DataRepresentationBackend.Models.TemporaryData;
import DataRepresentationBackend.Models.TemporaryDataDelete;
import org.springframework.stereotype.Service;
import tablerepresentation.TableElement;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;

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

    public void deleteData(TemporaryDataDelete data) throws Exception {
        PreparedStatement statementCheck = DatabaseConnection.getConnection().prepareStatement("select count(*) FROM" +
                " TEMPORARY_DATA WHERE type =? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ?");
        statementCheck.setString(1, data.type);
        statementCheck.setInt(2, data.x1);
        statementCheck.setInt(3, data.y1);
        statementCheck.setInt(4, data.x2);
        statementCheck.setInt(5, data.y2);
        statementCheck.setInt(6, data.floor);
        statementCheck.setString(7, data.room);
        ResultSet resultSet = statementCheck.executeQuery();
        resultSet.next();
        int response = resultSet.getInt(1);
        if (response < 1) {
            throw new OperationNotSupportedException("No data to delete.");
        }

        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("delete from TEMPORARY_DATA " +
                "WHERE type = ? and x1 = ? and y1 = ? and x2=? and y2 = ? and floor = ? and room = ?");
        statement.setString(1, data.type);
        statement.setInt(2, data.x1);
        statement.setInt(3, data.y1);
        statement.setInt(4, data.x2);
        statement.setInt(5, data.y2);
        statement.setInt(6, data.floor);
        statement.setString(7, data.room);
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

    public void rollback() throws SQLException {
        String plsql = "BEGIN ROLL_BACK; END;";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(plsql);
        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<TemporaryData> getTemporaryDataElements() throws SQLException {
        TemporaryData element = new TemporaryData();
        ArrayList<TemporaryData> elements = new ArrayList<>();
        String sql = "SELECT TYPE, x1, y1, x2, y2, floor, room, isExitWay, isExterior from temporary_data";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
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
        statement.close();
        resultSet.close();
        return elements;
    }

    //(String elementType, int xx1, int yy1, int xx2, int yy2, int floor, String theRoom, int exterior, int exit)
    public ArrayList<TableElement> getTemporaryDataTable() throws SQLException {
        ArrayList<TableElement> building = new ArrayList<>();
        String sql = "SELECT TYPE, x1, y1, x2, y2, floor, room, isExterior, isExitWay from temporary_data";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            TableElement tableElement = new TableElement(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                    resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8),
                    resultSet.getInt(9));
            building.add(tableElement);
        }
        resultSet.close();
        statement.close();
        return building;
    }

    public ArrayList<TableElement> getFinalDataTable() throws SQLException {
        ArrayList<TableElement> building = new ArrayList<>();
        String sql = "SELECT TYPE, x1, y1, x2, y2, floor, room, isExterior, isExitWay from final_data";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            TableElement tableElement = new TableElement(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                    resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8),
                    resultSet.getInt(9));
            building.add(tableElement);
        }
        resultSet.close();
        statement.close();
        return building;
    }
}