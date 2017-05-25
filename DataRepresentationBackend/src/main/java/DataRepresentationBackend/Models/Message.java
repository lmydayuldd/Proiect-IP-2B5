package DataRepresentationBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
public class Message {
    public String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public Boolean isValid() {
        return !(message == null);
    }
}
