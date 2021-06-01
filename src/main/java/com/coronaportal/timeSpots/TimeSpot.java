package com.coronaportal.timeSpots;

import java.time.LocalTime;

public class TimeSpot {
    private int id;
    private int leftSpots;
    private boolean available;
    private final LocalTime time;

    public TimeSpot(int leftSpots, LocalTime time) {
        this.leftSpots = leftSpots;
        this.available = true;
        this.time = time;
        this.id = this.hashCode();
    }

    public void reserveSpot() {
        --this.leftSpots;
        if (this.leftSpots == 0) {
            this.available = false;
        }

    }

    public int getLeftSpots() {
        return this.leftSpots;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}