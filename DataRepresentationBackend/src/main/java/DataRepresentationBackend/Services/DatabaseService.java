package DataRepresentationBackend.Services;

import DataRepresentationBackend.Models.TemporaryData;

import java.sql.Connection;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
public interface DatabaseService {
    // test
    int test();

    void commit();

    // functionality
    public Boolean checkExistsData(TemporaryData data) throws Exception;
    void addData(TemporaryData data) throws Exception;
    void deleteData(TemporaryData data) throws Exception;
    public ArrayList<TemporaryData> getTableElements() throws Exception;
    public void saveFinalData() throws Exception;
}
