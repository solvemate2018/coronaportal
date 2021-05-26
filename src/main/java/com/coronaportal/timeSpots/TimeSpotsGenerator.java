package com.coronaportal.timeSpots;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeSpotsGenerator {
    private List<TimeSpot> timeSpots = new ArrayList();
    private LocalTime currentTime = LocalTime.of(8, 0);

    public TimeSpotsGenerator(int capacity, int duration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.currentTime.format(formatter);
        this.timeSpots.add(new TimeSpot(capacity, this.currentTime));

        while(this.currentTime.isBefore(LocalTime.of(18, 0))) {
            LocalTime time;
            if (this.currentTime.getMinute() + duration >= 60) {
                time = LocalTime.of(this.currentTime.getHour() + 1, this.currentTime.getMinute() + 15 - 60);
                this.currentTime = LocalTime.of(this.currentTime.getHour() + 1, this.currentTime.getMinute() + 15 - 60);
            } else {
                time = LocalTime.of(this.currentTime.getHour(), this.currentTime.getMinute() + 15);
                this.currentTime = LocalTime.of(this.currentTime.getHour(), this.currentTime.getMinute() + 15);
            }

            time.format(formatter);
            this.timeSpots.add(new TimeSpot(capacity, time));
        }

    }

    public List<TimeSpot> getTimeSpots() {
        return this.timeSpots;
    }
}