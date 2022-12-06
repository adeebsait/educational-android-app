package com.example.codingwithbelong.Model;

import androidx.annotation.NonNull;

public class LineGraphData {

    private long date;
    private long totalTime;

    public LineGraphData(long date, long totalTime){
        this.date=date;
        this.totalTime=totalTime;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "LineGraphData{" +
                "date=" + date +
                ", totalTime=" + totalTime +
                '}';
    }
}
