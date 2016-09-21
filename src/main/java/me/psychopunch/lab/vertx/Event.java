package me.psychopunch.lab.vertx;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String description;

    @JsonCreator
    public Event(@JsonProperty("id") String id, @JsonProperty("description") String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
