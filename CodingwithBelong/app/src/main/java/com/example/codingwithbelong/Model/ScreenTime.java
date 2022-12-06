package com.example.codingwithbelong.Model;

import java.io.Serializable;

public class ScreenTime implements Serializable {

    private String userId;

    private long date;

    private long sessionTime;

    public ScreenTime(String userId, long date, long sessionTime){
        this.userId=userId;
        this.date=date;
        this.sessionTime=sessionTime;
    }

    public ScreenTime(){

    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public long getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(long sessionTime) {
        this.sessionTime = sessionTime;
    }
}
