package DataRepresentationBackend.Services;

import DataRepresentationBackend.Logic.TableRepresentation.TableElement;
import DataRepresentationBackend.Models.SingleObject;
import DataRepresentationBackend.Models.TemporaryData;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
public interface DatabaseService {
    void commit();

    // functionality
    Boolean checkExistsData(TemporaryData data) throws Exception;

    void addData(TemporaryData data) throws Exception;

    void deleteData(TemporaryData data) throws Exception;

    void deleteRoom(SingleObject data) throws Exception;

    void deleteFloor(SingleObject data) throws Exception;

    void replicateData() throws Exception;

    ArrayList<TemporaryData> getTemporaryDataElements() throws SQLException;

    ArrayList<TableElement> getTemporaryDataTable() throws SQLException;

    /*
    public void saveFinalData() throws Exception;
    */
}
