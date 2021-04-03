package com.example.veebiprogrammeerimine.responses;

public class UserResponse{

    private String id;
    private String name;
    private Object Sessions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSessions() {
        return Sessions;
    }

    public void setSessions(Object Sessions) {
        this.Sessions = Sessions;
    }
}
