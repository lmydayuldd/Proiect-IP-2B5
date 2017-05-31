package DataRepresentationBackend.Logic.TableRepresentation;

import java.util.ArrayList;

/**
 * @author Procop Vladimir
 */
public class DataNotValidExceptionLogger {
    private static final DataNotValidExceptionLogger INSTANCE = new DataNotValidExceptionLogger();
    private static ArrayList<String> log = new ArrayList<>();

    private DataNotValidExceptionLogger() {
    }

    public static DataNotValidExceptionLogger getInstance() {
        return INSTANCE;
    }

    public static void addExceptionMessage(String newException) {
        log.add(newException);
    }

    public static ArrayList<String> getExceptionMessage() {
        return log;
    }

    public static String getExceptionMessageAsString() {
        String answer = "";
        for (String mess : log) {
            answer += (mess + '\n');
        }
        return answer;
    }

    public static void clearLog() {
        log.clear();
    }
}