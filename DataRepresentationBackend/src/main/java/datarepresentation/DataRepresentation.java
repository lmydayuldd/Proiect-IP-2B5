/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datarepresentation;

import CustomExceptions.DataNotValidException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tablerepresentation.DataNotValidExceptionLogger;
import tablerepresentation.ElementManager;
import tablerepresentation.TableElement;
/**
 *
 * @author Procop Vladimir
 */
public class DataRepresentation {

    
//    public static ArrayList<TemporaryData> getTemporaryDataElements() throws SQLException {
//        TemporaryData element = new TemporaryData();
//        ArrayList<TemporaryData> elements = new ArrayList<>();
//        String sql = "SELECT TYPE, x1, y1, x2, y2, floor, room, isExitWay, isExterior from temporary_data";
//        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//            element = new TemporaryData();
//            element.setType(resultSet.getString(1));
//            element.setX1(resultSet.getInt(2));
//            element.setY1(resultSet.getInt(3));
//            element.setX2(resultSet.getInt(4));
//            element.setY2(resultSet.getInt(5));
//            element.setFloor(resultSet.getInt(6));
//            element.setRoom(resultSet.getString(7));
//            element.setIsExitWay(resultSet.getInt(8));
//            element.setIsExterior(resultSet.getInt(9));
//            elements.add(element);
//        }
//        statement.close();
//        resultSet.close();
//        return elements;
//}
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DataNotValidException, SQLException {
//        ArrayList<TemporaryData> tdBuilding = new ArrayList<>();
//        ArrayList<TableElement> teBuilding = new ArrayList<>();
//        tdBuilding = DataRepresentation.getTemporaryDataElements();
//        
//        for(int i = 0; i < tdBuilding.size(); i++){
//            teBuilding.add(new TableElement(tdBuilding.get(i)));
//        }
//        
//        ElementManager em = new ElementManager(teBuilding);
//            em.validateElements();
    }
}
