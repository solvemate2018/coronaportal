package com.coronaportal.timeSpots;

import java.time.LocalDate;

public class Date implements Comparable<Date> {
    private final int id;
    private LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
        this.id = this.hashCode();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public int compareTo(Date o) {
        return this.date.compareTo(o.getDate());
    }
}
