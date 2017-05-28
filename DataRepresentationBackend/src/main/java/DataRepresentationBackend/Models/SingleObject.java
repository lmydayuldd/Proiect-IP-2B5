package DataRepresentationBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 25-May-17.
 */
public class SingleObject {
    private String name;

    public SingleObject() {
    }

    public SingleObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Boolean isValid() {
        return !(getName() == null || getName().isEmpty());
    }
}
