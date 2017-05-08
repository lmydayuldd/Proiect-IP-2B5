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
    void addData(TemporaryData data) throws Exception;
    void deleteData(TemporaryData data) throws Exception;

}
