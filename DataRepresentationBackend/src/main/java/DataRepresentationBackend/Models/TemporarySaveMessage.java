package DataRepresentationBackend.Models;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 14-May-17.
 */
public class TemporarySaveMessage {
    private String message;
    private TemporaryData data;

    public TemporarySaveMessage(String message) {
        this.message = message;
    }

    public TemporarySaveMessage(String message, TemporaryData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TemporaryData getData() {
        return data;
    }

    public void setData(TemporaryData data) {
        this.data = data;
    }
}
