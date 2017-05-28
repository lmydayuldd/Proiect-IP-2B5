package DataRepresentationBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 14-May-17.
 */
public class TemporarySaveMessage {
    private String message;
    private String data;

    public TemporarySaveMessage(String message) {
        this.message = message;
    }

    public TemporarySaveMessage(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @JsonIgnore
    public Boolean isValid() {
        return !(getMessage() == null || getData() == null);
    }
}
