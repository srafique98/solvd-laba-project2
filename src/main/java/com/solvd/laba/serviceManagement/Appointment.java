package com.solvd.laba.serviceManagement;

import com.solvd.laba.interfaces.Scheduleable;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements Scheduleable {
    private LocalDate date;
    private LocalTime time;
    private String status;

    public Appointment(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public Appointment(LocalDate date, LocalTime time, String status) {
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void schAppointment(LocalDate userDate, LocalTime userTime) throws IllegalArgumentException {
        if (userDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }
        this.date = userDate;
        this.time = userTime;
        this.status = "Scheduled";

    }

    @Override
    public void scheduleAppointment(LocalDate userDate, LocalTime userTime) {
        schAppointment(userDate,userTime);
    }

    @Override
    public void cancelAppointment() throws IllegalStateException {
        if (this.date.isEqual(LocalDate.now()) || this.time.isBefore(LocalTime.now())) {
            throw new IllegalStateException("Appointment has already started");
        }
        if (this.status.equals("Completed")) {
            throw new IllegalStateException("Appointment has already been completed");
        }
        this.date = null;
        this.time = null;
        this.status = "Cancelled";
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
