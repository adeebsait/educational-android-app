package com.example.codingwithbelong.Model;

public class User implements Comparable<User>{
    String name;
    String bio;
    String photo;
    int score;
    int time;
    int fundamental;
    int ftotal;
    int python;
    int ptotal;
    int java;
    int jtotal;
    int cpp;
    int cpptotal;

    public User() {
    }

    public User(String name, String bio, String photo, int score, int time, int fundamental, int ftotal, int python, int ptotal, int java, int jtotal, int cpp, int cpptotal) {
        this.name = name;
        this.bio = bio;
        this.photo = photo;
        this.score = score;
        this.time = time;
        this.fundamental = fundamental;
        this.ftotal = ftotal;
        this.python = python;
        this.ptotal = ptotal;
        this.java = java;
        this.jtotal = jtotal;
        this.cpp = cpp;
        this.cpptotal = cpptotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFundamental() {
        return fundamental;
    }

    public void setFundamental(int fundamental) {
        this.fundamental = fundamental;
    }

    public int getFtotal() {
        return ftotal;
    }

    public void setFtotal(int ftotal) {
        this.ftotal = ftotal;
    }

    public int getPython() {
        return python;
    }

    public void setPython(int python) {
        this.python = python;
    }

    public int getPtotal() {
        return ptotal;
    }

    public void setPtotal(int ptotal) {
        this.ptotal = ptotal;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getJtotal() {
        return jtotal;
    }

    public void setJtotal(int jtotal) {
        this.jtotal = jtotal;
    }

    public int getCpp() {
        return cpp;
    }

    public void setCpp(int cpp) {
        this.cpp = cpp;
    }

    public int getCpptotal() {
        return cpptotal;
    }

    public void setCpptotal(int cpptotal) {
        this.cpptotal = cpptotal;
    }

    @Override
    public int compareTo(User user) {
        int compare = Integer.compare(score,user.score);
        if(compare == 0)
        {
            compare = Integer.compare(user.time,time);
        }
        return compare;
    }
    public String toString()
    {
        return "name : "+name +" score : "+score +" time : "+time +"\n";
    }

}
