package com.tripmanagement.asdc.model;

public class Credits {

    int credits;
    int userId;

    public Credits(int credits, int userId) {
        this.credits = credits;
        this.userId = userId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
