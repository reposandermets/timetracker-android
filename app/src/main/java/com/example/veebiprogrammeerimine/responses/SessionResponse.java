package com.example.veebiprogrammeerimine.responses;

public class SessionResponse {
    private String id;
    private String user_id;
    private String name;
    private String status;
    private String started_at;
    private String stopper_at;
    private String ended_at;
    private double seconds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStarted_at() {
        return started_at;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public String getStopper_at() {
        return stopper_at;
    }

    public void setStopper_at(String stopper_at) {
        this.stopper_at = stopper_at;
    }

    public String getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(String ended_at) {
        this.ended_at = ended_at;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
